package ch.noseryoung.devOps.primenumbers;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class PrimeServiceImpl implements PrimeService {

    @Override
    public Boolean isPrime(Long num) {
        if(num < 2){
            return false;
        }
        return LongStream.rangeClosed(2, (long)Math.sqrt(num)).allMatch(i -> num % i != 0);
    }

    @Override
    public List<Long> isPrimeUpTo(Long limit) {
        return isPrimeFromTo(2L, limit);
    }

    @Override
    public List<Long> isPrimeFromTo(Long start, Long end) {
        return LongStream.rangeClosed(start, end).filter(this::isPrime).boxed().collect(Collectors.toList());
    }
}
