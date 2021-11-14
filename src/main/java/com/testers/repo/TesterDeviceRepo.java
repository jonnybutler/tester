package com.testers.repo;

import com.testers.model.TesterDevice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesterDeviceRepo extends CrudRepository<TesterDevice, Long> {
}
