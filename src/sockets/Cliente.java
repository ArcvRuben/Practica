package sockets;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente {

    public Socket cliente;
    public int puerto = 6789;
    public DataOutputStream salida;
    public DataInputStream entrada;

    public DataOutputStream getSalida() {
        return salida;
    }

    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public void IniciarPSocketCliente() {
        try {
            Scanner leer = new Scanner(System.in); 
            this.cliente = new Socket("127.0.0.1", puerto);
            salida = new DataOutputStream(this.cliente.getOutputStream());
            entrada = new DataInputStream(this.cliente.getInputStream());

            while (true) {
                System.out.print("Ingrese una palabra: ");
                String palabra = leer.nextLine().trim().toLowerCase();

                if (palabra.isEmpty()) {
                    System.out.println("Ingrese una palabra válida.");
                    continue; 
                }

                salida.writeUTF(palabra); 

                String respuesta = entrada.readUTF();
                System.out.println("Servidor: " + respuesta);

                if (!respuesta.contains("no se encontró")) {
                    break;
                }
            }

            leer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Cliente clie = new Cliente();
        clie.IniciarPSocketCliente();
    }
}
