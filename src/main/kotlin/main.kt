const val MASTERCARD = "MasterCard"
const val VISA = "VISA"
const val VKPAY ="VKPay"
const val ERR_LIMIT = -1
const val ERR_TYPE = -2
fun main () {
    println("TASK #1")
    println("Был(а) в сети ${agoToText()}")

    println("TASK #2")
    val result = comission(VISA, 81440, 30)
    when (result) {
        ERR_TYPE -> println("Wrong Type")
        ERR_LIMIT -> println("Limit exceed")
        else -> println("Комиссия за перевод: $result")
    }

}

fun comission(type: String, amount: Int, previous: Int) : Any =
    when (type) {
        MASTERCARD -> if (amount + previous > 150000) ERR_LIMIT else if (amount <= 75000 && previous <= 600000) 0 else (amount/100) * 0.6 + 20
        VISA -> {
            when {
                amount + previous <= 10000 -> 0
                amount + previous > 600000 -> ERR_LIMIT
                amount <= 35 -> ERR_LIMIT
                else -> amount/100 * 0.75
            }
        }
        VKPAY -> {
            when {
                amount >= 15000 -> ERR_LIMIT
                amount + previous >= 40000 -> ERR_LIMIT
                else -> 0
            }
        }
        else -> ERR_TYPE
    }
fun agoToText(): Any {
    val seconds = 17000 // TIME AGO IN SECONDS
    val minutes = if (seconds/60 % 1 == 1 && seconds/60 <= 241 ) "минут" else if (seconds/60 % 2 == 0) "минут" else "минуты"
    val hours = if (seconds/3600 % 1 == 1 && seconds/3600 <= 3601) "час" else if (seconds/3600 % 2 == 0) "часа" else "часов"
    return when (seconds) {
        in 0..60 -> "Только что"
        in 61..3600 -> "${seconds/60} $minutes назад"
        in 3601..24 * 60 * 60 -> "${seconds/3600} $hours назад"
        in 3600 * 24..3600 * 48 -> "вчера"
        in 3600 * 48..3600 * 72 -> "позавчера"
        in 3600 * 72..3600 * 72 * 30 -> "давно"
        else -> "давно"
    }
}
