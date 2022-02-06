package com.example.recomendationapi.service.impl;

import com.example.recomendationapi.dto.CategoryAddsDto;
import com.example.recomendationapi.dto.Customer;
import com.example.recomendationapi.entity.CategoryAdds;
import com.example.recomendationapi.payloads.request.OrganizationPost;
import com.example.recomendationapi.payloads.response.UserAdUrl;
import com.example.recomendationapi.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CategoryAddsServiceImpl categoryAddsServiceImpl;

    @Autowired
    private KafkaTemplate<String, OrganizationPost> kafkaTemplate;

    public static final String topic="organizationposts";






    @Override
    public UserAdUrl getRecommendations(String userEmail) {

        System.out.println("inside service --->getRecommendations----"+userEmail);

        Customer customers =  restTemplate.exchange("http://localhost:6666/authenticate/interests/"+userEmail,HttpMethod.POST,null,Customer.class).getBody();

        UserAdUrl userAdUrl=new UserAdUrl();

        if(customers.getInterests()!=null)
        {
            List<CategoryAddsDto> categoryAddsDtoList=new ArrayList<>();

            for(String categoryId:customers.getInterests())
            {
                List<CategoryAddsDto> categoryAddsDtos=categoryAddsServiceImpl.getAllAddsByCategoryId(categoryId);
                categoryAddsDtoList.addAll(categoryAddsDtos);
            }

            int numberOfElements = 5;
            Random rand = new Random();
            List<CategoryAddsDto> output=new ArrayList<>();
            userAdUrl.setUserEmail(userEmail);

            for (int i = 0; i < numberOfElements; i++) {
                int randomIndex = rand.nextInt(categoryAddsDtoList.size());
                CategoryAddsDto random = categoryAddsDtoList.get(randomIndex);
                output.add(random);
                categoryAddsDtoList.remove(randomIndex);
            }
            userAdUrl.setCategoryAddsDto(output);
            return userAdUrl;
        }

        UserAdUrl userAd=getRandomAds();  //-> for now
        userAd.setUserEmail(userEmail);
        return userAd;

    }

    private UserAdUrl getRandomAds()
    {
        List<CategoryAddsDto> categoryAddsDtos=categoryAddsServiceImpl.getAllAds();
        UserAdUrl userAdUrl=new UserAdUrl();
        int numberOfElements = 5;
        Random rand = new Random();
        List<CategoryAddsDto> output=new ArrayList<>();

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(categoryAddsDtos.size());
            CategoryAddsDto random = categoryAddsDtos.get(randomIndex);
            output.add(random);
            categoryAddsDtos.remove(randomIndex);
        }
        userAdUrl.setCategoryAddsDto(output);
        return userAdUrl;
    }




    @Override
    @KafkaListener(topics = {"org","orgg"},groupId = "mygroup")
    public void listenToOrganizationPost(OrganizationPost organizationPost)
    {
        System.out.println(organizationPost.getUrl());
        CategoryAddsDto categoryAdds=new CategoryAddsDto();
        categoryAdds.setCategoryId(organizationPost.getCategoryId());
        categoryAdds.setUrl(organizationPost.getUrl());
        categoryAddsServiceImpl.addAd(categoryAdds);

        kafkaTemplate.send(topic,organizationPost);

    }


}




