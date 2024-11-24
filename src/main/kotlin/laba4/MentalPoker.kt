package ru.sergey.laba4

import ru.sergey.laba1.Laba1
import ru.sergey.laba2.ShamirCipher
import ru.sergey.laba3.pow2
import kotlin.random.Random

object MentalPoker {
    fun getPullCard(): Map<Int, String> {
        // Определим возможные масти и ранги карт
        val suits = listOf("Пики", "Червы", "Бубны", "Трефы")
        val ranks = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "Валет", "Дама", "Король", "Туз")

        // Генерируем все возможные комбинации мастей и рангов
        val cards = suits.flatMap { suit ->
            ranks.map { rank -> "$rank $suit" }
        }

        // Генерация случайных чисел и создание Map
        val cardMap = mutableMapOf<Int, String>()
        cards.forEach { card ->
            // Генерируем случайное число для каждой карты
            var randomKey: Int
            do {
                randomKey = Random.nextInt(0, 10001)  // случайное число от 0 до 10000
            } while (randomKey in cardMap)  // проверяем, чтобы ключ был уникальным

            // Добавляем пару в Map
            cardMap[randomKey] = card
        }

        return cardMap
    }

    fun play() {
        val p = ShamirCipher().getBigPrimeRand(10000)
        //Alisa
        var cA = 0L
        do {
            cA = Random.nextLong(0, p - 1L)
        } while (Laba1().Euclid(cA, p - 1L) != 1L)

        var dA = 0L
        do {
            dA = ShamirCipher().modInverse(cA, p - 1L)
        } while ((cA * dA) % (p - 1) != 1L)

        //Bob
        var cB = 0L
        do {
            cB = Random.nextLong(0, p - 1L)
        } while (Laba1().Euclid(cB, p - 1L) != 1L)

        var dB = 0L
        do {
            dB = ShamirCipher().modInverse(cB, p - 1L)
        } while ((cB * dB) % (p - 1) != 1L)

        //cart 9
        val pullCart = getPullCard()
        val keys = pullCart.keys.shuffled().take(9)

        //game
        val Alisa = mutableListOf<Int>()
        val Bob = mutableListOf<Int>()
        val commonPull = mutableListOf<Int>()

        //step1
        var uList = keys.map { pow2(it.toLong(), cA, p.toLong()) }.shuffled()

        //step2
        val uA = uList.shuffled().first()//боб выдаёт Алисе карту
        uList = uList - uA
        //Алтса расшифровывает кату
        val cardA = pow2(uA,dA,p.toLong()).toInt()
        Alisa.add(cardA)

        //step3
        uList = uList.map { pow2(it, cB, p.toLong()) }.shuffled()

        //step4
        val uB = uList.shuffled().first()//Алтса выдаёт бобу карту
        uList = uList - uB
        val wB = pow2(uB, dA, p.toLong())
        //боб расшифровывает кату
        val cardB = pow2(wB,dB,p.toLong()).toInt()

        Bob.add(cardB)

    }
}

fun main() {
    MentalPoker.play()
}