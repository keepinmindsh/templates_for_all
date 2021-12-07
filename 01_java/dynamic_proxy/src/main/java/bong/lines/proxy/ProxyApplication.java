package bong.lines.proxy;

import bong.lines.proxy.dynamic.ProxyGetter;

public class ProxyApplication {
    public static void main(String[] args) {
        ProxyGetter sampleService = new ProxyGetter();

        sampleService.test();

        sampleService.test2();
    }
}
