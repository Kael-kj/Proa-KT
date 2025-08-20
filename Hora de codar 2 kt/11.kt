fun adicao(){
    println("Informe o primeiro valor: ")
    val nmr1 = readLine()?.toIntOrNull()?:0
    println("Informe o segundo valor: ")
    val nmr2 = readLine()?.toIntOrNull()?:0

    var resultado = nmr1 + nmr2

    print("Seu resultado : $resultado")
    return(homepage())
}

fun subtracao(){
    println("Informe o primeiro valor: ")
    val nmr1 = readLine()?.toIntOrNull()?:0
    println("Informe o segundo valor: ")
    val nmr2 = readLine()?.toIntOrNull()?:0

    var resultado = nmr1 - nmr2

    print("Seu resultado : $resultado")
    return(homepage())
}

fun divisao(){
    println("Informe o primeiro valor: ")
    val nmr1 = readLine()?.toIntOrNull()?:0
    println("Informe o segundo valor: ")
    val nmr2 = readLine()?.toIntOrNull()?:0

    var resultado = nmr1 / nmr2

    print("Seu resultado : $resultado")
    return(homepage())
}

fun mutiplicacao(){
    println("Informe o primeiro valor: ")
    val nmr1 = readLine()?.toIntOrNull()?:0
    println("Informe o segundo valor: ")
    val nmr2 = readLine()?.toIntOrNull()?:0

    var resultado = nmr1 * nmr2

    print("Seu resultado : $resultado")
    return(homepage())
}



fun homepage(){
    println("\n=====CALCULATOR=====")
    println("1- Adicao")
    println("2- Subtracao")
    println("3- Divisao")
    println("4- Multiplicacao")
    println("5- Sair")
    println("Esolha sua opcao: ")
    var escolha = readLine()?.toIntOrNull()?:0
    when(escolha){
        1-> adicao()
        2-> subtracao()
        3->divisao()
        4->mutiplicacao()
        5->{
            println("Saindo do programa...")
        }
        else ->{
            println("Opção inválida, tente novamente.")
            return(homepage())
        }
    }
}


fun main(){
    homepage()
}