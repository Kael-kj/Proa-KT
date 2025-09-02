import java.util.Scanner
import kotlin.math.abs
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

val quartos = Array(20) { false }
val hospedagens = mutableListOf<Hospedagem>()
val listaHospedes = mutableListOf<String>()
var contadorCadastro = 1

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
    println("\n=== Home Page ===")
    println("Usuário logado: ${UserSession.currentUser}")
    println("1- Cadastro")
    println("2- Gerenciar hóspedes")
    println("3- Logout")
    println("4- Sair do programa")
    println("5- Pesquisar hospedagens")
    val escolha = scanner.nextLine()

    when (escolha) {
        "1" -> {
            cadastro(scanner)
            homepage(scanner)
        }
        "2" -> {
            cadastrarHospedes(scanner)
            homepage(scanner)
        }
        "3" -> {
            println("Encerrando login...")
            login(scanner)
        }
        "4" -> {
            println("Saindo do programa...")
            exitProcess(0)
        }
        "5" -> {
            pesquisarHospedagem(scanner)
            homepage(scanner)
        }
        "6"->{
            cadastrarEvento(scanner)
            homepage(scanner)
        }
        else -> {
            println("Opção inválida!")
            homepage(scanner)
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



fun cadastrarHospedes(scanner: Scanner) {
    while (true) {
        println("""
            Cadastro de Hóspedes
            1. Cadastrar
            2. Pesquisar
            3. Voltar
        """.trimIndent())

        when (scanner.nextLine()) {
            "1" -> {
                print("Nome do hóspede: ")
                val novo = scanner.nextLine()
                listaHospedes.add(novo)
                println("$novo cadastrado com sucesso!")
            }
            "2" -> {
                print("Pesquisar hóspede: ")
                val nome = scanner.nextLine()
                val encontrados = listaHospedes.filter { it.contains(nome, ignoreCase = true) }
                if (encontrados.isEmpty()) println("Nenhum hóspede encontrado.")
                else println("Encontrado(s): ${encontrados.joinToString(", ")}")
            }
            "3" -> return
            else -> println("Opção inválida!")
        }
    }
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





fun main() {
    val scanner = Scanner(System.`in`)
    login(scanner)
}