package bong.api.messages;

import bong.api.command.SampleCommand;
import bong.api.model.SampleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Sample {

    private final SampleCommand command;

    @GetMapping("/sample")
    public void sample(@RequestBody SampleDTO.SampleRequest sampleRequest){

    }
}
