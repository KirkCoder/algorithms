package leetcode.facebook

import showArray
import java.util.*

//Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
//If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
//
//The replacement must be in place and use only constant extra memory.
//
//
//
//Example 1:
//
//Input: nums = [1,2,3]
//Output: [1,3,2]
//Example 2:
//
//Input: nums = [3,2,1]
//Output: [1,2,3]
//Example 3:
//
//Input: nums = [1,1,5]
//Output: [1,5,1]
//Example 4:
//
//Input: nums = [1]
//Output: [1]

fun main() {
//    val arr = intArrayOf(1,2,3)
    val arr = intArrayOf(0,1,2,5,3,3,0)
    nextPermutation(arr)
    showArray(arr)
    Arrays.binarySearch(arr, 2)
}

fun nextPermutation(nums: IntArray): Unit {

    if(nums.isEmpty() || nums.size == 1) {
        return
    }

    var i = nums.size - 2
    var prev = nums[nums.size - 1]

    while(i > 0) {
        val next = nums[i]
        if(next < prev) {
            break
        }
        prev = next
        i--
    }

    if(i == 0 && nums[i] > nums[i + 1]) {
        nums.sort()
        return
    }

    var j = i + 1
    while(j < nums.size) {
        if(nums[i] >= nums[j]) {
            break
        }
        j++
    }
    j--
    val tmp = nums[j]
    nums[j] = nums[i]
    nums[i] = tmp
    var k = nums.size - 1
    i++
    while(i < k) {
        val tmp = nums[i]
        nums[i] = nums[k]
        nums[k] = tmp
        i++
        k--
    }
}