
package parcial2caso.Main;

import java.io.IOException;
import parcial2caso.Modelo.CasoHawkins;
import parcial2caso.Modelo.ClasificacionCaso;
import parcial2caso.persistencia.RegistroHawkins;

public class Main {
    public static void main(String[] args) {
        try {
            RegistroHawkins<CasoHawkins> registro = new RegistroHawkins<>();

            registro.agregar(new CasoHawkins(1, "Apertura cerca del laboratorio", "Dr. Brenner", ClasificacionCaso.APERTURA_DIMENSIONAL));
            registro.agregar(new CasoHawkins(2, "Actividad psíquica elevada", "Dr. Owens", ClasificacionCaso.SUJETO_PSIQUICO));
            registro.agregar(new CasoHawkins(3, "Rastros de entidad en Hawkins", "Jim Hopper", ClasificacionCaso.ENTIDAD_HOSTIL));
            registro.agregar(new CasoHawkins(4, "Señales electromagnéticas inusuales", "Nancy Wheeler", ClasificacionCaso.FENOMENO_ELECTROMAGNETICO));
            registro.agregar(new CasoHawkins(5, "Desaparición de joven en bosque", "Joyce Byers", ClasificacionCaso.DESAPARICION));

            System.out.println("Casos registrados:");
            registro.paraCadaElemento(caso -> System.out.println(caso));

            System.out.println("\nCasos tipo SUJETO_PSIQUICO:");
            registro.filtrar(caso -> caso.getClasificacion() == ClasificacionCaso.SUJETO_PSIQUICO)
                    .forEach(caso -> System.out.println(caso));

            System.out.println("\nCasos que contienen 'portal':");
            registro.filtrar(caso -> caso.getTitulo().toLowerCase().contains("portal"))
                    .forEach(caso -> System.out.println(caso));

            System.out.println("\nCasos ordenados por ID:");
            registro.ordenar(); 
            registro.paraCadaElemento(caso -> System.out.println(caso));

            System.out.println("\nCasos ordenados por título:");
            registro.ordenar((c1, c2) -> c1.getTitulo().compareToIgnoreCase(c2.getTitulo()));
            registro.paraCadaElemento(caso -> System.out.println(caso));

            
            registro.guardarEnArchivo("src/data/casos.dat");

            RegistroHawkins<CasoHawkins> cargado = new RegistroHawkins<>();
            cargado.cargarDesdeArchivo("src/data/casos.dat");

            System.out.println("\nCasos cargados desde archivo binario:");
            cargado.paraCadaElemento(caso -> System.out.println(caso));

            
            registro.guardarEnCSV("src/data/casos.csv");

            
            cargado.cargarDesdeCSV("src/data/casos.csv", linea -> CasoHawkins.fromCSV(linea));

            System.out.println("\nCasos cargados desde archivo CSV:");
            cargado.paraCadaElemento(caso -> System.out.println(caso));

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
