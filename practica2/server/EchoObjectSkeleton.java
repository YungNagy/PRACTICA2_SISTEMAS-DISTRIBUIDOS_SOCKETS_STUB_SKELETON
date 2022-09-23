
package server;
import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;
import rmi.EchoInterface;
public class EchoObjectSkeleton implements EchoInterface {
    String myURL="localhost";
    //Constructor de la clase EchoObjectSkeleton
    public EchoObjectSkeleton()
    {
        try {
// obtengo el nombre del equipo donde estoy ejecutando y lo guardo en la propiedad MyURL
            myURL=InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) 
               {
                // si no pude conocer el nombre del equipo, mantengo el nombre localhost para MyURL
               myURL="localhost";
              }
    }
// el Metodo Echo que es la implementacion de la interfaz EchoInterface
    public int pago(String cantidad_pagar,String tarjeta,String cvv) throws java.rmi.RemoteException 
    {
        int saldo=50000;
        int status=0;
        try {
            if(tarjeta.length()>=13 && tarjeta.length()<=18){
                System.out.println("Tarjeta Reconocida");
                if(cvv.length()==3){
                    System.out.println("CVV confirmado");
                    if(saldo>=Integer.parseInt(cantidad_pagar)){
                        System.out.println("Pago Exitoso");
                        status=1;
                    }else{
                       System.out.println("Saldo Insuficiente, Pago Declinado");     
                    }
                }else{
                    System.out.println("Cvv incorrecto, Pago Declinado");
                }
            }else{
                System.out.println("Tarjeta Incorrecta, Pago Declinado");
            }

        } catch (Exception e) {
            
        }
        return status;
    }
   }
