package ch.noseryoung.devOps.fibonacci;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class FibonacciServiceImpl implements FibonacciService {


    @Override
    public List<Long> getFibs(Long amount) {
        List<Long> fibs = new LinkedList<>();

        if (amount <= 1) {
            fibs.add(amount);
            return fibs;
        }

        fibs.add(1L);
        fibs.add(1L);


        while (fibs.size() != amount) {
            fibs.add(fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2));

        }
        return fibs;
    }

    @Override
    public List<Long> getFibsUpTo(Long limit) {
        List<Long> fibs = new LinkedList<>();

        if (limit <= 1) {
            fibs.add(limit);
            return fibs;
        }

        fibs.add(1L);
        fibs.add(1L);

        while ((fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2)) <= limit) {
            fibs.add(fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2));
        }
        return fibs;
    }

    @Override
    public Long getNthFib(Long nthNum) {
        List<Long> fibs = getFibs(nthNum);
        return fibs.get(fibs.size() - 1);

    }
}
