import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GestaoEstoque extends JFrame {
    private static final Map<String, Produto> estoque = new HashMap<>();
    private JTextField nomeField;
    private JTextField quantidadeField;
    private JTextArea displayArea;

    public GestaoEstoque() {
        setTitle("Gestão de Estoque");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cria os componentes da interface
        nomeField = new JTextField(20);
        quantidadeField = new JTextField(20);
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Painel para adicionar produtos
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Nome do Produto:"));
        panel.add(nomeField);
        panel.add(new JLabel("Quantidade:"));
        panel.add(quantidadeField);

        // Botões
        JButton addButton = new JButton("Adicionar Produto");
        JButton updateButton = new JButton("Atualizar Quantidade");
        JButton removeButton = new JButton("Remover Produto");
        JButton displayButton = new JButton("Exibir Estoque");
        
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(removeButton);
        panel.add(displayButton);

        // Adiciona o painel e a área de texto à janela
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Adiciona ações aos botões
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarQuantidade();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerProduto();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirEstoque();
            }
        });
    }

    private void adicionarProduto() {
        String nome = nomeField.getText();
        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeField.getText());
            Produto produto = new Produto(nome, quantidade);
            estoque.put(nome, produto);
            displayArea.append("Produto adicionado: " + produto + "\n");
        } catch (NumberFormatException e) {
            displayArea.append("Quantidade inválida!\n");
        }
    }

    private void atualizarQuantidade() {
        String nome = nomeField.getText();
        if (estoque.containsKey(nome)) {
            int quantidade;
            try {
                quantidade = Integer.parseInt(quantidadeField.getText());
                Produto produto = estoque.get(nome);
                produto.setQuantidade(quantidade);
                displayArea.append("Quantidade atualizada: " + produto + "\n");
            } catch (NumberFormatException e) {
                displayArea.append("Quantidade inválida!\n");
            }
        } else {
            displayArea.append("Produto não encontrado!\n");
        }
    }

    private void removerProduto() {
        String nome = nomeField.getText();
        if (estoque.containsKey(nome)) {
            estoque.remove(nome);
            displayArea.append("Produto removido: " + nome + "\n");
        } else {
            displayArea.append("Produto não encontrado!\n");
        }
    }

    private void exibirEstoque() {
        if (estoque.isEmpty()) {
            displayArea.append("Estoque vazio!\n");
        } else {
            displayArea.append("Estoque:\n");
            for (Produto produto : estoque.values()) {
                displayArea.append(produto + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestaoEstoque().setVisible(true);
            }
        });
    }
}