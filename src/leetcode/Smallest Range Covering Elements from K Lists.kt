package leetcode

//ou have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
//
//We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
//
//
//
//Example 1:
//
//Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
//Output: [20,24]
//Explanation:
//List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
//List 2: [0, 9, 12, 20], 20 is in range [20,24].
//List 3: [5, 18, 22, 30], 22 is in range [20,24].
//Example 2:
//
//Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
//Output: [1,1]
//Example 3:
//
//Input: nums = [[10,10],[11,11]]
//Output: [10,11]
//Example 4:
//
//Input: nums = [[10],[11]]
//Output: [10,11]
//Example 5:
//
//Input: nums = [[1],[2],[3],[4],[5],[6],[7]]
//Output: [1,7]


fun smallestRange(nums: List<List<Int>>): IntArray {
    val list = mutableListOf<Pair<Int, Int>>()
    for(i in nums.indices) {
        for(j in nums[i].indices) {
            list.add(Pair(nums[i][j], i))
        }
    }
    list.sortBy { it.first }
    return foundRange(list, nums.size)
}

fun foundRange(list: List<Pair<Int, Int>>, numsSize: Int): IntArray {
    val found = IntArray(numsSize)
    var nums = numsSize
    var i = 0
    var j = 0
    var min: Int? = null
    var max: Int? = null
    while(i < list.size) {
        val pair = list[i]
        found[pair.second] = found[pair.second] + 1
        if(found[pair.second] == 1) {
            nums--
        }

        if(nums == 0) {
            if(min == null || (max!! - min!! > list[i].first - list[j].first)) {
                min = list[j].first
                max = list[i].first
            }
            while(nums == 0) {
                val small = list[j]
                found[small.second] = found[small.second] - 1
                j++
                if(found[small.second] == 0) {
                    nums++
                } else {
                    if(min == null || (max!! - min!! > list[i].first - list[j].first)) {
                        min = list[j].first
                        max = list[i].first
                    }
                }
            }
        }
        i++
    }

    return intArrayOf(min!!, max!!)
}