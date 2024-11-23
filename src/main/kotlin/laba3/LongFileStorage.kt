package ru.sergey.laba3

import java.io.File
import java.nio.ByteBuffer

object LongFileStorage {

    // Сохраняем значение Long в файл
    fun saveToFile(value: Long, filePath: String) {
        val byteArray = ByteBuffer.allocate(8).putLong(value).array()  // Преобразуем Long в 8 байт
        File(filePath).writeBytes(byteArray)  // Записываем байты в файл
    }

    // Загружаем значение Long из файла
    fun loadFromFile(filePath: String): Long {
        val byteArray = File(filePath).readBytes()  // Читаем байты из файла
        if (byteArray.size != 8) {
            throw IllegalArgumentException("Файл должен содержать ровно 8 байт для значения Long.")
        }
        return ByteBuffer.wrap(byteArray).long  // Преобразуем 8 байт обратно в Long
    }
}