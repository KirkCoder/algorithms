package crackin.bytes

//Conversion: Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
//SOLUTION
//EXAMPLE
//Input: 29 (or: 111(1), 15 (or: (1111) Output: 2

fun main() {
    println(countChanges(0b11101, 0b01111))
    println(countChanges2(0b11101, 0b01111))
}

fun countChanges(from: Int, to: Int): Int {
    var res = from xor to
    var changes = 0
    while (res != 0) {
        if(res and 1 == 1) {
            changes++
        }
        res = res shr 1
    }
    return changes
}

fun countChanges2(from: Int, to: Int): Int {
    var res = from xor to
    var changes = 0
    while(res != 0) {
        res = res and (res - 1)
        changes++
    }
    return changes
}