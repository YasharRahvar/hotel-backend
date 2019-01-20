package com.hotel.activity.repository;

import com.hotel.activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {


    @Query("update Activity i set i.deletedOn = :#{T(java.time.ZonedDateTime).now()} where i.id=:id")
    @Modifying
    void sofDelete(@Param("id") Long id);
}
