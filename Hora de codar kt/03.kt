fun main(){
    print("Informe seu nome: ")
    val nome = readLine()?:""
    println("Qual sua idade: ")
    val idade= readLine()?.toIntOrNull()?:0
    println("Ola, $nome, sua idade: $idade")
}