package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa{

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String n, LocalDate d, BigDecimal s, String f) {
        super(n, d);
        this.funcao = f;
        this.salario = s;
    }

    //Getter e Setter de Salário
    public BigDecimal getSalario() {
        return salario;
    }
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    //Getter e Setter de Função
    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

}