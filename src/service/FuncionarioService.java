
package service;

import exception.ListaVaziaException;
import exception.ValorInvalidoException;
import main.Main;
import model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FuncionarioService {

    public static void removeFuncionarioPorNome(List<Funcionario> funcionarios , String nome){
        for(int i=0;i<funcionarios.size();i++){
            if(Objects.equals(funcionarios.get(i).getNome(), nome)){
                funcionarios.remove(i);
                break;
            }
        }
    }

    public static double calculaSalarioTotal(List<Funcionario> funcionarios) {

        if(funcionarios.isEmpty()){
            throw new ListaVaziaException("Lista de Funcionários vazia!");
        }

        double salarioTotal = 0.0;
        for (int i = 0; i < funcionarios.size(); i++) {
            salarioTotal += funcionarios.get(i).getSalario().doubleValue();
        }
        return salarioTotal;
    }

    public static String imprimeFuncionario(Funcionario f){

        if(f == null){
            throw new ValorInvalidoException("Funcionário inválido!");
        }

        return "Nome: " + f.getNome() + "\n" +
                "Data de Nasc.: " + exibeData(f.getDataNasc()) + "\n" +
                "Salário: " + exibeSalario(f.getSalario()) + "\n" +
                "Função: " + f.getFuncao() + "\n";
    }

    public static void aumentaSalario(List<Funcionario> funcionarios){

        if(funcionarios.isEmpty()){
            throw new ListaVaziaException("Lista de Funcionários vazia!");
        }

        for(int i=0;i<funcionarios.size();i++){
            double salario = funcionarios.get(i).getSalario().doubleValue();
            funcionarios.get(i).setSalario(BigDecimal.valueOf(salario+(salario/10)));
        }
    }

    public static Map<String,List<Funcionario>> agrupaFuncionariosPorFuncao(List<Funcionario> funcionarios){

        if(funcionarios.isEmpty()){
            throw new ListaVaziaException("Lista de Funcionários vazia!");
        }

        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public static String exibeData(LocalDate data){
        String datas = data.toString();
        String[] datav = datas.split("-");
        return datav[2]+"/"+datav[1]+"/"+datav[0];
    }

    public static String exibeSalario(BigDecimal salario){
        String salarios = String.valueOf(salario.doubleValue()).replace(".",",");
        if (salarios.split(",")[1].length()==1){
            salarios += "0";
        }

        String[] vetorSalario = salarios.split("");
        int c = 1;
        for(int i=vetorSalario.length-4;i>=0;i--){
            if(c%3==0){
                vetorSalario[i] = "."+vetorSalario[i];
            }
            c++;
        }

        String vetorSalarioS = "";
        for(int i=0;i<vetorSalario.length;i++){
            vetorSalarioS += vetorSalario[i];
        }

        return vetorSalarioS;
    }

    public static Integer calculaIdade(LocalDate nasc){
        return LocalDate.now().minusYears(nasc.getYear())
                .minusMonths(nasc.getMonth().getValue())
                .minusDays(nasc.getDayOfMonth())
                .getYear();
    }

}
