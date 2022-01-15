package bong.lings.springfor_tests.controller;

import bong.lings.springfor_tests.service.TDDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TddController {

    private final TDDService tddService;

    @GetMapping("/helloworld")
    String helloWorld(){
        return tddService.greeting();
    }
}
