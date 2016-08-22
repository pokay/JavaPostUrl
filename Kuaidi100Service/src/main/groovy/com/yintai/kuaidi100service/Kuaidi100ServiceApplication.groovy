package com.yintai.kuaidi100service

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@CompileStatic
@ComponentScan(["com.yintai.kuaidi100service","org.nofdev"])
@SpringBootApplication
class Kuaidi100ServiceApplication {

    static void main(String[] args) {
        SpringApplication.run Kuaidi100ServiceApplication, args

    }
}
