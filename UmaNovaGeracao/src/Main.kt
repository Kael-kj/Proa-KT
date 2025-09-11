val fome = 0
val felicidade = 100
val sono = 0
val vida = 100
var dinheiro = 200.00f


data class Bixinho(
    val nome : String,
    val idade : Int
)

data class Geladeira(
    val nome : String,
    val preco: Float,
    var quantidade: Int = 0
)

fun infoBixinho(lista2: MutableList<Geladeira>,lista: MutableList<Bixinho>){
    println("Bem vindo!\nVamos cadastrar seu bixinho virtual?\nMe informe o nome dele: ")
    val nome = readLine()?.toString()?: ""
    println("Agora me informe a idade dele: ")
    val idade = readLine()?.toIntOrNull()?: 0
    println("Muito obrigado por confiar em nossas plataformas para cuidar do seu bixinho virtual!\nAproveito para cuidar dele quando quiser.")
    lista.add(Bixinho(nome, idade))
    homepage(lista2,lista)
}

fun homepage(lista2: MutableList<Geladeira>, lista: MutableList<Bixinho>) {
    while(true) {
        println("\n=== Nova Geração  ===")
        println("1- Mercado")
        println("2- Alimentar")
        println("3- Logout")
        println("4- Sair do programa")
        println("5- Pesquisar hospedagens")
        val escolha = readLine()?.toIntOrNull()

        when (escolha) {
            1 -> {
                compras(lista,lista2)
            }

            2 -> {

            }

            3 -> {
            }

            else -> {
            }
        }
    }
}

