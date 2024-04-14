package com.percolators.trailhomie

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.compose.runtime.Composable
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Date

object DatabaseManagement{

    /**
        Get a trail name and new reported condition -> Make a DB reports

        @param trailName The name of the trail in question
        @param newCondition The new condition
     */
    fun sendReport(trailName:String, newCondition:Long) = CoroutineScope(Dispatchers.IO).launch{
        val searchResult = Firebase.firestore.collection("humboldt_trails").whereEqualTo("name", trailName).get().await()
        for (currDoc in searchResult){
            val theSubmission = mutableMapOf<String,Any>()
            theSubmission.put("newCondition", newCondition)
            theSubmission.put("submitted", FieldValue.serverTimestamp())
            Firebase.firestore.collection("humboldt_trails").document(currDoc.id).collection("reports").add(theSubmission)
            Log.d(TAG,"Success maybe adding -> ${theSubmission}")
        }

        updateCondition(trailName)

    }

    /**
     * Get a the documents in the humboldt_trails collection Probably use for
     * creating the list of trails later.
     */
     fun getTrailInfo() = CoroutineScope(Dispatchers.IO).launch {
        val result = Firebase.firestore.collection("humboldt_trails")
            .get()
            .await()
        for (document in result) {
            Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
            val theName = document.get("name")
            val theCondition = document.get("condition")
            val tempTrail = Trail(theName.toString(),theCondition as Long)
            TrailList.addTrail(tempTrail)
            Log.d(ContentValues.TAG,"The size here is ${TrailList.getSize()}")
        }
    }


    fun updateCondition(trailName: String) = CoroutineScope(Dispatchers.IO).launch{
        var expiration = Date(System.currentTimeMillis() - 86400000)
        val searchResult = Firebase.firestore.collection("humboldt_trails").whereEqualTo("name", trailName).get().await()
        for(document in searchResult){
            val reportCollection = Firebase.firestore.collection("humboldt_trails").document(document.id).collection("reports").whereGreaterThan("submitted", Timestamp(expiration)).get().await()
            var zeros = 0
            var negOnes = 0
            var ones = 0
            for (report in reportCollection){
                val theCon = report.get("newCondition")
                if (theCon as Long == -1L){
                    negOnes++
                }
                else if (theCon as Long == 1L){
                    ones++
                }
                else{
                    zeros++
                }
            }
            if (zeros == 0 && negOnes == 0 && ones == 0){
                Log.d(TAG,"Nothing to process")
            }
            else if (negOnes >= ones && negOnes >= zeros){
                Firebase.firestore.collection("humboldt_trails").document(document.id).update("condition", -1L)
            }
            else if (ones >= negOnes && ones >= zeros){
                Firebase.firestore.collection("humboldt_trails").document(document.id).update("condition", 1L)
            }
            else{
                Firebase.firestore.collection("humboldt_trails").document(document.id).update("condition", 0L)
            }
        }
    }
}
