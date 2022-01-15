package bong.lings.springfor_tests.service.impl;

import bong.lings.springfor_tests.service.TDDService;
import org.springframework.stereotype.Service;

@Service
public class TDDServiceImpl implements TDDService {
    @Override
    public String greeting() {
        return "Hello World";
    }
}
