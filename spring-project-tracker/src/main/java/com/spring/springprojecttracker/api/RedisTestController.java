package com.spring.springprojecttracker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RedisTestController {

    private static String ip;

    public RedisTestController(@Value("${CF_INSTANCE_IP:127.0.0.1}") String ip) {
        this.ip = ip;
    }

    @GetMapping("hi")
    Map uid(HttpSession session) {
        UUID uid = Optional.ofNullable(UUID.class.cast(session.getAttribute("uid"))).orElse(UUID.randomUUID());

        session.setAttribute("uid", uid);

        session.setAttribute("test", "test");
        session.setAttribute("test2", "test2");
        session.setAttribute("test3", "test3");
        session.setAttribute("test4", "test4");


        Map m = new HashMap<>();
        m.put("instance_ip", this.ip);
        m.put("uuid", uid.toString());
        return m;
    }

}
