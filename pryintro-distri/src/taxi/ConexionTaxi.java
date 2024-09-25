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
                System.out.println("3. Salir");
                Scanner scan = new Scanner(System.in);
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
                }
            } while(opcion != 3);
        }catch(Exception e){
            System.out.println("Error de conexión: "+e);
        }
    }
}