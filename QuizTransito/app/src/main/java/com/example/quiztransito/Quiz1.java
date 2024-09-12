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

public class Quiz1 extends AppCompatActivity {

    private int qntRespostasCertas = 0; // Inicializa a variável
    private RadioGroup rgpQuiz1;
    private RadioButton rbtQ1PrimeiraOpcao;
    private RadioButton rbtQ1SegundaOpcao;
    private RadioButton rbtQ1TerceiraOpcao;
    private RadioButton rbtQ1QuartaOpcao;
    private Button btnQ1Proximo;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // JAVA - XML
        rgpQuiz1 = findViewById(R.id.rgpQuiz1);
        rbtQ1PrimeiraOpcao = findViewById(R.id.rbtQ1PrimeiraOpcao);
        rbtQ1SegundaOpcao = findViewById(R.id.rbtQ1SegundaOpcao);
        rbtQ1TerceiraOpcao = findViewById(R.id.rbtQ1TerceiraOpcao);
        rbtQ1QuartaOpcao = findViewById(R.id.rbtQ1QuartaOpcao);
        btnQ1Proximo = findViewById(R.id.btnQ1Proximo);

        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("nomeUsuario");

        // Configura o listener para o RadioGroup
        rgpQuiz1.setOnCheckedChangeListener((group, checkedId) -> {
            // Habilita o botão se um RadioButton for selecionado, desabilita caso contrário
            btnQ1Proximo.setEnabled(checkedId != -1);
        });
    }

    public void proximo(View view) {
        int selecionado = rgpQuiz1.getCheckedRadioButtonId();

        // Verifica se nenhuma opção foi selecionada e emite uma mensagem para o usuário
        if (selecionado == -1) {
            Toast.makeText(this, "Por favor, selecione uma opção!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selecionado == R.id.rbtQ1TerceiraOpcao) {
            qntRespostasCertas++;
            Toast.makeText(this, "Resposta Certa ✔", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Resposta Errada ❌", Toast.LENGTH_SHORT).show();
        }
        // Criar um Intent para iniciar a próxima atividade
        Intent quiz2 = new Intent(this, Quiz2.class);

        // Enviar a quantidade de respostas corretas para a próxima atividade
        quiz2.putExtra("qntRespostasCertas", qntRespostasCertas);
        quiz2.putExtra("nomeUsuario", nomeUsuario);

        // Iniciar a próxima atividade
        startActivity(quiz2);
        finish();
    }
}
