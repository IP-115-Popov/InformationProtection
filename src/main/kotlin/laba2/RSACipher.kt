package ru.sergey.laba2

import ru.sergey.laba1.Laba1
import kotlin.random.Random
import kotlin.random.nextLong

object RSACipher {
    fun Test() : Boolean {
        val inputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba2/i1.jpg"
        val outputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba2/i2.jpg"


        val p = ShamirCipher().getBigPrimeRand(10000)
        var q = ShamirCipher().getBigPrimeRand(10000)
        while (p == q) {
            q = ShamirCipher().getBigPrimeRand(10000)
        }
        val n = p*q

        val f = (p-1)*(q-1)
        var d  = 0
        do {
            d = Random.nextInt(0,f-1)
            val v = Laba1().Euclid(d.toLong(), f.toLong())
        } while (v != 1L)

        var c = ShamirCipher().modInverse(d.toLong(), f.toLong())

        val cB : Long = c.toLong()
        val dB : Long = d.toLong()
        val nB : Long = n.toLong()

        // Преобразование JPG в массив Int
        val intArray = JpegToIntArrayConverter.jpegToIntArray(inputFilePath)

        val codeArr = intArray.map{Cecoding(it.toLong(),dB,nB)}

        val decode : IntArray = codeArr.map { Dececoding(it.toLong(),cB,nB)}.toIntArray()
        // Преобразование массива Int обратно в JPG
        JpegToIntArrayConverter.intArrayToJpeg(decode, outputFilePath)

         return intArray[0] == decode[0]
    }
    fun Cecoding(m : Long, dB : Long,nB : Long) : Int {
        return Laba1().pow(m,dB,nB).toInt()
    }
    fun Dececoding(e: Long,cB: Long,nB: Long) : Int {
        return Laba1().pow(e,cB,nB).toInt()
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