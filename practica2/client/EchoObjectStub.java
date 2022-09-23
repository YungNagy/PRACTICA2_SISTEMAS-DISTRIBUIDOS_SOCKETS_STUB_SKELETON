
 // conectarse y construir socket
        //escribir y leer en socket
//desconectarse 
package client;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.EchoInterface;

public class EchoObjectStub implements EchoInterface{
    private Socket echoSocket = null;
    private PrintWriter os = null;
    private BufferedReader is = null;
    private String host = "localhost";
    private int port=7;
    private int output = 0;
    private boolean connected = false;

    public void setHostAndPort(String host, int port) {
        this.host= host; this.port =port;
    }
    
    public int pago(String cantidad_pagar,String tarjeta, String cvv)throws java.rmi.RemoteException
    {
        try {
            connect();
            } catch (IOException ex) 
                {
                Logger.getLogger(EchoObjectStub.class.getName()).log(Level.SEVERE, null, ex);
                }
        if (echoSocket != null && os != null && is != null) 
            {
            try {
                os.println(cantidad_pagar);// escribe en el socket la cantidad a pagar
                os.flush();// limpia el canal de comunicacion del socket
                
                os.println(tarjeta);// escribe en el socket el No. tarjeta
                os.flush();// limpia el canal de comunicacion del socket
                
                os.println(cvv);// escribe en el socket el cvv
                os.flush();// limpia el canal de comunicacion del socket
                
                output= Integer.parseInt(is.readLine()); // lee del socket
                } catch (IOException e) 
                {
                System.err.println("I/O Fallo al leer /escribir socket");
                throw new java.rmi.RemoteException("I/O failed in reading/writing socket");
                }
           }
           try {
               disconnect();
               } catch (IOException ex) 
                  {
                  Logger.getLogger(EchoObjectStub.class.getName()).log(Level.SEVERE, null, ex);
                  }
    return output;
    }


    private synchronized void connect() throws
    java.rmi.RemoteException, IOException {
    //EJERCICIO: Implemente el método connect, que aqui ya esta implementado
        echoSocket = new Socket(host, port);
        os=new PrintWriter(echoSocket.getOutputStream());
        is=new BufferedReader( new InputStreamReader(echoSocket.getInputStream()));
    }
    private synchronized void disconnect() throws IOException{
    //EJERCICIO: Implemente el método disconnect
        os.close();
        is.close();
        echoSocket.close();
    }
}