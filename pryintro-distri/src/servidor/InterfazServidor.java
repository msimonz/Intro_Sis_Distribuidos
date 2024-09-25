package servidor;
import java.rmi.Remote;
import java.rmi.RemoteException;
import taxi.Taxi;
import java.util.ArrayList;
public interface InterfazServidor extends Remote{
    public int filasMatriz() throws RemoteException;
    public int columnasMatriz() throws RemoteException;
    public int posTaxiX() throws RemoteException;
    public int posTaxiY() throws RemoteException;
    public Taxi coorTaxi(Taxi taxi, ArrayList<Taxi> listataxis) throws RemoteException;
    public void imprimirTaxis() throws RemoteException;
    
}