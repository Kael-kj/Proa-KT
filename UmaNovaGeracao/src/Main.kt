import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.random.Random

var dinheiro = 200.00f


data class Bixinho(
    val nome : String,
    val idade : Int,
    val fome: Int = 0,
    val felicidade = 100,
    val sono: Int = 0,
    val vida: Int = 100,
    val sujeira: Int = 0,
    val apertado: Int = 0
){
    fun limitarValor(){
        fome = fome.coerceIn(0, 100)
        felicidade = felicidade.coerceIn(o, 100)
        sono = sono.coerceIn(0, 100)
        vida = vida.coerceIn(0, 100)
        sujeira = sujeira.coerceIn(0, 100)
        apertado = apertado.coerceIn(0, 100)
    }
}

data class Geladeira(
    val nome : String,
    val preco: Float,
    var quantidade: Int = 0
)

fun infoBixinho(geladeira: MutableList<Geladeira>, lista: MutableList<Bixinho>) {
    println("Bem vindo!\nVamos cadastrar seu bixinho virtual?\nMe informe o nome dele: ")
    val nome = readLine()?.toString() ?: ""
    println("Agora me informe a idade dele: ")
    val idade = readLine()?.toIntOrNull() ?: 0
    val bixinho = Bixinho(nome, idade)
    lista.add(bixinho)

    println("🎉 Cadastro concluído! Agora cuide bem do ${bixinho.nome}.")

    iniciarContadorAuto(bixinho)
    GlobalScope.launch { iniciarVidaAuto(bixinho) }

    homepage(geladeira, bixinho)
}

