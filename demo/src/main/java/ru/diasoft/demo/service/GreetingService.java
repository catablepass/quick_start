package ru.diasoft.demo.service;

import ru.diasoft.demo.domain.Greeting;

import java.util.List;

public interface GreetingService {

    public List<Greeting> getAll();

    public Greeting get(long id);

    public Greeting add(Greeting greeting);

    public Greeting update(Greeting greeting);

    public void remove(long id);
}
