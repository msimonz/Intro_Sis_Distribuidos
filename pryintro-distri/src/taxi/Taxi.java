package taxi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.rmi.RemoteException;

public class Taxi implements Serializable{
    private String id;
    private int posx;
    private int posy;
    private int velocidad;
    private final int numserv;
    private List<TaxiLatidoPosicion> suscriptores = new ArrayList<>();

    public Taxi(String id, int posx, int posy, int velocidad){
        this.id = id;
        this.posx = posx;
        this.posy = posy;
        this.velocidad = velocidad;
        this.numserv = 3;
    }

    public void añadirSuscriptor(TaxiLatidoPosicion suscriptor){
        suscriptores.add(suscriptor);
    }

    public void eliminarSuscriptor(TaxiLatidoPosicion suscriptor){
        suscriptores.remove(suscriptor);
    }
    private void notificarSuscriptores(){
        for(TaxiLatidoPosicion suscri: suscriptores){
            try {
            suscri.actualizarPosicion(this);
        } catch (RemoteException e) {
            System.out.println("Error al notificar al suscriptor: " + e.getMessage());
        }
        }
    }
    public Taxi(){
        this.numserv = 3;
    }
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        notificarSuscriptores();
        return posy;
    }

    public void setPosy(int posy) {
        notificarSuscriptores();
        this.posy = posy;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getNumserv() {
        return numserv;
    }

}