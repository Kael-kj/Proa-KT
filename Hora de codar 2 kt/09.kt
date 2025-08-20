fun main(){

    var idade : Int

    println("Em que ano estmos: ")
    val anoA = readLine()?.toIntOrNull()?:0
    println("Em que ano voce nasceu: ")
    val ano = readLine()?.toIntOrNull()?:0

    idade = anoA - ano

    if (idade >=16){
        println("Voce pode votar")
    }else println("Voce ainda nao pode votar")
}