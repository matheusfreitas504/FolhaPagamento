package folhapagamento;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Swing {
	public static void main(String[] args) {
		// Define a janela
		JFrame janela = new JFrame("Cadastro de Funcionário"); // Janela Normal
		janela.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setSize(420, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janela.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelPix = new JLabel("Chave Pix: ");
		JLabel labelSalario = new JLabel("Salário: ");
		JLabel labelCpf = new JLabel("CPF: ");
		// Posiciona os labels na janela
		labelNome.setBounds(50, 60, 100, 20); // coluna, linha, largura, tamanho
		labelPix.setBounds(50, 100, 150, 20); // coluna, linha, largura, tamanho
		labelSalario.setBounds(50, 140, 100, 20); // coluna, linha, largura, tamanho
		labelCpf.setBounds(50, 20, 100, 20); // coluna, linha, largura, tamanho		
		// Define os input box
		JTextField jTextNome = new JTextField();
		JTextField jTextPix = new JTextField();
		JTextField jTextSalario = new JTextField();
		JTextField jTextCpf = new JTextField();
		// Define se os campos estão habilitados ou não no início
		jTextNome.setEnabled(false);
		jTextPix.setEnabled(false);
		jTextSalario.setEnabled(false);
		jTextCpf.setEnabled(true);
		// Posiciona os input box
		jTextNome.setBounds(120, 60, 180, 20);
		jTextPix.setBounds(120, 100, 100, 20);
		jTextSalario.setBounds(120, 140, 100, 20);
		jTextCpf.setBounds(120, 20, 100, 20);		
		// Adiciona os rótulos e os input box na janela
		janela.add(labelNome);
		janela.add(labelPix);
		janela.add(labelSalario);
		janela.add(labelCpf);		
		janela.add(jTextNome);
		janela.add(jTextPix);
		janela.add(jTextSalario);
		janela.add(jTextCpf);		
		// Define botões e a localização deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 20, 100, 20);
		janela.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 210, 100, 20);
		botaoGravar.setEnabled(false);
		janela.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(200, 210, 100, 20);
		janela.add(botaoLimpar);
		JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(200, 185, 100, 20);
		janela.add(botaoAtualizar);
		JButton botaoRemover = new JButton("Remover");		
		botaoRemover.setBounds(50, 185, 100, 20);
		janela.add(botaoRemover);		
		// Define objeto conta para pesquisar no banco de dados

		Funcionario funcionario = new Funcionario();

		
		// Define ações dos botões
	
		botaoAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	double salario = Double.parseDouble(jTextSalario.getText());
				String pix = jTextPix.getText();  
				String nome = jTextNome.getText(); 
				String cpf = jTextCpf.getText(); 
                funcionario.atualizarFuncionario(cpf,nome,pix,salario);
                jTextPix.setText("");
                jTextSalario.setText("");
                jTextCpf.setText("");
                jTextNome.setText("");
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String cpf = jTextCpf.getText();
                funcionario.deletarCpf(cpf);
                jTextNome.setText("");
                jTextPix.setText("");
                jTextSalario.setText("");
                jTextCpf.setText("");   
                JOptionPane.showMessageDialog(janela, "CPF removido com sucesso");
            }
        });									
	
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = jTextCpf.getText().trim();
				String nome;
				String pix;
				double salario;
				try {
					botaoGravar.setEnabled(true);
					if (!funcionario.consultarCpf(cpf))
						cpf = "";
					else {
						nome = funcionario.getNome();
						jTextNome.setText(nome);											
						pix = funcionario.getPix();
						jTextPix.setText(pix);
						salario = funcionario.getSalario();
						jTextSalario.setText(String.valueOf(salario));;											
					}
					jTextNome.setEnabled(true);
					jTextPix.setEnabled(true);
					jTextCpf.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextSalario.setEnabled(true);
					jTextSalario.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janela,
							"Preencha os campos corretamente!!");
				}
			}
		});
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nome = jTextNome.getText().trim();
					String cpf = jTextCpf.getText().trim(); 	
					String pix = jTextPix.getText().trim(); 
					double salario = Double.parseDouble(jTextSalario.getText().trim());	
			
				if (nome.length() == 0 || pix.length() == 0 || cpf.length() == 0) {
					JOptionPane.showMessageDialog(janela, "Todos os campos devem ser preenchidos.");
					jTextSalario.requestFocus();
					jTextCpf.requestFocus();
					jTextPix.requestFocus();
					jTextNome.requestFocus();
				}
				else {
					if (!funcionario.consultarCpf(cpf)) {
						if (!funcionario.cadastrarFuncionario(cpf, nome,pix, salario))
							JOptionPane.showMessageDialog(janela, "Erro na inclusão dos campos!");
						else
							JOptionPane.showMessageDialog(janela, "Inclusão realizada!");
					} else {
						if (!funcionario.atualizarFuncionario(cpf,nome,pix, salario))
							JOptionPane.showMessageDialog(janela, "Erro na atualização do Salario!");
						else
							JOptionPane.showMessageDialog(janela, "Alteração realizada!");
					}
				}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janela,
							"Todos os campos devem ser preenchidos.");
				}
			}
		});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextNome.setText(""); // Limpar campo
				jTextPix.setText(""); // Limpar campo
				jTextSalario.setText(""); // Limpar campo
				jTextCpf.setText(""); // Limpar campo	
				jTextCpf.setEnabled(true);
				jTextNome.setEnabled(true);
				jTextPix.setEnabled(true);
				jTextSalario.setEnabled(true);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextNome.requestFocus(); // Colocar o foco em um campo
			}
		});
		// Apresenta a janela
		janela.setVisible(true); // Exibe a janela
	}
}
