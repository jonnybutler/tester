package com.testers.service;

import com.testers.model.SearchResponse;

import java.util.List;

public interface SearchService {

    SearchResponse searchByCountriesAndDevices(List<String> countries, List<String> devices);

}
