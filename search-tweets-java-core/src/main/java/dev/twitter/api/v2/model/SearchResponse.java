package dev.twitter.api.v2.model;

import java.util.List;
import lombok.Data;

@Data
public class SearchResponse {
    private List<Tweet> data;
    private Includes includes;
    private Meta meta;
}
