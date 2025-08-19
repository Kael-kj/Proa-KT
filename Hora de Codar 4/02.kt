fun main(){
    val planetas = listOf("Terra", "Marte", "Plutão", "Vênus", "Júpiter", "Saturno")

    println("Digite um nome de um planeta: ")
    val entrada = readLine()?: ""

    if(entrada in planetas){
        println("O planeta $entrada está na lista!")
    }else println("O planeta $entrada não está na lista!")
}