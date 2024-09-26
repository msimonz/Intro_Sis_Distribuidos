package servidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.ArrayList;
import taxi.Taxi;
import java.rmi.Remote;


public class ImplementacionServidor extends UnicastRemoteObject implements InterfazServidor{

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
        return taxi;

    }

    public void imprimirTaxis() throws RemoteException{
        for(Taxi taxi:taxis){
            System.out.println("Taxi: "+taxi.getId()+" Coordenadas: ["+taxi.getPosx()+ ", "+ taxi.getPosy()+"]");
        }
    }
    
    public void iniciarEnvioMensajes(TaxiSeleccionadoCallback callback) throws RemoteException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Espera hasta que haya al menos 3 taxis
                    while (taxis.size() < 3) {
                        Thread.sleep(1000); // Espera 1 segundo
                    }

                    // Selecciona un taxi al azar
                    Random random = new Random();
                    int indiceAleatorio = random.nextInt(taxis.size());
                    Taxi taxiSeleccionado = taxis.get(indiceAleatorio);
                    System.out.println("Taxi seleccionado: " + taxiSeleccionado.getId());

                    try {
                        callback.onTaxiSeleccionado(taxiSeleccionado);
                    } catch (RemoteException e) {
                        e.printStackTrace(); // Manejo de la excepción
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    public void actualizarPosicionTaxi(String idTaxi, int posX, int posY) throws RemoteException {
    for (Taxi taxi : taxis) {
        if (taxi.getId().equals(idTaxi)) {
            taxi.setPosx(posX);
            taxi.setPosy(posY);
            System.out.println("Nueva posición del taxi " + idTaxi + ": [" + posX + ", " + posY + "]");
            break;
        }
    }
}
}