package com.example.quiztransito;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quiz5 extends AppCompatActivity {

    private int qntRespostasCertas;
    private RadioGroup rgpQuiz5;
    private RadioButton rbtQ5PrimeiraOpcao;
    private RadioButton rbtQ5SegundaOpcao;
    private RadioButton rbtQ5TerceiraOpcao;
    private RadioButton rbtQ5QuartaOpcao;
    private Button btnQ5Proximo;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // JAVA - XML
        rgpQuiz5 = findViewById(R.id.rgpQuiz5);
        rbtQ5PrimeiraOpcao = findViewById(R.id.rbtQ5PrimeiraOpcao);
        rbtQ5SegundaOpcao = findViewById(R.id.rbtQ5SegundaOpcao);
        rbtQ5TerceiraOpcao = findViewById(R.id.rbtQ5TerceiraOpcao);
        rbtQ5QuartaOpcao = findViewById(R.id.rbtQ5QuartaOpcao);
        btnQ5Proximo = findViewById(R.id.btnQ5Proximo);

        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");
        qntRespostasCertas = intent.getIntExtra("qntRespostasCertas", 0);

        // Configura o listener para o RadioGroup
        rgpQuiz5.setOnCheckedChangeListener((group, checkedId) -> {
            // Habilita o botão se um RadioButton for selecionado, desabilita caso contrário
            btnQ5Proximo.setEnabled(checkedId != -1);
        });

    }

    public void proximo(View view) {
        int selecionado = rgpQuiz5.getCheckedRadioButtonId();

        // Verifica se nenhuma opção foi selecionada e emite uma mensagem para o usuário
        if (selecionado == -1) {
            Toast.makeText(this, "Por favor, selecione uma opção!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selecionado == R.id.rbtQ5PrimeiraOpcao) {  // A primeira opção é a correta
            qntRespostasCertas++;
            Toast.makeText(this, "Resposta Certa ✔", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resposta Errada ❌", Toast.LENGTH_SHORT).show();
        }

        // Criar um Intent para iniciar a atividade de Resultado
        Intent resultado = new Intent(this, Resultado.class);

        // Enviar a quantidade de respostas corretas e o nome do usuário para a atividade de Resultado
        resultado.putExtra("qntRespostasCertas", qntRespostasCertas);
        resultado.putExtra("nomeUsuario", nomeUsuario);

        // Iniciar a atividade de Resultado
        startActivity(resultado);
        finish();
    }
}
