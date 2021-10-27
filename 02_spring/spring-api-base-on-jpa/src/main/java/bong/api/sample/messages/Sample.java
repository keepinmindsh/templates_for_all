package bong.api.sample.messages;

import bong.api.sample.domain.SampleDomain;
import bong.api.sample.model.SampleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Sample {

    private final SampleDomain sampleDomain;

    @GetMapping("/{systemId}/sample")
    public Object sample(@PathVariable("systemId") String systemId, SampleDTO.SampleRequest sampleRequest) throws Exception {
        return sampleDomain.execute(sampleRequest);
    }
}
