package com.example.supportenginner1.repository;

import com.example.supportenginner1.entity.Bucket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BucketRepository extends CrudRepository<Bucket,Long> {
    List<Bucket> findAllBySupportPersonEmail(String supportPersonEmail);
}
