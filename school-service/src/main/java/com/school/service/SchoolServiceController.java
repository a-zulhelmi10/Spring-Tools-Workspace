package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SchoolServiceController {
	//invoke student service
	@Autowired
	private RestTemplate restTemplate; //use RestTemplate to invoke another service
	@RequestMapping("/getSchoolDetails/{schoolname}")
	public String getStudents(@PathVariable String schoolname)
	{
		//now you can change the http://localhost:1111 to http://student-service
		String response = restTemplate.exchange("http://student-service/getStudentDetailsForSchool/{schoolname}",HttpMethod.GET,null,new ParameterizedTypeReference<String>() {},schoolname).getBody(); //invoke this method using restTemplate
		return "School name : " +schoolname +"\n" +"Student details : " +response;
	}
	@LoadBalanced
	@Bean //so that object creation is taken care by springframework
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}

/*
 http://localhost:1313/getSchoolDetails/abSchool
 */
