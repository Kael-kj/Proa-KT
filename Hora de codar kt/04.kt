fun retangulo(){
    println("Informe o valor da base: ")
    val base = readLine()?.toDoubleOrNull()?:0.0
    println("Informe o valor da altura: ")
    val altura = readLine()?.toDoubleOrNull()?:0.0
    var resultado = base * altura

    print("Seu resultado : $resultado")
    return(homepage())
}

fun quadrado(){
    println("Informe o valor do lado: ")
    val lado = readLine()?.toDoubleOrNull()?:0.0
    var resultado = lado * lado

    print("Seu resultado : $resultado")
    return(homepage())
}

fun losango(){
    println("Informe o valor da diagonal maior: ")
    val diagonalM = readLine()?.toDoubleOrNull()?:0.0
    println("Informe o valor da diagonal menor: ")
    val diagonalm = readLine()?.toDoubleOrNull()?:0.0
    var resultado = (diagonalM * diagonalm) / 2

    print("Seu resultado : $resultado")
    return(homepage())
}

fun trapezio(){
    println("Informe o valor da diagonal maior: ")
    val diagonalM = readLine()?.toDoubleOrNull()?:0.0
    println("Informe o valor da diagonal menor: ")
    val diagonalm = readLine()?.toDoubleOrNull()?:0.0
    println("Informe o valor da altura: ")
    val h = readLine()?.toDoubleOrNull()?:0.0
    var resultado = ((diagonalM + diagonalm)*h) / 2

    print("Seu resultado : $resultado")
    return(homepage())
}

fun paralelogramo(){
    println("Informe o valor da base: ")
    val base = readLine()?.toDoubleOrNull()?:0.0
    println("Informe o valor da altura: ")
    val altura = readLine()?.toDoubleOrNull()?:0.0
    var resultado = base * altura

    print("Seu resultado : $resultado")
    return(homepage())
}

fun triangulo(){
    println("Informe o valor da base: ")
    val base = readLine()?.toDoubleOrNull()?:0.0
    println("Informe o valor da altura: ")
    val altura = readLine()?.toDoubleOrNull()?:0.0
    var resultado = (base * altura)/2

    print("Seu resultado : $resultado")
    return(homepage())
}


fun circulo(){
    println("Informe o valor do raio: ")
    val raio = readLine()?.toDoubleOrNull()?:0.0
    var resultado = 3.14*(raio*raio)

    print("Seu resultado : $resultado")
    return(homepage())
}


fun homepage(){
    println("\n=====AREAS=====")
    println("1- Retangulo")
    println("2- Quadrado")
    println("3- Losango")
    println("4- Trapezio")
    println("5- Paralelogramo")
    println("6- Triangulo")
    println("7- Circulo")
    println("8- Sair")
    println("Esolha sua opcao: ")
    var escolha = readLine()?.toIntOrNull()?:0
    when(escolha){
        1-> retangulo()
        2-> quadrado()
        3->losango()
        4->trapezio()
        5->paralelogramo()
        6->triangulo()
        7->circulo()
        8->{
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