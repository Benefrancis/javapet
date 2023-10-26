package br.com.fiap.domain.entity.pessoa;

import java.time.LocalDate;

public class PJ extends Pessoa {

    private String CNPJ;

    public PJ(String CNPJ) {
        super( "PJ" );
        this.CNPJ = CNPJ;
    }

    public PJ(Long id, String nome, LocalDate nascimento, String CNPJ) {
        super( id, nome, nascimento, "PJ" );
        this.CNPJ = CNPJ;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public PJ setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
        return this;
    }

    @Override
    public String toString() {
        return "PJ{" +
                "CNPJ='" + CNPJ + '\'' +
                "} " + super.toString();
    }
}
