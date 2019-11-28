package ch.noseryoung.devOps.fibonacci;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class FibonacciServiceImplTest {

    FibonacciServiceImpl fibonacciService = new FibonacciServiceImpl();

    @Test
    public void getFibs_requestAmount5_expect5(){
        List<Long> fibs = fibonacciService.getFibs(5L);
        Assertions.assertThat(fibs).isNotEmpty().containsExactly(1L,1L,2L,3L,5L);
    }

    @Test
    public void getFibsUpTo_requestLimit5_expect5(){
        List<Long> fibs = fibonacciService.getFibsUpTo(5L);
        Assertions.assertThat(fibs).isNotEmpty().containsExactly(1L,1L,2L,3L,5L);
    }

    @Test
    public void getNthFib_requestNum5_expect5(){
        Long fib = fibonacciService.getNthFib(5L);
        Assertions.assertThat(fib).isEqualTo(5);
    }




}