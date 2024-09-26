package taxi;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface TaxiLatidoPosicion extends Remote{
    void actualizarPosicion(Taxi taxi) throws RemoteException;
}
