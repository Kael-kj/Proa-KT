data class Pessoa(
    val nome : String,
    val endereco : String,
    val celular : String
        )


fun cadastrar(agenda: MutableList<Pessoa>){
    agenda.clear()
    for (i in 1 .. 10){
        println("\nCadastro #$i")
        print("Nome: ")
        val nome = readLine()?:""
        print("Endereco: ")
        val endereco = readLine()?:""
        print("Celular :")
        val celular = readLine()?:""

        agenda.add(Pessoa(nome, endereco,celular))
    }
    println(">> 10 registros cadastrados com sucesso!")
}

fun pesquisar(agenda: List<Pessoa>){
    if (agenda.isEmpty()){
        println("Nenhum registro feito")
        return
    }

    print("Digite o nome para pesquisar: ")
    val busca = readLine()?:""
    val pessoa = agenda.find{ it.nome.equals(busca, ignoreCase = true)}
    if(pessoa != null){
        println("Resgistro encontado: Nome=${pessoa.nome}, Endereco=${pessoa.endereco}, Celular=${pessoa.celular}")
    } else {
        println("Registro nao encontrado")
    }
}


fun classificar(agenda: MutableList<Pessoa>){
    if(agenda.isEmpty()){
        println("Nenhum registro cadastrado")
        return
    }else{
        agenda.sortedBy { it.nome }
        println(".. Registros calassificados por nome!")
    }

}


fun mostrarTodos(agenda: List<Pessoa>) {
    if (agenda.isEmpty()) {
        println("Nenhum registro feito")
        return
    } else {
        println("\n--- Lista de Registros ---")
        agenda.forEachIndexed { i, pessoa ->
            println("${i + 1}. Nome: ${pessoa.nome}, Endereço: ${pessoa.endereco}, Telefone: ${pessoa.celular}")
        }
    }
}


fun main(){
    val agenda = mutableListOf<Pessoa>()

    while (true) {
        println("\n===== MENU AGENDA =====")
        println("1 - Cadastrar 10 registros")
        println("2 - Pesquisar por nome")
        println("3 - Classificar por nome")
        println("4 - Mostrar todos os registros")
        println("5 - Sair")
        print("Escolha uma opção: ")

        when (readLine()?.toIntOrNull()) {
            1 -> cadastrar(agenda)
            2 -> pesquisar(agenda)
            3 -> classificar(agenda)
            4 -> mostrarTodos(agenda)
            5 -> {
                println("Saindo do programa...")
                break
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}