package servidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.ArrayList;
import taxi.Taxi;

public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor{
    ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
    ArrayList<Taxi> taxis = new ArrayList<>();
    public int numfilas;
    public int numcolumnas;
    public int posX;
    public int posY;
    Random random = new Random();
    //Constructor
    public ImplementacionServidor() throws RemoteException{

    }
    //Recursos a consumir
    public int filasMatriz() throws RemoteException{
        this.numfilas = random.nextInt(30) + 5;
        System.out.println("El número de filas es: "+numfilas);
        return this.numfilas;
    }
    public int columnasMatriz() throws RemoteException{
        this.numcolumnas = random.nextInt(30) + 5;
        System.out.println("El número de columnas es: "+numcolumnas);
        for(int i = 0; i<this.numcolumnas; i++){
            matriz.add(new ArrayList<>());
            for(int j = 0; j<this.numfilas; j++){
                matriz.get(i).add("");
            }
        }
        return this.numcolumnas;
    }
    public int posTaxiX() throws RemoteException{
        this.posX = random.nextInt((this.numfilas - 1));
        return this.posX;
    }
    public int posTaxiY() throws RemoteException{
        this.posY = random.nextInt((this.numcolumnas - 1));
        return this.posY;
    }
    //Resultados por recibir
    public void coorTaxi(String id) throws RemoteException{
        System.out.println("Coordenadas del Taxi "+ id + ": ["+this.posX+ ", "+ this.posY+"]");
        this.matriz.get(this.posY).set(this.posX, id);
        System.out.println(this.matriz.get(this.posY).get(this.posX));
        for(int i = 0; i<this.numfilas; i++){
            System.out.println("");
            for(int j = 0; j<this.numcolumnas; j++){
                if(matriz.get(j).get(i).equals("")){
                    System.out.print(j+"  ");
                }
                else{
                    System.out.print(matriz.get(j).get(i)+"  ");
                }
                
            }
        }
        System.out.println("");
    }
    public void crearTaxi() throws RemoteException{
        
    }
}