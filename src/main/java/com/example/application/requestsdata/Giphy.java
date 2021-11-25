package com.example.application.requestsdata;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Giphy {
    private String type;
    private String id;
    private String url;
    private String slug;
    @JsonProperty("bitly_gif_url")
    private String bitlyGifUrl;
    @JsonProperty("bitly_url")
    private String bitlyUrl;
    private String embed_url;
    private String username;
    private String source;
    private String title;
    private String rating;
    @JsonProperty("content_url")
    private String contentUrl;
    @JsonProperty("source_tld")
    private String sourceTld;
    @JsonProperty("source_post_url")
    private String sourcePostUrl;
    @JsonProperty("is_sticker")
    private String isSticker;
    @JsonProperty("import_datetime")
    private String importDatetime;
    @JsonProperty("trending_datetime")
    private String trendingDatetime;
    private List<Object> images;

    @JsonIgnore
    public void setImages(List<Object> images) {
        this.images = images;
    }
}


