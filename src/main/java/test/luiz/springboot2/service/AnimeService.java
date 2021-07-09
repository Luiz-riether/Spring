package test.luiz.springboot2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;
import test.luiz.springboot2.domain.Anime;
import test.luiz.springboot2.exception.BadRequestException;
import test.luiz.springboot2.mapper.AnimeMapper;
import test.luiz.springboot2.repository.AnimeRepository;
import test.luiz.springboot2.requests.AnimePostRequestBody;
import test.luiz.springboot2.requests.AnimePutRequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
    }

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public Anime findByIdOrTrowBadRequestException(long id){
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
//        Anime anime = AnimeMapper.INSTANCE.toAnime(animePostRequestBody);
//        if (anime.getName().equals("Naruto")){throw new MethodArgumentNotValidException();}
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrTrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrTrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
