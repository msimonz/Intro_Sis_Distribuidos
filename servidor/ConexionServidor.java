import java.rmi.registry.Registry;

public class ConexionServidor{
    public static void main(String[] args){
        try{
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            r.rebind("InterfazServidor", new ImplementacionServidor());
            System.out.println("Servidor creado con éxito");            
        }catch(Exception e){
            System.out.println("Error en la creación del servidor: "+e); 
        }

    }
}