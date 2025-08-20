fun main(){
    println("Escreva um numero: ")
    val nmr1 = readLine()?.toIntOrNull() ?: 0
    println("Escreva o segundo numero: ")
    val nmr2 = readLine()?.toIntOrNull()?: 0
    println("Escreva o terceiro numero: ")
    val nmr3 = readLine()?.toIntOrNull()?: 0

    if((nmr2 > nmr1)&& (nmr2 >nmr3)){
        println(nmr2)
    }else if ((nmr3 > nmr2)&& (nmr3 >nmr1)){
        println(nmr3)
    }else
        println(nmr1)
}