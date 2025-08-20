fun main(){
    println("Escreva um numero: ")
    val nmr1 = readLine()?.toIntOrNull() ?: 0
    println("Escreva outro numero: ")
    val nmr2 = readLine()?.toIntOrNull()?: 0

    if(nmr2 > nmr1){
        println(nmr2)
    }else println(nmr1)
}