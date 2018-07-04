/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrocesoenn;

import java.awt.Color;
import java.net.ServerSocket;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 *
 * @author ION Xtreme
 */
public class Sokets_Server implements Runnable{
    
    ServerSocket servidor;
    String puerto;
    Socket clienteNuevo;
    FromCliente serverFrom;

    public Sokets_Server(FromCliente serverFrom) {
    
        this.serverFrom=serverFrom;
        
    }
    
        public void iniciarServidor(String puerto) throws IOException{
        
            this.puerto=puerto;
            Thread hilo =  new Thread(this);
            hilo.start();
            
        } 

    @Override
    public void run() {
        
        try {
            this.servidor= new ServerSocket(Integer.parseInt(puerto));
            System.out.println("En linea");
           
            while (true) {                
               
        clienteNuevo = servidor.accept();
        System.err.println("llego usuario");
        ObjectInputStream recibir = new ObjectInputStream(clienteNuevo.getInputStream());
        System.out.println("mensaje");
        String  mensaje = recibir.readObject().toString();
        
        if (!mensaje.isEmpty()){
            
            if (mensaje.equals("true")){
                this.serverFrom.pintar(mensaje);
                 this.serverFrom.lbError.setText("!! Exito");
            }else if (mensaje.equals("false")){
                this.serverFrom.pintar(mensaje);
                this.serverFrom.lbError.setText("!! Error");
            }else if (mensaje.equals("Terminado")){
                this.serverFrom.estados();
                this.serverFrom.lbError.setText("!! Terminado !!");
            }else if (mensaje.split("-")[0].equals("reenviando")){
                int pos = Integer.parseInt(mensaje.split("-")[1]);
                this.serverFrom.conteo=(23+pos);
                this.serverFrom.lbError.setText("Reenviando");
            }else {
                this.serverFrom.llenar(mensaje);
            }
        
        }
        
        clienteNuevo.close();
        }
            
        } catch (IOException ex) {
            Logger.getLogger(Sokets_Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("No hay mensaje");;
        }
        
    }
        
        
}
