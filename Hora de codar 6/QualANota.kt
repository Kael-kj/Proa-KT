data class Alunos(
    val notas: List<Double>,
    val nome: String,
    val media : Double
)



//registrar os 20 alunos e organizar por nome
fun cadastrar(lista: MutableList<Alunos>){
    lista.clear()
    for (i in 1 .. 20){
        println("\nCadastro #$i")
        print("Nome: ")
        val nome = readLine()?:""

        val notas = mutableListOf<Double>()
        for (j in 1 ..4){
            print("Nota $j: ")
            val nota = readLine()?.toDoubleOrNull()?:0.0
            notas.add(nota)
        }

        val media = notas.average()
        lista.add(Alunos(notas, nome, media))
    }
    lista.sortedBy { it.nome }
    println(">> 20 registros cadastrados com sucesso e organizados por nome!")
}

//PESQUISAR ALUNOS E SABER APROVACAO POR MEDIA
fun pesquisar(lista: List<Alunos>){
    if (lista.isEmpty()){
        println("Nenhum registro feito")
        return
    }

    print("Digite o nome para pesquisar: ")
    val busca = readLine()?:""
    val aluno= lista.find{ it.nome.equals(busca, ignoreCase = true)}
    if(aluno != null){
        if (aluno.media >= 5.0) {
            println("Resgistro encontado: Nome=${aluno.nome}, Nota=${aluno.media}, APROVADO!")
        }else{
            println("Resgistro encontado: Nome=${aluno.nome}, Nota=${aluno.media}, REPROVADO!")
        }
    } else {
        println("Registro nao encontrado")
    }
}



//MOSTRAR A LISTA DE ALUNOS E SE FOI APROVADO OU NAO
fun mostrarTodos(lista: List<Alunos>) {
    if (lista.isEmpty()) {
        println("Nenhum registro feito")
        return
    } else {
        println("\n--- Lista de Registros ---")
        lista.forEachIndexed { i, alunos ->
            println("${i + 1}. Nome: ${alunos.nome}, Media: ${alunos.media} ${if(alunos.media >= 5 )"APROVADO" else "REPROVADO"}")
        }
    }
}


fun main(){
    val lista = mutableListOf<Alunos>()

    while (true) {
        println("\n===== MENU QUAL A NOTA? =====")
        println("1 - Cadastrar 20 alunos")
        println("2 - Pesquisar por nome")
        println("3 - Mostrar todos os alunos")
        println("4 - Sair")
        print("Escolha uma opção: ")

        when (readLine()?.toIntOrNull()) {
            1 -> cadastrar(lista)
            2 -> pesquisar(lista)
            3 -> mostrarTodos(lista)
            4 -> {
                println("Saindo do programa...")
                break
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}