val pilha = mutableMapOf<Int, String>()
var nivel = 0

fun main() {
    val regex = Regex("^[0-9+\\-*/()]+$")

    println("Digite sua expressão: ")
    var exp = readln().replace(" ", "")

    exp = exp.replace(Regex("(\\d)\\( "), "$1*(")
    exp = exp.replace(Regex("\\)(\\d)"), ")*$1")

    if (exp.length >= 9 && exp.matches(regex)) {
        println("Expressão aceita.")
    } else {
        println("Expressão negada.")
        return
    }

    var expTemporaria = ""
    exp.forEach { c ->
        when (c) {
            '(' -> {
                pilha[nivel] = pilha.getOrDefault(nivel, "") + expTemporaria
                expTemporaria = ""
                nivel++
            }
            ')' -> {
                val resultadoNivel = calcularMatematica(expTemporaria)
                nivel--
                expTemporaria = pilha.getOrDefault(nivel, "") + resultadoNivel
                pilha[nivel] = ""
            }
            else -> expTemporaria += c
        }
    }

    val resultadoFinal = calcularMatematica(expTemporaria)
    println("\nResultado final: $resultadoFinal")
}

fun calcularMatematica(expressao: String): String {
    if (expressao.isBlank()) return "0"

    val expLimpa = expressao.replace("++", "+").replace("--", "+").replace("+-", "-")

    val tokens = mutableListOf<String>()
    var numeroAcumulado = ""

    expLimpa.forEach { c ->
        if (c.isDigit() || c == '.') {
            numeroAcumulado += c
        } else {
            if (numeroAcumulado.isNotEmpty()) tokens.add(numeroAcumulado)
            tokens.add(c.toString())
            numeroAcumulado = ""
        }
    }
    if (numeroAcumulado.isNotEmpty()) tokens.add(numeroAcumulado)

    var i = 0
    while (i < tokens.size) {
        if (tokens[i] == "*" || tokens[i] == "/") {
            val num1 = tokens[i - 1].toDouble()
            val num2 = tokens[i + 1].toDouble()
            val res = if (tokens[i] == "*") num1 * num2 else num1 / num2

            tokens[i - 1] = res.toString()
            tokens.removeAt(i)
            tokens.removeAt(i)
            i--
        }
        i++
    }

    var resultado = tokens[0].toDouble()
    i = 1
    while (i < tokens.size) {
        val op = tokens[i]
        val proximoNum = tokens[i + 1].toDouble()
        if (op == "+") resultado += proximoNum
        if (op == "-") resultado -= proximoNum
        i += 2
    }

    return resultado.toString()
}