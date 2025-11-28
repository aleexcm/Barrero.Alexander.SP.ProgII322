
package parcial2caso.Modelo;

import parcial2caso.Modelo.CSVSerializable;
import parcial2caso.Modelo.ClasificacionCaso;
import java.io.Serializable;

public class CasoHawkins implements Comparable<CasoHawkins>, CSVSerializable, Serializable {
    private int id;
    private String titulo;
    private String investigador;
    private ClasificacionCaso clasificacion;

    public CasoHawkins(int id, String titulo, String investigador, ClasificacionCaso clasificacion) {
        this.id = id;
        this.titulo = titulo;
        this.investigador = investigador;
        this.clasificacion = clasificacion;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getInvestigador() { return investigador; }
    public ClasificacionCaso getClasificacion() { return clasificacion; }

    @Override
    public int compareTo(CasoHawkins otro) {
        return Integer.compare(this.id, otro.id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Título: %s | Investigador: %s | Clasificación: %s",
                id, titulo, investigador, clasificacion);
    }

    @Override
    public String toCSV() {
        return id + "," + titulo + "," + investigador + "," + clasificacion;
    }

    public static CasoHawkins fromCSV(String linea) {
        String[] partes = linea.split(",", 4);
        int id = Integer.parseInt(partes[0].trim());
        String titulo = partes[1].trim();
        String investigador = partes[2].trim();
        ClasificacionCaso clas = ClasificacionCaso.valueOf(partes[3].trim());
        return new CasoHawkins(id, titulo, investigador, clas);
    }
}
