package servidor;
import java.rmi.registry.Registry;
import java.util.ArrayList;
public class ConexionServidor{
    public static void main(String[] args){
        try{
            System.setProperty("java.rmi.server.hostname", "192.168.0.46");
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            r.rebind("InterfazServidor", new ImplementacionServidor());
            System.out.println("Servidor creado con éxito");
            ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();

        }catch(Exception e){
            System.out.println("Error en la creación del servidor: "+e); 
        }
        
    }
}