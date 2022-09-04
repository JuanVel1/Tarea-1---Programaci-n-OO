package panaderia.datos;

import java.nio.file.Path;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class LectorArchivo implements IFuenteDatos {
  private String nombreArchivo;

  public LectorArchivo(String nombreArchivo) {
    this.nombreArchivo = nombreArchivo;
  }

  @Override
  public List<String[]> obtenerDatosBase() throws IOException {
    Path rutaArchivo = Paths.get(nombreArchivo);
    BufferedReader lector = Files.newBufferedReader(rutaArchivo);

    ArrayList listaDatos = new ArrayList<String[]>();

    String linea;
    while ((linea = lector.readLine()) != null) {
      String[] datos = linea.split(",");
      listaDatos.add(datos);
    }

    return listaDatos;
  }
}