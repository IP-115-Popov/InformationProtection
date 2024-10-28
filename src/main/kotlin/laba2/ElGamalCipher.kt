package ru.sergey.laba2

import ru.sergey.laba1.Laba1
import kotlin.random.Random

object ElGamalCipher {
    fun Test() : Boolean {
        val inputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba2/i1.jpg"
        val outputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba2/i2.jpg"

        val p = getBigPrimeRand(5000000)
        val g = getRang(p).toInt()

        val k = Random.nextInt(1, p-2)

        //val cA = Random.nextInt(1, p-1)
        //val dA = Laba1().pow(g.toLong(),cA.toLong(),p.toLong())

        val cB = Random.nextInt(1, p-1)
        val dB = Laba1().pow(g.toLong(),cB.toLong(),p.toLong())

//        val m = 555745
//        val (r, e) = Cecoding(m,p,g,k, dB.toInt())
//        val rez = Dececoding(p,g,r,e, cB)
//        return m == rez
        // Преобразование JPG в массив Int
        val intArray = JpegToIntArrayConverter.jpegToIntArray(inputFilePath)

        val codeArr = intArray.map{Cecoding(it,p,g,k, dB.toInt())}

        val decode : IntArray = codeArr.map { it -> var (r, e) = it; Dececoding(p,g,r,e, cB)}.toIntArray()
        // Преобразование массива Int обратно в JPG
        JpegToIntArrayConverter.intArrayToJpeg(decode, outputFilePath)

        return intArray[0] == decode[0]
    }
    fun Cecoding(m : Int , p : Int, g : Int, k : Int, dB : Int) : Pair<Long,Long>{
        val r = Laba1().pow(g.toLong(), k.toLong(), p.toLong())
        val e = (m * Laba1().pow(dB.toLong(), k.toLong() ,p.toLong() ))%p
        return Pair(r,e)
    }
    fun Dececoding(p : Int, g : Int, r : Long, e : Long, cB : Int) : Int {
        val r_inv = Laba1().pow(r,(p-1-cB).toLong(),p.toLong())
        val m = (e * r_inv) % p;
        return m.toInt()
    }














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

    fun getRang(p : Int) : Long {
        val q = (p-1)/2
        var g = 0
        while (true) {
            g = Random.nextInt(0, 100)
            if ((1 < g) && (g < p - 1) && (Laba1().pow(g.toLong(), q.toLong(), p.toLong()) != 1L))
                return g.toLong();
            //println(p)

        }
    }
    fun FermaCheck(p : Int) : Boolean {
        if (p == 2)
            return true
        for (i in 0 until 100) {
            val a = Random.nextInt(1, p-2) + 1L//getRang(p-2)+1
            if (Euclid(a,p.toLong()).first != 1L)
                return false
            if (Laba1().pow(a, p-1L, p.toLong()) != 1L)
                return false
        }
        return true
    }
    fun getPrimeRand() : Int {
        var f = false
        var p = 0
        while (f == false) {
            p = Random.nextInt(5, 1_000)
            f = FermaCheck(p);

        }
        return p
    }
}