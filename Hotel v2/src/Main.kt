import java.util.Scanner
import kotlin.math.abs
import kotlin.math.round
import kotlin.system.exitProcess

data class Hospedagem(
    val id: Int,
    val nome: String,
    val dias: Int,
    val valorDiaria: Double,
    val subtotal: Double,
    val descontoPercent: Int,
    val descontoValor: Double,
    val total: Double,
    val deposito: Double,
    val saldo: Double,
    val quarto: Int,
    val listaHospedes: MutableList<String>
)

val quartos = Array(20) { false }
val hospedagens = mutableListOf<Hospedagem>()
val listaHospedes = mutableListOf<String>()
var contadorCadastro = 1

data class Evento(
    val id: Int,
    val empresa: String,
    val convidados: Int,
    val local: String,
    val dia: String,
    val hora: Int,
    val duracao: Int,
    val totalGarcons: Int,
    val custoGarcons: Double,
    val custoBuffet: Double,
    val custoTotal: Double,
    val cadeirasExtras: Int
)
val eventos = mutableListOf<Evento>()
var contadorEventos = 1

data class Transporte(
    val id: Int,
    val destino: String,
    val qtdPessoas: Int,
    val veiculos: Map<String, Int>, // ônibus -> 1, van -> 2, carro -> 2
    val custo: Int
)

val transportes = mutableListOf<Transporte>()
var contadorTransporte = 1

data class Combustivel(
    val posto: String,
    val tipo: String,
    val preco: Double,
    val custoTotal: Double
)

data class Empresa(
    val nome: String,
    val valorPorAparelho: Double,
    val quantidadeAparelhos: Int,
    val percentualDesconto: Double,
    val quantidadeMinimaDesconto: Int,
    val valorMinimoIsencao: Double,
    val taxaDeslocamento: Double,
    var valorFinal: Double = 0.0
)

object UserSession {
    var currentUser: String? = null
    val usuariosUsados = mutableSetOf<String>()

    fun login(user: String) {
        currentUser = user
        usuariosUsados.add(user)
    }

    fun logout() {
        currentUser = null
    }
}

fun login(scanner: Scanner) {
    val senhaCorreta = "2678"
    println("=== HOTEL TERABITHIA ===")

    while (true) {
        print("Digite o nome de usuário: ")
        val user = scanner.nextLine()
        if (user in UserSession.usuariosUsados) {
            println("Usuário ja foi utilizado. Tente outro nome de usuário para entrar!")
            continue
        }

        var tentativas = 0
        var autenticado = false
        while (tentativas < 3) {
            print("Digite a senha: ")
            val senha = scanner.nextLine()
            if (senha == senhaCorreta) {
                UserSession.login(user)
                println("Login realizado com sucesso! Bem-vindo ao Hotel Terabithia, ${UserSession.currentUser}.")
                homepage(scanner)
                autenticado = true
            } else {
                tentativas++
                println("Senha incorreta. Tentativas restantes: ${3 - tentativas}")
            }
        }
        if (!autenticado) {
            println("Tentativas excedidas. Troque o usuário para entrar!")
        }
    }
}

fun homepage(scanner: Scanner) {
    while (true) {
        println("\n=== HOTEL TERABITHIA ===")
        println("Usuário logado: ${UserSession.currentUser}")
        println("1 - Cadastro de hóspedes")
        println("2 - Pesquisar hospedagens")
        println("3 - Cadastrar evento")
        println("4 - Pesquisa de abastecimento")
        println("5 - Cadastrar transporte")
        println("6 - Manutenção de ar-condicionado")
        println("7 - Logout")
        println("8 - Sair do programa")

        print("Escolha uma opção: ")
        val escolha = scanner.nextLine()

        when (escolha) {
            "1" -> cadastro(scanner)
            "2" -> pesquisarHospedagem(scanner)
            "3" -> cadastrarEvento(scanner)
            "4" -> pesquisaAbastecimento(scanner)
            "5" -> cadastrarTransporte(scanner)
            "6" -> manutencaoDeAr()
            "7" -> {
                println("Encerrando login...")
                login(scanner)
                return
            }
            "8" -> {
                println("Saindo do programa...")
                exitProcess(0)
            }
            else -> println(" Opção inválida! Tente novamente.")
        }
    }
}


