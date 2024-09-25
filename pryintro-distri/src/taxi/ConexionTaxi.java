package taxi;
import java.rmi.Naming;
import servidor.InterfazServidor;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.ArrayList;
public class ConexionTaxi{
    public static void main(String[] args){
        int opcion = 0;
        int numfilas = 0;
        int numcolumnas = 0;
        ArrayList<Taxi> taxis = new ArrayList<>();
        try{
            Registry myRegistry = LocateRegistry.getRegistry("192.168.0.16", 1099);
            InterfazServidor serverIn = (InterfazServidor) myRegistry.lookup("InterfazServidor");
            System.out.println("Conexión al Servidor establecida correctamente");
            do{
                System.out.println("Bienvenido a Amarillitos, escoja una de las siguientes opciones:");
                System.out.println("1. Obtener el número de filas");
                System.out.println("2. Obtener el número de columnas");
                System.out.println("3. Crear un Taxi");
                System.out.println("4. /////");
                System.out.println("7. Salir");
                Scanner scan = new Scanner(System.in);
                opcion = scan.nextInt();
                switch(opcion){
                    case 1 ->{
                        numfilas = serverIn.filasMatriz();
                        System.out.println("Numero de filas: "+ numfilas);
                    }
                    case 2 ->{
                        numcolumnas = serverIn.columnasMatriz();
                        System.out.println("Número de columnas: "+numcolumnas);
                    }
                    case 3 ->{
                        int cont = 0;
                        for(Taxi taxi: taxis){
                            cont++;
                        }
                        String id = "T"+cont;
                        int posx = serverIn.posTaxiX();
                        int posy = serverIn.posTaxiY();
                        int velocidad = 1;
                        Taxi nuevo = new Taxi(id, posx, posy, velocidad);
                        System.out.println("Las coordenadas del taxi son: ["+nuevo.getPosx()+", "+nuevo.getPosy()+"].");
                        serverIn.coorTaxi(nuevo.getId());
                    }
                    case 4 ->{
                        System.out.println("Enviando coordenadas al servidor...");
                    }
                }
            } while(opcion != 7);
        }catch(Exception e){
            System.out.println("Error de conexión: "+e);
        }
    }
}