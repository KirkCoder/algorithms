package leetcode.facebook

import java.io.StringReader
import java.util.*
import kotlin.Comparator

//Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
//
//
//
//Example 1:
//
//Input: nums = [1,2,3]
//Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//Example 2:
//
//Input: nums = [0,1]
//Output: [[0,1],[1,0]]
//Example 3:
//
//Input: nums = [1]
//Output: [[1]]

fun main() {
    val s = "kdf ldlkf"
    val list = mutableListOf<Pair<String, String>>()

    list.sortWith(Comparator<Pair<String, String>> { o1, o2 ->
        if (o1.second == o2.second) {
            o1.first.compareTo(o2.first)
        } else {
            o1.second.compareTo(o2.second)
        }
    })

    val r = s.indexOfFirst { it == ' ' }
    println(r)
//    Collections.swap(nums, first, i)
}

fun permute(nums: IntArray): List<List<Int>> {
    return permute(nums, 0)
}

fun permute(nums: IntArray, index: Int): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    if(index == nums.size) {
        res.add(listOf<Int>())
        return res
    }

    val permutes = permute(nums, index + 1)
    val element = nums[index]

    for(p in permutes) {
        for(i in p.indices) {
            val list = mutableListOf<Int>()
            for(k in p.indices) {
                if(i == k) {
                    list.add(element)
                }
                list.add(p[k])
            }
            res.add(list)
        }
        if(p.isEmpty()) {
            res.add(listOf(element))
        } else {
            val list = mutableListOf<Int>()
            list.addAll(p)
            list.add(element)
            res.add(list)
        }
    }
    return res
}