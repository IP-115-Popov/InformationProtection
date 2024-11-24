package ru.sergey.laba3

import ru.sergey.laba1.Laba1
import ru.sergey.laba2.ElGamalCipher.getBigPrimeRand
import ru.sergey.laba2.ShamirCipher
import java.security.SecureRandom
import kotlin.random.Random


object GostInt {
    val random = SecureRandom()
    // Функция для проверки простоты числа
    fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        if (n == 2 || n == 3) return true
        if (n % 2 == 0 || n % 3 == 0) return false
        var i = 5
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false
            i += 6
        }
        return true
    }
    // Функция для генерации случайного простого числа типа Int
    fun generatePrime(min : Int = 2 ,max: Int): Int {
        val random = SecureRandom()
        var prime: Int
        do {
            // Генерация случайного числа в диапазоне от 2 до max
            prime = random.nextInt(min, max)
        } while (!isPrime(prime))  // Проверяем, что оно простое
        return prime
    }

    // Функция для генерации p = b * q + 1
    fun generateP(q: Int): Int {

        var b: Int
        var p: Int
        do {
            // Генерируем случайное число b, чтобы p было простым
            b = random.nextInt(1, 1000)  // Ограниваем b для быстрой генерации
            p = b * q + 1
        } while (!isPrime(p))  // Проверяем, что p простое
        return p
    }

    fun sign() {
        val m = "pjoafs"

        var h = md5BigInteger(m).mod(Int.MAX_VALUE.toBigInteger()).toInt()
        //генерим параметры
        // Устанавливаем верхний предел для q (например, до 1000)
        val maxQ = 1000

        // Генерация простого числа q
        val q = generatePrime(h,h+maxQ)

        // Генерация p = b * q + 1
        val p = generateP(q)
        println("q: $q")
        println("p: $p")

        var a : Int
        val random = SecureRandom()
        do {
            a = random.nextInt(1, 1000)
        } while (pow2(a,q,p) != 1)

        val x = random.nextInt(0, q)

        val y = pow2(a,x,p)

        //
        require(h in 0..q) {"h !in 0..q"}

        var s : Int = 0
        var k: Int
        var r: Int = 0
        do {
            do {
                k = random.nextInt(0, q)
                r = pow2(a, k, p) % q
            } while (r == 0 || r !in 0..q)
            s = (k * h + x * r) % q
        } while (s == 0 || s !in 0..q)

        //проверка подписи
        require(r in 0..q) {"r in 0..q"}
        require(s in 0..q) {"s in 0..q"}

        val u1 = s * ShamirCipher().modInverse(h.toLong(), p-1L)%q
        val u2 = r * ShamirCipher().modInverse(h.toLong(), p-1L)%q

        val au1 = pow2(a, u1.toInt(), p)
        val uu2 = pow2(y, u2.toInt(), p)
        val u =(( au1 * uu2)%p)%q

        println("Проверка: " + (u==r))

    }
}
fun main() {
    GostInt.sign()
}