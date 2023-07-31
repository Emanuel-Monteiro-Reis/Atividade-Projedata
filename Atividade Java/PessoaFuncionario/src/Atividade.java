//Observação: As classes ATIVIDADE, PESSOA e FUNCIONARIO foram criadas em arquivos separados.
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Atividade {

	public static void main(String[] args) {
	ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	ArrayList<Funcionario> funcionariosValidos = new ArrayList<Funcionario>();
	
	Map<String, ArrayList<Funcionario>> mapaFuncionarios = new HashMap<>();
	
	//Objetos de formatação
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	BigDecimal salarioTotal = BigDecimal.ZERO;
	
	//3.1 - Inserir funcionarios
	funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")),new BigDecimal(2009.44), "Operador"));
	funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy")),new BigDecimal(2284.38), "Operador"));
	funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", DateTimeFormatter.ofPattern("dd/MM/yyyy")),new BigDecimal(9836.14), "Coordenador"));
	funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")),new BigDecimal(19119.88), "Diretor"));
	funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy")),new BigDecimal(2234.68), "Recepcionista"));
	funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy")),new BigDecimal(1582.72), "Operador"));
	funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy")),new BigDecimal(4071.84), "Contador"));
	funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", DateTimeFormatter.ofPattern("dd/MM/yyyy")),new BigDecimal(3017.45), "Gerente"));
	funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy")),new BigDecimal(1606.85), "Eletricista"));
	funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", DateTimeFormatter.ofPattern("dd/MM/yyyy")),new BigDecimal(2799.93), "Gerente"));	
	
	//3.2 - Loop para remover Funcionário "João" da lista
	for(Funcionario funcionario : funcionarios) {
		if(!funcionario.nome.equalsIgnoreCase("João")) {
			funcionariosValidos.add(funcionario);
		}
	}
	
	//3.3 - Impressão dos valores, utilizar "|" como separador em um arquivo CSV
	System.out.println("--------------------------------------------------");
	System.out.println("3.3 – Imprimir todos os funcionários com todas suas informações:");
	System.out.println("Nome|Data Nascimento|Salário|Função");
	
	for(Funcionario funcionario : funcionariosValidos) {
			System.out.println(funcionario.nome + "|" + funcionario.dataNascimento.format(dateTimeFormatter) + "|" + String.format("%,.2f", funcionario.salario) + "|" + funcionario.funcao);
	}
	
	//3.4 - Atualizar lista de salários com 10% de aumento
	for(Funcionario funcionario : funcionariosValidos) {
		funcionario.salario =  funcionario.salario.add(funcionario.salario.multiply(new BigDecimal(0.1)));
	}
	
	System.out.println("--------------------------------------------------");
	
	//3.4 - Impressão dos valores, utilizar "|" como separador em um arquivo CSV
	System.out.println("3.4 - Impressão de novos valores atualizados (10% AUMENTO):");
	System.out.println("--------------------------------------------------");
	System.out.println("Nome|Data Nascimento|Salário|Função");
	
	for(Funcionario funcionario : funcionariosValidos) {
			System.out.println(funcionario.nome + "|" + funcionario.dataNascimento.format(dateTimeFormatter) + "|" + String.format("%,.2f", funcionario.salario) + "|" + funcionario.funcao);
	}
	
	//3.5 - Funcao para adicionar funcionarios em função MAP:
	for(Funcionario funcionario : funcionariosValidos) {
		mapaFuncionarios.put(funcionario.funcao, new ArrayList<Funcionario>());
	}
	
	for(Map.Entry<String, ArrayList<Funcionario>> entry : mapaFuncionarios.entrySet()){
		for(Funcionario funcionario : funcionariosValidos) {
			if(funcionario.funcao.equalsIgnoreCase(entry.getKey().toString())) {
				ArrayList<Funcionario> funcionarioMap = new ArrayList<Funcionario>();
				funcionarioMap = entry.getValue();
				funcionarioMap.add(funcionario);
				mapaFuncionarios.put(funcionario.funcao, funcionarioMap);
			}
		}
	}
	
	System.out.println("--------------------------------------------------");
	System.out.println("3.6 - Impressão de novos valores atualizados (MAP agrupando por funcao):");
	System.out.println("--------------------------------------------------");
	System.out.println("Função|Nome");
	
	for (Map.Entry<String, ArrayList<Funcionario>> entry : mapaFuncionarios.entrySet()) {
		for(Funcionario funcionario : entry.getValue()) {
			System.out.println(entry.getKey() + "|" + funcionario.nome);
		}	
	}
	
	System.out.println("--------------------------------------------------");
	System.out.println("3.8 - Impressão de funcionários que fazem aniversário no mês 10 e 12:");
	System.out.println("--------------------------------------------------");
	System.out.println("Função|Nome|Data aniversário");
	
	for (Map.Entry<String, ArrayList<Funcionario>> entry : mapaFuncionarios.entrySet()) {
		for(Funcionario funcionario : entry.getValue()) {
			if(funcionario.dataNascimento.getMonthValue() == 10
					|| funcionario.dataNascimento.getMonthValue() == 12) {
				System.out.println(entry.getKey() + "|" + funcionario.nome + "|" + funcionario.dataNascimento.format(dateTimeFormatter));
			}
		}	
	}
	
	System.out.println("--------------------------------------------------");
	System.out.println("3.9 - Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade:");
	System.out.println("--------------------------------------------------");
	System.out.println("Nome|Idade");
	
	for (Map.Entry<String, ArrayList<Funcionario>> entry : mapaFuncionarios.entrySet()) {
		for(Funcionario funcionario : entry.getValue()) {
			if(Year.now().getValue() - funcionario.dataNascimento.getYear() >= 18) {
				System.out.println(funcionario.nome + "|" + (Year.now().getValue() - funcionario.dataNascimento.getYear()));
			}
		}	
	}
	
	
	System.out.println("--------------------------------------------------");
	System.out.println("3.10 - Imprimir a lista de funcionários por ordem alfabética:");
	System.out.println("--------------------------------------------------");
	System.out.println("Nome");
	
	//3.4 - Função para ordenedar ArrayList, utilizando nome como parametro.
	Collections.sort(funcionariosValidos, (i1, i2) -> i1.nome .compareTo(i2.nome));
	
	for(Funcionario funcionario : funcionariosValidos) {
		System.out.println(funcionario.nome);
	}
	
	System.out.println("--------------------------------------------------");
	System.out.println("3.11 - Imprimir o total dos salários dos funcionários:");
	System.out.println("--------------------------------------------------");
	System.out.println("Salário total");
	
	for(Funcionario funcionario : funcionariosValidos) {
		salarioTotal = salarioTotal.add(funcionario.salario);
	}
	
	System.out.println(String.format("%,.2f", salarioTotal));
	
	System.out.println("--------------------------------------------------");
	System.out.println("3.12 - Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00:");
	System.out.println("--------------------------------------------------");
	System.out.println("Função|Nome|Salário|Quantidade de salários mínimos");
	
	for(Funcionario funcionario : funcionariosValidos) {
		System.out.println(funcionario.funcao + "|" + funcionario.nome + "|" + String.format("%,.2f", funcionario.salario) + "|" 
							+ String.format("%,.2f", funcionario.salario.divide(new BigDecimal(1212), RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP)));
	}
	
	}

}
