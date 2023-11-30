package ignacio.campillos.androidstudio2_sharedpreferences_ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import ignacio.campillos.androidstudio2_sharedpreferences_ejercicio1.configuracion.Constantes;
import ignacio.campillos.androidstudio2_sharedpreferences_ejercicio1.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    public ActivitySecondBinding binding;
    public SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences(Constantes.LISTA_USUARIOS, MODE_PRIVATE);

        rellenarInformacion();

        binding.btnLogoutSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();

                editor.clear();
                editor.apply();

                finish();

            }
        });
    }

    private String descodificar(String codigo){
        String resultado = null;

        try {
            resultado = new String(Base64.getDecoder().decode(codigo.getBytes("UTF8")),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    private void rellenarInformacion() {
         String password = descodificar(sp.getString(Constantes.PASSWORD, ""));

        binding.lbUsuarioSecond.setText(sp.getString(Constantes.USUARIO,""));
        binding.lbPasswordSecond.setText(password);
    }
}