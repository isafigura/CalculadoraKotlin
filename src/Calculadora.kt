fun main() {
    calcular()
}

fun calcular() {
    println("Digite uma operação:")
    val operacao = readln().replace(" ", "")

    if (operacao.contains("+")) {
        val numeros = operacao.split('+')
        var resultado = 0.0

        for (n in numeros) {
            resultado += n.toDouble()
        }

        println("Resultado: $resultado")
    }

    else if (operacao.contains("-")) {
        val numeros = operacao.split('-')
        var resultado = numeros[0].toDouble()

        for (i in 1 until numeros.size) {
            resultado -= numeros[i].toDouble()
        }

        println("Resultado: $resultado")
    }

    else if (operacao.contains("*")) {
        val numeros = operacao.split('*')
        var resultado = 1.0

        for (n in numeros) {
            resultado *= n.toDouble()
        }

        println("Resultado: $resultado")
    }

    else if (operacao.contains("/")) {
        val numeros = operacao.split('/')
        var resultado = numeros[0].toDouble()

        for (i in 1 until numeros.size) {
            resultado /= numeros[i].toDouble()
        }

        println("Resultado: $resultado")
    }

    else {
        println("Operação inválida")
    }
}