package com.mlaguirre.mtech;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnBuscarMain:
                Intent in = new Intent(this, Registro.class);
                startActivity(in);
                break;
            case R.id.btnCancelMain:
                finish();
        }

    }
}
