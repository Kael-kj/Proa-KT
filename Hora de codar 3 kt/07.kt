fun main(){
    val notas = mutableListOf<Double>()

    for (i in 1 .. 6){
        var nota : Double
        while (true){
            print("Digite a nota $i (0 a 10): ")
            nota = readLine()?.toDoubleOrNull()?: -1.0
            if(nota in 0.0.. 10.0){
                break
            }else{
                println("Valor invalido! Digite uma nota entre 0 a 10.")
            }
        }
        notas.add(nota)
    }
    val media = notas.average()
    println("\nNotas do aluno $notas")
    println("Media final: $media")
}