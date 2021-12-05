package bong.lines.sample;


import bong.di.Inject;

public class AccountService {

    @Inject
    public AccountRepository accountRepository;

    public void join(){
        System.out.println("join");
        accountRepository.save();
    }
}
