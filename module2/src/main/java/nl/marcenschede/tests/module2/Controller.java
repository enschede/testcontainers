package nl.marcenschede.tests.module2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @GetMapping(value = "/greeting/{name}")
    public Mono<String> sayHello(@PathVariable String name) {
        return Mono.just( "Hello " + name);
    }
}
