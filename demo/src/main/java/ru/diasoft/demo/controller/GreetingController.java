package ru.diasoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.demo.aop.Loggable;
import ru.diasoft.demo.domain.Greeting;
import ru.diasoft.demo.service.GreetingServiceImpl;

import java.util.List;

@Loggable
@RestController
@RequestMapping("greeting")
public class GreetingController {

    @Autowired
    private GreetingServiceImpl greetingService;

    @GetMapping
    public List<Greeting> list() {
        return greetingService.getAll();
    }

    @GetMapping("{id}")
    public Greeting getOne(@PathVariable long id) {
        return greetingService.get(id);
    }

    @PostMapping
    public Greeting addOne(@RequestBody Greeting greeting) {
        return greetingService.add(greeting);
    }

    @PutMapping("{id}")
    public Greeting update(@PathVariable long id, @RequestBody Greeting greeting) {
        return greetingService.update(id, greeting);
    }

    @DeleteMapping({"{id}"})
    public void delete(@PathVariable long id) {
        greetingService.remove(id);
    }
}
