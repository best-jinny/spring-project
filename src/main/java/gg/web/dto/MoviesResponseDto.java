package gg.web.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class MoviesResponseDto {
    private int display;
    private Item[] items;

    @Data
    public class Item {
        public String title;
        public String link;
        public String image;
        public String subtitle;
        public Date pubDate;
        public String director;
        public String actor;
        public float userRating; }


}
