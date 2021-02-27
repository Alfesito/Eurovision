package es.upm.dit.adsw.eurovision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final int OBTENER_VOTOS = 0;

    private TextView resultado;
    private EditText paisVotanteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botonVotar = (Button) findViewById(R.id.botonVotacion);
        paisVotanteEditText = (EditText) findViewById(R.id.editTextPaisVotante);
        resultado = (TextView) findViewById(R.id.textViewResultado);
        botonVotar.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {
        String country = paisVotanteEditText.getText().toString();
        Intent intent = new Intent(MainActivity.this, VotacionActivity.class);
        intent.putExtra("country_voting", country);
        startActivityForResult(intent, OBTENER_VOTOS);
    }   });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OBTENER_VOTOS) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                String paisVotante = extras.getString("country_voting");
                String paisVotado = extras.getString("country_voted");
                int votos = extras.getInt("votes");
                String msg = votos + " votos recibidos de " + paisVotante + " para " + paisVotado;
                paisVotanteEditText.setEnabled(false);
                resultado.setText(msg);
                resultado.setVisibility(View.VISIBLE);
            }
        }
    }
}
