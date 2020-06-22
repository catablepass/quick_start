package ru.diasoft.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.diasoft.demo.domain.Greeting;

import java.util.List;
import java.util.Optional;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {

    List<Greeting> findAll();

    Optional<Greeting> findById(Long id);
}
