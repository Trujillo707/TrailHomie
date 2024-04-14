package com.percolators.trailhomie

class Trail(trailName:String, distanceAway:Float, aCondition:Int) {

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

    fun getCondition(): Int{
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
}