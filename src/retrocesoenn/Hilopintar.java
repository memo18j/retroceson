/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrocesoenn;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Gabriel Contreras
 */
public class Hilopintar  implements Runnable {

    FromCliente form;
    String mensaje="";
    Sokets_Cliente cliente ;
    
    public Hilopintar(FromCliente form, String mensaje,Sokets_Cliente cliente ) {
        this.form=form;
        this.mensaje=mensaje;
        this.cliente=cliente;
    }
    
    

    @Override
    public void run() {
        
        String caracter="/";
        int save=0;
        boolean enconrado=false;
        boolean reenvio=false;
        form.limpiar();
        int vertical=0;
        //recibe un array de string del longitud = a la cantidad de cpus q tenga la pc q contiene el consumo para cada cpu
        form.caracteres = mensaje.toCharArray();
        if(form.caracteres!=null){
          JTextField [] campos = new JTextField[form.caracteres.length];
        //creo un array de los jTextField del tamaño del array anterior
       int x=1;
       int x2=1;
       int x3=1;
       int y=352;
        for (int i = 0; i < form.caracteres.length; i++) {
            String aux = String.valueOf(form.caracteres[i]);
            
            if (reenvio){
               if (aux.equals("/")){
                    aux=aux.replace("/", "a");
                }
            }
            if (!enconrado){
            if (aux.equals(caracter)){
            enconrado=true;
            save=i;
            campos[i]= new JTextField();
            campos[i].setText("-");
            campos[i].setBackground(Color.red);
            }else {
            campos[i]= new JTextField();
            campos[i].setText(aux);
            campos[i].setBackground(Color.green);
            }
            }else {
            campos[i]= new JTextField();
            campos[i].setText("-");
            campos[i].setBackground(Color.red); 
            }
            if(vertical<22){
                campos[i].setBounds(29*(x),y, 30, 30);
                x=x+1;
            }if (vertical>23&&vertical<46){
               
                y=390;
                campos[i].setBounds(29*(x2),y, 30, 30);
                 x2=x2+1;
            }if (vertical>47&&vertical<70){
               
                y=428;
                campos[i].setBounds(29*(x3),y, 30, 30);
                 x3=x3+1;
            }
            vertical++;
            campos[i].setBorder(form.border);
            campos[i].setHorizontalAlignment(SwingConstants.CENTER);                
           
            form.jpanel.add(campos[i]);
            form.jpanel.repaint();
            
             if (!enconrado){
            try {
                  cliente.responder();
                   Thread.sleep(1000);
              } catch (IOException ex) {
                  Logger.getLogger(Hilopintar.class.getName()).log(Level.SEVERE, null, ex);
              } catch (InterruptedException ex) {
                    Logger.getLogger(Hilopintar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else {
                 try {
                  cliente.respondermal();
                   Thread.sleep(1000);
              } catch (IOException ex) {
                  Logger.getLogger(Hilopintar.class.getName()).log(Level.SEVERE, null, ex);
              } catch (InterruptedException ex) {
                    Logger.getLogger(Hilopintar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             if (i==form.caracteres.length-1 && enconrado){
                 try {
                  cliente.responder2(save);
                  i=save-1;
                  reenvio=true;
                  enconrado=false;
                     
                  Thread.sleep(1000);
              } catch (IOException ex) {
                  Logger.getLogger(Hilopintar.class.getName()).log(Level.SEVERE, null, ex);
              } catch (InterruptedException ex) {
                    Logger.getLogger(Hilopintar.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
             if (i==form.caracteres.length-1 && !enconrado){
                 try {
                  
                  cliente.responderTerminar();
                  Thread.sleep(1000);
              } catch (IOException ex) {
                  Logger.getLogger(Hilopintar.class.getName()).log(Level.SEVERE, null, ex);
              } catch (InterruptedException ex) {
                    Logger.getLogger(Hilopintar.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
            
        }
          
        }
        
    }
    
}
