package bong.lings.springfor_tests.service;

import bong.lings.springfor_tests.model.Member;

import java.util.List;

public interface TDDService {
    List<Member> member();

    void memberSave(String name);
}
