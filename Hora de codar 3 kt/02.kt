fun main(){
    println("Informe o primeiro valor: ")
    var vlr1 = readLine()?.toIntOrNull()?: 0
    println("Informe o segundo valor: ")
    var vlr2 = readLine()?.toIntOrNull()?: 0
    while(vlr2 <= 0 ){
        println("Valor invalido! Digite novamente o seugndo valor (maior que zero): ")
        vlr2 = readLine()?.toIntOrNull()?: 0
    }
    println("Resultado da divisao: ${vlr1 / vlr2}")
}