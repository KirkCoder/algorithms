package test

import java.io.File

fun main() {
    readFullFile()
}

fun readFullFile() {
    val file = File("D:" + File.separator + "test" + File.separator + "DSCN0163.jpg")
    val bytes = file.readBytes()
    File("D:" + File.separator + "test" + File.separator + "test.file").createNewFile()
    val newFile = File("D:" + File.separator + "test" + File.separator + "test.file")
    newFile.writeBytes(bytes)
}

fun readFileFromBuffer() {
    val film = File("D:" + File.separator + "test" + File.separator + "DSCN0163.jpg")
    File("D:" + File.separator + "test" + File.separator + "test.file").createNewFile()
    val newFile = File("D:" + File.separator + "test" + File.separator + "test.file")
    film.inputStream().use { input ->
        val bytes = ByteArray(1024)
        while (true) {
            val ofset = input.read(bytes, 0, 1024)
            if (ofset < 0) break
            newFile.appendBytes(bytes)
        }
    }
}
