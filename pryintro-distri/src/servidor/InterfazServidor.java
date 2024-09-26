package servidor;
import java.rmi.Remote;
import java.rmi.RemoteException;
import taxi.Taxi;
import java.util.ArrayList;
import taxi.TaxiLatidoPosicion;
public interface InterfazServidor extends Remote, TaxiLatidoPosicion{
    public int filasMatriz() throws RemoteException;
    public int columnasMatriz() throws RemoteException;
    public int posTaxiX() throws RemoteException;
    public int posTaxiY() throws RemoteException;
    public Taxi coorTaxi(Taxi taxi, ArrayList<Taxi> listataxis) throws RemoteException;
    public void imprimirTaxis() throws RemoteException;
    public Taxi seleccionarTaxi() throws RemoteException;
    public void actualizarPosicionTaxi(String idTaxi, int posX, int posY) throws RemoteException;
    void actualizarPosicion(Taxi taxi);
    
}