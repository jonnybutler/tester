package com.testers.repo;

import com.testers.model.Devices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevicesRepo extends CrudRepository<Devices, Long>{

    @Query(
            value = "Select d.deviceId from Devices d where d.device in :devicesList")
    List<Long> findAllByDevicesList(List<String> devicesList);

    @Query(
            value = "Select deviceId FROM Devices",
            nativeQuery = true)
    List<Long> findAllById();

}

