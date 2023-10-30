package br.com.fiap.domain.entity.pessoa;

import java.time.LocalDate;

public  class Pessoa {

    private Long id;

    private String nome;

    private LocalDate nascimento;

    private String tipo;


    public Pessoa() {
    }

    public Pessoa(String tipo) {
        this.tipo = tipo;
    }

    public Pessoa(Long id, String nome, LocalDate nascimento, String tipo) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public Pessoa setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Pessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Pessoa setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Pessoa setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }


    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
