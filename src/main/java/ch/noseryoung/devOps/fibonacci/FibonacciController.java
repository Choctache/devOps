package ch.noseryoung.devOps.fibonacci;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/fibonacci")
public class FibonacciController {

    private FibonacciService fibService;

    public FibonacciController(FibonacciService fibService){
        this.fibService = fibService;
    }

    @GetMapping("/get-nth-fib")
    public ResponseEntity<Long> getNthFib(@RequestParam Long num){
        return new ResponseEntity<>(fibService.getNthFib(num), HttpStatus.OK);
    }
    @GetMapping("/get-fibs")
    public ResponseEntity<List<Long>> getFibs(@RequestParam Long amount){
        return new ResponseEntity<>(fibService.getFibs(amount), HttpStatus.OK);
    }

    @GetMapping("/get-fibs-up-to")
    public ResponseEntity<List<Long>> getFibsUptTo(@RequestParam Long limit){
        return new ResponseEntity<>(fibService.getFibsUpTo(limit), HttpStatus.OK);
    }
}
