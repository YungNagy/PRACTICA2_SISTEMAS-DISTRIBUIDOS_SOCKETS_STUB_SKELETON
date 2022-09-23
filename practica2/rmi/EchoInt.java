
package rmi;
//Interfaz de tipo remota, cabe resaltar que
// solo tiene el metodo echo ( no hya instrucciones) 
public interface EchoInt extends java.rmi.Remote 
{
    public String pago(String cantidad_pagar,String tarjeta, String cvv)throws java.rmi.RemoteException;
}
