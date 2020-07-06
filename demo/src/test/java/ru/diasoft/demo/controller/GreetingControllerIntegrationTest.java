package ru.diasoft.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.diasoft.demo.DemoApplication;
import ru.diasoft.demo.domain.Greeting;
import ru.diasoft.demo.repository.GreetingRepository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class GreetingControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private GreetingRepository greetingRepo;

    @Test
    public void getNewGreetingTest()
            throws Exception {

        Greeting test = new Greeting("Hello, Integration!");

        greetingRepo.save(test);

        mvc.perform(get("/greeting/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is("Hello, Integration!")));
    }
}
