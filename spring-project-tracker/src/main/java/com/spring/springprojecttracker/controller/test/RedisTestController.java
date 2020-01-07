package com.spring.springprojecttracker.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RedisTestController {

    private static String ip;

    @Autowired
    private HttpSession testSession;

    public RedisTestController(@Value("${CF_INSTANCE_IP:127.0.0.1}") String ip) {
        this.ip = ip;
    }

    @GetMapping("hi")
    Map uid(HttpSession session) {
        UUID uid = Optional.ofNullable(UUID.class.cast(session.getAttribute("uid"))).orElse(UUID.randomUUID());

        session.setAttribute("uid", uid);

        session.setAttribute("test", "test");
        session.setAttribute("test2", "test2zzzz");
        session.setAttribute("test3", "test3");
        session.setAttribute("test4", "test4");

        testSession = session;

        Map m = new HashMap<>();
        m.put("instance_ip", this.ip);
        m.put("uuid", uid.toString());
        return m;
    }

    @GetMapping("hello")
    String test(HttpSession session, HttpServletRequest request) {
        System.out.println("-023-02-302-0-23");
        System.out.println(testSession.getAttribute("test"));
        System.out.println(session.getAttribute("test2"));
        Cookie[] getCookie = request.getCookies();
        if(getCookie != null){
            for(int i=0; i<getCookie.length; i++){
                Cookie c = getCookie[i];
                String name = c.getName(); // 쿠키 이름 가져오기
                System.out.println(name);
                String value = c.getValue(); // 쿠키 값 가져오기
                System.out.println(value);
            }
        }
        return testSession.getId();
    }
}
