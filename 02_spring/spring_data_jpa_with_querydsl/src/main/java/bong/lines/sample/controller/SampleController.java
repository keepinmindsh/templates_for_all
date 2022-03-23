package bong.lines.sample.controller;

import bong.lines.sample.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SampleController {

    private final ItemService itemService;

    @GetMapping("/helloworld")
    public Object helloWorld(){
        return itemService.getHelloWorld();
    }
}
