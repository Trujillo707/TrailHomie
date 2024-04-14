package com.percolators.trailhomie

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.compose.runtime.Composable
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

object DatabaseManagement{

    /**
        Get a trail name and new reported condition -> Make a DB reports

        @param trailName The name of the trail in question
        @param newCondition The new condition
     */
    fun sendReport(trailName:String, newCondition:Int) = CoroutineScope(Dispatchers.IO).launch{
        val searchResult = Firebase.firestore.collection("humboldt_trails").whereEqualTo("name", trailName).get().await()
        for (currDoc in searchResult){
            //val theSubmission = Map<>
            //Firebase.firestore.collection("humboldt_reports").add()
        }
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


    fun updateCondition(){

    }
}
