package nl.marcenschede.tests.module1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final Client client;

    public Controller(Client client) {
        this.client = client;
    }

    @GetMapping("/{name}")
    public String sayHello(@PathVariable String name) {

        return "Bye " + client.getGreeting(name);

    }
}
