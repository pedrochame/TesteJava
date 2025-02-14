package main;
import model.Funcionario;
import model.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // Lista de Funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        // Tópico 3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        funcionarios.add(new Funcionario("João",LocalDate.of(1990,1,1),BigDecimal.valueOf(2000),"Desenvolvedor"));
        funcionarios.add(new Funcionario("Maria",LocalDate.of(1985,3,15),BigDecimal.valueOf(4000), "Analista"));
        funcionarios.add(new Funcionario("José",LocalDate.of(1992,7,22),BigDecimal.valueOf(2500), "Tester"));
        funcionarios.add(new Funcionario("Ana",LocalDate.of(1980,9,10),BigDecimal.valueOf(3500), "Gerente"));
        funcionarios.add(new Funcionario("Pedro",LocalDate.of(1988,12,3),BigDecimal.valueOf(2800), "Gerente"));

        // Tópico 3.2 - Remover o funcionário “João” da lista
        removeFuncionarioPorNome(funcionarios,"João");

        // Tópico 3.3 - Imprimir todos os funcionários com todas suas informações, sendo que:
        //		• informação de data deve ser exibido no formato dd/mm/aaaa;
        //		• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        for(int i=0;i<funcionarios.size();i++){
            System.out.println(
                    "Nome: "+funcionarios.get(i).getNome()+"\n"+
                    "Data de Nasc.: "+exibeData(funcionarios.get(i).getDataNasc())+"\n"+
                    "Salário: "+exibeSalario(funcionarios.get(i).getSalario())+"\n"+
                    "Função: "+funcionarios.get(i).getFuncao()+"\n"
            );
        }

        // Tópico 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        for(int i=0;i<funcionarios.size();i++){
            double salario = funcionarios.get(i).getSalario().doubleValue();
            funcionarios.get(i).setSalario(BigDecimal.valueOf(salario+(salario/10)));
        }

        // Tópico 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String,List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // Tópico 3.6 – Imprimir os funcionários, agrupados por função.
        System.out.println("Funcionários agrupados por função:");
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println(funcao+":");
            for (Funcionario f : funcionariosPorFuncao.get(funcao)) {
                System.out.println(
                        "Nome: " + f.getNome() + "\n" +
                                "Data de Nasc.: " + exibeData(f.getDataNasc()) + "\n" +
                                "Salário: " + exibeSalario(f.getSalario()) + "\n" +
                                "Função: " + f.getFuncao() + "\n"
                );
            }
        }

        // Tópico 3.7 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("Funcionários que fazem aniversário no mês 10 e 12:");
        for(int i=0;i<funcionarios.size();i++){
            if(funcionarios.get(i).getDataNasc().getMonth().getValue() == 10 || funcionarios.get(i).getDataNasc().getMonth().getValue() == 12) {
                System.out.println(
                        "Nome: " + funcionarios.get(i).getNome() + "\n" +
                                "Data de Nasc.: " + exibeData(funcionarios.get(i).getDataNasc()) + "\n" +
                                "Salário: " + exibeSalario(funcionarios.get(i).getSalario()) + "\n" +
                                "Função: " + funcionarios.get(i).getFuncao() + "\n"
                );
            }
        }

        // Tópico 3.8 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Optional<Funcionario> func = funcionarios.stream().min(Comparator.comparing(Pessoa::getDataNasc));
        if (func.isPresent()) {
            System.out.println("Funcionário com a maior idade:");
            System.out.println(
                    "Nome: " + func.get().getNome() + "\n" +
                            "Idade: " + calculaIdade(func.get().getDataNasc()) + "\n"
            );
        }


        // Tópico 3.9 – Imprimir a lista de funcionários por ordem alfabética.
        funcionarios.sort(Comparator.comparing(Pessoa::getNome));
        System.out.println("Lista de Funcionários por ordem alfabética:");
        for(int i = 0; i< funcionarios.size(); i++) {
            System.out.println(
                            "Nome: " + funcionarios.get(i).getNome() + "\n" +
                            "Data de Nasc.: " + exibeData(funcionarios.get(i).getDataNasc()) + "\n" +
                            "Salário: " + exibeSalario(funcionarios.get(i).getSalario()) + "\n" +
                            "Função: " + funcionarios.get(i).getFuncao() + "\n"
            );
        }

        // Tópico 3.10 – Imprimir o total dos salários dos funcionários.
        double salarioTotal = 0.0;
        for(int i=0;i<funcionarios.size();i++){
            salarioTotal += funcionarios.get(i).getSalario().doubleValue();
        }
        System.out.println("Salário total dos funcionários: "+exibeSalario(BigDecimal.valueOf(salarioTotal)));

        // Tópico 3.11 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00
        for(int i=0;i<funcionarios.size();i++) {
            Funcionario f = funcionarios.get(i);
            double qt = f.getSalario().doubleValue()/1212.00;
            System.out.println(f.getNome()+" recebe "+
                    f.getSalario()+" -> " +
                    qt+" salários mínimos.");
        }
    }

    //Funções auxiliares

    public static void removeFuncionarioPorNome(List<Funcionario> funcionarios , String nome){
        for(int i=0;i<funcionarios.size();i++){
            if(Objects.equals(funcionarios.get(i).getNome(), nome)){
                funcionarios.remove(i);
                break;
            }
        }
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