fun homepage(geladeira: MutableList<Geladeira>, bixinho: Bixinho) = runBlocking {
    while (true) {
        println("\n=== 🏠 Home Page do ${bixinho.nome} ===")
        println("Dinheiro: R$$dinheiro | Vida: ${bixinho.vida} | Fome: ${bixinho.fome} | Felicidade: ${bixinho.felicidade} | Sono: ${bixinho.sono}")
        println("1 - Mercado 🛒")
        println("2 - Alimentar 🍴")
        println("3 - Brincar 🎲")
        println("4 - Dormir 😴")
        println("5 - Dar banho 🚿")
        println("6 - Ir ao banheiro 🚽")
        println("7 - Status 📊")
        println("8 - Sair ❌")

        when (readln().toIntOrNull()) {
            1 -> compras(mutableListOf(bixinho), geladeira)
            2 -> alimentar(bixinho, geladeira)
            3 -> brincar(bixinho)
            4 -> dormir(bixinho)
            5 -> tomarBanho(bixinho)
            6 -> irAoBanheiro(bixinho)
            7 -> verStatus(bixinho, geladeira)
            8 -> {
                println("Saindo do jogo... Até logo! 👋")
                return@runBlocking
            }
            else -> println("❌ Opção inválida!")
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


fun iniciarContadorAuto(bixinho: Bixinho){
    thread(start = true){
        var minutos = 0
        whil(true){
            Thread.sleep(60000)
            minutos++

            if( minutos % 3 == 0){
                bixinho.fome++
                println("⏰ ${bixinho.nome} ficou com mais fome! Fome: ${bixinho.fome}")
            }
            if(minutos % 2 == 0){
                bixinho.felicidade -= 10
                println("😢 ${bixinho.nome} ficou entediado! Felicidade: ${bixinho.felicidade}")
            }
            if(minutos % 5 == 0){
                bixinho.sono++
                println("😴 ${bixinho.nome} ficou mais cansado! Sono atual: ${bixinho.sono}")
            }
            if (bixinho.fome >= 75 || bixinho.sono 75 || bixinho.sujeira >= 75 || bixinho.apertado >= 75 || bixinho.felicidade <= 25){
                bixinho -= 2
                println("⚠️ ${bixinho.nome} perdeu 2 de vida por descuido! Vida: ${bixinho.vida}")
            }
            if (minutos % 10080 == 0){
                bixinho.idade++
                println("🎂 ${bixinho.nome} fez aniversário! Agora tem ${bixinho.idade} aninhos!")
            }
            if (bixinho.fome >= 100 || bixinho.sono >= 100 || bixinho.sujeira >= 100 || bixinho.apertado >= 100){
                bixinho.vida = 0
                println("⭐ ${bixinho.nome} ficou muito fraquinho e virou uma estrelinha no céu!")
                break
            }
            if (bixinho.vida <= 0){
                println("⭐ ${bixinho.nome} descansou e virou uma estrelinha no céu!")
                break
            }
            if (bixinho.idade = 50){
                println("🎂 ${bixinho.nome} VOCÊ GANHOU O JOGO DA VIDA DOS BIXINHOS! Agora tem ${bixinho.idade} aninhos!")
            }
            bixinho.limitarValor()
        }
    }
}

fun alimentar(bixinho: Bixinho, geladeira: MutableList<Geladeira>){
    while (true){
        println("=== Geladeira ===")
        geladeira.forEachIndexed {index , item ->
            println("${index + 1} - ${item.nome} (Qtd: ${item.quantidade})")
        }
        println("${geladeira.size+1} - Voltar")

        val escolha readln().toIntOrNull()
        if (escolha == null || escolha !in 1..geladeira.size)break

        val alimento = geladeira[escolha - 1]

        if (alimento.quantidade > 0){
            val satisfacao = when (alimento.nome){
                "Bife" -> 6
                "Macarrão" -> 5
                "Strogonoff" -> 7
                "Pizza" -> 8
                "Brigadeiro" -> 2
                "Torta de Limão" -> 4
                "Sorvete de pote" -> 3
                "Tiramissu" -> 5
                "Água" -> 1
                "Suco de caixa" -> 2
                "Refrigerante" -> 2
                "Energético" -> 1
                "Alface" -> 2
                "Salada simples" -> 4
                "Salada completa" -> 6
                "Laranja" -> 3
                "Melancia" -> 5
                "Fruta do Conde" -> 6
                else -> 1
            }

            alimento.quantidade--
            bixinho.fome = (bixinho.fome - satisfacao). coerceAtLeast(0)
            bixinho.apertado = (bixinho.apertado + satisfacao). coerceAtLeast(100)
            bixinho.limitarValor()

            println("${bixinho.nome} comeu ${alimento.nome} 🍴")
            println("Fome atual: ${bixinho.fome}")
        } else {
            println("❌ Você não tem mais ${alimento.nome} na geladeira!")
        }
    }
}

fun brincar(bixinho: Bixinho){
    while (ture){
        println("\n=== Escolha uma brincadeira com ${bixinho.nome} ===")
        println("1 - Fazer carinho (+5 felicidade)")
        println("2 - Passeio (+10 felicidade, +5 fome)")
        println("3 - Pedra, Papel e Tesoura")
        println("4 - Correr atrás da bolinha")
        println("5 - Dança do bixinho")
        println("6 - Sair")
        when (readln().toInt()){
            1-> carinho(bixinho)
            2-> passeio(bixinho)
            3-> jokenpo(bixinho)
            4-> bola(bixinho)
            5-> danca(bixinho)
            6-> break
            else-> {
                println("Opção inválida! ")
            }
        }
    }
}

fun carinho(bixinho: Bixinho){
    bixinho.felicidade = (bixinho.felicidade + 5).coerceAtMost(100)
    bixinho.sujeira = (bixinho.sujeira + 5).coerceAtMost(100)
    bixinho.limitarValor()
    println("Você fez carinho em ${meuBixinho.nome}. Felicidade: ${meuBixinho.felicidade}")
}
fun passeio(bixinho: Bixinho){
    bixinho.felicidade = (bixinho.felicidade + 10).coerceAtMost(100)
    bixinho.fome += 5
    bixinho.sono += 5
    bixinho.sujeira += 5
    bixinho.limitarValor()
    println("Vocês foram passear! Felicidade: ${meuBixinho.felicidade}, Fome: ${meuBixinho.fome}")
}
fun jokenpo(bixinho: Bixinho){
    val opcoes = listOf("Pedra", "Papel", "Tesoura")
    println("Escolha: 1-Pedra, 2-Papel, 3-Tesoura")
    val jogador = readln()toInto - 1
    val bixinhoEscolha = Random.nextInt(3)
    println("Você: ${opcoes[jogador]}, ${meuBixinho.nome}: ${opcoes[bixinhoEscolha]}")

    if(jogador == bixinhoEscolha){
        bixinho.felicidade += 2
        bixinho.sono += 5
        bixinho.limitarValor()
        println("Empate! Felicidade: ${meuBixinho.felicidade}")
    }else if ((jogador == 0 && bixinhoEscolha 2) ||
        (jogador == 1 && bixinhoEscolha == 0) ||
        (jogador == 2 && bixinhoEscolha == 1)) {
        bixinho.felicidade += 5
        bixinho.sono += 5
        bixinho.limitarValor()
        dinheiro += 10
        println("Você venceu! Felicidade: ${bixinho.felicidade}")
    }else {
        bixinho.felicidade++
        bixinho.sono += 2
        bixinho.limitarValor()
        println("${bixinho.nome} venceu! Felicidade: ${bixinho.felicidade}")
    }
    bixinho.sujeira += 5
    bixinho.limitarValor()
}
suspend fun bola(bixinho: Bixinho){
    println("Digite 'BOLA' rápido!")

    val contador = GlobalScope.launch {
        for(i in 5 downTo 1){
            println("⏳ $i...")
            delay(1000)
        }
    }

    val resposta = withTimeoutOrNull(5000){
        readln()
    }

    if (resposta == null){
        bixinho.felicidade -= 5
        bixinho.sono += 10
        bixinho.fome += 5
        bixinho.limitarValor()
        println("⏰ Tempo esgotado! ${bixinho.nome} ficou triste... Felicidade: ${bixinho.felicidade}")
    }else if (resposta.uppercase() == "BOLA"){
        bixinho.felicidade += 7
        bixinho.sono += 5
        bixinho.limitarValor()
        dinheiro += 10
        println("Boa! ${bixinho.nome} pegou a bolinha! Felicidade: ${bixinho.felicidade}")
    }else {
        bixinho.felicidade += 2
        bixinho.sono += 5
        bixinho.fome += 2
        bixinho.limitarValor()
        println("${bixinho.nome} ficou esperando... Felicidade: ${bixinho.felicidade}")
    }
    bixinho.sujeira += 5
    bixinho.limitarValor()
}
suspend fun danca(bixinho: Bixinho){
    val setas = listOf("↑", "↓", "→", "←")
    val sequencia = List(5) { setas.random() }
    val pontos = 0

    println("${bixinho.nome} vai dançar! Digite as setas certas dentro de 5 segundos!")

    for (passo in sequencia){
        println("Pressione: $passo")

        val resposta = withTimeoutOrNull(5000){
            readln()
        }

        if (resposta == null){
            println("⏰ Você não respondeu a tempo!")
        }else if (resposta == passo){
            pontos += 2
            println("✅ Acertou! (+2 pontos)")
        }else {
            pontos += 0
            println("❌ Errou! Era $passo")
        }
    }

    bixinho.felicidade += pontos
    bixinho.sono += 5
    bixinho.sujeira += 5
    bixinho.limitarValor()
    println("🎉 Dança finalizada! Felicidade atual de ${bixinho.nome}: ${bixinho.felicidade}")
}

suspend fun dormir(bixinho: Bixinho){
    println("😴 ${bixinho.nome} está dormindo... (digite 'ACORDAR' para interromper o sono)")

    val dormindo = AtomicBoolean(true)


    GlobalScope.launch {
        while (dormindo.get()) {
            val input = readln()
            if (input.equals("ACORDAR", ignoreCase = true)) {
                dormindo.set(false)
                println("Você acordou ${bixinho.nome}!")
            }
        }
    }
    while (bixinho.sono > 0 && dormindo.get()){
        delay(5000)
        bixinho.sono -= 2
        bixinho.vida += 2
        bixinho.limitarValor()
        println("zZZ Sono de ${bixinho.nome}: ${bixinho.sono}")
    }
    dormindo.set(false)
    println("${bixinho.nome} acordou naturalmente!")
}

fun tomarBanho(bixinho: Bixinho) = runBlocking {
    val palavrasBanho = listOf("ESPUMA", "ESPONJA", "TOALHA", "SABONETE", "BANHO")
    val palavraEscolhida = palavrasBanho.random()

    println("🚿 É hora do banho do ${bixinho.nome}!")
    println("Digite a palavra '$palavraEscolhida' em até 5 segundos:")

    val resposta = withTimeoutOrNull (5000) {
        readln()
    }

    if (resposta != null && resposta.uppercase() == palavraEscolhida){
        bixinho.sujeira = (bixinho.sujeira - 5).coerceAtLeast(0)
        println("✅ ${bixinho.nome} ficou mais limpo! Sujeira atual: ${bixinho.sujeira}")
    } else {
        println("❌ Você errou ou demorou! ${bixinho.nome} continua com sujeira: ${bixinho.sujeira}")
    }
    bixinho.limitarValor()
}

suspend fun irAoBanheiro(bixinho: Bixinho){
    val palavras = listOf("DESCARGA", "VASO", "PAPEL", "TORNEIRA")
    val palavraEscolhida = palavras.random()

    println("🚽 Seu bixinho ${bixinho.nome} quer ir ao banheiro!")
    println("Digite a palavra '$palavraEscolhida' em até 5 segundos:")

    val resposta = withTimeoutOrNull(5000){
        readlnOrNull()
    }
    if (resposta != null && resposta.uppercase() == palavraEscolhida){
        bixinho.apertado -=5
        println("✅ Muito bem! ${bixinho.nome} usou o banheiro direitinho. Sujeira atual: ${bixinho.sujeira}")
    } else {
        println("❌ ${bixinho.nome} não conseguiu usar o banheiro a tempo... Sujeira: ${bixinho.sujeira}")
    }
}

suspend fun iniciarVidaAuto(bixinho: Bixinho){
    while true {
        delay(300_000)


        if (bixinho.fome >= 100 || bixinho.sono >= 100){
            bixinho.vida = 0
            println("💤 ${bixinho.nome} ficou muito cansado e virou uma estrelinha ⭐")
            break
        }

        var perdeuVida = false
        val motivo = mutableListOf<String>()

        if (bixinho.fome >= 75){ perdeuVida = true; motivos.add("FOME ALTA!") }
        if (bixinho.felicidade <= 25){ perdeuVida = true; motivos.add("FELICIDADE BAIXA!") }
        if (bixinho.sono >= 75){ perdeuVida = true; motivos.add("SONO ALTO!") }
        if (bixinho.sujeira >= 75){ perdeuVida = true; motivos.add("SUJEIRA ALTA!") }
        if (bixinho.apertado >= 75){ perdeuVida = true; motivos.add("VONTADE DE IR AO BANHEIRO!") }

        if (perdeuVida) {
            bixinho.vida -= 2
            bixinho.limitarValor()
            println("⚠️ ${bixinho.nome} perdeu 2 de vida por: ${motivos.joinToString(", ")}. Vida atual: ${bixinho.vida}")
        }

        if (bixinho.vida <= 0){
            println("⭐ ${bixinho.nome} ficou muito fraquinho e virou uma estrelinha no céu!")
            break
        }
    }
}

fun verStatus(bixinho: Bixinho, geladeira: MutableList<Geladeira>){
    println("\n=== 📊 Status do Bixinho ===")
    println("Nome: ${bixinho.nome}")
    println("Idade: ${bixinho.idade}")
    println("Vida: ${bixinho.vida}/100")
    println("Fome: ${bixinho.fome}/100")
    println("Felicidade: ${bixinho.felicidade}/100")
    println("Sono: ${bixinho.sono}/100")
    println("Dinheiro disponível: R$$dinheiro")

    println("\n=== 🧊 Geladeira ===")
    if (geladeira.isEmpty()) {
        println("A geladeira está vazia.")
    } else {
        geladeira.forEach {
            println("🍖 ${it.salgado} | 🍰 ${it.doce} | 🥤 ${it.bebida} | 🥗 ${it.salada} | 🍎 ${it.frutas}")
        }
    }
    println("==========================\n")
}

fun main() {
    val lista = mutableListOf<Bixinho>()
    val geladeira = mutableListOf<Geladeira>()
    infoBixinho(geladeira, lista)
}
