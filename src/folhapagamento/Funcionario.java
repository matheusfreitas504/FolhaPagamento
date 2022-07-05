package folhapagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Matheus Freitas
 * @see folhapagamento.Conexao
 * @see folhapagamento.Swing
 *
 */

public class Funcionario {

	private String cpf;
	private String nome;
	private String pix;
	private double salario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPix() {
		return pix;
	}

	public void setPix(String pix) {
		this.pix = pix;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void deletarCpf(String cpf) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaMatheusFreitas();
			// Define a consulta
			String sql = "delete from funcionario where cpf=?" ;
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setString(1, cpf); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
			ps.execute();
		} catch (SQLException erro) {
	
			}
		}
	

	// Define a conex�o

	public boolean consultarCpf(String cpf) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaMatheusFreitas();
			// Define a consulta
			String sql = "select * from funcionario where cpf=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setString(1, cpf); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se n�o est� antes do primeiro registro
				return false; // Conta n�o cadastrada
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.nome = rs.getString("nome");
					this.pix = rs.getString("pix");
					this.salario = rs.getDouble("salario");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar CPF: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarFuncionario(String cpf, String nome, String pix, double salario) {
		if (!consultarCpf(cpf))
			return false;
		else {
			// Define a conex�o
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaMatheusFreitas();
				// Define a consulta
		        String sql = "update funcionario set nome=?, pix=?, salario=? where cpf=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);				
				// Define os par�metros da atualiza��o
				ps.setString(1, nome); // Substitui o terceiro par�metro da consulta pelo titular informado
				ps.setString(2, pix); // Substitui o terceiro par�metro da consulta pelo titular informado				
				ps.setDouble(3, salario); // Substitui o terceiro par�metro da consulta pelo titular informado	
				ps.setString(4, cpf); // Substitui o terceiro par�metro da consulta pelo titular informado				
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("N�o foi feita a atualiza��o!");
				else
					System.out.println("Atualiza��o realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar funcionario: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	
	
	
	
	
	
	

	public boolean cadastrarFuncionario(String cpf, String nome, String pix, double salario) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaMatheusFreitas();
			// Define a consulta
			String sql = "insert into funcionario set cpf=?, nome=?, pix=?, salario=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setString(1, cpf); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
			ps.setString(2, nome); // Substitui o segundo par�metro da consulta pela conta informada
			ps.setString(3, pix); // Substitui o terceiro par�metro da consulta pelo titular informado
			ps.setDouble(4, salario); // Substitui o terceiro par�metro da consulta pelo titular informado
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("N�o foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar o funcionario: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

}









