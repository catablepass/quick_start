package ru.diasoft.demo.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.demo.domain.Greeting;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GreetingRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GreetingRepository greetingRepo;

    @Test
    public void findAllTest() {

        int expectedSize = greetingRepo.findAll().size() + 1;

        Greeting testGreet = new Greeting("Hello, Anybody!");
        entityManager.persist(testGreet);
        entityManager.flush();

        Assert.assertEquals(expectedSize, greetingRepo.findAll().size());
    }
}