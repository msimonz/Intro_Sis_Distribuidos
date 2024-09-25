package taxi;
import java.rmi.Naming;
import servidor.InterfazServidor;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class ConexionTaxi{
    public static void main(String[] args){
        int opcion = 0;
        try{
            Registry myRegistry = LocateRegistry.getRegistry("192.168.0.46", 1099);
            InterfazServidor serverIn = (InterfazServidor) myRegistry.lookup("InterfazServidor");
            System.out.println("Conexión al Servidor establecida correctamente");
            do{
                System.out.println("Bienvenido a Amarillitos, escoja una de las siguientes opciones:");
                System.out.println("1. Obtener el número de columnas");
                System.out.println("2. Obtener el número de filas");
                System.out.println("3. Obtener coordenadas del Taxi en la matriz");
                System.out.println("4. Ubicar Taxi en la Matriz");
                System.out.println("7. Salir");
                Scanner scan = new Scanner(System.in);
                Taxi taxi = new Taxi("T1", 0, 0, 1);
                opcion = scan.nextInt();
                switch(opcion){
                    case 1 ->{
                        opcion = serverIn.columnasMatriz();
                        System.out.println("Numero de columnas: "+ opcion);
                    }
                    case 2 ->{
                        opcion = serverIn.filasMatriz();
                        System.out.println("Número de filas: "+opcion);
                    }
                    case 3 ->{
                        taxi.setPosx(serverIn.posTaxiX());
                        taxi.setPosy(serverIn.posTaxiY());
                        System.out.println("Las coordenadas del taxi son: ["+taxi.getPosx()+", "+taxi.getPosy()+"].");
                    }
                    case 4 ->{
                        System.out.println("Enviando coordenadas al servidor...");
                        serverIn.coorTaxi(taxi.getId());
                    }
                }
            } while(opcion != 7);
        }catch(Exception e){
            System.out.println("Error de conexión: "+e);
        }
    }
}