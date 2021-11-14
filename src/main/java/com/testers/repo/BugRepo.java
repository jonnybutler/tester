package com.testers.repo;

import com.testers.model.Bug;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BugRepo extends CrudRepository<Bug, Long> {
    @Query(
            value = "SELECT * FROM Bug b",
            nativeQuery = true)
    Collection<Object> findAllBugsNative();

    @Query(
            value = "SELECT count(bugId) FROM Bug where deviceId in :deviceIds and testerId =:testerId")
    Long countByDeviceIdsAndTesterId(List<Long> deviceIds, Long testerId);

}
