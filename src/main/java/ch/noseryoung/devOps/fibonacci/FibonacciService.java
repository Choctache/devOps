package ch.noseryoung.devOps.fibonacci;


import java.util.List;
import java.util.function.Predicate;

public interface FibonacciService {

    Long getNthFib(Long num);

    List<Long> getFibs(Long amount);

    List<Long> getFibsUpTo(Long limit);
}
