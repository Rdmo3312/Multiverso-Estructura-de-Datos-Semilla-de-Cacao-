import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciamos el multiverso (Nace con sus 32 nodos)
        SemillaCacao multiverso = new SemillaCacao();
        System.out.println("Multiverso creado con " + multiverso.getCantidadUniversos() + " universos interconectados.");

        // 2. Agregamos universos adicionales más allá de los 32 base
        multiverso.agregarUniverso(32, 15); // Conectamos el 32 al 15
        multiverso.agregarUniverso(33, 32); // Conectamos el 33 al 32
        
        // Agregamos un "pliegue" extra en la semilla (conectando nodos lejanos manualmente)
        // Por ejemplo, forzamos un portal entre el universo 0 y el 33
        // (En una implementación real, agregarías un método público en SemillaCacao para enlazar nodos existentes)

        // 3. Probamos el sistema de navegación (Ruta del 0 al 33)
        System.out.println("\nCalculando ruta de salto cuántico del Universo 0 al 33...");
        List<Integer> ruta = multiverso.encontrarCamino(0, 33);
        
        if (!ruta.isEmpty()) {
            System.out.println("Ruta encontrada: " + ruta);
            System.out.println("Total de saltos requeridos: " + (ruta.size() - 1));
        } else {
            System.out.println("No se encontró ruta o los universos no existen.");
        }
    }
}