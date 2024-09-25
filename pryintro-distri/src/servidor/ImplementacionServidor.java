package servidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor{
    public ImplementacionServidor() throws RemoteException{

    }
    public int filasMatriz() throws RemoteException{
        Random random = new Random();
        int numfilas = random.nextInt(30) + 1;
        System.out.println("El número de filas es: "+numfilas);
        return numfilas;
    }
    public int columnasMatriz() throws RemoteException{
        Random random = new Random();
        int numcolumnas = random.nextInt(30) + 1;
        System.out.println("El número de filas es: "+numcolumnas);
        return numcolumnas;
    }
}