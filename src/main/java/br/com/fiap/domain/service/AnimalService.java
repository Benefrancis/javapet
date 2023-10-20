package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.animal.Animal;
import br.com.fiap.domain.repository.AnimalRepository;

import java.util.List;

public class AnimalService implements Service<Animal, Long> {

    AnimalRepository repo = AnimalRepository.build();

    @Override
    public List<Animal> findAll() {
        return repo.findAll();
    }

    @Override
    public Animal findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Animal persiste(Animal animal) {
        return repo.persiste(animal);
    }
}
