package br.com.ismael;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(MoveRobotsController.class)
@TestPropertySource("classpath:application.properties")
public class MoveRobotsControllerTest {

    @Value("${server.contextPath}")
    private String contextPath;
    @Autowired
    private MockMvc mvc;

    @Test
    public void test() throws Exception {
        List<String> listValidTests = new ArrayList<>();
        List<String> listInvalidTests = new ArrayList<>();
        listValidTests.add("MMRMMRMM");
        listValidTests.add("MML");
        listInvalidTests.add("AAA");
        listInvalidTests.add("MMMMMMMMMMMMMMMMMMMMMMMM");
        for (String string : listValidTests) {
            mvc.perform(MockMvcRequestBuilders.post("/" + string)).andExpect(MockMvcResultMatchers.status().isOk());
        }
        for (String string : listInvalidTests) {
            mvc.perform(MockMvcRequestBuilders.post("/" + string))
                            .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }
    }

}
