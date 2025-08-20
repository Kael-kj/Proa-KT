
import java.util.Scanner
import java.math.BigDecimal
import java.math.RoundingMode

/*
01- inventar nome
02- ao acessar exibir "Bem-vindo ao (nome do hotel)
03- ao acessar perguntar nome de usuario e uma senha. Nome n tem validaocao, senha tem q ser 2678
04- inicio usar switch/case para validar escolha do usuario
05- sempre q um usuario acessar dar boas vindas com nome do hotel e nome do usuario
06- sempre usar o nome do hotel em vez da palavra hotel
07- sempre q sair falar muito obrigado e ate logo nomedousuario
08- para cada escolha feita pelo usuario deve ter uma funcao diferente
09- o hotel tem 20 quartos e ao iniciar o programa todos estao livres
10- Atualize o menu de opc e a fun inicio com todas as opcoes de programas abaixo
11- Atualize a fun erro com as novas opc do meunu.
12- sempre q encerrar qualquer fun deve ir para o menu inicial
 */

/*
home page{
senha 2678, ate 3 tentativas por acesso se passar volta ao menu de login e notifica tentativas excedidas, obriga mudar o usuario
ter um log para (Login_ok,Login_fail,pre_reserva,confirma_reserva,cancela_reserva,erro_validacao)
manter o map dos 20 quartos ativos enquanto o programa estar ativo
crie uma função pura chamada resumirOcupacao(quartos[]) que:

Recebe uma lista/array de quartos (quartos[]).

Retorna uma String que descreve a ocupação (se estão livres, ocupados etc).

Essa string será usada tanto no menu quanto em outros módulos do sistema (assim você não precisa duplicar código).

"Função pura" quer dizer que a função não deve depender de variáveis externas nem modificar nada fora dela. Apenas entra dados → sai resultado.

}
registro 1 {
--cadastro de quarto por diaria, com valor auto de 7,50 sem desconto, 5-9 dias 8%,10-30 dias 15%, mostrar valor
deposito de garantia 30%(arredondar para centavos em regras bancarias), exibit memoria de calculo, minimo 3 caracteries por nome
qualquer erro durante a pre-reserva libera o quarto auto antes de voltar ao menu
 quantos dias ficara( realizar tratativa de erro para validar info)
valor n negativo
dias n negativo
dias n maior q 30
caso de info invalida "Valor Invalido" e retornar ao inicio
--nome do hospede da diaria e codigo aleatorio gerado pelo programa
--numero do quarto de 1 a 20
caso quarto ja esteja ocupado informar "Quarto ja esta ocupado" e oferecer outro quarto
--perguntar se confirma a reserva e mostrar todas as informacoes acima, voltar para ao menu inicial
 */
data class Hospedagem(
    val nome: String,
    val dias: Int,
    val valorDiaria: BigDecimal,
    val subtotalBruto: BigDecimal,
    val descontoPercent: Int,
    val descontoValor: BigDecimal,
    val totalComDesconto: BigDecimal,
    val depositoGarantia: BigDecimal,
    val saldoNaSaida: BigDecimal
)

object UserSession{
    var currentUser : String? = null
    val loggedIn : Boolean
        get() = currentUser != null

    fun login(user: String){
        currentUser = user
    }
    fun logout(){
        currentUser = null
    }

}


fun login(scanner: Scanner) {
    println("=== BEM-VINDO AO TERABITHIA ===")

    val correctPassword = "2678"
    var loginSuccess = false

    while (!loginSuccess) {
        print("Digite o nome de usuário: ")
        val inputUsername = scanner.nextLine()

        var attempts = 0
        var authenticated = false

        while (attempts < 3 && !authenticated) {
            print("Digite a senha: ")
            val inputPassword = scanner.nextLine()
            if (inputPassword == correctPassword) {
                UserSession.login(inputUsername)
                println("Login realizado com sucesso! Bem-vindo ao Hotel Terabithia, ${UserSession.currentUser}. E um imenso prazer ter voce por aqui!")
                authenticated = true
                loginSuccess = true
                homePage(scanner)
            } else {
                attempts++
                if (attempts < 3) {
                    println("Senha incorreta. Tentativas restantes: ${3 - attempts}")
                }
            }
        }

        if (!authenticated) {
            println("Tentativas excedidas para o usuário \"$inputUsername\". Tente novamente com outro usuário.\n")
        }
    }
}

