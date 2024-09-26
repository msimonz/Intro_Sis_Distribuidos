package taxi;
import servidor.InterfazServidor;
public class MovimientoTaxi extends Thread {
    private Taxi taxi;
    private InterfazServidor serverIn;
    private boolean continuar;

    public MovimientoTaxi(Taxi taxi, InterfazServidor serverIn) {
        this.taxi = taxi;
        this.serverIn = serverIn;
        this.continuar = true;
    }

   
    public void mover() {
        while (continuar) {
            try{
                // Movimiento aleatorio
                int nuevaPosX = (int) (Math.random() * serverIn.filasMatriz());
                int nuevaPosY = (int) (Math.random() * serverIn.columnasMatriz());
                
                // Actualiza la posición del taxi en el servidor
                serverIn.actualizarPosicionTaxi(taxi.getId(), nuevaPosX, nuevaPosY);
                
                // Actualiza la posición local
                taxi.setPosx(nuevaPosX);
                taxi.setPosy(nuevaPosY);
                
                // Espera un tiempo antes de mover de nuevo
                Thread.sleep(1000); // Espera 1 segundo
            } catch (Exception e) {
                System.out.println("Error al mover el taxi: " + e);
                continuar = false; // Detiene el hilo en caso de error
            }
        }
    }
    
    public void detener() {
        continuar = false; // Método para detener el movimiento
    }
}
