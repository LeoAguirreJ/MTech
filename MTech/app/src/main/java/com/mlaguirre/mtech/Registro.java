package com.mlaguirre.mtech;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mlaguirre.mtech.modelo.Cliente;
import com.mlaguirre.mtech.modelo.DatabaseHelper;

public class Registro extends ActionBarActivity {

    private EditText cedula;
    private EditText nombre;
    private EditText apellido;
    private EditText direccion;
    private EditText telefono;
    private EditText email;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        cedula = (EditText) findViewById(R.id.txtRegCedula);
        nombre = (EditText) findViewById(R.id.txtRegNombre);
        apellido = (EditText) findViewById(R.id.txtRegApellido);
        direccion = (EditText) findViewById(R.id.txtRegDir);
        telefono = (EditText) findViewById(R.id.txtRegTel);
        email = (EditText) findViewById(R.id.txtRegEmail);
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnRegDatos:


                String cedulaStr = cedula.getText().toString();
                String nombreStr = nombre.getText().toString();
                String apellidoStr = apellido.getText().toString();
                String dirStr = direccion.getText().toString();
                String telStr = telefono.getText().toString();
                String emailStr = email.getText().toString();

                //insertar datos en las DB
                Cliente u = new Cliente();
                u.setCedula(cedulaStr);
                u.setNombre(nombreStr);
                u.setApellido(apellidoStr);
                u.setDireccion(dirStr);
                u.setTelefono(telStr);
                u.setEmail(emailStr);

                helper.agregarCliente(u);
                Toast msg = Toast.makeText(Registro.this, "Datos ingresados correctamente", Toast.LENGTH_SHORT);
                msg.show();

                cedula.setText("");
                nombre.setText("");
                apellido.setText("");
                direccion.setText("");
                telefono.setText("");
                email.setText("");

                break;
            case R.id.btnRegBuscar:

                String cedulaBus = cedula.getText().toString();
                Cliente cliente = new Cliente();
                cliente = helper.buscarCliente(cedulaBus);

                if (cliente != null) {


                    String n1 = cliente.getNombre();
                    String a1 = cliente.getApellido();
                    String d1 = cliente.getDireccion();
                    String t1 = cliente.getTelefono();
                    String e1 = cliente.getEmail();

                    nombre.setText(n1);
                    apellido.setText(a1);
                    direccion.setText(d1);
                    telefono.setText(t1);
                    email.setText(e1);
                }else{
                    Toast msg1 = Toast.makeText(Registro.this, "Cliente no encontrado", Toast.LENGTH_SHORT);
                    msg1.show();
                }

                break;
            case R.id.btnRegModificar:

                String c2 = cedula.getText().toString();
                String n2 = nombre.getText().toString();
                String a2 = apellido.getText().toString();
                String d2 = direccion.getText().toString();
                String t2 = telefono.getText().toString();
                String e2 = email.getText().toString();

                Cliente cliente2 = new Cliente();
                cliente2.setCedula(c2);
                cliente2.setNombre(n2);
                cliente2.setApellido(a2);
                cliente2.setDireccion(d2);
                cliente2.setTelefono(t2);
                cliente2.setEmail(e2);

                String msg2 = helper.modificarCliente(cliente2);
                Toast ms = Toast.makeText(Registro.this, msg2, Toast.LENGTH_SHORT);
                ms.show();

                cedula.setText("");
                nombre.setText("");
                apellido.setText("");
                direccion.setText("");
                telefono.setText("");
                email.setText("");

                break;
            case R.id.btnRegVolver:
                Intent in = new Intent(this, MainActivity.class);
                startActivity(in);
                break;
        }
    }
}
