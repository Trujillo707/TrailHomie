package com.percolators.trailhomie

import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.compose.runtime.Composable
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class DatabaseManagement{
    val db = Firebase.firestore

    /**
        Get a trail name and new reported condition -> Make a DB reports

        @param trailName The name of the trail in question
        @param newCondition The new condition
     */
    fun sendReport(trailName:String, newCondition:Int){

    }

    /**
     * Get a map of <TrailName:String, TrailCondition:Int>. Probably use for
     * creating the list of trails later.
     */
//    fun getTrailInfo():Map<String, Int>{
//        var trails = mutableMapOf<String, Int>()
//        db.collection("humboldtTrails")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    document.data
//                    //trails.put(document.get("name", String), document.get("content"))
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.e("DatabaseManagement::getTrailInfo()","Cannot access humboldtTrails", exception)
//            }
//    }

    fun updateCondition(){

    }
}
