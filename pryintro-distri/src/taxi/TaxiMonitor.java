package taxi;

import java.util.ArrayList;
import servidor.InterfazServidor;
import java.rmi.RemoteException; 
public class TaxiMonitor implements Runnable {
    private final ArrayList<Taxi> taxis;
    private InterfazServidor serverIn;
    public TaxiMonitor(ArrayList<Taxi> taxis, InterfazServidor serverIn) {
        this.taxis = taxis;
        this.serverIn = serverIn;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (taxis) {
                if (taxis.size() >= 3) {
                    System.out.println("Se han registrado tres o más taxis.");
                    try {
                        MovimientoTaxi mv = new MovimientoTaxi(serverIn.seleccionarTaxi(), serverIn);
                        mv.mover();
                        System.out.println("El Mensaje ha llegado con éxito");
                    } catch (RemoteException e) {
                        System.out.println("Error al seleccionar el taxi: " + e.getMessage());
                    }
                    break; 
                }
            }

            // Esperar un momento antes de volver a comprobar
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Manejar la interrupción
            }
        }
    }
}
