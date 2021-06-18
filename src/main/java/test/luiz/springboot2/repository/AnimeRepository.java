package test.luiz.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.luiz.springboot2.domain.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
