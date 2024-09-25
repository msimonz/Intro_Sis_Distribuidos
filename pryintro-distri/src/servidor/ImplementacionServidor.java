import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor{
    public ImplementacionServidor() throws RemoteException{
        
    }
    public void hola() throws RemoteException{
        System.out.println("Hola mundo");
    }
}