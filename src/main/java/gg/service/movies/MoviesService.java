package gg.service.movies;

import gg.api.MovieApiClient;
import gg.web.dto.MoviesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MoviesService {
    private final MovieApiClient movieApiClient;

    @Transactional(readOnly = true)
    public int findByKeyword(String keyword) {
        movieApiClient.requestMovie(keyword);
        return 1;
    }
}
