fun main(){
    println("Escreva um numero: ")
    val nmr1 = readLine()?.toIntOrNull() ?: 0
    println("Escreva o segundo numero: ")
    val nmr2 = readLine()?.toIntOrNull()?: 0
    println("Escreva o terceiro numero: ")
    val nmr3 = readLine()?.toIntOrNull()?: 0
    var maior1 : Int
    var maior2 : Int



    if(nmr1 > nmr2){
        maior1 = nmr1
        maior2 = nmr2
    }else {
        maior1 = nmr2
        maior2 = nmr1
    }
    if (nmr3 > maior1){
        maior2 = maior1
        maior1 = nmr3
    }else
        maior2 = nmr3

    var soma = maior1 + maior2
    println("A soma dos dois maiores numeros da: $soma")

}