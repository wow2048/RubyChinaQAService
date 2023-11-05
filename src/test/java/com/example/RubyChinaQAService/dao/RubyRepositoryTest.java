package com.example.RubyChinaQAService.dao;

import com.example.RubyChinaQAService.entity.po.Ruby;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RubyRepositoryTest {

    @Autowired
    private RubyRepository rubyRepository;

    @Test
    void findByName() {
        Ruby ruby = rubyRepository.findByName("Ruby");
        System.out.println(ruby);
    }
}