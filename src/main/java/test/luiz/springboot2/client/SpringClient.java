package test.luiz.springboot2.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import test.luiz.springboot2.domain.Anime;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate()
                .getForEntity("http://localhost:8080/animes/{id}", Anime.class, 8);
        log.info(entity);

        Anime object = new RestTemplate()
                .getForObject("http://localhost:8080/animes/{id}", Anime.class, 8);
        log.info(object);

        Anime[] animes = new RestTemplate()
                .getForObject("http://localhost:8080/animes/all", Anime[].class);
        log.info(Arrays.toString(animes));

        new RestTemplate().exchange("http://localhost:8080/animes/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Anime>>() {});
        log.info(Arrays.toString(animes));
    }
}
