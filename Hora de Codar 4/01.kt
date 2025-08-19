fun main() {
    val nomes = mutableListOf<String>()
    var contador = 1
    println("Digite nomes (ou 'pare' para encerrar):")

    while (true) {
        val nome = readLine()?.trim().orEmpty()
        if (nome.equals("pare", true)) break
        if (nome.isNotEmpty()) nomes.add(nome)
    }
    for (n in nomes) {
        println("$contador. $n")
        contador++
    }
}
