fun main(){
    println("Escreva um numero: ")
    val nmr1 = readLine()?.toIntOrNull() ?: 0
    println("Escreva o segundo numero: ")
    val nmr2 = readLine()?.toIntOrNull()?: 0
    println("Escreva o terceiro numero: ")
    val nmr3 = readLine()?.toIntOrNull()?: 0
    println("Escreva o quarto numero: ")
    val nmr4 = readLine()?.toIntOrNull()?: 0
    var maior1 : Int



    if(nmr1 > nmr2){
        maior1 = nmr1
    }else {
        maior1 = nmr2
    }
    if (nmr3 > maior1){
        maior1 = nmr3
    }else{}

    println("O maior deles: $maior1 \nO primeiro: $nmr1 \nO ultimo: $nmr4")
}