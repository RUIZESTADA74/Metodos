package JR.metodosandroidstudio;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Usuarios {
    private String nombre;
    private String metodo;
    private String radiobuttonseleccionado;
    private ArrayList<String> seleccioncheckbox;

    public Usuarios(String nombre, String metodo, String radiobuttonseleccionado, ArrayList<String> seleccioncheckbox) {
        this.nombre = nombre;
        this.metodo = metodo;
        this.radiobuttonseleccionado = radiobuttonseleccionado;
        this.seleccioncheckbox = seleccioncheckbox;
    }

    public String getNombre() {return nombre;}
    public String getMetodo() {return metodo;}

    public String getRadiobuttonseleccionado() {return radiobuttonseleccionado;}

    public ArrayList<String> getSeleccioncheckbox() {
        return seleccioncheckbox;
    }



    @NonNull
    @Override
    public String toString() {return nombre;}
}

