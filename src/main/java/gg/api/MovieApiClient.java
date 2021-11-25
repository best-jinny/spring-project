package gg.api;

import gg.web.dto.MoviesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class MovieApiClient {
    @Autowired
    private final RestTemplate restTemplate;

    private final String CLIENT_ID = "Xg4LDBHlVyETj_25Cn0T";
    private final String CLIENT_SECRET = "Wc87Jz8T08";

    private final String apiUrl = "https://openapi.naver.com/v1/search/movie.json?query={keyword}";

    public int requestMovie(String keyword) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        MoviesResponseDto result = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, MoviesResponseDto.class, keyword).getBody();

        System.out.println(result.getItems()[0].getTitle());

        return 1;
    }
}