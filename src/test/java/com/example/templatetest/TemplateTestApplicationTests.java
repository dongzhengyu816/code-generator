package com.example.templatetest;

import com.entity.MessageConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

@SpringBootTest
class TemplateTestApplicationTests {
    @Autowired
    private MessageConstruct messageConstruct;

    @Test
    void contextLoads() {
        System.out.println(messageConstruct.getList());
    }


}
