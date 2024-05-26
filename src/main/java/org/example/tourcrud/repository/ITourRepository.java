package org.example.tourcrud.repository;

import org.example.tourcrud.model.Tour;
import org.example.tourcrud.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface ITourRepository extends CrudRepository<Tour, Long> {
    Iterable<Tour> findAllByType(Type type);
}
