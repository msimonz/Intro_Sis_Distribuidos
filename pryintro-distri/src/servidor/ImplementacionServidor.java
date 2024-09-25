package servidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor{
    public ImplementacionServidor() throws RemoteException{
        
    }
    public void creacionMatriz() throws RemoteException{
        System.out.println("Hola mundo");
    }
    public void filasMatriz() throws RemoteException{
        System.out.println("Hola mundo");
    }
    public void columnasMatriz() throws RemoteException{
        System.out.println("Hola mundo");
    }
}