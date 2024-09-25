import java.rmi.Naming;
import servidor.InterfazServidor;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Taxi{
    public static void main(String[] args){
        try{
            Registry myRegistry = LocateRegistry.getRegistry("192.168.0.46", 1099);
            
            InterfazServidor serverIn = (InterfazServidor) myRegistry.lookup("InterfazServidor");

            System.out.println("Conexión al Servidor establecida correctamente");
        }catch(Excpetion e){
            System.out.println("Error de conexión: "+e);
        }
    }
}