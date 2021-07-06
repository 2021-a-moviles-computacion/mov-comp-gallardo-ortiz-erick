import java.io.File
import javax.swing.JFrame
import javax.swing.JOptionPane
import kotlin.math.absoluteValue


fun main(args: Array<String>) {
    menu()
}
fun menu(){
    val je=J_E()
    val seleccion = when(CRUD()){
        0 -> crear(je)
        1 -> buscar(cedula = JOptionPane.showInputDialog( "Introduzca la cedula"),"gui")
        2 -> editar(cedula = JOptionPane.showInputDialog( "Introduzca la cedula"))
        3 -> eliminar(cedula = JOptionPane.showInputDialog( "Introduzca la cedula"))
        else -> "opcion invalida"

    }
    print(seleccion)
    return menu()
}
fun J_E():Int{
    val options = arrayOf<Any>("Jugador", "Entrenador")
    val je = JOptionPane.showOptionDialog(
        null, "Selecciona el rol", "Sistema",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
        null, options, options[0]
    )
    return je.absoluteValue
}
fun CRUD():Int{
    val options = arrayOf<Any>("Crear", "Buscar", "Editar","Eliminar")
    val opt = JOptionPane.showOptionDialog(
        null, "Seleccione lo que desee hacer", "Sistema",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
        null, options, options[0]
    )
    return opt.absoluteValue
}
fun crear(firstpass: Int){
    var frame = JFrame()
    val nombre = JOptionPane.showInputDialog(frame, "Introduzca el nombre")
    val apellido = JOptionPane.showInputDialog(frame, "Introduzca el apellido")
    var cedula = JOptionPane.showInputDialog(frame, "Introduzca la cedula")
    while(buscar(cedula)==true){
        JOptionPane.showMessageDialog(frame,"Cedula existente.","Alerta",JOptionPane.WARNING_MESSAGE);
        cedula = JOptionPane.showInputDialog(frame, "Introduzca la cedula")
    }
    var entrenador: String =""
    var rol: String
    if(firstpass==1){
        entrenador="1"
        rol="entrenador"
    }else{
        rol="jugador"
        entrenador = JOptionPane.showInputDialog(frame, "Introduzca la cedula de su entrenador")
    }
    val final = cedula+":"+entrenador+":"+nombre+":"+apellido+":"+rol+";"
    escribir_archivo(final)
}
fun buscar(cedula:String):Boolean{
    var datos=leer_archivo()
    if(datos.indexOf(cedula)==-1){
        println("no existe la cedula")
        return false
    }else{
        println("cedula encontrada")
        return true
    }
}
fun buscar(cedula:String,id: String){
    var datos=leer_archivo()
    var frame = JFrame()
    if(datos.indexOf(cedula)==-1){
        JOptionPane.showMessageDialog(frame,"No se encontro la cedula.","Alerta",JOptionPane.WARNING_MESSAGE);
    }else{
        JOptionPane.showMessageDialog(frame,"Cedula encontrada.","",JOptionPane.INFORMATION_MESSAGE);
        var result = datos.split(";").map { it.trim() }
        result.forEach {
            if(it.indexOf(cedula)!=-1){
                var result2 = it.split(":").map { it.trim() }
                var str:String=""
                result2.forEach {
                    str+=" "+it.toString()
                }
                JOptionPane.showMessageDialog(frame,""+str,"",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
fun editar(cedula: String){
    var datos=leer_archivo()
    var frame = JFrame()
    var str: String=""
    var aux: String=""
    if(datos.indexOf(cedula)==-1){
        JOptionPane.showMessageDialog(frame,"No se encontro la cedula.","Alerta",JOptionPane.WARNING_MESSAGE);
    }else{
        JOptionPane.showMessageDialog(frame,"Cedula encontrada.","",JOptionPane.INFORMATION_MESSAGE);
        var result = datos.split(";").map { it.trim() }

        result.forEach {
            if(it.indexOf(cedula)!=-1){
                aux=it+";"
                var result2 = it.split((":")).map{it.trim()}
                result2.forEach {
                    if(it.indexOf("jugador")!=-1||it.indexOf("entrenador")!=-1){
                        if(it.indexOf("jugador")!=-1){
                            str+="entrenador;"
                        }else{
                            str+="jugador;"
                        }
                    }else{
                        str+=it+":"
                    }

                }
                print("\nel string a mandar es:\n"+str)
                JOptionPane.showMessageDialog(frame,"Persona editada de roll "+cedula,"",JOptionPane.INFORMATION_MESSAGE);
            }else{
                str+=it
            }
        }
        print("\nelstring a enviar eess\n"+str+"\n\n")
        val archivo = File("archivo.txt")
        if (!archivo.exists()) {
            print("No existe el archivo")
            archivo.createNewFile();
        }
        archivo.writeText(str)
    }
}
fun eliminar(cedula: String){
    var datos=leer_archivo()
    var frame = JFrame()
    var str: String=""
    if(datos.indexOf(cedula)==-1){
        JOptionPane.showMessageDialog(frame,"No se encontro la cedula.","Alerta",JOptionPane.WARNING_MESSAGE);
    }else{
        JOptionPane.showMessageDialog(frame,"Cedula encontrada.","",JOptionPane.INFORMATION_MESSAGE);
        var result = datos.split(";").map { it.trim() }

        result.forEach {
            if(it.indexOf(cedula)!=-1){
                JOptionPane.showMessageDialog(frame,"Persona Eliminada "+cedula,"",JOptionPane.INFORMATION_MESSAGE);
            }else{
                str+=it
            }
        }
        print("\nelstring a enviar eess\n"+str+"\n\n")
        val archivo = File("archivo.txt")
        if (!archivo.exists()) {
            print("No existe el archivo")
            archivo.createNewFile();
        }
        archivo.writeText(str)
    }
}
fun leer_archivo():String{
    var archivo = File("archivo.txt")
    if (!archivo.exists()) {
        print("No existe el archivo")
        archivo.createNewFile();
    }
    print("lectura del archivo "+archivo.readText())
    return archivo.readText()
}
fun escribir_archivo(nuevo: String){
    val archivo = File("archivo.txt")
    if (!archivo.exists()) {
        print("No existe el archivo")
        archivo.createNewFile();
    }
    val string = leer_archivo()
    //val string =""
    archivo.writeText(string+nuevo)
}
fun eliminar_archivo(cedula: String){
    val archivo = File("archivo.txt")
    if (!archivo.exists()) {
        print("No existe el archivo")
        archivo.createNewFile();
    }
    var sin_mod = archivo.readText()
}
fun separador(input: String,entrenador: String){
    var result = input.split(";").map { it.trim() }
    result.forEach {
        var result2 = it.split(":").map { it.trim() }
        result2.forEach {
            println("palabra: " + it)
        }
    }
}