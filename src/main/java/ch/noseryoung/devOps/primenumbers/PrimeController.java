package ch.noseryoung.devOps.primenumbers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/primes")
public class PrimeController {

    private PrimeService primeService;

    @Autowired
    public PrimeController(PrimeService primeService){
        this.primeService = primeService;
    }

    @GetMapping("/is-prime")
    public ResponseEntity<Boolean> isPrime(@RequestParam Long num){

        return new ResponseEntity<>(primeService.isPrime(num), HttpStatus.OK);
    }

    @GetMapping("/get-primes-up-to")
    public ResponseEntity<List<Long>> getPrimesUpTo(@RequestParam Long limit){

        List<Long> primes = primeService.isPrimeUpTo(limit);

        return new ResponseEntity<>(primes, HttpStatus.OK);
    }

    @GetMapping("/get-primes-from-to")
    public ResponseEntity<List<Long>> getPrimesFromTo(@RequestParam Long start, @RequestParam Long end){

        List<Long> primes = primeService.isPrimeFromTo(start, end);

        return new ResponseEntity<>(primes, HttpStatus.OK);
    }

}
