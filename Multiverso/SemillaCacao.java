import java.util.*;

public class SemillaCacao {
    // Diccionario para acceder a cualquier universo instantáneamente por su ID
    private Map<Integer, Universo> universos;

    public SemillaCacao() {
        this.universos = new HashMap<>();
        germinarSemillaBase(); // Al instanciar, nacen los 32 nodos base
    }

    // Inicializa la estructura con 32 nodos garantizando que sea un grafo conexo
    private void germinarSemillaBase() {
        // Nace el Universo Origen (Embrión)
        agregarUniverso(0, null);
        
        Random random = new Random();
        
        // Nacen los siguientes 31 universos
        for (int i = 1; i < 32; i++) {
            // Seleccionamos un universo aleatorio existente para anclar el nuevo
            List<Integer> idsExistentes = new ArrayList<>(universos.keySet());
            int idOrigenAleatorio = idsExistentes.get(random.nextInt(idsExistentes.size()));
            
            agregarUniverso(i, idOrigenAleatorio);
        }
    }

    // Agrega un universo nuevo asegurando que esté conectado a la red
    public void agregarUniverso(int nuevoId, Integer idOrigen) {
        if (universos.containsKey(nuevoId)) {
            System.out.println("El universo " + nuevoId + " ya existe.");
            return;
        }

        Universo nuevoUniverso = new Universo(nuevoId);

        if (universos.isEmpty()) {
            // Es el primer nodo (Nodo 0), no necesita conexión previa
            universos.put(nuevoId, nuevoUniverso);
        } else if (idOrigen != null && universos.containsKey(idOrigen)) {
            // Se agrega y se conecta inmediatamente al origen especificado
            universos.put(nuevoId, nuevoUniverso);
            Universo origen = universos.get(idOrigen);
            nuevoUniverso.conectarCon(origen);
        } else {
            throw new IllegalArgumentException("Violación de estructura: No se puede crear un universo aislado en la Semilla de Cacao.");
        }
    }

    // Algoritmo BFS para encontrar la ruta más corta entre dos universos
    public List<Integer> encontrarCamino(int idInicio, int idDestino) {
        if (!universos.containsKey(idInicio) || !universos.containsKey(idDestino)) {
            return Collections.emptyList(); // Retorna lista vacía si los nodos no existen
        }

        Set<Integer> visitados = new HashSet<>();
        Queue<List<Universo>> cola = new LinkedList<>();

        // Inicializamos la cola con el camino que solo contiene el nodo de inicio
        List<Universo> caminoInicial = new ArrayList<>();
        caminoInicial.add(universos.get(idInicio));
        cola.add(caminoInicial);
        visitados.add(idInicio);

        while (!cola.isEmpty()) {
            List<Universo> caminoActual = cola.poll();
            Universo nodoActual = caminoActual.get(caminoActual.size() - 1);

            // Si llegamos al destino, extraemos los IDs y devolvemos la ruta
            if (nodoActual.getId() == idDestino) {
                List<Integer> rutaFinal = new ArrayList<>();
                for (Universo u : caminoActual) {
                    rutaFinal.add(u.getId());
                }
                return rutaFinal;
            }

            // Exploramos los universos conectados (vecinos)
            for (Universo vecino : nodoActual.getConexiones()) {
                if (!visitados.contains(vecino.getId())) {
                    visitados.add(vecino.getId());
                    
                    // Creamos una nueva bifurcación del camino
                    List<Universo> nuevoCamino = new ArrayList<>(caminoActual);
                    nuevoCamino.add(vecino);
                    cola.add(nuevoCamino);
                }
            }
        }
        
        return Collections.emptyList();
    }
    
    // Método auxiliar para ver cuántos nodos hay
    public int getCantidadUniversos() {
        return universos.size();
    }
}