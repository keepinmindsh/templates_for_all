package bong.lines.domain.mb.controller;

import bong.lines.domain.mb.command.SearchCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchCommand searchCommand;

    @GetMapping("/hello")
    private Object helloWorldwithMyBatis(){
        return searchCommand.getValue();
    }
}
