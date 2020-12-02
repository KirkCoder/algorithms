package leetcode

//Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
//
//Example:
//
//Input: s = 7, nums = [2,3,1,2,4,3]
//Output: 2
//Explanation: the subarray [4,3] has the minimal length under the problem constraint.
//Follow up:
//If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

fun minSubArrayLen(s: Int, nums: IntArray): Int {
    if(nums.isEmpty()) return 0
    var start = -1
    var end = -1
    var diff = s - nums[0]
    var j = 0
    var i = 0
    while(i < nums.size && j <= i) {
        if(diff <= 0) {
            if((start == -1 && end == -1) || (i - j < end - start)) {
                start = j
                end = i
            }
            diff = diff + nums[j]
            j++
        } else {
            i++
            if(i < nums.size) {
                diff = diff - nums[i]
            }
        }
    }

    return if(end == -1 || start == -1) {
        0
    } else {
        end - start + 1
    }
}