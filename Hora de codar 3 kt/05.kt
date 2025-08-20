fun main(){
    println("Informe o menor numero: ")
    var nmr1 = readLine()?.toIntOrNull()?:0
    println("Informe o maior numero: ")
    var nmr2 = readLine()?.toIntOrNull()?:0
    var contador = 0
    var soma = 0
    for (i in nmr1 .. nmr2){
        soma += i
        contador++
    }
    val media = soma.toDouble() / contador
    println("A média aritmética dos números entre $nmr1 e $nmr2 é: $media")
}