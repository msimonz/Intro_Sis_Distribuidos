package servidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.ArrayList;
import taxi.Taxi;
import java.rmi.Remote;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import taxi.TaxiLatidoPosicion;

public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor, TaxiLatidoPosicion{

    ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
    ArrayList<Taxi> taxis = new ArrayList<>();
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
    public Taxi coorTaxi(Taxi taxi, ArrayList<Taxi> listataxis) throws RemoteException{
        taxis = listataxis;
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
        for(int i = 0; i<taxis.size(); i++){
            Taxi taxii = taxis.get(i);
            if(taxii.getId().equals(taxi.getId())){
                taxis.add(i, taxi);
            }
        }
        String contenido = "Se creó el Taxi "+taxi.getId()+" en la posición ["+taxi.getPosx()+", "+taxi.getPosy()+"]";
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("taxis.txt", true))) {
            escritor.write(contenido);
            escritor.newLine();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
        return taxi;

    }

    public void imprimirTaxis() throws RemoteException{
        for(Taxi taxi:taxis){
            System.out.println("Taxi: "+taxi.getId()+" Coordenadas: ["+taxi.getPosx()+ ", "+ taxi.getPosy()+"]");
        }
    }
    
    public Taxi seleccionarTaxi() throws RemoteException {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(taxis.size());
        System.out.println("Taxi seleccionado: " + taxis.get(indiceAleatorio).getId());
        return taxis.get(indiceAleatorio);
    }

    @Override
    public void actualizarPosicion(Taxi taxi)throws RemoteException{
        for(int i=0; i<taxis.size(); i++){
            Taxi taxii = taxis.get(i);
            if(taxii.getId().equals(taxi.getId())){
                taxis.add(i, taxi);
            }
        }
        System.out.println("Nueva posición del taxi " + taxi.getId() + ": [" + taxi.getPosx() + ", " + taxi.getPosy() + "]");

    }

}