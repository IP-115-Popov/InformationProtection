package ru.sergey

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.random.Random

class Laba2 {
//    fun ShamirCipher(m : Int) : Boolean {
//        val p = getBigPrimeRand(m).toLong() //m<p
//
//        var cA = 0L
//        do {
//            cA = Random.nextInt(1, 10_000).toLong()
//        } while(Laba1().Euclid(cA, p - 1) != 1L)
//        var dA = modInverse(cA, p - 1)
//
//        var cB = 0L
//        do {
//            cB = Random.nextInt(1, 10_000).toLong()
//        } while(Laba1().Euclid(cB, p - 1) != 1L)
//        var dB = modInverse(cB, p - 1)
//
//        val x1 = Laba1().pow(m.toLong(),cA,p)
//        val x2 = Laba1().pow(x1,cB,p)
//        val x3 = Laba1().pow(x2,dA,p)
//        val x4 = Laba1().pow(x3,dB,p)
//        return x4 == m.toLong()
//    }
    fun ShamirCipher() : Boolean {
        var m : Int = 50
        val p = getBigPrimeRand(m).toLong() //m<p

        var cA = 0L
        do {
            cA = Random.nextInt(1, 10_000).toLong()
        } while(Laba1().Euclid(cA, p - 1) != 1L)

        var cB = 0L
        do {
            cB = Random.nextInt(1, 10_000).toLong()
        } while(Laba1().Euclid(cB, p - 1) != 1L)




    val inputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/i1.jpg"
    val outputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/i2.jpg"

    // Преобразование JPG в массив Int
    val intArray = jpegToIntArray(inputFilePath)

    // Преобразование массива Int обратно в JPG
    intArrayToJpeg(intArray, outputFilePath)




        val x3 = ShamirCipherCecoding(m, p, cA, cB)
        val x4 = ShamirCipherDecoding(x3, p ,cA, cB)
        return m.toLong() == x4
    }
    fun ShamirCipherCecoding(m : Int, p : Long, cA : Long, cB : Long) : Long {
        val p = p.toLong() //m<p

        var dA = modInverse(cA, p - 1)

        val x1 = Laba1().pow(m.toLong(),cA,p)
        val x2 = Laba1().pow(x1,cB,p)
        val x3 = Laba1().pow(x2,dA,p)

        return x3
    }
    fun ShamirCipherDecoding(x3 : Long, p : Long,  cA : Long, cB : Long) : Long {
        val p = p.toLong() //m<p


        var dB = modInverse(cB, p - 1)

        val x4 = Laba1().pow(x3,dB,p)
        return x4
    }
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
    fun  ElGamalCipher() {}
    fun  VernamCipher() {}
    fun  RSACipher() {}

    fun getBigPrimeRand(start : Int) : Int {
        var f = false
        var p = 0
        while (f == false) {
            p = Random.nextInt(start, start+1_000)
            f = Laba1().FermaCheck(p);

        }
        return p
    }
    private fun modInverse(d: Long, phi: Long): Long {
        var (g, x ,y) = Euclid(d, phi)
        return (x % phi + phi) % phi;
    }
    fun Euclid(a : Long, b :Long) : Triple<Long, Long, Long> {
       if (a == 0L) {
           return Triple(a , 0, 1)
       }
        val (gcd, x1 ,y1) = Euclid(b%a, a)
        var x =  y1 - (b / a) * x1;
        var y = x1;
        return Triple(gcd, x , y)
    }
}