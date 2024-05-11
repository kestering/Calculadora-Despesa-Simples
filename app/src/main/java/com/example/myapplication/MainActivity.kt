// Nome: Thiago Kestering Barbosa
// RA: 210424

package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Binding para a view
    private lateinit var binding: ActivityMainBinding

    // Variáveis para os elementos da interface do usuário
    private lateinit var txtDespesa: EditText
    private lateinit var txtRenda: EditText
    private lateinit var btnEnviarDespesa: Button
    private lateinit var btnEnviarRenda: Button
    private lateinit var listDespesa: ListView
    private lateinit var txtSaldo: TextView



    // Adaptador para a lista de despesas
    private lateinit var adapter: ArrayAdapter<String>

    // Lista de despesas
    private val despesasList = ArrayList<String>()

    // Array de categorias pré-definidas
    val categorias = arrayOf("Comida", "Transporte", "Lazer")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialização do binding para a view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialização dos elementos da interface do usuário
        txtDespesa = findViewById(R.id.txtRegistroDespesa)
        btnEnviarDespesa = findViewById(R.id.btnEnviarDespesa)
        listDespesa = findViewById(R.id.listDespesa)
        txtRenda = findViewById(R.id.txtRegistroRenda)
        btnEnviarRenda = findViewById(R.id.btnEnviarRenda)
        txtSaldo = findViewById(R.id.txtSaldo)

        // Inicialização do Spinner para as categorias
        val spinnerCategoria: Spinner = findViewById(R.id.spinnerCategoria)
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategoria.adapter = spinnerAdapter

        // Inicialização do adaptador para a lista de despesas
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, despesasList)
        listDespesa.adapter = adapter

        // Configuração dos listeners dos botões
        btnEnviarDespesa.setOnClickListener {
            val despesa = txtDespesa.text.toString().trim()
            val categoria = spinnerCategoria.selectedItem.toString()
            if (despesa.isNotEmpty()) {
                adicionarDespesa(despesa, categoria)
                txtRenda.text.clear()
            }
        }
        btnEnviarRenda.setOnClickListener {
            val renda = txtRenda.text.toString().trim()
            val categoria = spinnerCategoria.selectedItem.toString()
            if (renda.isNotEmpty()) {
                adicionarRenda(renda, categoria)
                txtRenda.text.clear()
            }
        }

    }

    private fun adicionarRenda(renda: String, categoria: String) {
        // Formatando a renda com a categoria selecionada
        val rendaFormatada = "Renda: $renda R$ ($categoria)"

        // Adicionando a renda à lista de despesas
        despesasList.add(rendaFormatada)
        adapter.notifyDataSetChanged()

        // Obtendo o saldo atual
        val saldoAtual = txtSaldo.text.toString().replace(" R$", "").toFloatOrNull() ?: 0.0f

        // Convertendo a renda para float
        val rendaFloat = renda.toFloatOrNull() ?: 0.0f

        // Calculando o novo saldo
        val novoSaldo = saldoAtual + rendaFloat

        // Formatando o novo saldo
        val novoSaldoFormatado = "%.2f".format(novoSaldo)

        // Exibindo o novo saldo na TextView
        txtSaldo.text = "$novoSaldoFormatado R$"
    }

    private fun adicionarDespesa(despesa: String, categoria: String) {
        // Formatando a despesa
        val despesaFormatada = "Renda: $despesa R$ ($categoria)"

        // Adicionando a despesa à lista de despesas
        despesasList.add(despesaFormatada)
        adapter.notifyDataSetChanged()

        // Obtendo o saldo atual
        val saldoAtual = txtSaldo.text.toString().replace(" R$", "").toFloatOrNull() ?: 0.0f

        // Convertendo a despesa para float
        val despesaFloat = despesa.toFloatOrNull() ?: 0.0f

        // Calculando o novo saldo
        val novoSaldo = saldoAtual - despesaFloat

        // Formatando o novo saldo
        val novoSaldoFormatado = "%.2f".format(novoSaldo)

        // Exibindo o novo saldo na TextView
        txtSaldo.text = "$novoSaldoFormatado R$"
    }

}

