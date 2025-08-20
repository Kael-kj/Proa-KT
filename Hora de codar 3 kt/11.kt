fun main() {
    print("Digite um valor inteiro : ")
    val nmr = readLine()?.toIntOrNull() ?: 0

    if (nmr > 0) {
        for (i in 1..nmr) {
            println("\nTabuada do $i:")
            for (j in 1..10) {
                println("$i x $j = ${i * j}")
            }
        }
    } else {
        println("Valor inválido! Digite um número maior que 0.")
    }
}
