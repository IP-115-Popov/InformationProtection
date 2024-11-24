package ru.sergey.laba3

import java.math.BigInteger
import java.security.SecureRandom

object Gost {
//    // Функция для генерации простого числа заданной длины в битах
//    fun generatePrime(bits: Int): BigInteger {
//        val random = SecureRandom()
//        var prime: BigInteger
//        do {
//            // Генерация случайного числа, старший бит равен 1 (за счет старшего бита устанавливаем "бит длины")
//            prime = BigInteger(bits, random)
//                .setBit(bits - 1) // Убедиться, что старший бит равен 1
//        } while (!prime.isProbablePrime(50)) // Проверка на простоту с вероятностью 50
//        return prime
//    }
//
//    // Генерация p = b * q + 1, где p - простое число длиной 1024 бита
//    fun generateP(q: BigInteger, minPBits: Int = 1024): BigInteger {
//        val random = SecureRandom()
//        var b: BigInteger
//        var p: BigInteger
//        // Генерируем случайное число b
//        b = BigInteger(minPBits, random)
//            .setBit(minPBits - 1) // Убедиться, что старший бит b равен 1
//        do {
//            // Вычисляем p
//            p = b.multiply(q).add(BigInteger.ONE)
//        } while (p.bitLength() != minPBits || !p.isProbablePrime(50)) // Проверка, что p - простое и длина 1024 бита
//        return p
//    }
//    // Функция для генерации случайного числа от 0 до max (не включая max)
//    fun generateRandomBigInteger(max: BigInteger): BigInteger {
//        val random = SecureRandom()
//
//        // Генерация случайного числа в диапазоне от 0 до max
//        // Если max — это большое число, нужно использовать nextBigInteger для безопасного и корректного извлечения случайных чисел
//        val n = max.bitLength()  // Длина в битах числа max
//        var randomBigInt: BigInteger
//        do {
//            randomBigInt = BigInteger(n, random)  // Генерируем случайное число длиной n бит
//        } while (randomBigInt >= max)  // Повторяем, пока число не будет меньше max
//
//        return randomBigInt
//    }
//    fun pow2(base: BigInteger, exponent: BigInteger, modulus: BigInteger): BigInteger {
//        require(modulus > BigInteger.ZERO) { "Модуль должен быть больше 0." }
//        require(base >= BigInteger.ZERO && exponent >= BigInteger.ZERO) { "Основание и показатель степени должны быть неотрицательными." }
//
//        var result = BigInteger.ONE
//        var b = base.mod(modulus)
//        var exp = exponent
//
//        while (exp > BigInteger.ZERO) {
//            if (exp % BigInteger.TWO == BigInteger.ONE) { // Если exponent нечетный
//                result = (result * b).mod(modulus)
//            }
//            exp /= BigInteger.TWO
//            b = (b * b).mod(modulus) // Увеличиваем базу
//        }
//
//        return result
//    }
//    fun sign() {
//        val inputFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba3/1b.jpg"
//        val signFilePath = "C:/Users/serzh/IdeaProjects/InformationProtection/src/main/kotlin/laba2/i2.jpg"
//
//        //Генерация павраметров
//        val q = generatePrime(256)
//        // Генерация простого числа p длиной 1024 бита, где p = b * q + 1
//        val p = generateP(q)
//
//        var a : BigInteger
//        val random = SecureRandom()
//        do {
//            a = BigInteger(32, random)
//        } while (pow2(a,q,p) != BigInteger.ONE)
//
//        val x = generateRandomBigInteger(q)
//
//        val y = pow2(a,x,p)
//
//
//        //
//
//    }


}
fun main() {
    //Gost.sign()
//    // Генерация простого числа q длиной 256 бит
//    val q = generatePrime(256)
//    println("q: $q")
//
//    // Генерация простого числа p длиной 1024 бита, где p = b * q + 1
//    val p = generateP(q)
//    println("p: $p")
}