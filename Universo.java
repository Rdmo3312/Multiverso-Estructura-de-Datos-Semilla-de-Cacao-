import java.util.HashSet;
import java.util.Set;

public class Universo {
    private int id;
    // Usamos HashSet para garantizar que no haya portales duplicados al mismo universo
    private Set<Universo> conexiones;

    public Universo(int id) {
        this.id = id;
        this.conexiones = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public Set<Universo> getConexiones() {
        return conexiones;
    }

    // Método para crear un portal bidireccional
    public void conectarCon(Universo otroUniverso) {
        this.conexiones.add(otroUniverso);
        otroUniverso.getConexiones().add(this);
    }
}