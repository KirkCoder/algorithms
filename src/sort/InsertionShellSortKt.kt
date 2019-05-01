package sort

fun main(args: Array<String>){
    val arr = generateArray()
    showArray(arr)
    var step = arr.size / 2
    while(step >= 1){
        sort(arr, step)
        step /= 2
    }
    showArray(arr)
}

fun sort(arr: Array<Int>, gap: Int){
    for(i in 1 until arr.size step gap){
        val element = arr[i]
        for(j in i downTo -1 step gap){
            if(j - gap > -1 && arr[j - gap] > element){
                val tmp = arr[j]
                arr[j] = arr[j - gap]
                arr[j - gap] = tmp
            }
        }
    }
}