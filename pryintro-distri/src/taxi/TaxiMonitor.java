package taxi;

import java.util.ArrayList;
import servidor.InterfazServidor;
import java.rmi.RemoteException; 
public class TaxiMonitor extends Thread{
    private final ArrayList<Taxi> taxis;
    private InterfazServidor serverIn;
    private boolean continuar;
    private MovimientoTaxi mv;
    public TaxiMonitor(ArrayList<Taxi> taxis, InterfazServidor serverIn) {
        this.taxis = taxis;
        this.serverIn = serverIn;
        this.continuar = true;
    }

    @Override
    public void run() {
        while (continuar) {
            synchronized (taxis) {
                if (taxis.size() >= 3) {
                    System.out.println("Se han registrado tres o más taxis.");
                    try {
                        Taxi seleccionado = serverIn.seleccionarTaxi();
                        System.out.println("El Mensaje ha llegado con éxito: El taxi "+seleccionado.getId()+" ha sido seleccionado para realizar un servicio.");
                        if (mv != null) {
                            mv.detener(); // Detener el hilo anterior si existe
                        }
                        mv = new MovimientoTaxi(seleccionado, serverIn);
                        for(int i = 0; i<5; i++){
                            mv.mover();
                            mv.detener();
                        }
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
    public void detener(){
        this.continuar = false;
    }
}
