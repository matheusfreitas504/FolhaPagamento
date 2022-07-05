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
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaMatheusFreitas();
			// Define a consulta
			String sql = "delete from funcionario where cpf=?" ;
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setString(1, cpf); // Substitui o primeiro parâmetro da consulta pela agência informada
			ps.execute();
		} catch (SQLException erro) {
	
			}
		}
	

	// Define a conexão

	public boolean consultarCpf(String cpf) {
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaMatheusFreitas();
			// Define a consulta
			String sql = "select * from funcionario where cpf=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setString(1, cpf); // Substitui o primeiro parâmetro da consulta pela agência informada
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
				return false; // Conta não cadastrada
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
			// Define a conexão
			Connection conexao = null;
			try {
				// Define a conexão
				conexao = Conexao.conectaMatheusFreitas();
				// Define a consulta
		        String sql = "update funcionario set nome=?, pix=?, salario=? where cpf=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);				
				// Define os parâmetros da atualização
				ps.setString(1, nome); // Substitui o terceiro parâmetro da consulta pelo titular informado
				ps.setString(2, pix); // Substitui o terceiro parâmetro da consulta pelo titular informado				
				ps.setDouble(3, salario); // Substitui o terceiro parâmetro da consulta pelo titular informado	
				ps.setString(4, cpf); // Substitui o terceiro parâmetro da consulta pelo titular informado				
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
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
		// Define a conexão
		Connection conexao = null;
		try {
			conexao = Conexao.conectaMatheusFreitas();
			// Define a consulta
			String sql = "insert into funcionario set cpf=?, nome=?, pix=?, salario=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os parâmetros da consulta
			ps.setString(1, cpf); // Substitui o primeiro parâmetro da consulta pela agência informada
			ps.setString(2, nome); // Substitui o segundo parâmetro da consulta pela conta informada
			ps.setString(3, pix); // Substitui o terceiro parâmetro da consulta pelo titular informado
			ps.setDouble(4, salario); // Substitui o terceiro parâmetro da consulta pelo titular informado
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
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









