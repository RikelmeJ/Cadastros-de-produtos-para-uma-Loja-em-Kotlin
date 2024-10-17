import java.util.Scanner

// Produto data class
data class Produto(val codigo: Int, var nome: String, var valorUnitario: Double, var unidade: String)

// Classe Lojinha do Sr. Abu
class LojinhaAbu {
    private val produtos = mutableListOf<Produto>()
    private val carrinho = mutableListOf<Pair<Produto, Int>>()

    // (a) Cadastro de Produtos
    fun cadastrarProduto(codigo: Int, nome: String, valorUnitario: Double, unidade: String) {
        val produto = Produto(codigo, nome, valorUnitario, unidade)
        produtos.add(produto)
        println("Produto ${produto.nome} cadastrado com sucesso!")
    }

    // Exibir produtos cadastrados
    fun exibirProdutos() {
        if (produtos.isEmpty()) {
            println("Nenhum produto cadastrado.")
        } else {
            println("\nProdutos disponíveis:")
            produtos.forEach { println("Código: ${it.codigo} | Nome: ${it.nome} | Valor: R$ ${it.valorUnitario} | Unidade: ${it.unidade}") }
        }
    }

    // (b) Alteração de Produtos
    fun alterarProduto(codigo: Int, novoNome: String, novoValor: Double, novaUnidade: String) {
        val produto = produtos.find { it.codigo == codigo }
        produto?.apply {
            nome = novoNome
            valorUnitario = novoValor
            unidade = novaUnidade
            println("Produto $nome alterado com sucesso!")
        } ?: println("Produto com código $codigo não encontrado.")
    }

    // (b) Excluir de produtos
    fun excluirProduto(codigo: Int) {
        val produto = produtos.find { it.codigo == codigo }
        if (produto != null) {
            produtos.remove(produto)
            println("Produto ${produto.nome} removido com sucesso!")
        } else {
            println("Produto com código $codigo não encontrado.")
        }
    }

    // (c) Realizar Venda
    fun adicionarAoCarrinho(codigo: Int, quantidade: Int) {
        val produto = produtos.find { it.codigo == codigo }
        if (produto != null) {
            carrinho.add(produto to quantidade)
            println("Adicionado $quantidade de ${produto.nome} ao carrinho.")
        } else {
            println("Produto com código $codigo não encontrado.")
        }
    }

    // Finalizar a Compra
    fun finalizarCompra() {
        var total = 0.0
        println("\nResumo da Compra:")
        carrinho.forEach { (produto, quantidade) ->
            val subtotal = produto.valorUnitario * quantidade
            println("${produto.nome} - $quantidade ${produto.unidade} - R$ $subtotal")
            total += subtotal
        }
        println("Total: R$ $total")
        FormaDePagamento(total)
        carrinho.clear()
    }

    // (d) Forma de Pagamento
    private fun FormaDePagamento(total: Double) {
        println("Selecione a forma de pagamento:")
        println("1. Pix")
        println("2. Cartão (Crédito/Débito)")
        println("3. Dinheiro")
        val opcao = readLine()?.toIntOrNull()

        when (opcao) {
            1 -> {
                println("Código PIX: 123456789")
                println("Compra finalizada com sucesso.")
            }
            2 -> {
                println("Digite os dados do cartão:")
                println("Número do cartão:")
                val numeroCartao = readLine()
                println("Nome no cartão:")
                val nomeCartao = readLine()
                println("Compra no cartão $nomeCartao finalizada com sucesso.")
            }
            3 -> {
                println("Digite o valor pago:")
                val valorPago = readLine()?.toDoubleOrNull()
                if (valorPago != null && valorPago >= total) {
                    val troco = valorPago - total
                    println("Troco: R$ $troco")
                    println("Compra finalizada com sucesso.")
                } else {
                    println("Valor insuficiente.")
                }
            }
            else -> println("Forma de pagamento inválida.")
        }
    }
}

fun main() {
    val loja = LojinhaAbu()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\n--- Menu Principal ---")
        println("1. Cadastrar Produto")
        println("2. Alterar Produto")
        println("3. Excluir Produto")
        println("4. Exibir Produtos")
        println("5. Adicionar Produto ao Carrinho")
        println("6. Finalizar Compra")
        println("0. Sair")
        println("Escolha uma opção:")

        when (scanner.nextInt()) {
            1 -> {
                // Cadastrar produto
                println("Digite o código do produto:")
                val codigo = scanner.nextInt()
                println("Digite o nome do produto:")
                val nome = scanner.next()
                println("Digite o valor unitário do produto:")
                val valor = scanner.nextDouble()
                println("Digite a unidade do produto (ex: kg, unidade):")
                val unidade = scanner.next()
                loja.cadastrarProduto(codigo, nome, valor, unidade)
            }
            2 -> {
                // Alterar produto
                println("Digite o código do produto a ser alterado:")
                val codigo = scanner.nextInt()
                println("Digite o novo nome do produto:")
                val nome = scanner.next()
                println("Digite o novo valor unitário do produto:")
                val valor = scanner.nextDouble()
                println("Digite a nova unidade do produto:")
                val unidade = scanner.next()
                loja.alterarProduto(codigo, nome, valor, unidade)
            }
            3 -> {
                // Excluir produto
                println("Digite o código do produto a ser excluído:")
                val codigo = scanner.nextInt()
                loja.excluirProduto(codigo)
            }
            4 -> {
                // Exibir produtos
                loja.exibirProdutos()
            }
            5 -> {
                // Adicionar ao carrinho
                println("Digite o código do produto:")
                val codigo = scanner.nextInt()
                println("Digite a quantidade:")
                val quantidade = scanner.nextInt()
                loja.adicionarAoCarrinho(codigo, quantidade)
            }
            6 -> {
                // Finalizar compra
                loja.finalizarCompra()
            }
            0 -> {
                println("Saindo...")
                break
            }
            else -> println("Opção inválida!")
        }
    }
}
