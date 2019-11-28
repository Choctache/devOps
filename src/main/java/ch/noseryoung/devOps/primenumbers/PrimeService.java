package ch.noseryoung.devOps.primenumbers;

import org.springframework.stereotype.Service;

import java.util.List;


public interface PrimeService {


    Boolean isPrime(Long num);

    List<Long> isPrimeUpTo(Long limit);

    List<Long> isPrimeFromTo(Long start, Long end);

}
