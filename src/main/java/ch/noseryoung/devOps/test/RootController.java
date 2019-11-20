package ch.noseryoung.devOps.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping
    public ResponseEntity<String> getAnswer(){
        return new ResponseEntity<>("Hello it's a me, Mario!", HttpStatus.OK);
    }
}
