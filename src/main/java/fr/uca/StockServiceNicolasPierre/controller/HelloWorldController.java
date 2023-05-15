package fr.uca.StockServiceNicolasPierre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/hello-world")
public class HelloWorldController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(
            @RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) {
        return "Hello world !";
    }
}