fun cadastro(scanner: Scanner): Hospedagem {

    //half_even = arredondamento bancario
    val HALF_EVEN = RoundingMode.HALF_EVEN
    val valorDiaria = BigDecimal("7.50")
    // formata com 2 casas decimas de forma half_even
    fun fmt(bd: BigDecimal): String =
        "R$ " + bd.setScale(2, HALF_EVEN).toPlainString().replace('.', ',')
    //Formata com 4 casas decimais para a log da conta
    fun fmt4(bd: BigDecimal): String =
        bd.setScale(4, HALF_EVEN).toPlainString().replace('.', ',')

    // Entrada e validações
    val nome: String = run {
        while (true) {
            println("Informe o nome do hóspede (mínimo 3 caracteres, sem números):")
            val n = scanner.nextLine().trim()
            if (n.length < 3 || n.any { it.isDigit() }) {
                println("Nome inválido! Tente novamente.")
                continue
            }
            return@run n
        }
        ""
    }

    val dias: Int = run {
        while (true) {
            println("Informe a quantidade de dias (1 a 30):")
            val entrada = scanner.nextLine().trim()
            val d = entrada.toIntOrNull()
            if (d == null || d !in 1..30) {
                println("Valor inválido! Deve ser um número entre 1 e 30.")
                continue
            }
            return@run d
        }
        0
    }

    // Regras de desconto
    val descontoPercent = when (dias) {
        in 5..9 -> 8
        in 10..30 -> 15
        else -> 0
    }
    val descRate = BigDecimal(descontoPercent).divide(BigDecimal("100"))

    // Cálculos (todos com HALF_EVEN para 2 casas)
    val subtotal = valorDiaria.multiply(BigDecimal(dias)).setScale(2, HALF_EVEN)
    val descontoValor = subtotal.multiply(descRate).setScale(2, HALF_EVEN)
    val totalComDesc = subtotal.subtract(descontoValor).setScale(2, HALF_EVEN)

    // Depósito de garantia 30% (mostrar memória de arredondamento)
    val depositoRaw = totalComDesc.multiply(BigDecimal("0.30")) // sem forçar 2 casas ainda
    val deposito = depositoRaw.setScale(2, HALF_EVEN)
    val saldo = totalComDesc.subtract(deposito).setScale(2, HALF_EVEN)

    // Exibição — memória de cálculo
    println("\n=== Cadastro concluído ===")
    println("Hóspede: $nome")
    println("Dias: $dias")
    println("Diária: ${fmt(valorDiaria)}")
    println("Subtotal bruto (${fmt(valorDiaria)} x $dias) = ${fmt(subtotal)}")

    if (descontoPercent > 0) {
        println("Desconto $descontoPercent% (${fmt(subtotal)} x ${descontoPercent}% ) = ${fmt(descontoValor)}")
        println("Total com desconto: ${fmt(subtotal)} - ${fmt(descontoValor)} = ${fmt(totalComDesc)}")
    } else {
        println("Sem desconto aplicado (menos de 5 dias).")
        println("Total: ${fmt(totalComDesc)}")
    }

    // Memória do depósito com arredondamento bancário
    println("Depósito de garantia 30% (memória): ${fmt(totalComDesc)} x 0,30 = ${fmt4(depositoRaw)} → arredondado (bancário) = ${fmt(deposito)}")
    println("Saldo a pagar na saída: ${fmt(totalComDesc)} - ${fmt(deposito)} = ${fmt(saldo)}\n")

    return Hospedagem(
        nome = nome,
        dias = dias,
        valorDiaria = valorDiaria.setScale(2, HALF_EVEN),
        subtotalBruto = subtotal,
        descontoPercent = descontoPercent,
        descontoValor = descontoValor,
        totalComDesconto = totalComDesc,
        depositoGarantia = deposito,
        saldoNaSaida = saldo
    )
}

fun homePage(scanner: Scanner) {
    println("\n=== Home Page ===")
    println("Usuário logado: ${UserSession.currentUser}")
    println("1- Cadastro")
    println("2 - Logout")
    println("3 - Sair do programa")

}

fun main() {
    val scanner = Scanner(System.`in`)
    login(scanner)
}





































