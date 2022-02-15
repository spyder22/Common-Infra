package com.example.supportenginner1.service.impl;

import com.example.supportenginner1.dto.BucketDto;
import com.example.supportenginner1.entity.Bucket;
import com.example.supportenginner1.repository.BucketRepository;
import com.example.supportenginner1.service.BucketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BucketServiceImpl implements BucketService {

    @Autowired
    private BucketRepository bucketRepository;


    @Override
    public void save(BucketDto bucketDto) {
        Bucket bucket=new Bucket();
        BeanUtils.copyProperties(bucketDto,bucket);
        try
        {
            bucketRepository.save(bucket);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByBucketId(Long bucketId) {
        bucketRepository.deleteById(bucketId);
    }

    @Override
    public List<BucketDto> getAllBySupportPersonEmail(String supportPersonEmail) {
        return convertEntityToDto(bucketRepository.findAllBySupportPersonEmail(supportPersonEmail));
    }

    @Override
    public List<BucketDto> getAllTickets() {
        return convertEntityToDto((List) bucketRepository.findAll());
    }


    private List<BucketDto> convertEntityToDto(List<Bucket> bucketList)
    {
        List<BucketDto> bucketDtos=new ArrayList<>();
        for (Bucket bucket:bucketList)
        {
            BucketDto bucketDto=new BucketDto();
            BeanUtils.copyProperties(bucket,bucketDto);
            bucketDtos.add(bucketDto);
        }
        return bucketDtos;

    }
}
