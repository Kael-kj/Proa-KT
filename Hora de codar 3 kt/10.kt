fun main(){
    var contador1 = 0
    var contador2 = 0
    for(i in 1 .. 10){
        var nmr : Int
        while (true){
            print("Digite o numero $i : ")
            nmr = readLine()?.toIntOrNull()?:0
            if(nmr in 24.. 42){
                contador1 ++
                break
            }else{
                contador2++
                break
            }
        }
    }
    print("$contador1 estao entre 24 e 42 e $contador2 estao fora deste intervalo!")
}