fun cadastro(scanner: Scanner) {
    print("Nome do hóspede principal: ")
    val nomePrincipal = scanner.nextLine()

    print("Idade do hóspede principal: ")
    val idadePrincipal = scanner.nextLine().toInt()

    print("Quantidade de dias: ")
    val dias = scanner.nextLine().toInt()

    print("Valor da diária: R$ ")
    val valorDiaria = scanner.nextLine().toDouble()

    var quartoEscolhido: Int
    while (true) {
        println("Escolha um quarto (1 a 20):")
        quartoEscolhido = scanner.nextLine().toInt()
        if (quartoEscolhido !in 1..20) {
            println("Quarto inválido! Digite entre 1 e 20.")
            continue
        }
        if (quartos[quartoEscolhido - 1]) {
            println("Quarto $quartoEscolhido já está ocupado!")
            val sugestoes = (1..20).filter { !quartos[it - 1] }
                .sortedBy { abs(it - quartoEscolhido) }
                .take(3)
            if (sugestoes.isEmpty()) {
                println("Todos os quartos estão ocupados!")
                return
            } else {
                println("Sugestões de quartos próximos: ${sugestoes.joinToString(", ")}")
            }
        } else break
    }


    val listaHospedes = mutableListOf<Pair<String, Int>>()
    listaHospedes.add(nomePrincipal to idadePrincipal)


    print("Há familiares que irão se hospedar com você? (S/N): ")
    val temFamiliares = scanner.nextLine().uppercase()
    if (temFamiliares == "S") {
        while (true) {
            print("Digite o nome do familiar (ou ENTER para finalizar): ")
            val nomeFamiliar = scanner.nextLine()
            if (nomeFamiliar.isEmpty()) break
            print("Digite a idade de $nomeFamiliar: ")
            val idadeFamiliar = scanner.nextLine().toInt()
            listaHospedes.add(nomeFamiliar to idadeFamiliar)
        }
    }

    var subtotal = 0.0
    println("\n--- RESUMO POR HÓSPEDE ---")
    listaHospedes.forEach { (nome, idade) ->
        val diariaFinal = when {
            idade < 6 -> 0.0
            idade >= 60 -> valorDiaria / 2
            else -> valorDiaria
        }
        println("Hóspede: $nome, Idade: $idade, Valor diária: R$ $diariaFinal")
        subtotal += diariaFinal * dias
    }

    val descontoPercent = when (dias) {
        in 5..9 -> 8
        in 10..30 -> 15
        else -> 0
    }
    val descontoValor = subtotal * descontoPercent / 100
    val totalSemTaxa = subtotal - descontoValor
    val taxaEstado = 7.50
    val total = totalSemTaxa + taxaEstado
    val deposito = total * 0.3
    val saldo = total - deposito

    println("\n--- RESUMO GERAL ---")
    println("Total de hóspedes: ${listaHospedes.size}")
    println("Subtotal: R$ $subtotal")
    println("Desconto: R$ $descontoValor")
    println("Total sem taxa: R$ $totalSemTaxa")
    println("Taxa do estado: R$ $taxaEstado")
    println("Total final: R$ $total")
    println("Depósito (30%): R$ $deposito")
    println("Saldo a pagar: R$ $saldo")
    println("---------------\n")

    print("Deseja confirmar o cadastro? (S/N): ")
    val confirmar = scanner.nextLine().uppercase()
    if (confirmar == "S") {
        quartos[quartoEscolhido - 1] = true
        val hospedagem = Hospedagem(
            id = contadorCadastro++,
            nome = nomePrincipal,
            dias = dias,
            valorDiaria = valorDiaria,
            subtotal = subtotal,
            descontoPercent = descontoPercent,
            descontoValor = descontoValor,
            total = total,
            deposito = deposito,
            saldo = saldo,
            quarto = quartoEscolhido,
            listaHospedes = listaHospedes.map { it.first }.toMutableList()
        )
        hospedagens.add(hospedagem)
        println("Cadastro confirmado! Número de cadastro: ${hospedagem.id}\n")
    } else println("Cadastro cancelado.\n")
}




