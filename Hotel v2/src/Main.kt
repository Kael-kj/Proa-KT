import java.security.interfaces.DSAParams
import java.util.Scanner

/*
1-pagina de login
2-home page
3-cadastro de cliente
diaria
quarto
desconto
armazenamento de conta
30% de entrada
cofirmacao de cadastro
retorno home page
 */



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
    val quarto: Int
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

val quartos = Array(20) {false}
val hospedagens = mutableListOf<Hospedagem>()
var contadorCadastro = 1

fun login(scanner : Scanner){
    val senhaCorreta = "2678"

    println("=== HOTEL TERABITHIA ===")

    while (true) {

        print("Digite o nome de usuário: ")
        val user = scanner.nextLine()
        if( user in UserSession.usuariosUsados){
            println("Usuário ja foi utilizado. Tente outro nome de usuário para entrar!")
            continue
        }

        var tentativas = 0
        var autenticado = false
        while (tentativas < 3) {
            print("Digite a senha:")
            val senha = scanner.nextLine()
            if (senha == senhaCorreta) {
                UserSession.login(user)
                println("Login realizado com sucesso! Bem-vindo ao Hotel Terabithia, ${UserSession.currentUser}. E um imenso prazer ter voce por aqui!")
                homepage(scanner)
                autenticado = true
            } else {
                tentativas++
                println("Senha incorreta. Tentativas restantes: ${3 - tentativas}")
            }
        }
        if (!autenticado){
            println("Tentativas excedidas. Troque o usuário para entrar!")
            }
    }
}



fun homepage(scanner: Scanner){
    println("\n=== Home Page ===")
    println("Usuário logado: ${UserSession.currentUser}")
    println("1- Cadastro")
    println("2 - Logout")
    println("3 - Sair do programa")
    val escolha = scanner.nextLine()

    when(escolha){
        "1"->{
            cadastro(scanner)
            homepage(scanner)
        }
        "2"->{
            print("Encerrando login...")
            login(scanner)
        }
        else -> println("Opção inválida!")
    }

}



fun cadastro(scanner: Scanner) {
    print("Nome do hóspede: ")
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
                .sortedBy { kotlin.math.abs(it - quartoEscolhido) }
                .take(3)

            if (sugestoes.isEmpty()) {
                println("Todos os quartos estão ocupados!")
                return
            } else {
                println("Sugestões de quartos próximos: ${sugestoes.joinToString(", ")}")
            }
        } else {
            break
        }
    }

    val subtotal = valorDiaria * dias
    val descontoPercent = when (dias) {
        in 5..9 -> 8
        in 10..30 -> 15
        else -> 0
    }


    val descontoValor: Double = subtotal * descontoPercent / 100
    val totalSemTaxa: Double = subtotal - descontoValor
    val taxaEstado = 7.50
    val total: Double = (subtotal - descontoValor) + taxaEstado
    val deposito: Double = total * 0.3
    val saldo: Double = total - deposito

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
            quarto = quartoEscolhido
        )
        hospedagens.add(hospedagem)
        println("Cadastro confirmado! Número de cadastro: ${hospedagem.id}\n")
    } else {
        println("Cadastro cancelado.\n")
    }
}



fun main(){
    val scanner = Scanner(System.`in`)
    login(scanner)
}