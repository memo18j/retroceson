/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrocesoenn;

import java.net.Socket;
import java.io.*;
/**
 *192.168.1.28
 * @author ION Xtreme
 */
public class Sokets_Cliente{
    
Socket cliente;
FromCliente form;
    
    public Sokets_Cliente(FromCliente form) {
    
        this.form=form;
        
    }
    
    public void conectar() throws IOException{
       
       try {     
        cliente = new Socket(form.txtIP.getText(), Integer.parseInt(form.txtPuerto.getText()));
         try (ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream())) {
               enviar.writeObject(form.txtMensajesRecibidos.getText());
               form.txtMensajesRecibidos.setText("");
              
           }
       }catch(NumberFormatException | IOException e){
           System.out.println("No se pudo enviar");
           
       }   
    
}
    public void conectarDañado() throws IOException{
       
       try {     
        cliente = new Socket(form.txtIP.getText(), Integer.parseInt(form.txtPuerto.getText()));
        String dañada =form.txtMensajesRecibidos.getText().replace('a','/');
          
         try (ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream())) {
               enviar.writeObject(dañada);
               form.txtMensajesRecibidos.setText("");
              
           }
       }catch(NumberFormatException | IOException e){
           System.out.println("No se pudo enviar");
           
       }   
    
}
    
    public void responder() throws IOException{
       
       try {     
        cliente = new Socket(form.txtIP.getText(), Integer.parseInt(form.txtPuerto.getText()));
         try (ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream())) {
               enviar.writeObject("true");
               
              
           }
       }catch(NumberFormatException | IOException e){
           System.out.println("No se pudo enviar");
           
       }   
    
}
    public void responder2(int i) throws IOException{
       
       try {     
        cliente = new Socket(form.txtIP.getText(), Integer.parseInt(form.txtPuerto.getText()));
         try (ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream())) {
               enviar.writeObject("reenviando-"+i);
               
              
           }
       }catch(NumberFormatException | IOException e){
           System.out.println("No se pudo enviar");
           
       }   
    
}
        public void responderTerminar() throws IOException{
       
       try {     
        cliente = new Socket(form.txtIP.getText(), Integer.parseInt(form.txtPuerto.getText()));
         try (ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream())) {
               enviar.writeObject("Terminado");
               
              
           }
       }catch(NumberFormatException | IOException e){
           System.out.println("No se pudo enviar");
           
       }   
    
}
    public void respondermal() throws IOException{
       
       try {     
        cliente = new Socket(form.txtIP.getText(), Integer.parseInt(form.txtPuerto.getText()));
         try (ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream())) {
               enviar.writeObject("false");
               
              
           }
       }catch(NumberFormatException | IOException e){
           System.out.println("No se pudo enviar");
           
       }   
    
}
    public void enviarMensaje(){
        try (ObjectOutputStream enviar = new ObjectOutputStream(cliente.getOutputStream())) {
               enviar.writeObject(form.txtMensajesRecibidos.getText());
           }catch(NumberFormatException | IOException e){
           System.out.println("No mensaje");
           
       }   
    }
}
