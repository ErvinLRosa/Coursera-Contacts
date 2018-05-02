package com.example.erosa.courseracontacto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etNombre, etTelefono, etEmail, etDescripcion;
    private TextView tvFecha;
    private View dpFecha;
    private Date fechaNacimieto;

    public final Calendar c = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre = findViewById(R.id.et_nombre);
        etTelefono = findViewById(R.id.et_telefono);
        etEmail = findViewById(R.id.et_email);
        etDescripcion = findViewById(R.id.et_descripcion);
        dpFecha = findViewById(R.id.dp_fecha);
        tvFecha = findViewById(R.id.tv_fecha);
        fechaNacimieto = new Date(c.getTimeInMillis());

        dpFecha.setOnClickListener(this);
        getSupportActionBar().setTitle(getString(R.string.activity_main_title));
        findViewById(R.id.btn_siguiente).setOnClickListener(this);

        if (getIntent().hasExtra(DatosActivity.EXTRA_NOMBRE) &&
                getIntent().hasExtra(DatosActivity.EXTRA_FECHA) &&
                getIntent().hasExtra(DatosActivity.EXTRA_TELEFONO) &&
                getIntent().hasExtra(DatosActivity.EXTRA_EMAIL) &&
                getIntent().hasExtra(DatosActivity.EXTRA_DESCRIP)){

            etNombre.setText(getIntent().getStringExtra(DatosActivity.EXTRA_NOMBRE));
            tvFecha.setText(getIntent().getStringExtra(DatosActivity.EXTRA_FECHA));
            etTelefono.setText(getIntent().getStringExtra(DatosActivity.EXTRA_TELEFONO));
            etEmail.setText(getIntent().getStringExtra(DatosActivity.EXTRA_EMAIL));
            etDescripcion.setText(getIntent().getStringExtra(DatosActivity.EXTRA_DESCRIP));
        } else {
            setFecha(fechaNacimieto.getDay() - 1, fechaNacimieto.getMonth(), fechaNacimieto.getYear() + 1900);
        }
    }
    public boolean validarDatos(){
        if (TextUtils.isEmpty(etNombre.getText()) || etNombre.getText().length() <= 1){
            etNombre.requestFocus();
            etNombre.setError(getString(R.string.nombre_error));
            return false;
        }
        if (TextUtils.isEmpty(etTelefono.getText()) || etTelefono.getText().length() <= 4){
            etTelefono.requestFocus();
            etTelefono.setError(getString(R.string.telefono_error));
            return false;
        }
        if (TextUtils.isEmpty(etEmail.getText()) || !etEmail.getText().toString().contains("@")){
            etEmail.requestFocus();
            etEmail.setError(getString(R.string.correo_error));
            return false;
        }
        if (TextUtils.isEmpty(etDescripcion.getText())){
            etDescripcion.requestFocus();
            etDescripcion.setError(getString(R.string.descrip_error));
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dp_fecha:
                DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        setFecha(dayOfMonth, month, year);
                        fechaNacimieto = new Date(year, month, dayOfMonth);
                    }
                }, (fechaNacimieto.getYear() < 1900) ? fechaNacimieto.getYear() + 1900 : fechaNacimieto.getYear(), fechaNacimieto.getMonth(), fechaNacimieto.getDay() - 1);
                datePicker.show();
                break;
            case R.id.btn_siguiente:
                if (validarDatos()){
                    Intent datosActivity = new Intent(this, DatosActivity.class);
                    datosActivity.putExtra(DatosActivity.EXTRA_NOMBRE, etNombre.getText().toString());
                    datosActivity.putExtra(DatosActivity.EXTRA_FECHA, tvFecha.getText().toString());
                    datosActivity.putExtra(DatosActivity.EXTRA_TELEFONO, etTelefono.getText().toString());
                    datosActivity.putExtra(DatosActivity.EXTRA_EMAIL, etEmail.getText().toString());
                    datosActivity.putExtra(DatosActivity.EXTRA_DESCRIP, etDescripcion.getText().toString());
                    startActivity(datosActivity);
                    finish();
                }

                break;
        }
    }
    public void setFecha(int day, int month, int year){
        String dia = (day < 10)? "0" + String.valueOf(day):String.valueOf(day);
        String mes = (month < 10)? "0" + String.valueOf(month + 1):String.valueOf(month + 1);
        tvFecha.setText(String.format(getString(R.string.fecha_formateada), dia, mes, year));
    }
}