fun compras(lista: MutableList<Bixinho>,lista2: MutableList<Geladeira>){
    var contaTotal = 0.00f
    val carrinho=mutableListOf<Geladeira>()
    while (true) {
        println("Bem vindo ao mercado Nova Compra!\nSeu saldo é $dinheiro")
        println("Qual setor deseja entrar: ")
        println("1- Salgados")
        println("2- Doces")
        println("3- Bebidas")
        println("4- Saladas")
        println("5- Frutas")
        println("6- Caixa")
        val escolha = readln()?.toIntOrNull()
        when(escolha){
            1-> {
                while (true) {
                    println("1- Bife - R$2.00")
                    println("2- Macarrão - R$5.00")
                    println("3- Strogonoff - R$7.00")
                    println("4- Pizza - R$15.00")
                    println("5- Ir para outro setor")
                    val escolha2 = readln().toIntOrNull()
                    when (escolha2) {
                        1 -> {
                            val item = carrinho.find { it.nome == "Bife" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Bife", 2.00f, 1))
                            contaTotal += 2.00f
                            println("Bife adicionado! Total: R$$contaTotal")
                        }
                        2 -> {
                            val item = carrinho.find { it.nome == "Macarrão" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Macarrão", 5.00f, 1))
                            contaTotal += 5.00f
                            println("Macarrão adicionado! Total: R$$contaTotal")
                        }
                        3 -> {
                            val item = carrinho.find { it.nome == "Strogonoff" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Strogonoff", 7.00f, 1))
                            contaTotal += 7.00f
                            println("Strogonoff adicionado! Total: R$$contaTotal")
                        }
                        4 -> {
                            val item = carrinho.find { it.nome == "Pizza" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Pizza", 15.00f, 1))
                            contaTotal += 15.00f
                            println("Pizza adicionada! Total: R$$contaTotal")
                        }
                        5 -> break
                    }
                }
            }
            2->{
                while (true) {
                    println("1- Brigadeiro - R$2.00")
                    println("2- Torta de Limão - R$5.00")
                    println("3- Sorvete de pote - R$7.00")
                    println("4- Tiramissu - R$15.00")
                    println("5- Ir para outro setor")
                    val escolha2 = readln().toIntOrNull()
                    when (escolha2) {
                        1 -> {
                            val item = carrinho.find { it.nome == "Brigadiero" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Brigadeiro", 2.00f, 1))
                            contaTotal += 2.00f
                            println("Brigadeiro adicionado! Total: R$$contaTotal")
                        }
                        2 -> {
                            val item = carrinho.find { it.nome == "Torta de Limão" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Torta de Limão", 5.00f, 1))
                            contaTotal += 5.00f
                            println("Torta de Limão adicionada! Total: R$$contaTotal")
                        }
                        3 -> {
                            val item = carrinho.find { it.nome == "Sorvete de pote" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Sorvete de pote", 7.00f, 1))
                            contaTotal += 7.00f
                            println("Sorvete de pote adicionado! Total: R$$contaTotal")
                        }
                        4 -> {
                            val item = carrinho.find { it.nome == "Tiramissu" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Tiramissu", 15.00f, 1))
                            contaTotal += 15.00f
                            println("Tiramissu adicionada! Total: R$$contaTotal")
                        }
                        5 -> break
                    }
                }
            }
            3->{
                while (true) {
                    println("1- Aguá - R$2.00")
                    println("2- Suco de caixa - R$5.00")
                    println("3- Refrigerante - R$7.00")
                    println("4- Energético - R$15.00")
                    println("5- Ir para outro setor")
                    val escolha2 = readln().toIntOrNull()
                    when (escolha2) {
                        1 -> {
                            val item = carrinho.find { it.nome == "Aguá" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Aguá", 2.00f, 1))
                            contaTotal += 2.00f
                            println("Aguá adicionada! Total: R$$contaTotal")
                        }
                        2 -> {
                            val item = carrinho.find { it.nome == "Suco de caixa" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Suco de caixa", 5.00f, 1))
                            contaTotal += 5.00f
                            println("Suco de caixa adicionado! Total: R$$contaTotal")
                        }
                        3 -> {
                            val item = carrinho.find { it.nome == "Refrigerante" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Refrigerante", 7.00f, 1))
                            contaTotal += 7.00f
                            println("Refrigerante adicionado! Total: R$$contaTotal")
                        }
                        4 -> {
                            val item = carrinho.find { it.nome == "Energético" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Energético", 15.00f, 1))
                            contaTotal += 15.00f
                            println("Energético adicionada! Total: R$$contaTotal")
                        }
                        5 -> break
                    }
                }
            }
            4->{
                while (true) {
                    println("1- Alface - R$2.00")
                    println("2- Salada simples - R$5.00")
                    println("3- salada completa - R$7.00")
                    println("4- Ir para outro setor")
                    val escolha2 = readln().toIntOrNull()
                    when (escolha2) {
                        1 -> {
                            val item = carrinho.find { it.nome == "Alface" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Alface", 2.00f, 1))
                            contaTotal += 2.00f
                            println("Alface adicionada! Total: R$$contaTotal")
                        }
                        2 -> {
                            val item = carrinho.find { it.nome == "Salada simples" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Salada simples", 5.00f, 1))
                            contaTotal += 5.00f
                            println("Salada simples adicionada! Total: R$$contaTotal")
                        }
                        3 -> {
                            val item = carrinho.find { it.nome == "Salada completa" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Salada completa", 7.00f, 1))
                            contaTotal += 7.00f
                            println("Salada completa adicionada! Total: R$$contaTotal")
                        }
                        4 -> break
                    }
                }
            }
            5->{
                while (true) {
                    println("1- Laranja - R$2.00")
                    println("2- Melancia - R$5.00")
                    println("3- Fruta do Conde - R$7.00")
                    println("4- Ir para outro setor")
                    val escolha2 = readln().toIntOrNull()
                    when (escolha2) {
                        1 -> {
                            val item = carrinho.find { it.nome == "Laranja" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Laranja", 2.00f, 1))
                            contaTotal += 2.00f
                            println("Laranja adicionada! Total: R$$contaTotal")
                        }
                        2 -> {
                            val item = carrinho.find { it.nome == "Melancia" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Melancia", 5.00f, 1))
                            contaTotal += 5.00f
                            println("Melancia adicionada! Total: R$$contaTotal")
                        }
                        3 -> {
                            val item = carrinho.find { it.nome == "Fruta do Conde" }
                            if (item != null) item.quantidade++
                            else carrinho.add(Geladeira("Fruta do Conde", 7.00f, 1))
                            contaTotal += 7.00f
                            println("Fruta do Conde adicionada! Total: R$$contaTotal")
                        }
                        4 -> break
                    }
                }
            }
            6-> {
                println("=== Caixa ===")
                println("Itens comprados:")
                carrinho.forEach {
                    println("${it.nome} | Qtd: ${it.quantidade} | Unit: R$${it.preco} | Total: R$${it.preco * it.quantidade}")
                }
                println("TOTAL: R$$contaTotal")
                while (true) {
                    println("\nDeseja realizar o pagamento? (Saldo: $dinheiro)")
                    println("1- Sim")
                    println("2- Não")
                    when (readln().toIntOrNull()) {
                        1 -> {
                            if (dinheiro >= contaTotal) {
                                dinheiro -= contaTotal
                                lista2.addAll(carrinho)
                                println("Compra realizada com sucesso! Saldo atual: R$$dinheiro")
                            } else {
                                println("Saldo insuficiente!")
                            }
                            contaTotal = 0.0f
                            homepage(lista2, lista)
                        }
                        2 -> {
                            println("Compra cancelada!")
                            contaTotal = 0.0f
                            homepage(lista2, lista)
                        }
                        else -> "Valor Inválido"
                    }
                }
            }
        }
    }

}





fun main(){
    val lista = mutableListOf<Bixinho>()
    val lista2 = mutableListOf<Geladeira>()
    infoBixinho(lista2,lista)
}
