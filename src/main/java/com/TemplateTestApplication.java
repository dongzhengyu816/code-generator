package com;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TemplateTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateTestApplication.class, args);
    }
}
