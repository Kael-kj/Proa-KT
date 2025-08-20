fun main(){
    var contador = 1
    var soma = 0.00
    var media : Double
    while (contador <= 6){
        println("Digite o $contador º número:")
        val nmr = readLine()?.toIntOrNull()?: 0

        soma = soma + nmr
        contador++
    }

    media = soma / 6
    println("A média dos números é: $media")
}