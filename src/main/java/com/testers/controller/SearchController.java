package com.testers.controller;


import com.testers.model.SearchRequest;
import com.testers.model.SearchResponse;
import com.testers.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService service;

    @PostMapping("/search")
    public ResponseEntity<SearchResponse> searchTesters(@RequestBody SearchRequest request) {
        SearchResponse searchResponse = service.searchByCountriesAndDevices(request.getCountries(), request.getDevices());
        return new ResponseEntity<>(searchResponse, HttpStatus.OK);
    }
}
