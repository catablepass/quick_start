package ru.diasoft.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.demo.domain.Greeting;
import ru.diasoft.demo.repository.GreetingRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GreetingServiceImplTest {

    @TestConfiguration
    static class GreetingServiceImplTestContextConfiguration {

        @Bean
        public GreetingService greetingService() {
            return new GreetingServiceImpl();
        }
    }

    @Autowired
    private GreetingService greetingService;

    @MockBean
    private GreetingRepository greetingRepo;

    @Before
    public void setUp() {
        Greeting test = new Greeting("Hello, test!");

        when(greetingRepo.findById(anyLong()))
                .thenReturn(java.util.Optional.of(test));
    }

    @Test
    public void getTest() {
        Greeting expected = new Greeting("Hello, test!");
        Greeting found = greetingService.get(1L);

        assertEquals(expected.getContent(), found.getContent());
    }
}
