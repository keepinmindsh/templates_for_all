package bong.lines.springboot.controller;

public class RestController {
    @Value("${appName}")
    private String appName;

    @GetMapping
    public String indexHome() {
        return appName + " index";
    }
}
