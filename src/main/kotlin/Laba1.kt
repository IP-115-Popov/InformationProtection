package ru.sergey

import kotlin.math.exp
import kotlin.random.Random

class Laba1 {
    fun pow(base: Long, exponent: Long, modulus: Long): Long {
        require(modulus > 0) { "Modulus must be greater than 0." }
        require(base >= 0 && exponent >= 0) { "Base and exponent must be non-negative." }

        var result = 1L
        var b = base % modulus
        var exp = exponent

        while (exp > 0) {
            if (exp % 2 == 1L) { // Если exponent нечетный
                result = (result * b) % modulus
            }
            exp /= 2
            b = (b * b) % modulus // Увеличиваем базу
        }

        return result
    }

//    fun pow(a : Long, x : Long , p : Long) : Long {
//        var binaryString: String = x.toString(2)
//        //binaryString =  binaryString.reversed()
//        var xList : Array<Long> = Array(binaryString.length, {0L})
//
//
//        xList[0] = x%p
//        for (i in 1 until xList.size) {
//            val xPrev = xList[i-1]
//            if (xPrev == 1L)
//            {
//                xList[i] = 1
//            } else {
//                xList[i] = (xPrev * xPrev) % p
//            }
//        }
//
//        var rez = 1L
//        binaryString.forEachIndexed{index: Int, c: Char ->
//            if (c == '1')
//            {
//                rez *= xList[index]
//            }
//        }
//        return rez % p
//    }


    fun Euclid(a : Long, b :Long) : Long {
        var u = Array(3,{0L})
        var v = Array(3,{0L})
        var t = Array(3,{0L})
        if (a >= b) {
            u[0] = a
            u[1] = 1
            u[2] = 0

            v[0] = b
            v[1] = 0
            v[2] = 1

        } else {
            u[0] = b
            u[1] = 0
            u[2] = 1

            v[0] = a
            v[1] = 1
            v[2] = 0
        }
        do {
            val q = u[0]/v[0]

            t[0] = u[0] - q*v[0]
            t[1] = u[0] - q*v[1]
            t[2] = u[0] - q*v[2]

            u[0] = v[0]
            u[1] = v[1]
            u[2] = v[2]

            v[0] = t[0]
            v[1] = t[1]
            v[2] = t[2]

        } while (v[0] != 0L)

        println("x = " + v[1].toString() + "\ny = " + v[2].toString())

        return u[0]
    }
    fun hellman() : Boolean {
        fun getRang(p : Int) : Long {
            val q = (p-1)/2
            var g = 0
            while (true) {
                g = Random.nextInt(0, 100)
                if ((1 < g) && (g < p - 1) && (pow(g.toLong(), q.toLong(), p.toLong()) != 1L))
                    return g.toLong();
                //println(p)

            }
        }
        fun FermaCheck(p : Int) : Boolean {
            if (p == 2)
                return true
            for (i in 0 until 100) {
                val a = Random.nextInt(1, p-2) + 1L//getRang(p-2)+1
                if (Euclid(a,p.toLong()) != 1L)
                    return false
                if (pow(a, p-1L, p.toLong()) != 1L)
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



        val p = 17//getPrimeRand()
        val g = getRang(p)

        val xA = Random.nextInt(1, 1_000).toLong()
        val xB = Random.nextInt(1, 1_000).toLong()

        val yA = pow(g,xA,p.toLong())
        val yB = pow(g,xB,p.toLong())


        val zAB = pow(yB, xA, p.toLong())
        val zBA = pow(yA, xB, p.toLong())
        return zAB == zBA
    }
}