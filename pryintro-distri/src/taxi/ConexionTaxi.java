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
            Registry myRegistry = LocateRegistry.getRegistry("192.168.0.49", 1099);
            InterfazServidor serverIn = (InterfazServidor) myRegistry.lookup("InterfazServidor");
            System.out.println("Conexión al Servidor establecida correctamente");
            do{
                System.out.println("Bienvenido a Amarillitos, escoja una de las siguientes opciones:");
                System.out.println("1. Obtener el Tamaño de la Matriz");
                System.out.println("2. Crear un Taxi");
                System.out.println("4. Imprimir taxis");
                System.out.println("7. Salir");
                Scanner scan = new Scanner(System.in);
                opcion = scan.nextInt();
                switch(opcion){
                    case 1 ->{
                        numfilas = serverIn.filasMatriz();
                        numcolumnas = serverIn.columnasMatriz();
                        System.out.println("*********************************************************");
                        System.out.println("El tamaño de la matriz es de: ["+numfilas+"x"+numcolumnas+"].");
                        System.out.println("*********************************************************");
                    }
                    case 2 ->{
                        int cont = 0;
                        for(Taxi taxi: taxis){
                            cont++;
                        }
                        String id = "T"+cont;
                        int posx = serverIn.posTaxiX();
                        int posy = serverIn.posTaxiY();
                        int velocidad = 0;
                        Taxi nuevo = new Taxi(id, posx, posy, velocidad);
                        nuevo = serverIn.coorTaxi(nuevo, taxis);
                        System.out.println("*********************************************************");
                        System.out.println("Las coordenadas del taxi son: ["+nuevo.getPosx()+", "+nuevo.getPosy()+"].");
                        System.out.println("*********************************************************");
                        taxis.add(nuevo);
                    }
                    case 4 ->{
                        serverIn.imprimirTaxis();
                    }
                }
            } while(opcion != 7);
        }catch(Exception e){
            System.out.println("Error de conexión: "+e);
        }
    }
}