package leetcode.facebook

import java.lang.Integer.max
import java.lang.Integer.min

//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
//
//Example 1:
//
//
//Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6
//Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
//Example 2:
//
//Input: height = [4,2,0,3,2,5]
//Output: 9

fun main() {
    val list = mutableListOf<Int>()
}

fun trap(height: IntArray): Int {
    if(height.isEmpty()) {
        return 0
    }
    var count = 0
    val leftMax = IntArray(height.size)
    val rightMax = IntArray(height.size)

    leftMax[0] = height[0]
    var i = 1
    while(i < height.size) {
        leftMax[i] = max(height[i], leftMax[i - 1])
        i++
    }

    rightMax[height.size - 1] = height[height.size - 1]
    i = height.size - 2

    while(i > -1) {
        rightMax[i] = max(rightMax[i + 1], height[i])
        i--
    }

    for(j in 1 .. height.size - 1) {
        count += min(leftMax[j], rightMax[j]) - height[j]
    }

    return count
}

fun trapBad(height: IntArray): Int {
    var count = 0
    for(i in height.indices) {
        if(height[i] != 0) {
            if(i + 1 < height.size && height[i + 1] < height[i]) {
                val h = height[i + 1] + 1
                for(tmpH in h .. height[i]) {
                    var k = i + 1
                    var tmpCount = 0
                    while(k < height.size) {
                        if(height[k] < tmpH) {
                            tmpCount++
                        } else {
                            break
                        }
                        k++
                    }
                    if(k == height.size) {
                        tmpCount = 0
                    }
                    count += tmpCount
                }
            }
        }
    }
    return count
}