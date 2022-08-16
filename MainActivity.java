package com.example.expresionesregulares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.expresionesregulares.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    //Variables
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        //hacer clic en boton, comenzar a verificar el correo electrónico
        mainBinding.verifyEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //obtener datos del email
                email = mainBinding.emailEt.getText().toString();
                //validar datos
                if (!email.isEmpty()){
                    //Si el EdtitText no esta vacio comenzar el proceso de esta
                    //parte del codigo
                    if (verify_email_with_regular_expressions(email)){
                        //Si el email es correcto con expresiones regulares mandar a la otra tarea
                        Toast.makeText(MainActivity.this, "El correo es correcto", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), NameActivity.class));
                        //aqui con el finish estoy matando la vida del cliclo del activity
                        finish();
                    }
                    else {
                        //Si el email no es correcto verificanco con expresiones regulares mandar este mensaje
                        Toast.makeText(MainActivity.this, "email error", Toast.LENGTH_SHORT).show();
                    }
                }
                else  {
                    //Si el EditText esta vacio mandarle este mensaje de error
                    Snackbar.make(mainBinding.emailRl, "El campo esta vacio", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    //Esta parte del codigo es para verificar que sea email
    public boolean verify_email_with_regular_expressions(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}