
package parcial2caso.persistencia;


import parcial2caso.Modelo.CSVConverter;
import parcial2caso.Modelo.CSVSerializable;
import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class RegistroHawkins<T extends CSVSerializable & Serializable> {
    private List<T> elementos;

    public RegistroHawkins() {
        elementos = new ArrayList<>();
    }

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public T obtener(int indice) {
        return elementos.get(indice);
    }

    public void eliminar(int indice) {
        elementos.remove(indice);
    }

    public List<T> filtrar(Predicate<T> criterio) {
        List<T> resultado = new ArrayList<>();
        for (T e : elementos) {
            if (criterio.test(e)) resultado.add(e);
        }
        return resultado;
    }

    
    @SuppressWarnings("unchecked")
    public void ordenar() {
        Collections.sort((List<? extends Comparable>) elementos);
    }

    
    public void ordenar(Comparator<T> comp) {
        elementos.sort(comp);
    }

    public void paraCadaElemento(java.util.function.Consumer<T> accion) {
        for (T e : elementos) accion.accept(e);
    }

    
    public void guardarEnArchivo(String ruta) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
            out.writeObject(elementos);
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            elementos = (List<T>) in.readObject();
        }
    }

    
    public void guardarEnCSV(String ruta) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (T e : elementos) {
                bw.write(e.toCSV());
                bw.newLine();
            }
        }
    }

    public void cargarDesdeCSV(String ruta, CSVConverter<T> conversor) throws IOException {
        elementos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                elementos.add(conversor.convert(linea));
            }
        }
    }
}
