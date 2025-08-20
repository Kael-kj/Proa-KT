fun main(){
    val numeros = mutableListOf<Int>()
    var soma = 0
    var contador = 1

    for (i in 1..4){
        print("Digite sua nota $i: ")
        val num = readLine()?.toIntOrNull()?: 0
        numeros.add(num)

        if ((num > 0) && (num < 10)){
            soma += num
            contador++
        }
    }

    soma = soma / contador

    if (soma >= 5){
        println("Voce passou no teste")
    }else println("Tente Novamente")
}