package servidor;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazServidor extends Remote{
    public int filasMatriz() throws RemoteException;
    public int columnasMatriz() throws RemoteException;
    public int posTaxiX() throws RemoteException;
    public int posTaxiY() throws RemoteException;
    public void coorTaxi(String id) throws RemoteException;
    public void crearTaxi() throws RemoteException;
    
}