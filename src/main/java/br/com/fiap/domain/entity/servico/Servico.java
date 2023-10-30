package br.com.fiap.domain.entity.servico;

import br.com.fiap.domain.entity.animal.Animal;

import java.time.LocalDate;

public class Servico {

    private Long id;

    private String descricao;

    private Animal animal;

    private LocalDate realizacao;

    private String tipo;

    public Servico() {
    }

    public Servico(String tipo) {
        this.tipo = tipo;
    }

    public Servico(Long id, String descricao, Animal animal, LocalDate realizacao, String tipo) {
        this.id = id;
        this.descricao = descricao;
        this.animal = animal;
        this.realizacao = realizacao;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public Servico setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Servico setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Servico setAnimal(Animal animal) {
        this.animal = animal;
        return this;
    }

    public LocalDate getRealizacao() {
        return realizacao;
    }

    public Servico setRealizacao(LocalDate realizacao) {
        this.realizacao = realizacao;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Servico setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }


    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", animal=" + animal +
                ", realizacao=" + realizacao +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
