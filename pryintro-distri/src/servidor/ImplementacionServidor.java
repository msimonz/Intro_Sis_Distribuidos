package servidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.ArrayList;
import taxi.Taxi;

public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor{
    ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
    public int numfilas = -1;
    public int numcolumnas = -1;
    public int posX;
    public int posY;
    Random random = new Random();
    //Constructor
    public ImplementacionServidor() throws RemoteException{

    }
    //Recursos a consumir
    public int filasMatriz() throws RemoteException{
        if(this.numfilas == -1){
            this.numfilas = random.nextInt(30) + 5;
            System.out.println("El número de filas es: "+numfilas);
            return this.numfilas;
        }
        else{
            return this.numfilas;
        }
        
    }
    public int columnasMatriz() throws RemoteException{
        if(this.numcolumnas == -1){
            this.numcolumnas = random.nextInt(30) + 5;
            System.out.println("El número de columnas es: "+numcolumnas);
            for(int i = 0; i<this.numcolumnas; i++){
                matriz.add(new ArrayList<>());
                for(int j = 0; j<this.numfilas; j++){
                    matriz.get(i).add("*");
                }
            }
            return this.numcolumnas;
        }
        else{
            return this.numcolumnas;
        }
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
    public Taxi coorTaxi(Taxi taxi) throws RemoteException{
        System.out.println("*********************************************************");
        System.out.println("Coordenadas del Taxi "+ taxi.getId() + ": ["+taxi.getPosx()+ ", "+ taxi.getPosy()+"]");
        System.out.println("*********************************************************");
        if(matriz.get(taxi.getPosy()).get(taxi.getPosx()).equals("*")){
            this.matriz.get(taxi.getPosy()).set(taxi.getPosx(), taxi.getId());
        }
        else{
            for(int i = 0; i<this.numfilas; i++){
                for(int j = 0; j<this.numcolumnas; j++){
                    if(matriz.get(j).get(i).equals("*")){
                        taxi.setPosx(i);
                        taxi.setPosy(j);
                        this.matriz.get(taxi.getPosy()).set(taxi.getPosx(), taxi.getId());
                    }
                }
            }
        }
        for(int i = 0; i<this.numfilas; i++){
            System.out.println("");
            for(int j = 0; j<this.numcolumnas; j++){
                System.out.print(matriz.get(j).get(i)+"  ");
            }
        }
        System.out.println("");
        return taxi;
    }
    public boolean crearTaxi() throws RemoteException{
        
        return true;
    }
}