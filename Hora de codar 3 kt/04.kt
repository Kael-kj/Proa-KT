fun main(){
    var soma = 0
    var contador = 0
    for (i in 15 .. 100){
        soma += i
        contador++
    }
    val media = soma.toDouble() / contador
    println("A média aritmética dos números entre 15 e 100 é: $media")
}