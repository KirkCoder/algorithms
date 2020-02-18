fun main() {
//    val arr = Array(700) { (Math.random() * 32000).toInt() }
    val arr = arrayOf(1, 48, 1, 78, 78, 78, 2010, 552, 31000, 7800, 7600, 5200, 31000, 32000, 32000)
    showArray(arr)
    findDublicates(arr)
}

fun findDublicates(array: Array<Int>) {
    val bits = Array(1024) { 0 }

    for(a in array){
        val position = (a / 8) / 4
        val bit = bits[position]
        val count = a % 32
        val mask = (1 shl count)
        if (bit and mask == mask){
            print("$a, ")
        } else {
            bits[position] = bit or mask
        }

    }
}