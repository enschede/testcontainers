package nl.marcenschede.tests.it;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.ContainerState;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class ContainerIntegrationTest {

    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
                    .withExposedService("module1", 8080, Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)))
                    .withExposedService("module2", 8081, Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)));

    private Integer servicePortModule1;

    @BeforeEach
     void startContainers() {
        // De opstart gebeurt nu maar 1x. De start van de tweede test kost slecht ongeveer 3s.
        environment.start();

        servicePortModule1 = environment.getServicePort("module1", 8080);

        final Optional<ContainerState> dummyService = environment.getContainerByServiceName("module1_1");
    }

    @Test
    void connect1() {
        final var responseEntity =
                new RestTemplate().getForEntity(String.format("http://localhost:%d/marc", servicePortModule1), String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Hello Hello marc");
    }

    @Test
    void connect2() {
        final var responseEntity =
                new RestTemplate().getForEntity(String.format("http://localhost:%d/marc", servicePortModule1), String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Hello Hello marc");
    }

}
