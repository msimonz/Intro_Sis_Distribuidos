package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import taxi.Taxi;

public interface TaxiSeleccionadoCallback extends Remote {
    void onTaxiSeleccionado(Taxi taxi) throws RemoteException;
}