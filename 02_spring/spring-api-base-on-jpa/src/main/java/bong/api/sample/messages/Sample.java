package bong.api.sample.messages;

import bong.api.sample.domain.SampleDomain;
import bong.api.sample.model.sample.SampleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Sample {

    private final SampleDomain sampleDomain;

    @GetMapping("/{systemId}/sample")
    public Object sampleSelect(@PathVariable("systemId") String systemId, SampleDTO.SampleRequest sampleRequest) throws Exception {
        return sampleDomain.execute(systemId, sampleRequest);
    }

    @PostMapping("/{systemId}/sample")
    public Object sampleSave(@PathVariable("systemId") String systemId, @RequestBody SampleDTO.SampleRequest sampleRequest) throws Exception {
        return sampleDomain.execute(systemId, sampleRequest);
    }
}
