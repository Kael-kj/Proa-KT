data class Pessoas(
    val nome : String,
    val altura : Float
)



//registrar as 15 pessoas
fun cadastrar(lista: MutableList<Pessoas>){
    lista.clear()
    for (i in 1 .. 15){
        println("\nCadastro #$i")
        print("Nome: ")
        val nome = readLine()?:""
        print("Altura em metros: ")
        val altura = readLine()?.toFloatOrNull()?:0.0f


        lista.add(Pessoas(nome, altura))
    }
    println(">> 15 registros cadastrados com sucesso!")
}


//Apresentar registros <= 1.5

fun mostrarBaixos(lista: List<Pessoas>){
    println("\n--- Pessoas com altura menor ou igual 1.5m")
    val filtrar = lista.filter { it.altura <=1.5 }

    if(lista.isEmpty()){
        println("Nenhuma pessoa cadastrada")
        return
    }else{
        filtrar.forEach {
            println("Nome: ${it.nome}, Altura: ${it.altura}")
        }
    }

}


//Apresentar registros > 1.5

fun mostrarMaiores(lista: List<Pessoas>){
    println("\n--- Pessoas com altura maior que 1.5m")
    val filtrar = lista.filter { it.altura >1.5 }

    if(lista.isEmpty()){
        println("Nenhuma pessoa cadastrada")
        return
    }else{
        filtrar.forEach {
            println("Nome: ${it.nome}, Altura: ${it.altura}")
        }
    }

}


//Apresentar registros > 1.5 e < 2.0

fun mostrarAltos(lista: List<Pessoas>){
    println("\n--- Pessoas com altura maior que 1.5m e menor que 2.0")
    val filtrar = lista.filter { (it.altura > 1.5) && (it.altura< 2.0)}

    if(lista.isEmpty()){
        println("Nenhuma pessoa cadastrada")
        return
    }else{
        filtrar.forEach {
            println("Nome: ${it.nome}, Altura: ${it.altura}")
        }
    }
}




// calcular media das alturas

fun media(lista: List<Pessoas>){
    if (lista.isEmpty()){
        println("Nenhuma pessoa cadastrada")
    }else{
        val media = lista.map{ it.altura }.average()
        println("A media de altura dos nomes cadastrados: %.2f m".format(media))
    }
}


fun main(){
    val lista = mutableListOf<Pessoas>()

    while (true) {
        println("\n===== MENU ALTURAS =====")
        println("1 - Cadastrar 15 registros")
        println("2 - Pessoas com menos de 1.5m")
        println("3 - Pessoas com mais de 1.5m")
        println("4 - Pessoas com mais de 1.5m e com menos de 2.0m")
        println("5 - Media das alturas")
        println("6 - Sair")
        print("Escolha uma opção: ")

        when (readLine()?.toIntOrNull()) {
            1 -> cadastrar(lista)
            2 -> mostrarBaixos(lista)
            3 -> mostrarMaiores(lista)
            4 -> mostrarAltos(lista)
            5 -> media(lista)
            6 -> {
                println("Saindo do programa...")
                break
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}