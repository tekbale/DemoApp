package test.tek.demo1.db.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test.tek.demo1.db.entity.ShiftData;

import java.util.List;

@Repository
public interface ShiftDataRepository extends PagingAndSortingRepository<ShiftData, Long> {

    @Query("SELECT sd FROM ShiftData sd WHERE sd.userId = :userId")
    List<ShiftData> findByUserId(@Param("userId") String userId, Pageable pageable);
}
