package servidor;
import java.rmi.Remote;
import java.rmi.RemoteException;
import taxi.Taxi;
public interface InterfazServidor extends Remote{
    public int filasMatriz() throws RemoteException;
    public int columnasMatriz() throws RemoteException;
    public int posTaxiX() throws RemoteException;
    public int posTaxiY() throws RemoteException;
    public Taxi coorTaxi(Taxi taxi) throws RemoteException;
    public boolean crearTaxi() throws RemoteException;
    
}