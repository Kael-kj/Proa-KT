fun main(){
    val numeros = mutableListOf<Int>()
    var soma = 0

    for (i in 1..6){
        print("Digite o numero $i: ")
        val num = readLine()?.toIntOrNull()?: 0
        numeros.add(num)

        if (num < 72){
            soma += num
        }
    }

    println("\nNúmeros informados: ${numeros.joinToString(", ")}")
    println("Soma dos números menores que 72: $soma")
}