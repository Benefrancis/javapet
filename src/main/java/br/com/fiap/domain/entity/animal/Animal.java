package br.com.fiap.domain.entity.animal;

import br.com.fiap.domain.entity.pessoa.Pessoa;

public class Animal {

    private Long id;

    private String nome;

    private String raca;

    private String descricao;

    private Pessoa dono;

    private String tipo;

    public Animal() {
    }


    public Animal(String tipo) {
        this.tipo = tipo;
    }

    public Animal(Long id, String nome, String raca, String descricao, Pessoa dono, String tipo) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.descricao = descricao;
        this.dono = dono;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public Animal setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Animal setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getRaca() {
        return raca;
    }

    public Animal setRaca(String raca) {
        this.raca = raca;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Animal setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Pessoa getDono() {
        return dono;
    }

    public Animal setDono(Pessoa dono) {
        this.dono = dono;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Animal setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dono=" + dono +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
