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

public class Quiz3 extends AppCompatActivity {

    private int qntRespostasCertas;
    private RadioGroup rgpQuiz3;
    private RadioButton rbtQ3PrimeiraOpcao;
    private RadioButton rbtQ3SegundaOpcao;
    private RadioButton rbtQ3TerceiraOpcao;
    private RadioButton rbtQ3QuartaOpcao;
    private Button btnQ3Proximo;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // JAVA - XML
        rgpQuiz3 = findViewById(R.id.rgpQuiz3);
        rbtQ3PrimeiraOpcao = findViewById(R.id.rbtQ3PrimeiraOpcao);
        rbtQ3SegundaOpcao = findViewById(R.id.rbtQ3SegundaOpcao);
        rbtQ3TerceiraOpcao = findViewById(R.id.rbtQ3TerceiraOpcao);
        rbtQ3QuartaOpcao = findViewById(R.id.rbtQ3QuartaOpcao);
        btnQ3Proximo = findViewById(R.id.btnQ3Proximo);

        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");
        qntRespostasCertas = intent.getIntExtra("qntRespostasCertas", 0);

        // Configura o listener para o RadioGroup
        rgpQuiz3.setOnCheckedChangeListener((group, checkedId) -> {
            // Habilita o botão se um RadioButton for selecionado, desabilita caso contrário
            btnQ3Proximo.setEnabled(checkedId != -1);
        });
    }

    public void proximo(View view) {
        int selecionado = rgpQuiz3.getCheckedRadioButtonId();

        // Verifica se nenhuma opção foi selecionada e emite uma mensagem para o usuário
        if (selecionado == -1) {
            Toast.makeText(this, "Por favor, selecione uma opção!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selecionado == R.id.rbtQ3SegundaOpcao) {  // A segunda opção é a correta
            qntRespostasCertas++;
            Toast.makeText(this, "Resposta Certa ✔", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resposta Errada ❌", Toast.LENGTH_SHORT).show();
        }

        // Criar um Intent para iniciar a próxima atividade
        Intent quiz4 = new Intent(this, Quiz4.class);

        // Enviar a quantidade de respostas corretas para a próxima atividade
        quiz4.putExtra("qntRespostasCertas", qntRespostasCertas);
        quiz4.putExtra("nomeUsuario", nomeUsuario);

        // Iniciar a próxima atividade
        startActivity(quiz4);
        finish();
    }
}
