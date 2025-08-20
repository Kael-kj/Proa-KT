fun main() {
    print("Digite um valor N (maior que 0): ")
    val n = readLine()?.toIntOrNull() ?: 0

    if (n > 0) {
        println("Valores de 1 até $n:")
        for (i in 1..n) {
            print("$i ")
        }
    } else {
        println("Valor inválido! O número deve ser maior que 0.")
    }
}