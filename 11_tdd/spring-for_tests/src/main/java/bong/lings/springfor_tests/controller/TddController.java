package bong.lings.springfor_tests.controller;

import bong.lings.springfor_tests.model.Member;
import bong.lings.springfor_tests.service.TDDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TddController {

    private final TDDService tddService;

    @GetMapping("/member")
    List<Member> member(){
        return tddService.member();
    }

    @PostMapping("/member")
    void memberSave(String name){
        tddService.memberSave(name);
    }
}
