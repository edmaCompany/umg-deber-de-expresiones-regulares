package com.example.expresionesregulares;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.expresionesregulares.databinding.ActivityNameBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameActivity extends AppCompatActivity {

    ActivityNameBinding nameBinding;
    String name = "", surname = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameBinding = ActivityNameBinding.inflate(getLayoutInflater());
        setContentView(nameBinding.getRoot());

        //hacer clic en boton, comienza a verificar el nombre y apellido del usario
        nameBinding.verifyNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //obtener datos del nombre completo
                name = nameBinding.nameEt.getText().toString();
                surname = nameBinding.surnameEt.getText().toString();
                if (!name.isEmpty() && !surname.isEmpty()){
                    String full_name = name + surname;
                    if (verify_name_with_regular_expressions(full_name)){
                        //Si el nombre y el apellido es correcto con expresiones regulares mandar este mensaj

                        enviar_mensaje();

                    }else {
                        //Si el nombre y apellido no es correcto verificanco con expresiones regulares mandar este mensaje
                        Toast.makeText(NameActivity.this, "El nombre o el apellido estan incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    //Si el EditText esta vacio mandarle este mensaje de error
                    Snackbar.make(nameBinding.nameRl, "Los campos estan vacios", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void enviar_mensaje() {
        //Aqui vamos hacer un alerta de dialogo "AlertDialog"
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("¿Quieres terminar la tarea?")
                .setMessage("Si finaliza la tarea, se enviará a la página principal.")
                .setPositiveButton("Continúe probando otros métodos", null)
                .setNegativeButton("terminar la tarea", null)
                .show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        //boton positivo
        positiveButton.setTextColor(Color.GRAY);
        positiveButton.setTextSize(12);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aqui le diremos al codigo que siga con la tarea
                dialog.dismiss();
            }
        });

        // boton negativo
        negativeButton.setTextColor(Color.BLACK);
        negativeButton.setTextSize(12);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                dialog.dismiss();
            }
        });

    }

    //Esta parte del codigo es para verificar letras
    public boolean verify_name_with_regular_expressions(String full_name) {
        return full_name.matches("[a-zA-Z]*");
    }

}