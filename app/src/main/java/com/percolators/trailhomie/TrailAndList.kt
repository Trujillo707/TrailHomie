package com.percolators.trailhomie

import android.content.ContentValues.TAG
import android.util.Log

class Trail(trailName:String, aCondition:Long, distanceAway:Float = 1f) {

    private val name = trailName
    private var distance = distanceAway
    private var condition = aCondition

    fun getName(): String {
        return name
    }

    fun getDistance(): Float{
        return distance
    }

    fun setDistance(newDistance:Float){
        distance = newDistance
    }

    fun getCondition(): Long{
        return condition
    }
}


// Singleton because it works
object TrailList{
    private var theList = mutableListOf<Trail>()

    fun addTrail(theTrail:Trail){
        theList.add(theTrail)
    }
    fun sortedDistance():List<Trail>{
        return theList.sortedBy { it.getDistance() }
    }

    fun searchByName(theName:String): Trail? {
        for(trails in theList){
            if (trails.getName() == theName){
                return trails
            }
        }
        return null
    }

    fun getSize() : Int{
        return theList.size
    }

    fun flushTrails(theData:MutableList<Map<String,Any>>){
        theList.clear()
        for (entry in theData){
            Log.d(TAG,"flush loop iterated")
            val theName = entry["name"]
            val theCondition = entry["condition"]
            addTrail(Trail(theName.toString(),theCondition as Long))
        }
    }


}