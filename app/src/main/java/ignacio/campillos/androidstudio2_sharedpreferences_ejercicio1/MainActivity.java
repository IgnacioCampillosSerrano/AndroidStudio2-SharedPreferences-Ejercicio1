package ignacio.campillos.androidstudio2_sharedpreferences_ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import ignacio.campillos.androidstudio2_sharedpreferences_ejercicio1.configuracion.Constantes;
import ignacio.campillos.androidstudio2_sharedpreferences_ejercicio1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences(Constantes.LISTA_USUARIOS, MODE_PRIVATE);

        comprobarLogeo();

        binding.btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = binding.txtUsernameMain.getText().toString();
                String password = binding.txtPasswordMain.getText().toString();

                if (usuario.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferences.Editor editor = sp.edit();

                    editor.putString(Constantes.USUARIO, usuario);
                    editor.putString(Constantes.PASSWORD, password);
                    editor.apply();

                    binding.txtPasswordMain.setText("");
                    binding.txtUsernameMain.setText("");
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                }

            }
        });
    }

    private void comprobarLogeo() {
        if (!sp.getString(Constantes.USUARIO,"").equals("") ||
        !sp.getString(Constantes.PASSWORD,"").equals("")){

            startActivity(new Intent(MainActivity.this, SecondActivity.class));

        }
    }
}