fun pesquisarHospedagem(scanner: Scanner){
    println("\n=== PESQUISAR HOSPEDAGEM ===")
    println("1- Por nome do hóspede principal")
    println("2- Por número do quarto")
    println("3- Por ID do cadastro")
    println("4- Voltar")
    print("Escolha: ")
    when(scanner.nextLine()) {
        "1" -> {
            print("Digite o nome: ")
            val nomeBusca = scanner.nextLine()
            val resultados = hospedagens.filter { it.nome.contains(nomeBusca, ignoreCase = true) }
            if (resultados.isEmpty()) {
                println("Nenhum hóspede encontrado.")
            } else resultados.forEach { mostrarResumoHospedagem(it) }
        }

        "2" -> {
            print("Digite o número do quarto: ")
            val quartoBusca = scanner.nextLine().toIntOrNull()
            if (quartoBusca == null) {
                println("Entrada inválida!")
            } else {
                val resultados = hospedagens.filter { it.quarto == quartoBusca }
                if (resultados.isEmpty()) println("Nenhum hóspede encontrado nesse quarto.")
                else resultados.forEach { mostrarResumoHospedagem(it) }
            }
        }

        "3" -> {
            print("Digite o ID do cadastro: ")
            val idBusca = scanner.nextLine().toIntOrNull()
            if (idBusca == null) {
                println("Entrada inválida!")
            } else {
                val resultado = hospedagens.find { it.id == idBusca }
                if (resultado == null) println("Nenhum cadastro encontrado com esse ID.")
                else mostrarResumoHospedagem(resultado)
            }
        }
        "4"-> return
        else-> println("Opção inválida!")
    }
}

fun mostrarResumoHospedagem(h: Hospedagem) {
    println("""
        --- HOSPEDAGEM ENCONTRADA ---
        ID: ${h.id}
        Hóspede principal: ${h.nome}
        Dias: ${h.dias}
        Quarto: ${h.quarto}
        Total: R$ ${h.total}
        Hóspedes: ${h.listaHospedes.joinToString(", ")}
        -----------------------------
    """.trimIndent())
}

fun cadastrarEvento(scanner:Scanner){
    println("=== CADASTRO DE EVENTO ===")
    print("Qual o número de convidados para o evento? ")
    val convidados = scanner.nextLine().toInt()

    if (convidados <= 0 || convidados > 350) {
        println("Número de convidados inválido!")
        return
    }
    var local : String
    var cadeirasExtras = 0

    if(convidados<= 220){
        local = "Adutório Laranja"
        if(convidados > 150) cadeirasExtras = convidados - 150
        println("use o $local (inclua $cadeirasExtras cadeiras extras)")
    }else {
        local = "Auditório Colorado"
        println("Use o $local")
    }

    print("Qual o dia do evento? (dias da semana)")
    val dia = scanner.nextLine().lowercase()
    println("Qual hora do evento? (apenas hora cheia)")
    val hora = scanner.nextLine().toInt()
    val disponivel = when(dia){
        "segunda", "terca", "quarta", "quinta", "sexta" -> hora in 7..23
        "sabado", "domingo" -> hora in 7..15
        else -> false
    }

    if (!disponivel) {
        println("Auditório indisponível")
        return
    }

    print("Qual o nome da empresa? ")
    val empresa = scanner.nextLine()

    println("Auditório reservado para $empresa. $dia às ${hora}h.")

    print("Qual a duração do evento em horas? ")
    val duracao = scanner.nextLine().toInt()

    val garconsBase = Math.ceil(convidados / 12.0).toInt()
    val garconsExtras = Math.ceil(duracao / 2.0).toInt()
    val totalGarcons = garconsBase * garconsExtras
    val custoGarcons = totalGarcons * 10.5 * duracao

    println("Serão necessários $totalGarcons garçons.")
    println("Custo: R$ $custoGarcons")
    println("Agora vamos calcular o buffet do hotel para o evento.")


    val litrosCafe = convidados * 0.2
    val litrosAgua = convidados * 0.5
    val qtdSalgados = convidados * 7

    val custoCafe = litrosCafe * 0.80
    val custoAgua = litrosAgua * 0.40
    val custoSalgados = (qtdSalgados / 100.0) * 34.0
    val custoBuffet = custoCafe + custoAgua + custoSalgados

    println("O evento precisará de ${litrosCafe.toInt()} litros de café, " +
            "${litrosAgua.toInt()} litros de água, $qtdSalgados salgados.")
    println("Custo total do buffet: R$ $custoBuffet")

    val custoTotal = custoGarcons + custoBuffet


    val evento = Evento(
        id = contadorEventos++,
        empresa = empresa,
        convidados = convidados,
        local = local,
        dia = dia,
        hora = hora,
        duracao = duracao,
        totalGarcons = totalGarcons,
        custoGarcons = custoGarcons,
        custoBuffet = custoBuffet,
        custoTotal = custoTotal,
        cadeirasExtras = cadeirasExtras
    )

    eventos.add(evento)

    println("\n=== RESUMO FINAL ===")
    println("ID do evento: ${evento.id}")
    println("Local: ${evento.local}")
    println("Empresa: ${evento.empresa}")
    println("Dia: ${evento.dia} às ${evento.hora}h")
    println("Convidados: ${evento.convidados}")
    if (evento.cadeirasExtras > 0) println("Cadeiras extras: ${evento.cadeirasExtras}")
    println("Garçons: ${evento.totalGarcons} (Custo: R$ ${evento.custoGarcons})")
    println("Buffet: R$ ${evento.custoBuffet}")
    println("CUSTO TOTAL DO EVENTO: R$ ${evento.custoTotal}")
    println("Evento cadastrado com sucesso!\n")
}

