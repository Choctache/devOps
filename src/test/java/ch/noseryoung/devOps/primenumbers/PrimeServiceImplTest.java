package ch.noseryoung.devOps.primenumbers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
class PrimeServiceImplTest {

    PrimeServiceImpl primeServiceImpl = new PrimeServiceImpl();

    // Tests for getPrimesUpTo
    @Test
    public void getPrimesUpTo_requestLimit11_expect5Primes(){
        List<Long> list = primeServiceImpl.isPrimeUpTo(11L);
        Assertions.assertThat(list).isNotEmpty().containsExactly(2L,3L,5L,7L, 11L);
    }

    @Test
    public void getPrimesUpTo_requestLimitNeg1_expectZero(){
        List<Long> list = primeServiceImpl.isPrimeUpTo(-1L);
        Assertions.assertThat(list).isEmpty();
    }
    @Test
    public void getPrimesUpTo_requestLimit0_expectZero(){
        List<Long> list = primeServiceImpl.isPrimeUpTo(0L);
        Assertions.assertThat(list).isEmpty();
    }
    @Test
    public void getPrimesUpTo_requestLimit1_expect4Primes(){
        List<Long> list = primeServiceImpl.isPrimeUpTo(1L);
        Assertions.assertThat(list).isEmpty();
    }

    //Tests for isPrime
    @Test
    public void isPrime_requestNum3_expectTrue(){
        Boolean isPrime = primeServiceImpl.isPrime(3L);
        Assertions.assertThat(isPrime).isTrue();
    }

    @Test
    public void isPrime_requestNum10_expectFalse(){
        Boolean isPrime = primeServiceImpl.isPrime(10L);
        Assertions.assertThat(isPrime).isFalse();
    }

    @Test
    public void isPrime_requestNumNeg2_expectFalse(){
        Boolean isPrime = primeServiceImpl.isPrime(-2L);
        Assertions.assertThat(isPrime).isFalse();
    }

    @Test
    public void isPrime_requestNum10000_expectFalse(){
        Boolean isPrime = primeServiceImpl.isPrime(10000L);
        Assertions.assertThat(isPrime).isFalse();
    }

    //Tests for isPrimeFromTo
    @Test
    public void isPrimeFromTo_requestStart2_requestEnd3_expect2(){
        List<Long> primes = primeServiceImpl.isPrimeFromTo(2L,3L);
        Assertions.assertThat(primes).isNotEmpty().containsExactly(2L,3L);
    }

    @Test
    public void isPrimeFromTo_requestStart0_requestEnd0_exceptEmpty(){
        List<Long> primes = primeServiceImpl.isPrimeFromTo(0L,0L);
        Assertions.assertThat(primes).isEmpty();
    }

    @Test
    public void isPrimeFromTo_requestStartNeg2_requestEnd0_exceptEmpty(){
        List<Long> primes = primeServiceImpl.isPrimeFromTo(-2L,0L);
        Assertions.assertThat(primes).isEmpty();
    }

    @Test
    public void isPrimeFromTo_requestStartNeg5_requestEnd5_exceptEmpty(){
        List<Long> primes = primeServiceImpl.isPrimeFromTo(-5L,5L);
        Assertions.assertThat(primes).isNotEmpty().containsExactly(2L, 3L, 5L);
    }

}