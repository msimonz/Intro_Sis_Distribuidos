public class Taxi{
    private String id;
    private int posx;
    private int posy;
    private int velocidad;
    private final int numserv;

    public Taxi(String id, int posx, int posy, int velocidad){
        this.id = id;
        this.posx = posx;
        this.posy = posy;
        this.velocidad = velocidad;
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
        return posy;
    }

    public void setPosy(int posy) {
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