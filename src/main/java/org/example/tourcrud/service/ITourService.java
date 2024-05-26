package org.example.tourcrud.service;

import org.example.tourcrud.model.Tour;
import org.example.tourcrud.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface ITourService extends IGenerateService<Tour> {
    Iterable<Tour> findAllByType(Type type);
}