fun cadastrarTransporte(scanner: Scanner) {
    println("=== Cadastro de Transporte ===")
    print("Destino do passeio: ")
    val destino = scanner.nextLine()

    print("Quantidade de pessoas para o passeio: ")
    val qtdPessoas = scanner.nextLine().toInt()

    val capacidadeOnibus = 50
    val capacidadeVan = 12
    val capacidadeCarro = 4

    var restante = qtdPessoas
    var onibus = restante / capacidadeOnibus
    restante %= capacidadeOnibus

    var vans = restante / capacidadeVan
    restante %= capacidadeVan

    var carros = (restante + capacidadeCarro - 1) / capacidadeCarro // arredonda para cima

    val veiculos = mapOf(
        "Ônibus" to onibus,
        "Vans" to vans,
        "Carros" to carros
    )

    // custo base de exemplo
    val custo = (onibus * 500) + (vans * 200) + (carros * 100)

    println("\n--- RESUMO TRANSPORTE ---")
    println("Destino: $destino")
    println("Pessoas: $qtdPessoas")
    veiculos.forEach { (tipo, qtd) -> if (qtd > 0) println("$tipo: $qtd") }
    println("Custo total: R$ $custo")
    println("--------------------------")

    val transporte = Transporte(
        id = contadorTransporte++,
        destino = destino,
        qtdPessoas = qtdPessoas,
        veiculos = veiculos,
        custo = custo
    )
    transportes.add(transporte)
}

