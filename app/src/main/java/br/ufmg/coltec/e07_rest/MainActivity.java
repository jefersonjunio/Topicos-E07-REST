package br.ufmg.coltec.e07_rest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import models.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webservice.GitHubConfig;
import webservice.UserService;

public class MainActivity extends AppCompatActivity {
    
    private EditText et_pesquisaUsuario;
    private Button btn_buscarUsario;
    private TextView tv_nomeUsuario;
    private TextView tv_loginUsuario;
    private TextView tv_cidadeUsuario;
    private TextView tv_emailUsuario;
    private TextView tv_empresaUsuario;
    private TextView tv_repositorioUsuario;
    private TextView tv_seguidoresUsuario;
    private TextView tv_blogUsuario;
    private TextView tv_biografiaUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        et_pesquisaUsuario = this.findViewById(R.id.et_pesquisarUsuario);
        btn_buscarUsario = this.findViewById(R.id.btn_buscarUsuario);
        tv_nomeUsuario = this.findViewById(R.id.tv_nomeUsuario);
        tv_loginUsuario = this.findViewById(R.id.tv_loginUsuario);
        tv_cidadeUsuario = this.findViewById(R.id.tv_cidadeUsuario);
        tv_emailUsuario = this.findViewById(R.id.tv_emailUsuario);
        tv_empresaUsuario = this.findViewById(R.id.tv_empresaUsuario);
        tv_repositorioUsuario = this.findViewById(R.id.tv_repositorioUsuario);
        tv_seguidoresUsuario = this.findViewById(R.id.tv_seguidoresUsuario);
        tv_blogUsuario = this.findViewById(R.id.tv_blogUsuario);
        tv_biografiaUsuario = this.findViewById(R.id.tv_biografiaUsuario);

        GitHubConfig gitHubConfig = new GitHubConfig();

        
        btn_buscarUsario.setOnClickListener(view ->{
            String usuario = et_pesquisaUsuario.getText().toString();

            UserService userService = gitHubConfig.getUserService();
            Call<Users> userCall = userService.getUser(usuario);
            
            userCall.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    Toast.makeText(MainActivity.this, "Deu certo", Toast.LENGTH_SHORT).show();

                    Users users = response.body();
                    tv_nomeUsuario.setText(users.getName());
                    tv_loginUsuario.setText(users.getLogin());
                    tv_cidadeUsuario.setText(users.getLocation());
                    tv_emailUsuario.setText(users.getEmail());
                    tv_empresaUsuario.setText(users.getCompany());
                    tv_repositorioUsuario.setText(users.getPublic_repos());
                    tv_seguidoresUsuario.setText(users.getFollowers());
                    tv_blogUsuario.setText(users.getBlog());
                    tv_biografiaUsuario.setText(users.getBio());
                    Log.d("ON_RESPONSE", users.toString());
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Falhou", Toast.LENGTH_SHORT).show();
                }
            });
            
        });
    }
}