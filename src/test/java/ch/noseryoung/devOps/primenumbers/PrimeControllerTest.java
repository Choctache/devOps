package ch.noseryoung.devOps.primenumbers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PrimeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PrimeService primeService;

    @BeforeEach
    public void onSetUp() {
        given(primeService.isPrime(-2L)).willReturn(false);
        given(primeService.isPrime(0L)).willReturn(false);
        given(primeService.isPrime(1L)).willReturn(false);
        given(primeService.isPrime(2L)).willReturn(true);

        given(primeService.isPrimeFromTo(2L, 3L)).willReturn(List.of(2L, 3L));
        given(primeService.isPrimeFromTo(0L, 0L)).willReturn(List.of());
        given(primeService.isPrimeFromTo(-2L, 0L)).willReturn(List.of());
        given(primeService.isPrimeFromTo(-5L, 5L)).willReturn(List.of(2L, 3L, 5L));

        given(primeService.isPrimeUpTo(1L)).willReturn(List.of());
        given(primeService.isPrimeUpTo(0L)).willReturn(List.of());
        given(primeService.isPrimeUpTo(-11L)).willReturn(List.of());
        given(primeService.isPrimeUpTo(11L)).willReturn(List.of(2L, 3L, 5L, 7L, 11L));
    }

    @Test
    void isPrime_requestNum0_expectTrue() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/is-prime?num={num}", 0))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("false"));

        verify(primeService, times(1)).isPrime(0L);
    }

    @Test
    void isPrime_requestNumNeg2_expectFalse() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/is-prime?num={num}", -2)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("false"));

        verify(primeService, times(1)).isPrime(-2L);
    }

    @Test
    void isPrime_requestNum1_expectFalse() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/is-prime?num={num}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("false"));

        verify(primeService, times(1)).isPrime(1L);
    }

    @Test
    void isPrime_requestNum2_expectFalse() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/is-prime?num={num}", 2)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        verify(primeService, times(1)).isPrime(2L);
    }

    @Test
    void getPrimesUpTo_requestLimit11_except5() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/get-primes-up-to?limit={num}", 11)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3]").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4]").value(11));

        verify(primeService, times(1)).isPrimeUpTo(11L);
    }

    @Test
    void getPrimesUpTo_requestLimit1_exceptEmpty() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/get-primes-up-to?limit={num}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));

        verify(primeService, times(1)).isPrimeUpTo(1L);
    }

    @Test
    void getPrimesUpTo_requestLimit0_exceptEmpty() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/get-primes-up-to?limit={num}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));

        verify(primeService, times(1)).isPrimeUpTo(0L);
    }

    @Test
    void getPrimesUpTo_requestLimitNeg11_except() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/get-primes-up-to?limit={num}", -11)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));

        verify(primeService, times(1)).isPrimeUpTo(-11L);
    }

    @Test
    void getPrimesFromTo_requestStart2_requestEnd3_expect() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/primes/get-primes-from-to?start={st}&end={en}", 2,3)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(3));

        verify(primeService, times(1)).isPrimeFromTo(2L,3L);
    }

    @Test
    void getPrimesFromTo_requestStart0_requestEnd0_expect() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/primes/get-primes-from-to?start={st}&end={en}", 0,0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));

        verify(primeService, times(1)).isPrimeFromTo(0L,0L);
    }

    @Test
    void getPrimesFromTo_requestStartNeg2_requestEnd0_expect() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/primes/get-primes-from-to?start={st}&end={en}", -2,0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));

        verify(primeService, times(1)).isPrimeFromTo(-2L,0L);
    }

    @Test
    void getPrimesFromTo_requestStartNeg5_requestEnd5_expect() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/primes/get-primes-from-to?start={st}&end={en}", -5,5)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value(5));

        verify(primeService, times(1)).isPrimeFromTo(-5L,5L);
    }

}