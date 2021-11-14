package com.testers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@AllArgsConstructor
@Data
@Builder
public class SearchResponse {
    private Map<Long, Tester> testersByExperience;

    public SearchResponse(){
        this.testersByExperience = new TreeMap<Long, Tester>((Collections.reverseOrder()));
    }
}
