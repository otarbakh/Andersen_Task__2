package com.otarbakh.andersen_task__2.common


import android.graphics.Color
import java.util.Random


object ColorUtils {
    fun getRandomColor(): Int {
        val rand = Random()
        val red = rand.nextInt(256)
        val green = rand.nextInt(256)
        val blue = rand.nextInt(256)
        return Color.rgb(red, green, blue)
    }
}