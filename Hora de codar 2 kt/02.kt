fun main(){
    println("Escreva um numero: ")
    val nmr1 = readLine()?.toIntOrNull() ?: 0
    if(nmr1 > 0 ){
        println("Positivo")
    }else if(nmr1 == 0){
        println("Zero")
    }else println("Negativo")
}