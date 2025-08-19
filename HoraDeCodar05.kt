import java.util.Locale
import java.util.Locale.getDefault
import kotlin.system.exitProcess

var saldo = 150.00

fun main(){
    inicio()
}
fun inicio(){
    println("Escolha uma opção:\n 1. Ver saldo\n 2. Fazer depósito\n 3. Fazer saque\n 4. Sair\n")
    val entrada = readLine()?.toIntOrNull()
    val caixa = when(entrada){
        1 -> verSaldo()
        2 -> fazerDeposito()
        3 -> fazerSaque()
        4 -> sair()
        else -> {println("Opção inválida")
            inicio()
        }
    }
}
fun verSaldo(){
    println("Seu saldo é: $saldo")
    inicio()
}
fun fazerDeposito(){
    println("Qual o valor do depósito? ")
    val deposito = readLine()?.toIntOrNull()

    if(deposito == null){
        println("Informe um valor válido!")
        fazerDeposito()
    }else saldo += deposito
    verSaldo()
}
fun fazerSaque(){
    println("Qual o valor do saque?")
    val saque = readLine()?.toIntOrNull()

    if((saque == null) || (saque > saldo)){
        println("Valor inválido ou saldo insuficiente")
        fazerSaque()
    }else saldo -= saque
    verSaldo()
}
fun sair(){
    print("Você deseja sair? (S/N) ")
    val confirma = readLine()?.uppercase(getDefault())

    when (confirma) {
        "S" -> exitProcess(0)
        "N" -> inicio()
        else -> sair()
    }
}