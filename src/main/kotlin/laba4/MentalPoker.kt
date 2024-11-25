package ru.sergey.laba4

import ru.sergey.laba1.Laba1
import ru.sergey.laba2.ShamirCipher
import ru.sergey.laba3.pow2
import javax.annotation.processing.Generated
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
    fun displayCard(card : String, line: Int = 0) {
        val kind: Char
        val rang: Char

        val rangParam: String = card.split(' ').first()
        val kindParam: String = card.split(' ').last()

        when (kindParam) {
            "Пики" -> kind = '\u2660'  // ♠
            "Червы" -> kind = '\u2661'  // ♡
            "Бубны" -> kind = '\u2662'  // ♢
            "Трефы" -> kind = '\u2663'  // ♣
            else -> return
        }

        rang = when (rangParam) {
            "2" -> '2'
            "3" -> '3'
            "4" -> '4'
            "5" -> '5'
            "6" -> '6'
            "7" -> '7'
            "8" -> '8'
            "9" -> '9'
            "10" -> '0'
            "Валет"-> 'J'
            "Дама" -> 'Q'
            "Король" -> 'K'
            "Туз" -> 'A'
            else -> return
        }

        if (rang != '0') {
            when(line) {
                0 -> print("┌───────┐")
                1 -> print ("│ $kind     │")
                2 -> print ("│ $rang     │")
                3 -> print ("│     $rang │")
                4 -> print ("│     $kind │")
                5 -> print ("└───────┘")
            }
        } else {
            when(line) {
                0 -> print("┌───────┐")
                1 -> print("│ $kind     │")
                2 -> print ("│ 10    │")
                3 -> print ("│    10 │")
                4 -> print ("│     $kind │")
                5 -> print ("└───────┘")
            }
        }
    }

    fun GeneratedCD(p : Int) :Pair<Long, Long> {
        var cA = 0L
        do {
            cA = Random.nextLong(0, p - 1L)
        } while (Laba1().Euclid(cA, p - 1L) != 1L)

        var dA = 0L
        do {
            dA = ShamirCipher().modInverse(cA, p - 1L)
        } while ((cA * dA) % (p - 1) != 1L)

        return Pair(cA,dA)
    }
    data class Player(
        val c : Long,
        val d: Long,
        var carts : List<Int>,
        var shiferCars : List<Long>,
    )
    fun play() {

        val peopleCount = 3
        val handsCardCount = 5



        val pullCart = getPullCard()
        val keys = pullCart.keys.shuffled()


        val p = ShamirCipher().getBigPrimeRand(10000)



        val players = (0..peopleCount).map {
            val (c,d) = GeneratedCD(p)
            Player(
                c,
                d,
                listOf(),
                listOf(),
            )
        }

        //step1
        var shifrCards : List<Long> = keys.map { it.toLong() }
        players.forEach { player->
            shifrCards = shifrCards.map { pow2(it.toLong(), player.c, p.toLong()) }.shuffled()
        }
        //step2

        players.forEach { player->
            val cardForPlayer = shifrCards.take(handsCardCount)
            player.shiferCars = cardForPlayer

            shifrCards = shifrCards - cardForPlayer

        }

        players.forEach { player->
            players.forEach { j ->
                player.shiferCars = player.shiferCars.map { k ->
                    pow2(k, j.d, p.toLong())
                }
            }
            shifrCards = shifrCards.map { k ->
                pow2(k, player.d, p.toLong())
            }
        }

        players.forEach { player->
            player.carts = player.shiferCars.map { it.toInt() }
        }
        val pullCartOst = shifrCards.take(5)

        println(keys)

        println(pullCartOst)
        println("commonPull")
        (0..5).forEach{ line -> pullCartOst.forEach{displayCard(pullCart[it.toInt()]!!, line)}; println()}

        players.forEachIndexed{ i, player ->
            println("player: " + (i+1).toString())
            println(player.carts)
            (0..5).forEach{ line -> player.carts.forEach{displayCard(pullCart[it.toInt()]!!, line)}; println()}

        }



        //порядок у карт в киоске был взят
        //println(keys)
//        println(commonPull)
//        println(Alisa)
//        println(Bob)
//        //проверки
//        println(keys.containsAll(Alisa))
//        println(keys.containsAll(Bob))
//        println(keys.containsAll(commonPull))
//        //вывод карт
//
//        println("keys")
//        (0..5).forEach{ line -> keys.forEach{ displayCard(pullCart[it]!!, line) };println() }
//        println("Alisa")
//        (0..5).forEach{ line -> Alisa.forEach{displayCard(pullCart[it]!!, line)}; println()}
//        println("Bob")
//        (0..5).forEach{ line -> Bob.forEach{displayCard(pullCart[it]!!, line)}; println()}
//        println("commonPull")
//        (0..5).forEach{ line -> commonPull.forEach{displayCard(pullCart[it]!!, line)}; println()}



//        val (cA, dA) = GeneratedCD(p)
//        val (cB, dB) = GeneratedCD(p)
//        //game
//        val Alisa = mutableListOf<Int>()
//        val Bob = mutableListOf<Int>()
//        val commonPull = mutableListOf<Int>()
//
//
//        //пудлл кард хранится на сервере игроки могут отсылать полученные карты
//
//        commonPull.addAll(keys)
//
//        var uList = emptyList<Long>()
//        //Алиса
//        repeat(1){
//            //step1
//            uList = keys.map { pow2(it.toLong(), cA, p.toLong()) }.shuffled()
//
//            repeat(handsCardCount) {
//                //step2
//                val uA = uList.shuffled().first()//боб выдаёт Алисе карту
//                uList = uList - uA
//                //Алтса расшифровывает кату
//                val cardA = pow2(uA, dA, p.toLong()).toInt()
//                Alisa.add(cardA)
//                commonPull.remove(cardA)
//            }
//        }
//        //Боб
//        repeat(1) {
//            //step3
//            uList = uList.map { pow2(it, cB, p.toLong()) }.shuffled()
//
//            repeat(handsCardCount) {
//                //step4
//                val uB = uList.shuffled().first()//Алтса выдаёт бобу карту
//                uList = uList - uB
//                val wB = pow2(uB, dA, p.toLong())
//                //боб расшифровывает кату
//                val cardB = pow2(wB, dB, p.toLong()).toInt()
//                Bob.add(cardB)
//                commonPull.remove(cardB)
//            }
//        }
//
//
//        //порядок у карт в киоске был взят
//        println(keys)
//        println(commonPull)
//        println(Alisa)
//        println(Bob)
//        //проверки
//        println(keys.containsAll(Alisa))
//        println(keys.containsAll(Bob))
//        println(keys.containsAll(commonPull))
//        //вывод карт
//
//        println("keys")
//        (0..5).forEach{ line -> keys.forEach{ displayCard(pullCart[it]!!, line) };println() }
//        println("Alisa")
//        (0..5).forEach{ line -> Alisa.forEach{displayCard(pullCart[it]!!, line)}; println()}
//        println("Bob")
//        (0..5).forEach{ line -> Bob.forEach{displayCard(pullCart[it]!!, line)}; println()}
//        println("commonPull")
//        (0..5).forEach{ line -> commonPull.forEach{displayCard(pullCart[it]!!, line)}; println()}
    }
}

fun main() {
    MentalPoker.play()
}