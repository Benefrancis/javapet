package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.pessoa.PJ;
import br.com.fiap.domain.repository.PJRepository;

import java.util.List;

public class PJService implements Service<PJ, Long> {

    private PJRepository repo = PJRepository.build();

    @Override
    public List<PJ> findAll() {
        return repo.findAll();
    }

    @Override
    public PJ findById(Long id) {
        return repo.findById( id );
    }

    @Override
    public PJ persiste(PJ pj) {
        return repo.persiste( pj );
    }
}