fun pesquisaAbastecimento(scanner: Scanner) {
    println("=== PESQUISA DE MELHOR ABASTECIMENTO ===")

    fun lerPreco(msg: String): Double {
        var valor: Double
        while (true) {
            print(msg)
            valor = scanner.nextLine().replace(",", ".").toDoubleOrNull() ?: -1.0
            if (valor > 0) {
                return round(valor * 100) / 100.0
            } else {
                println("Valor inválido! Digite novamente.")
            }
        }
    }

    val tanque = 42.0

    val alcoolWayne = lerPreco("Álcool - Wayne Oil: ")
    val gasolinaWayne = lerPreco("Gasolina - Wayne Oil: ")
    val alcoolStark = lerPreco("Álcool - Stark Petrol: ")
    val gasolinaStark = lerPreco("Gasolina - Stark Petrol: ")

    val opcoes = listOf(
        Combustivel("Wayne Oil", "Álcool", alcoolWayne, alcoolWayne * tanque),
        Combustivel("Wayne Oil", "Gasolina", gasolinaWayne, gasolinaWayne * tanque),
        Combustivel("Stark Petrol", "Álcool", alcoolStark, alcoolStark * tanque),
        Combustivel("Stark Petrol", "Gasolina", gasolinaStark, gasolinaStark * tanque)
    )

    println("\n--- TABELA ---")
    opcoes.forEach { println("${it.posto} - ${it.tipo}: R$ %.2f".format(it.custoTotal)) }

    val vantagemWayne = if (alcoolWayne <= gasolinaWayne * 0.70) "Álcool" else "Gasolina"
    val vantagemStark = if (alcoolStark <= gasolinaStark * 0.70) "Álcool" else "Gasolina"

    println("\nNo Wayne Oil, vale mais a pena abastecer com $vantagemWayne.")
    println("No Stark Petrol, vale mais a pena abastecer com $vantagemStark.")

    val melhor = opcoes.minByOrNull { it.custoTotal }!!
    println("\n>>> Melhor opção: ${melhor.posto} - ${melhor.tipo}, total = R$ %.2f".format(melhor.custoTotal))
}

fun calcularOrcamento(empresa: Empresa): Double {
    var valorBruto = empresa.valorPorAparelho * empresa.quantidadeAparelhos
    var valorComDesconto = valorBruto

    if (empresa.quantidadeAparelhos >= empresa.quantidadeMinimaDesconto) {
        val desconto = valorBruto * (empresa.percentualDesconto / 100.0)
        valorComDesconto -= desconto
    }

    if (valorComDesconto < empresa.valorMinimoIsencao) {
        valorComDesconto += empresa.taxaDeslocamento
    }

    return valorComDesconto
}


fun manutencaoDeAr() {
    val empresas = mutableListOf<Empresa>()

    do {
        println("Informe os dados da empresa:")

        print("Nome da empresa: ")
        val nome = readLine() ?: ""

        print("Valor por aparelho: ")
        val valorPorAparelho = readLine()?.toDoubleOrNull() ?: 0.0

        print("Quantidade de aparelhos: ")
        val quantidadeAparelhos = readLine()?.toIntOrNull() ?: 0

        print("Percentual de desconto (%): ")
        val percentualDesconto = readLine()?.toDoubleOrNull() ?: 0.0

        print("Quantidade mínima de aparelhos para desconto: ")
        val quantidadeMinima = readLine()?.toIntOrNull() ?: 0

        print("Valor mínimo para isenção da taxa de deslocamento: ")
        val valorMinimo = readLine()?.toDoubleOrNull() ?: 0.0

        print("Valor da taxa de deslocamento: ")
        val taxaDeslocamento = readLine()?.toDoubleOrNull() ?: 0.0

        val empresa = Empresa(
            nome,
            valorPorAparelho,
            quantidadeAparelhos,
            percentualDesconto,
            quantidadeMinima,
            valorMinimo,
            taxaDeslocamento
        )
        empresa.valorFinal = calcularOrcamento(empresa)

        empresas.add(empresa)

        println("O serviço de ${empresa.nome} custará R$ ${empresa.valorFinal}")

        print("Deseja informar novos dados? (S/N): ")
        val opcao = readLine()?.uppercase()

        if (opcao == "N") break

    } while (true)

    for (i in 0 until empresas.size - 1) {
        var menor = i
        for (j in i + 1 until empresas.size) {
            if (empresas[j].valorFinal < empresas[menor].valorFinal) {
                menor = j
            }
        }
        if (menor != i) {
            val temp = empresas[i]
            empresas[i] = empresas[menor]
            empresas[menor] = temp
        }
    }

    println("\nOrçamentos em ordem crescente:")
    empresas.forEach {
        println("${it.nome}: R$ ${it.valorFinal}")
    }

    val menorEmpresa = empresas.first()
    println("\nO orçamento de menor valor é o de ${menorEmpresa.nome} por R$ ${menorEmpresa.valorFinal}")
}

fun main() {
    val scanner = Scanner(System.`in`)
    login(scanner)
}