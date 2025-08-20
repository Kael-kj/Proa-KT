data class Funcionario(
    val nome : String,
    val matricula : Int,
    val salario : Float
)



//registrar os 20 funcionarios e organizar por matricula
fun cadastrar(lista: MutableList<Funcionario>){
    lista.clear()
    for (i in 1 .. 20){
        println("\nCadastro #$i")
        print("Nome: ")
        val nome = readLine()?:""
        print("Salario: ")
        val salario = readLine()?.toFloatOrNull()?:0.00f
        print("Matricula: ")
        val matricula = readLine()?.toIntOrNull()?:0


        lista.add(Funcionario(nome, matricula, salario))
    }
    lista.sortedBy { it.matricula }
    println(">> 20 registros cadastrados com sucesso e organizados por matricula!")
}



//pesquisa das pessoas cadastradas
fun pesquisar(lista: List<Funcionario>){
    if (lista.isEmpty()){
        println("Nenhum registro feito")
        return
    }

    print("Digite o nome para pesquisar: ")
    val busca = readLine()?.toIntOrNull()
    if(busca != null){
        val funcionario = lista.find{ it.matricula == busca}
        if (funcionario != null){
        println("Resgistro encontado: Nome=${funcionario.nome}, Salario=${funcionario.salario}, Matricula=${funcionario.matricula}")
        }
    } else {
        println("Registro nao encontrado")
    }
}


//mostrar salarios acima de 1k
fun mostrarSalario1(lista: List<Funcionario>){
    println("\n--- Funcionarios com o salario acima de R$1.000,00")
    val filtrar = lista.filter { it.salario > 1000.00 }

    if(lista.isEmpty()){
        println("Nenhum funcionario cadastrado")
        return
    }else{
        filtrar.forEach {
            println("Nome: ${it.nome}, Salario: ${it.salario}, Matricula: ${it.matricula}")
        }
    }
}

//mostrar salarios abaixo de 1k
fun mostrarSalario2(lista: List<Funcionario>){
    println("\n--- Funcionarios com o salario abaixo de R$1.000,00")
    val filtrar = lista.filter { it.salario < 1000.00 }

    if(lista.isEmpty()){
        println("Nenhum funcionario cadastrado")
        return
    }else{
        filtrar.forEach {
            println("Nome: ${it.nome}, Salario: ${it.salario}, Matricula: ${it.matricula}")
        }
    }
}

//mostrar salarios iguais a 1k
fun mostrarSalario3(lista: List<Funcionario>){
    println("\n--- Funcionarios com o salario acima de R$1.000,00")
    val filtrar = lista.filter { it.salario.toDouble() == 1000.00 }

    if(lista.isEmpty()){
        println("Nenhum funcionario cadastrado")
        return
    }else{
        filtrar.forEach {
            println("Nome: ${it.nome}, Salario: ${it.salario}, Matricula: ${it.matricula}")
        }
    }
}


fun main(){
    val lista = mutableListOf<Funcionario>()

    while (true) {
        println("\n===== MENU FUNCIONARIOS =====")
        println("1 - Cadastrar 20 registros")
        println("2 - Pesquisar funcionarios")
        println("3 - Salarios maiores que R$1.000,00")
        println("4 - Salarios menores que R$1.000,00")
        println("5 - Salarios iguais a R$1.000,00")
        println("6 - Sair")
        print("Escolha uma opção: ")

        when (readLine()?.toIntOrNull()) {
            1 -> cadastrar(lista)
            2 -> pesquisar(lista)
            3 -> mostrarSalario1(lista)
            4 -> mostrarSalario2(lista)
            5 -> mostrarSalario3(lista)
            6 -> {
                println("Saindo do programa...")
                break
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}