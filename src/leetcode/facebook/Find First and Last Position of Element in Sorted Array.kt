package leetcode.facebook

import showArray
import java.util.*

//Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
//
//If target is not found in the array, return [-1, -1].
//
//Follow up: Could you write an algorithm with O(log n) runtime complexity?
//
//
//
//Example 1:
//
//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4]
//Example 2:
//
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1]
//Example 3:
//
//Input: nums = [], target = 0
//Output: [-1,-1]


fun main() {
    showArray(searchRange(intArrayOf(5,7,7,8,8,10), 6))
}

fun searchRange(nums: IntArray, target: Int): IntArray {
    if(nums.isEmpty()) {
        return intArrayOf(-1, -1)
    }

    val index = Arrays.binarySearch(nums, target)
    if(index < 0) {
        return intArrayOf(-1, -1)
    }


    var less = index
    while(less >= 0) {
        if(nums[less] != target) {
            less++
            break
        }
        less--
    }
    var more = index
    while(more < nums.size) {
        if(nums[more] != target) {
            more--
            break
        }
        more++
    }

    less = if(less < 0) 0 else less
    more = if(more == nums.size) nums.size - 1 else more

    return intArrayOf(less, more)
}