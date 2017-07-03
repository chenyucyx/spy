package src; /**
 * pajk.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ComponentScan(basePackageClasses = ApplicationMain.class)
public class ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

}
