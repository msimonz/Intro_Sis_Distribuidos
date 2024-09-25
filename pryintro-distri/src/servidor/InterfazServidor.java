package servidor;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazServidor extends Remote{
    public void hola() throws RemoteException;
}