fun main(){
    var escolha = 0

    println("Escolha seu genero designado ao nascer\n" +
            "1- Feminino\n" +
            "2- Masculino\n" +
            "DIGITE O NUMERO REFERENTE A ESCOLHA: ")

    when(escolha){
        1->{
            println("Informe sua altura: ")
            val h = readLine()?.toDoubleOrNull()?:00.00
            var peso = (62.1 * h) - 44.7

            println("Seu peso ideal: $peso")
            return
        }
        2->{
            println("Informe sua altura: ")
            val h = readLine()?.toDoubleOrNull()?:00.00
            var peso = (72.7 * h) - 58

            println("Seu peso ideal: $peso")
            return
        }
        else -> {
            println("Informe uma alternativa valida!")
            return
        }
    }
}