package ch.noseryoung.devOps.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FibonacciControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FibonacciService fibonacciService;

    @BeforeEach
    public void onSetUp(){
        given(fibonacciService.getNthFib(5L)).willReturn(5L);

        given(fibonacciService.getFibsUpTo(5L)).willReturn(List.of(1L,1L,2L,3L,5L));

        given(fibonacciService.getFibs(5L)).willReturn(List.of(1L,1L,2L,3L,5L));
    }


    @Test
    public void getNthFibs_requestNum5_expect5() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/fibonacci/get-nth-fib?num=5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("5"));
    }

    @Test
    public void getFibsUpTo_requestNum5_expect5() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/fibonacci/get-fibs-up-to?limit=5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3]").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4]").value(5));

    }

    @Test
    public void getFibs_requestNum5_expect5() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/fibonacci/get-fibs?amount=5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3]").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4]").value(5));
    }


}