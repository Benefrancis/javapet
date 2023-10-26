package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.servico.Servico;
import br.com.fiap.domain.repository.ServicoRepository;

import java.util.List;

public class ServicoService implements Service<Servico, Long> {

    ServicoRepository repo = ServicoRepository.build();

    @Override
    public List<Servico> findAll() {
        return repo.findAll();
    }

    @Override
    public Servico findById(Long id) {
        return repo.findById( id );
    }

    @Override
    public Servico persiste(Servico servico) {
        return repo.persiste( servico );
    }
}
