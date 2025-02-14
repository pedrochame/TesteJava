package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {

    private String nome;
    private LocalDate dataNasc;

    //Constructor
    public Pessoa(String n, LocalDate d){
        this.nome = n;
        this.dataNasc = d;
    }

    //Getter e Setter de Nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Getter e Setter de Data de Nascimento
    public LocalDate getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }


}
