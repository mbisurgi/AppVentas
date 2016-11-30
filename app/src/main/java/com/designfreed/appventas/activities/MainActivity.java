package com.designfreed.appventas.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.designfreed.appventas.R;
import com.designfreed.appventas.entities.Chofer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HttpAsynTask().execute(username.getText().toString(), password.getText().toString());
            }
        });
    }

    private class HttpAsynTask extends AsyncTask<String, Void, Chofer> {
        @Override
        protected Chofer doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];

            String urlService = "http://localhost:8080/VentasService/services/ventaService/login?";
            urlService.concat("username=").concat(username);
            urlService.concat("&");
            urlService.concat("password=").concat(password);

            String json = "";

            HttpURLConnection httpURLConnection = null;

            Chofer chofer = null;

            try {
                URL url = new URL(urlService);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    json = readFromStream(inputStream);

                    chofer = obtenerChofer(json);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return chofer;
        }

        @Override
        protected void onPostExecute(Chofer chofer) {
            super.onPostExecute(chofer);

            if (chofer != null) {

            }
        }
    }

    private Chofer obtenerChofer(String json) {
        Chofer cho = new Chofer();

        try {
            JSONObject jsonChofer = new JSONObject(json);

            Long id = jsonChofer.getLong("id");
            String username = jsonChofer.getString("username");
            String password = jsonChofer.getString("password");
            String nombre = jsonChofer.getString("nombre");
            String apellido = jsonChofer.getString("apellido");

            cho.setId(id);
            cho.setUsername(username);
            cho.setPassword(password);
            cho.setNombre(nombre);
            cho.setApellido(apellido);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cho;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }
}


