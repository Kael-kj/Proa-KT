fun main(){
    var aprovados = 0

    while (true){
        print("Digite a primeira nota: ")
        val nota1 = readLine()?.toDoubleOrNull()?: 0.0
        print("Digite a primeira nota: ")
        val nota2 = readLine()?.toDoubleOrNull()?: 0.0
        val media = (nota1 + nota2) / 2
        println("Media final: $media")
        if (media >= 9.5){
            println("Aluno aprovado!")
            aprovados++
        }else {
            println("Aluno reprovado!")
        }

        print("Calcular a media de outro aluno? (S/N): ")
        val resposta = readLine()?.trim()?.uppercase()

        if(resposta != "S"){
            break
        }
    }
    println("Programa encerrado. Total de alunos aprovados: $aprovados")
}