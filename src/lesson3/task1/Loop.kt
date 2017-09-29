@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import jdk.nashorn.internal.ir.WhileNode
import lesson1.task1.sqr
import kotlin.coroutines.experimental.buildIterator

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var i = 0
    var k: Int = n
    do {
        i += 1
        k /= 10
    } while (k != 0)
    return i
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {

    if (n < 3) return 1
    else return fib(n - 1) + fib(n - 2)

}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var y = 0
    for (k in 1..n * m) {
        if (k % m != 0 || k % n != 0) continue
        y = k
        if (y % m == 0 || y % n == 0) break
    }
    return y
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var k = 0
    for (i in 2..n) {
        if (n % i != 0) continue
        k = i
        if (n % k == 0) break
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var k = 0
    for (i in n / 2 downTo 1) {
        if (n % 1 != 0) continue
        k = i
        if (n % k == 0) break
    }
    return k
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var result = true
    val min = Math.min(m, n)
    val max = Math.max(m, n)
    if (max % min == 0) return false
    else for (k in 2..min / 2) {
        if (m % k != 0 && n % k != 0) result = true
        else result = false
    }
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k: Double
    var q: Double
    var result = true
    val x = Math.sqrt(m * 1.0).toInt()
    val y = Math.sqrt(n * 1.0).toInt()
    for (i in x..y) {
        k = i.toDouble()
        q = sqr(k)
        if (q in m..n) result = true
        else result = false
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var s: Double
    var s1 = 1.0
    var a = x
    var i = 1
    while (a > 0 && a >= 2 * Math.PI) {
        a = a - 2 * Math.PI
    }
    while (a < 0 && a <= -2 * Math.PI) {
        a = a + 2 * Math.PI
    }
    var t = a
    s = a
    while (Math.abs(s / s1) > Math.abs(eps)) {
        s = s * t * t * (-1)
        s1 = s1 * (i + 1) * (i + 2)
        a = a + s / s1
        i = i + 2
    }
    return a
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var e = n
    var s = 0
    var t: Int
    while (e != 0) {
        t = e % 10
        s = s * 10 + t
        e = e / 10
    }
    return s
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    var e = n
    var s = 0
    var t = 0
    while (e != 0) {
        t = e % 10
        s = s * 10 + t
        e = e / 10
    }
    if (s == n) return true
    else return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var k=0
    var t=0
    var x=0
    while (k<n) {
        t+=1
        x=t*t
        k= k + digitNumber(x)
    }
    for (i in 1..k-n){x=x/10}
    return x%10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var k = 2
    var fn = 0
    var i=1
    var x=1
        while (k < n) {
            k = k + digitNumber(fib(i))
            fn= fib(i)
            i++
        }
        while (x < k - n + 1) {
            fn / 10
            x++
        }
        return fn % 10
    }


