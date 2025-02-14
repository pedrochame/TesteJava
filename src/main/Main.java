package main;
import model.Funcionario;
import model.Pessoa;
import service.FuncionarioService;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        FuncionarioService.removeFuncionarioPorNome(funcionarios,"João");

        // Tópico 3.3 - Imprimir todos os funcionários com todas suas informações, sendo que:
        //		• informação de data deve ser exibido no formato dd/mm/aaaa;
        //		• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        for(int i=0;i<funcionarios.size();i++){
            System.out.println(FuncionarioService.imprimeFuncionario(funcionarios.get(i)));
        }

        // Tópico 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        FuncionarioService.aumentaSalario(funcionarios);

        // Tópico 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String,List<Funcionario>> funcionariosPorFuncao = FuncionarioService.agrupaFuncionariosPorFuncao(funcionarios);

        // Tópico 3.6 – Imprimir os funcionários, agrupados por função.
        System.out.println("Funcionários agrupados por função:");
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println(funcao+":");
            for (Funcionario f : funcionariosPorFuncao.get(funcao)) {
                System.out.println(FuncionarioService.imprimeFuncionario(f));
            }
        }

        // Tópico 3.7 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("Funcionários que fazem aniversário no mês 10 e 12:");
        for(int i=0;i<funcionarios.size();i++){
            if(funcionarios.get(i).getDataNasc().getMonth().getValue() == 10 || funcionarios.get(i).getDataNasc().getMonth().getValue() == 12) {
                System.out.println(FuncionarioService.imprimeFuncionario(funcionarios.get(i)));
            }
        }

        // Tópico 3.8 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Optional<Funcionario> func = funcionarios.stream().min(Comparator.comparing(Pessoa::getDataNasc));
        if (func.isPresent()) {
            System.out.println("Funcionário com a maior idade:");
            System.out.println(
                    "Nome: " + func.get().getNome() + "\n" +
                            "Idade: " + FuncionarioService.calculaIdade(func.get().getDataNasc()) + "\n"
            );
        }


        // Tópico 3.9 – Imprimir a lista de funcionários por ordem alfabética.
        funcionarios.sort(Comparator.comparing(Pessoa::getNome));
        System.out.println("Lista de Funcionários por ordem alfabética:");
        for(int i = 0; i< funcionarios.size(); i++) {
            System.out.println(FuncionarioService.imprimeFuncionario(funcionarios.get(i)));
        }

        // Tópico 3.10 – Imprimir o total dos salários dos funcionários.
        System.out.println("Salário total dos funcionários: "+FuncionarioService.exibeSalario(BigDecimal.valueOf(FuncionarioService.calculaSalarioTotal(funcionarios))));

        // Tópico 3.11 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00
        for(int i=0;i<funcionarios.size();i++) {
            Funcionario f = funcionarios.get(i);
            double qt = f.getSalario().doubleValue()/1212.00;
            System.out.println(f.getNome()+" recebe "+
                    f.getSalario()+" -> " +
                    qt+" salários mínimos.");
        }
    }

}