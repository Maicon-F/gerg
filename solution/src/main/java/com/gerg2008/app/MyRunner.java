package com.gerg2008.app;

import com.gerg2008.app.model.BiCombination;
import com.gerg2008.app.service.impl.ComponentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private ComponentServiceImpl service;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("This is a Spring Boot console application!");
        // Your custom logic here
     //   Iterable<com.gerg2008.app.model.Component> componentList = service.getAll();
        List<BiCombination> biCombinationList = new ArrayList<>();
        for (BiCombination b : biCombinationList) {

        }
        System.out.println("ok");
    }
}
