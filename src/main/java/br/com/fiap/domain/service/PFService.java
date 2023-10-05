package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.pessoa.PF;
import br.com.fiap.domain.repository.PFRepository;

import java.util.List;

public class PFService implements Service<PF , Long> {


    private PFRepository repo = PFRepository.build();


    @Override
    public List<PF> findAll() {
        return repo.findAll();
    }

    @Override
    public PF findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<PF> findByTexto(String texto) {
        return repo.findByTexto(texto);
    }

    @Override
    public PF persiste(PF pf) {
        return repo.persiste(pf);
    }

    @Override
    public PF update(Long id, PF pf) {

        //regra de negocio


        return repo.update(pf);
    }

    @Override
    public boolean delete(PF pf) {
        return repo.delete(pf);
    }
}
