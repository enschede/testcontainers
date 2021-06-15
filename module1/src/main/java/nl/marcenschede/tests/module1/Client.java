package nl.marcenschede.tests.module1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "greeter"
        , url = "http://module2:8081/"
)
public interface Client {

    @RequestMapping(method = RequestMethod.GET, value = "/greeting/{name}")
    String getGreeting(@PathVariable String name);

}
