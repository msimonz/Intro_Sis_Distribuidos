package servidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor{
    public int numfilas;
    public int numcolumnas;
    public int posX;
    public int posY;
    Random random = new Random();
    public ImplementacionServidor() throws RemoteException{

    }
    public int filasMatriz() throws RemoteException{
        this.numfilas = random.nextInt(30) + 1;
        System.out.println("El número de filas es: "+numfilas);
        return this.numfilas;
    }
    public int columnasMatriz() throws RemoteException{
        this.numcolumnas = random.nextInt(30) + 1;
        System.out.println("El número de columnas es: "+numcolumnas);
        return this.numcolumnas;
    }
    public int posTaxiX() throws RemoteException{
        this.posX = random.nextInt((this.numfilas - 1))
        return this.posX;
    }
    public int posTaxiY() throws RemoteException{
        this.posY = random.nextInt((this.numcolumnas - 1))
        return this.posY;
    }
}