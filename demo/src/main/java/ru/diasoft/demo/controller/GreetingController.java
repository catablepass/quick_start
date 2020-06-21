package ru.diasoft.demo.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;
import ru.diasoft.demo.aop.Loggable;
import ru.diasoft.demo.domain.Greeting;
import ru.diasoft.demo.exception.GreetingNotFoundException;

@Loggable
@RestController
@RequestMapping("greeting")
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private List<Greeting> greetings = new LinkedList<>();

    @GetMapping
    public List<Greeting> list() {
        return greetings;
    }

    @GetMapping("{id}")
    public Greeting getOne(@PathVariable long id) {
        return getGreeting(id);
    }

    private Greeting getGreeting(@PathVariable long id) {
        return greetings.stream()
                .filter(greeting -> greeting.getId()==id)
                .findFirst()
                .orElseThrow(GreetingNotFoundException::new);
    }

    @PostMapping
    public Greeting addOne(@RequestBody Greeting greeting) {
        greeting.setId(counter.incrementAndGet());
        greeting.setContent(String.format(template, greeting.getContent()));
        greetings.add(greeting);

        return greeting;
    }

    @PutMapping({"{id}"})
    public Greeting update(@PathVariable long id, @RequestBody Greeting greeting) {
        Greeting foundGreeting = getGreeting(id);

        foundGreeting.setContent(String.format(template, greeting.getContent()));

        return foundGreeting;
    }

    @DeleteMapping({"{id}"})
    public void delete(@PathVariable long id) {
        Greeting foundGreeting = getGreeting(id);

        greetings.remove(foundGreeting);
    }


}
