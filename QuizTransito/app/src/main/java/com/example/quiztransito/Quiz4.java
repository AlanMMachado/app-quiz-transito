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

public class Quiz4 extends AppCompatActivity {

    private int qntRespostasCertas;
    private RadioGroup rgpQuiz4;
    private RadioButton rbtQ4PrimeiraOpcao;
    private RadioButton rbtQ4SegundaOpcao;
    private RadioButton rbtQ4TerceiraOpcao;
    private RadioButton rbtQ4QuartaOpcao;
    private Button btnQ4Proximo;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // JAVA - XML
        rgpQuiz4 = findViewById(R.id.rgpQuiz4);
        rbtQ4PrimeiraOpcao = findViewById(R.id.rbtQ4PrimeiraOpcao);
        rbtQ4SegundaOpcao = findViewById(R.id.rbtQ4SegundaOpcao);
        rbtQ4TerceiraOpcao = findViewById(R.id.rbtQ4TerceiraOpcao);
        rbtQ4QuartaOpcao = findViewById(R.id.rbtQ4QuartaOpcao);
        btnQ4Proximo = findViewById(R.id.btnQ4Proximo);

        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");
        qntRespostasCertas = intent.getIntExtra("qntRespostasCertas", 0);

        // Configura o listener para o RadioGroup
        rgpQuiz4.setOnCheckedChangeListener((group, checkedId) -> {
            // Habilita o botão se um RadioButton for selecionado, desabilita caso contrário
            btnQ4Proximo.setEnabled(checkedId != -1);
        });

    }

    public void proximo(View view) {
        int selecionado = rgpQuiz4.getCheckedRadioButtonId();

        // Verifica se nenhuma opção foi selecionada e emite uma mensagem para o usuário
        if (selecionado == -1) {
            Toast.makeText(this, "Por favor, selecione uma opção!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selecionado == R.id.rbtQ4PrimeiraOpcao) {  // A primeira opção é a correta
            qntRespostasCertas++;
            Toast.makeText(this, "Resposta Certa ✔", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resposta Errada ❌", Toast.LENGTH_SHORT).show();
        }

        // Criar um Intent para iniciar a próxima atividade
        Intent quiz5 = new Intent(this, Quiz5.class);

        // Enviar a quantidade de respostas corretas para a próxima atividade
        quiz5.putExtra("qntRespostasCertas", qntRespostasCertas);
        quiz5.putExtra("nomeUsuario", nomeUsuario);

        // Iniciar a próxima atividade
        startActivity(quiz5);
        finish();
    }
}
