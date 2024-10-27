package ru.sergey

fun main() {
 val af = Laba1().pow(10, 88, 17)
 println(af)

 val z1 = Laba1().pow(3,100,7)
 println(z1)

 val z2 = Laba1().Euclid(28,7)
 println(z2)

 val z3 = Laba1().hellman()
 println(z3)

 // Пример использования
 val a = 2L  // Основание
 val p = 23L // Простое число
 val y = 9L// Значение, для которого ищется дискретный логарифм

 val result = Laba1().babyStepGiantStep(a, p, y)
 if (result != -1L) {
  println("Дискретный логарифм: x = $result")
 } else {
  println("Решение не найдено.")
 }
}