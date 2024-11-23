package ru.sergey.laba3

import java.math.BigInteger
import java.security.MessageDigest

fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}
fun hashStringToBigInteger(hashString: String): BigInteger {
    val bigint = BigInteger(hashString, 16)
    return bigint
}
fun md5BigInteger(input:String): BigInteger {
    val res  = hashStringToBigInteger(md5(input))
    return res
}
fun pow2(base: Long, exponent: Long, modulus: Long): Long {
    require(modulus > 0) { "Модуль должен быть больше 0." }
    require(base >= 0 && exponent >= 0) { "Основание и показатель степени должны быть неотрицательными." }
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
//fun pow2(base: BigInteger, exponent: BigInteger, modulus: BigInteger): BigInteger {
//    require(modulus > BigInteger.ZERO) { "Модуль должен быть больше 0." }
//    require(base >= BigInteger.ZERO && exponent >= BigInteger.ZERO) { "Основание и показатель степени должны быть неотрицательными." }
//
//    var result = BigInteger.ONE
//    var b = base.mod(modulus)
//    var exp = exponent
//
//    while (exp > BigInteger.ZERO) {
//        if (exp % BigInteger.TWO == BigInteger.ONE) { // Если exponent нечетный
//            result = (result * b).mod(modulus)
//        }
//        exp /= BigInteger.TWO
//        b = (b * b).mod(modulus) // Увеличиваем базу
//    }
//
//    return result
//}