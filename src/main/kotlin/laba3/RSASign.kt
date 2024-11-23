package ru.sergey.laba3

import ru.sergey.laba1.Laba1
import ru.sergey.laba2.JpegToIntArrayConverter
import ru.sergey.laba2.ShamirCipher
import java.io.File
import java.nio.ByteBuffer
import kotlin.random.Random

object RSASign {
    fun sign() {
        val inputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba3/1b.jpg"
        val signFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba2/i2.jpg"

        val p = ShamirCipher().getBigPrimeRand(10000)
        var q = ShamirCipher().getBigPrimeRand(10000)
        while (p == q) {
            q = ShamirCipher().getBigPrimeRand(10000)
        }
        val n = (p * q).toLong()
        val f = (p - 1L) * (q - 1L)

        var d = 0L
        do {
            d = Random.nextLong(0, f - 1L)
            val v = Laba1().Euclid(d, f)
        } while (v != 1L)

        var c = ShamirCipher().modInverse(d, f)

        // Преобразование JPG в массив Int
        val intArray = JpegToIntArrayConverter.jpegToIntArray(inputFilePath)

        val stringArray: String = intArray.fold("") {
            acc: String, i: Int -> acc + i.toString()
        }

        val m = stringArray
        var yy = md5BigInteger(m)


        //сомнительно но очень фигово
        val y = yy.toInt().mod(n.toInt())

        val s =  pow2(y.toLong(), c, n)

        //проверка

        val w =  pow2(s, d, n)

        // Вывод для проверки
        println("Подпись (s) = $s")
        println("Проверка подписи (w) = $w")
        println("Хеш сообщения (y) = $y")

        LongFileStorage.saveToFile(s, signFilePath)

        val sformdile = LongFileStorage.loadFromFile(signFilePath)
        println("s form file = $sformdile")
    }
}

fun main() {
    RSASign.sign()
}

