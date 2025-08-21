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
        else -> {
            println("Opção inválida!")
            homepage(scanner)
        }
    }
}

fun cadastro(scanner: Scanner) {
    print("Nome do hóspede principal: ")
    val nome = scanner.nextLine()

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

    val subtotal = valorDiaria * dias
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

    println("\n--- RESUMO ---")
    println("Hóspede: $nome")
    println("Dias: $dias")
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
            nome = nome,
            dias = dias,
            valorDiaria = valorDiaria,
            subtotal = subtotal,
            descontoPercent = descontoPercent,
            descontoValor = descontoValor,
            total = total,
            deposito = deposito,
            saldo = saldo,
            quarto = quartoEscolhido,
            listaHospedes = mutableListOf(nome)
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

fun main() {
    val scanner = Scanner(System.`in`)
    login(scanner)
}
