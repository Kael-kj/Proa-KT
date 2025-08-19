fun main(){
    val frutas =  mutableListOf("Morango","Maçã","Uva","Laranja")
    while(frutas.isNotEmpty()){
        println("Sua lista de compras é: ${frutas.joinToString(",")}")
        println("Qual fruta deseja retirar da lista?\n")
        val entrada = readLine()?:""
        val frutaEncontrada = frutas.find{ it.equals(entrada, ignoreCase = true)}
        if(frutaEncontrada != null){
            frutas.remove(frutaEncontrada)
            println("A fruta $frutaEncontrada foi removida da lista")
        }else println("Fruta indisponível no nosso mercado")

        if (frutas.isEmpty()){
            println("Lista de compras finalizada")
        }

    }
}