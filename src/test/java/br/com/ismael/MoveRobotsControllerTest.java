package br.com.ismael;

import static org.hamcrest.Matchers.containsString;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(MoveRobotsController.class)
public class MoveRobotsControllerTest {

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
        mvc.perform(MockMvcRequestBuilders.post("/MMRMMRMM")).andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().string("(2, 0, S)"));

        mvc.perform(MockMvcRequestBuilders.post("/MML")).andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().string("(0, 2, W)"));

        mvc.perform(MockMvcRequestBuilders.post("/AAA")).andExpect(MockMvcResultMatchers.status().isBadRequest())
                        .andExpect(MockMvcResultMatchers.content().string("Posição inválida"));

        mvc.perform(MockMvcRequestBuilders.post("/MMMMMMMMMMMMMMMMMMMMMMMM"))
                        .andExpect(MockMvcResultMatchers.status().isBadRequest())
                        .andExpect(MockMvcResultMatchers.content().string(containsString("Posição inválida")));
    }

}
