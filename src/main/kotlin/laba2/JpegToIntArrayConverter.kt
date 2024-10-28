package ru.sergey.laba2

import java.io.File

object JpegToIntArrayConverter {
    fun jpegToIntArray(filePath: String): IntArray {
        // Чтение файла в массив байтов
        val bytes = File(filePath).readBytes()
        // Преобразование массива байтов в массив Int
        return bytes.map { it.toInt() and 0xFF }.toIntArray()
    }
    fun intArrayToJpeg(intArray: IntArray, outputPath: String) {
        // Преобразование массива Int обратно в массив байтов
        val bytes = ByteArray(intArray.size)
        for (i in intArray.indices) {
            bytes[i] = intArray[i].toByte()
        }
        // Запись массива байтов в файл
        File(outputPath).writeBytes(bytes)
    }
}