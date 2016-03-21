package com.mlaguirre.mtech;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mlaguirre.mtech.modelo.Cliente;
import com.mlaguirre.mtech.modelo.DatabaseHelper;
import com.mlaguirre.mtech.modelo.DatosCita;

public class ProgramarCita extends ActionBarActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    private TextView nombre;
    private EditText diag, desc,fecha, hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programarcita);

        String cedulaBus = getIntent().getStringExtra("cedula");
        Cliente cliente = new Cliente();
        cliente = helper.buscarCliente(cedulaBus);
        nombre = (TextView) findViewById(R.id.tvPnombre);
        diag = (EditText) findViewById(R.id.txtPdiagnostico);
        desc = (EditText) findViewById(R.id.txtPdescripcion);
        fecha = (EditText) findViewById(R.id.txtPfecha);
        hora = (EditText) findViewById(R.id.txtPhora);

        nombre.setText(cliente.getNombre()+ " "+ cliente.getApellido());
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnPRegistrar:

                String cedStr = getIntent().getStringExtra("cedula");
                String diagStr = diag.getText().toString();
                String descStr = desc.getText().toString();
                String fechaStr = fecha.getText().toString();
                String horaStr = hora.getText().toString();

                //insertar datos en las DB
                DatosCita datosCita = new DatosCita();
                datosCita.setCedula(cedStr);
                datosCita.setDiagnostico(diagStr);
                datosCita.setDescripcion(descStr);
                datosCita.setFecha(fechaStr);
                datosCita.setHora(horaStr);

                helper.crearCita(datosCita);
                Toast msg = Toast.makeText(ProgramarCita.this, "Datos ingresados correctamente", Toast.LENGTH_SHORT);
                msg.show();

                break;
            case R.id.btnPCancel:
                Intent in = new Intent(this, Registro.class);
                startActivity(in);
        }

    }
}
