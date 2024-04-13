package com.percolators.trailhomie

class Trail(trailName:String, distanceAway:Float, condition:Int) {

    private val name = trailName
    private var distance = distanceAway
    fun getName(): String {
        return name
    }

    fun getDistance(): Float{
        return distance
    }

    fun setDistance(newDistance:Float){
        distance = newDistance
    }
}

