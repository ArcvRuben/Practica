import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

    ServerSocket servidor;
    Socket cliente;
    Map<String, String> map;

    public Servidor() {}

    public void cargarDiccionario() {
        map = new HashMap<>();
        map.put("java", "lenguaje de programación");
        map.put("algoritmo", "es básicamente un conjunto de pasos para una determinada tarea.");
        map.put("lenguaje", "conjunto de definiciones utilizadas para hacer un texto que la computadora sea capaz de entender");
        map.put("codigo", "conjunto de palabras o símbolos que contienen instrucciones para la computadora.");
        map.put("bug", "problema en el código que hace que no ejecute correctamente su función.");
        map.put("frameworks", "colecciones de herramientas, componentes y soluciones que puedes encontrar en un mismo paquete.");
        map.put("queries", "solicitud de información y/o datos para una base de datos.");
        map.put("backup", "copia de archivos en otro dispositivo para guardar información.");
        map.put("bandwidth", "ancho de banda. Dice cuánto se puede viajar en una red.");
        map.put("bit", "abreviatura de binary digit, es decir, representa solo un valor binario");
    }

    public void iniciarsocketServidor(int puerto) {
        try {
            cargarDiccionario();
            System.out.println("Esperando conexión en el puerto: " + puerto);
            servidor = new ServerSocket(puerto);
            cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress());

            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            while (true) {
               
                String palabra = entrada.readUTF();
                System.out.println("Cliente: " + palabra);

                String definicion = map.getOrDefault(palabra, "La palabra no se encontró");
                salida.writeUTF(definicion);

                if (!definicion.equals("La palabra no se encontró")) {
                    break;
                }
            }

            cliente.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        servidor.iniciarsocketServidor(6789);
    }
}