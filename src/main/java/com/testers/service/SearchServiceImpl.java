package com.testers.service;

import com.testers.model.SearchResponse;
import com.testers.model.Tester;
import com.testers.repo.BugRepo;
import com.testers.repo.DevicesRepo;
import com.testers.repo.TesterDeviceRepo;
import com.testers.repo.TestersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{

    private final BugRepo bugRepo;
    private final DevicesRepo devicesRepo;
    private final TestersRepo testersRepo;
    private final TesterDeviceRepo testerDeviceRepo;

    @Override
    public SearchResponse searchByCountriesAndDevices(List<String> countries, List<String> devices){

        List<Tester> testers;
        // Check if Countries is ALL
        if (countries.size() == 1 && countries.get(0).equals("ALL")){
            testers = testersRepo.findAll();
        } else{
            testers = testersRepo.findAllByCountry(countries);
        }

        List<Long> deviceIds;
        // Check if Devices is ALL
        if (devices.size() == 1 && devices.get(0).equals("ALL")){
            deviceIds = devicesRepo.findAllById();
        } else{
            deviceIds = devicesRepo.findAllByDevicesList(devices);
        }

        Long bugCount;

        SearchResponse searchResponse = new SearchResponse();

        for (int i=0; i < testers.size(); i++){
            bugCount = bugRepo.countByDeviceIdsAndTesterId(deviceIds, testers.get(i).getTesterId());
            testers.get(i).setBugCount(bugCount);
            searchResponse.getTestersByExperience().put(bugCount, testers.get(i));
        }
        return searchResponse;
    }
}
