import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa{
	
BigDecimal salario;
String funcao;

public Funcionario(BigDecimal salario, String funcao) {
	super();
	this.salario = salario;
	this.funcao = funcao;
}

public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
	super(nome, dataNascimento);
	this.salario = salario;
	this.funcao = funcao;
}

}
