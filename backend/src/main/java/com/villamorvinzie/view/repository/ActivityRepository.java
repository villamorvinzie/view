package com.villamorvinzie.view.repository;

import com.villamorvinzie.view.domain.Activity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findAllByUserId(String userId);
}
