package com.testers.repo;

import com.testers.model.Tester;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestersRepo extends CrudRepository<Tester, Long> {

    @Query(
            value = "SELECT * FROM Tester t where t.country in :countries",
            nativeQuery = true)
    List<Tester> findAllByCountry(List<String> countries);

    List<Tester> findAll();

}
