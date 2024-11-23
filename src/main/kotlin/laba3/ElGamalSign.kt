package ru.sergey.laba3

import ru.sergey.laba1.Laba1
import ru.sergey.laba2.ElGamalCipher.getBigPrimeRand
import ru.sergey.laba2.ElGamalCipher.getRang
import ru.sergey.laba2.JpegToIntArrayConverter
import ru.sergey.laba2.ShamirCipher
import kotlin.random.Random

object ElGamalSign {
    fun sign() {
        val inputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba3/1b.jpg"
        val signFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba2/i2.jpg"
//        //генерим параметры
//        val p = getBigPrimeRand(5000000)
//        val g = getRang(p).toInt()
//        val cB = Random.nextInt(1, p - 1)
//        val dB = Laba1().pow(g.toLong(), cB.toLong(), p.toLong())

        //
        val intArray = JpegToIntArrayConverter.jpegToIntArray(inputFilePath)

        val stringArray: String = intArray.fold("") { acc: String, i: Int ->
            acc + i.toString()
        }
//
        val p = 23//getBigPrimeRand(5000000)
        val g = 5//getRang(p).toInt()
        //val cB = //Random.nextInt(1, p - 1)
        //val dB = //Laba1().pow(g.toLong(), cB.toLong(), p.toLong())
         val x = 7//cB
         val y = 17//dB
  //

        val m = stringArray
       // val x = cB
       // val y = dB

        var hash = 3//md5BigInteger(m).toInt()

        var k = 5
//        do {
//            k = Random.nextInt(0, p - 1)
//            val v = Laba1().Euclid(k.toLong(), p - 1L)
//        } while (v != 1L)

        val r = pow2(g.toLong(), k.toLong(), p.toLong())

        val u =17// Math.abs((hash - x * r)) % (p - 1)

        val s = (ShamirCipher().modInverse(k.toLong(), p-1L) * u) % (p-1)



        val a = pow2(y.toLong(), r, p.toLong())
        val b = pow2(r, s, p.toLong())
        val resL = ( a * b) % p

        val resR = pow2(g.toLong(), hash.toLong(), p.toLong())
        val res = resL == resR
        println(res)

        // Вывод для проверки
        println("Подпись (s) = $s")
        println("Хеш сообщения (y) = $hash")

        LongFileStorage.saveToFile(s, signFilePath)

        val sformdile = LongFileStorage.loadFromFile(signFilePath)
        println("s form file = $sformdile")

    }
}

fun main() {
    ElGamalSign.sign()
}