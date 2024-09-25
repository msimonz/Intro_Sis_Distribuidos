package servidor;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazServidor extends Remote{
    public void creacionMatriz() throws RemoteException;
    public int filasMatriz() throws RemoteException;
    public int columnasMatriz() throws RemoteException;
}