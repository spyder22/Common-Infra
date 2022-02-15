package com.example.supportenginner1.service;

import com.example.supportenginner1.dto.BucketDto;

import java.util.List;

public interface BucketService {

    void save(BucketDto bucketDto);
    void deleteByBucketId(Long bucketId);
    List<BucketDto> getAllBySupportPersonEmail(String supportPersonEmail);
    List<BucketDto> getAllTickets();


}
