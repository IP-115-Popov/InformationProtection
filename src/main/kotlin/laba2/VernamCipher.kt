package ru.sergey.laba2

import kotlin.random.Random

object VernamCipher {
    fun Test() : Boolean {
        val inputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba2/i1.jpg"
        val outputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba2/i2.jpg"

        val outputFilePathE = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba2/e2.jpg"


        val key = Random.nextInt(0, 1_000_000_000)
        // Преобразование JPG в массив Int
        val intArray = JpegToIntArrayConverter.jpegToIntArray(inputFilePath)
//кодирование
        val codeArrE = intArray.map{CecodingDececoding(key, it)}.toIntArray()
        JpegToIntArrayConverter.intArrayToJpeg(codeArrE, outputFilePathE)

        //декодинг
        val codeArr = JpegToIntArrayConverter.jpegToIntArray(outputFilePathE)

        val decode : IntArray = codeArr.map { CecodingDececoding(key,it)}.toIntArray()
        // Преобразование массива Int обратно в JPG
        JpegToIntArrayConverter.intArrayToJpeg(decode, outputFilePath)

       // return intArray[0] == decode[0]
        return true
    }
    fun CecodingDececoding(key : Int, message : Int) : Int {
        return message xor key
    }
}