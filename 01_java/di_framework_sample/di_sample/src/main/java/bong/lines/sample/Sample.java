package bong.lines.sample;

import bong.di.ContainerService;

public class Sample {
    public static void main(String[] args) {
        AccountService accountService = ContainerService.getObject(AccountService.class);

        accountService.join();
    }
}
