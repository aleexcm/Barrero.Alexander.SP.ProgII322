
package parcial2caso.Modelo;


@FunctionalInterface
public interface CSVConverter<T> {
    T convert(String linea);
}
