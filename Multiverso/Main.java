import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SemillaCacao multiverso = null; // Inicia vacío hasta que decidas crearlo
        boolean salir = false;

        System.out.println("=========================================");
        System.out.println("  SISTEMA DE NAVEGACIÓN MULTIVERSAL");
        System.out.println("=========================================");

        while (!salir) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Crear un nuevo Multiverso (Germinar semilla de 32 nodos)");
            System.out.println("2. Consultar ruta entre dos universos");
            System.out.println("3. Salir");
            System.out.print("Elige una opción (1-3): ");
            
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    // Creamos la instancia, lo que dispara la germinación automática
                    multiverso = new SemillaCacao();
                    System.out.println("\n[!] ¡BOOM! Ha nacido un nuevo multiverso.");
                    System.out.println("[!] La semilla tiene " + multiverso.getCantidadUniversos() + " universos interconectados (Nodos 0 al 31).");
                    break;

                case "2":
                    if (multiverso == null) {
                        System.out.println("\n[X] Error: El multiverso no existe aún. Usa la opción 1 para crearlo primero.");
                        break;
                    }

                    try {
                        System.out.print("\nIngresa el ID del universo de ORIGEN (ej. 0): ");
                        int origen = Integer.parseInt(scanner.nextLine());
                        
                        System.out.print("Ingresa el ID del universo de DESTINO (ej. 31): ");
                        int destino = Integer.parseInt(scanner.nextLine());

                        System.out.println("\nCalculando ruta de salto cuántico del " + origen + " al " + destino + "...");
                        List<Integer> ruta = multiverso.encontrarCamino(origen, destino);
                        
                        if (!ruta.isEmpty()) {
                            System.out.println("-> Ruta encontrada: " + ruta);
                            System.out.println("-> Total de saltos requeridos: " + (ruta.size() - 1));
                        } else {
                            System.out.println("[X] No se encontró ruta. Verifica que los IDs estén entre 0 y 31.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("\n[X] Error: Por favor ingresa solo números enteros.");
                    }
                    break;

                case "3":
                    salir = true;
                    System.out.println("\nApagando sistema multiversal. ¡Hasta la próxima!");
                    break;

                default:
                    System.out.println("\n[X] Opción no válida. Intenta de nuevo.");
            }
        }
        
        scanner.close(); // Cerramos el escáner para liberar memoria
    }
}
