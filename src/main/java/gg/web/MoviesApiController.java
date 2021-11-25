package gg.web;

import gg.service.movies.MoviesService;
import gg.web.dto.MoviesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MoviesApiController {
    private final MoviesService moviesService;

    @GetMapping("/api/v1/movies/{keyword}")
    public int get(@PathVariable("keyword") String keyword) {
        System.out.println("*********************키워드" + keyword);
        moviesService.findByKeyword(keyword);
        return 1;
    }
}
