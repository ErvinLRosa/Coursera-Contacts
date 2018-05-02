package com.example.erosa.courseracontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DatosActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_NOMBRE = "datos:nombre";
    public static final String EXTRA_FECHA = "datos:fecha";
    public static final String EXTRA_TELEFONO = "datos:telefono";
    public static final String EXTRA_EMAIL = "datos:email";
    public static final String EXTRA_DESCRIP= "datos:descrip";

    private TextView tvNombre, tvFecha, tvTelefono, tvEmail, tvDescrip;
    private String nombre, fecha, telf, email, descrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        tvNombre = findViewById(R.id.tv_nombre);
        tvFecha = findViewById(R.id.tv_nacimiento);
        tvTelefono = findViewById(R.id.tv_telefono);
        tvEmail = findViewById(R.id.tv_email);
        tvDescrip = findViewById(R.id.tv_descripcion);

        if (getIntent().hasExtra(EXTRA_NOMBRE) &&
                getIntent().hasExtra(EXTRA_FECHA) &&
                getIntent().hasExtra(EXTRA_TELEFONO) &&
                getIntent().hasExtra(EXTRA_EMAIL) &&
                getIntent().hasExtra(EXTRA_DESCRIP)){

            nombre = getIntent().getStringExtra(EXTRA_NOMBRE);
            fecha = getIntent().getStringExtra(EXTRA_FECHA);
            telf = getIntent().getStringExtra(EXTRA_TELEFONO);
            email = getIntent().getStringExtra(EXTRA_EMAIL);
            descrip = getIntent().getStringExtra(EXTRA_DESCRIP);

            tvNombre.setText(nombre);
            tvFecha.setText(getString(R.string.fecha_nacimiento, fecha));
            tvTelefono.setText(getString(R.string.tel_concat, telf));
            tvEmail.setText(getString(R.string.email_concat, email));
            tvDescrip.setText(descrip);
        }
        findViewById(R.id.btn_editar).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_editar:
                Intent datosActivity = new Intent(this, MainActivity.class);
                datosActivity.putExtra(DatosActivity.EXTRA_NOMBRE, nombre);
                datosActivity.putExtra(DatosActivity.EXTRA_FECHA, fecha);
                datosActivity.putExtra(DatosActivity.EXTRA_TELEFONO, telf);
                datosActivity.putExtra(DatosActivity.EXTRA_EMAIL, email);
                datosActivity.putExtra(DatosActivity.EXTRA_DESCRIP, descrip);
                startActivity(datosActivity);
                finish();
            break;
        }
    }
}
