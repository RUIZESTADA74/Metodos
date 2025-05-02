package JR.metodosandroidstudio;




import static android.widget.AdapterView.*;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // INDICAMOS QUE OBJETOS VAMOS A UTILIZAR Y LES ASIGNAMOS EL ID DE COMPONENTE.

    Spinner sp_metodos,sp_usuarios;
    EditText et_nombre;
    Switch sw_apagar;
    Button b_guardar;
    RadioGroup rg_opciones;
    RadioButton rb_opcion1,rb_opcion2;
    CheckBox cb_seleccion1,cb_seleccion2;
    ArrayList<String> seleccioncheckbox = new ArrayList<>();
    ArrayList<Usuarios> datos = new ArrayList<>();
    TextView tv_datosusuario,tv_tipometodo;



    // COMIENZO DEL PROGRAMA

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recuperarcomponentes();  // MANDA A RECUPERARCOMPONENTES
        anadirlisteners();    // MANDA A ANADIRLISTENERS

    }

    private void recuperarcomponentes() {

        // CONVIERTE EL COMPONENTE EN OBJETO.

        sp_metodos = findViewById(R.id.sp_metodos);
        et_nombre = findViewById(R.id.et_nombre);
        sw_apagar = findViewById(R.id.sw_apagar);
        b_guardar = findViewById(R.id.b_guardar);
        rg_opciones = findViewById(R.id.rg_opciones);
        rb_opcion1 = findViewById(R.id.rb_opcion1);
        rb_opcion2 = findViewById(R.id.rb_opcion2);
        cb_seleccion1 = findViewById(R.id.cb_seleccion1);
        cb_seleccion2 = findViewById(R.id.cb_seleccion2);
        sp_usuarios = findViewById(R.id.sp_usuarios);
        tv_datosusuario = findViewById(R.id.tv_datosusuario);
        tv_tipometodo = findViewById(R.id.tv_tipometodo);
    }


    private void anadirlisteners() {

        /* AÑADE LOS DATOS DEL METODO UTILIZADO A LAS VARIABLES Y
           "MUESTRA UN MENSAJE CON LO SELECCIONADO" A TRAVÉS DE LISTENERS */

        et_nombre.setOnClickListener(view -> {
                et_nombre.setText(""); // BORRA LA LINEA DEL NOMBRE.
        });

        // SPINNER DE SELECCION
        sp_metodos.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cb_seleccion1.setEnabled(false);
                cb_seleccion2.setEnabled(false);
                rb_opcion1.setEnabled(false);
                rb_opcion2.setEnabled(false);
                String nombre = et_nombre.getText().toString();
                String metodo = sp_metodos.getSelectedItem().toString();
                String mensaje = nombre + " se ha elegido el método " + metodo + " en el Spinner.";
                Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG).show();
                if (i==0) {
                    String recursosstring = getResources().getString(R.string.tvspinner);
                    tv_tipometodo.setText(recursosstring);
                    // presenta mensaje de pulsación en snackbar y añade la seleccion a metodo.
                }
                if (i==1) {
                    // RADIOGROUP
                    rb_opcion1.setEnabled(true);  // hace visible el metodo
                    rb_opcion2.setEnabled(true);
                    cb_seleccion1.setEnabled(false);
                    cb_seleccion2.setEnabled(false);
                    String recursosstring = getResources().getString(R.string.tvradiobuttons); // recupera el recurso de strings.xml
                    tv_tipometodo.setText(recursosstring); // lo presenta en el textview.
                    rg_opciones.setOnCheckedChangeListener((radioGroup, b) -> {
                        // presenta mensaje de pulsación en snackbar y añade la seleccion a sopcionelegida.
                        RadioButton rb = findViewById(b);
                        String opcionelegida = rb.getText().toString();
                        //String nombre = et_nombre.getText().toString();
                        Snackbar.make(view, nombre + " se ha elegido la Opción " + opcionelegida + " del método Radiobutton", Snackbar.LENGTH_LONG).show();
                    });
                }
                if (i==2) {
                    rb_opcion1.setEnabled(false);
                    rb_opcion2.setEnabled(false);
                    cb_seleccion1.setEnabled(true);
                    cb_seleccion2.setEnabled(true);
                    String recursosstring = getResources().getString(R.string.tvcheckbox);
                    tv_tipometodo.setText(recursosstring);
                    // CHECKBOX
                    cb_seleccion1.setOnCheckedChangeListener((compoundButton, isCheked) -> {

                        // presenta mensaje de pulsación en snackbar y añade la seleccion a seleccioncheckbox.

                        if (isCheked) {
                            seleccioncheckbox.add("Selección 1");
                            Snackbar.make(compoundButton, nombre + " se ha Marcado la Opción Selección 1 del método CheckBox.", Snackbar.LENGTH_LONG).show();
                        } else {
                            seleccioncheckbox.remove("Selección 1");
                            Snackbar.make(compoundButton, nombre + " se ha Desmarcado la Opción Selección 1 del método CheckBox", Snackbar.LENGTH_LONG).show();
                        }
                    });
                    cb_seleccion2.setOnCheckedChangeListener((compoundButton, isCheked) -> {

                        // presenta mensaje de pulsación en snackbar y añade la seleccion a seleccioncheckbox.

                        if (isCheked) {
                            seleccioncheckbox.add("Selección 2");
                            Snackbar.make(compoundButton, nombre + " se ha Marcado la Opción Selección 2 del método CheckBox.", Snackbar.LENGTH_LONG).show();
                        } else {
                            seleccioncheckbox.remove("Selección 2");
                            Snackbar.make(compoundButton, nombre + " se ha Desmarcado la Opción Selección 2 del método CheckBox.", Snackbar.LENGTH_LONG).show();

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        // GUARDA UN REGISTRO DEL USIARIO CON LO SELECCIONADO

        b_guardar.setOnClickListener(view -> {
                String nombre = et_nombre.getText().toString();
                if (nombre.isEmpty()) {   // ENVIA MENSAJE SI NO SE ESCRIBE NADA.
                    Snackbar.make(view, "Lo siento, no es posible guardar un registro sin un Nombre", Snackbar.LENGTH_LONG).show();
                } else {
                    String metodo = sp_metodos.getSelectedItem().toString();  // recoge el último metodo seleccionado.
                    int selectedId = rg_opciones.getCheckedRadioButtonId();  // crea una variable de la seleccion Radiobutton..
                    RadioButton radiobutton = findViewById(selectedId);    // trae la del id a una variable Radiobutton.
                    String radiobuttonseleccionado = radiobutton.getText().toString(); // Convierte el valor en un String.
                    Usuarios usuario = new Usuarios(nombre, metodo, radiobuttonseleccionado, seleccioncheckbox); // crea el usuario con las variables a utilizar.
                    datos.add(usuario); // añade los datos a un Array datos.
                    sp_usuarios.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos)); //cra un adaptador para Usuarios.
                    int ultimoElementoPosicion = sp_usuarios.getCount() - 1;  // recoge el último elemento del spinner.
                    sp_usuarios.setSelection(ultimoElementoPosicion);// lo presenta como seleccion.
                    sp_usuarios.setOnItemSelectedListener(new OnItemSelectedListener() { //presenta el spinner con los usuarios.
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long id) {
                            // presenta los datos del usuario seleccionado a traves del array datos.
                            tv_datosusuario.setText("DATOS DEL USUARIO\n \n NOMBRE:  "+(datos.get(posicion).getNombre())+
                                    "\n METODO:  "+(datos.get(posicion).getMetodo())+
                                    "\n OPCIONES RADIOBUTTON:  "+(datos.get(posicion).getRadiobuttonseleccionado())+
                                    "\n OPCIONES CHECKBOX:  "+(datos.get(posicion).getSeleccioncheckbox()));
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
        });

        // DESACTIVA LOS COMPONENTES DE ENTRADA DE DATOS CON EL SWITCH

        ((Switch)findViewById(R.id.sw_apagar)).setOnCheckedChangeListener((compoundButton, b) -> {
            sp_metodos.setEnabled(b);
            et_nombre.setEnabled(b);
            b_guardar.setEnabled(b);
            rb_opcion1.setEnabled(b);
            rb_opcion2.setEnabled(b);
            cb_seleccion1.setEnabled(b);
            cb_seleccion2.setEnabled(b);
        });
    }
}





