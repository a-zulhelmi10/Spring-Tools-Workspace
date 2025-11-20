package com.student.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.service.Student;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController  
public class StudentServiceController {
	private static Map<String,List<Student>> schoolDB;
	static
	{
		schoolDB = new HashMap<String,List<Student>>(); //try to use db on your own
		List<Student> studList = new ArrayList<>();
		Student stud = new Student("John","Class a");
		studList.add(stud);
		stud = new Student("Randy","Class b");
		studList.add(stud);
		schoolDB.put("abSchool", studList);
		studList = new ArrayList<>();
		stud = new Student("Amir","Class x");
		studList.add(stud);
		stud = new Student("Farhan","Class y");
		studList.add(stud);
		schoolDB.put("xy School", studList);
	}
	@RequestMapping("/getStudentDetailsForSchool/{schoolname}")
	public List<Student> getStudents(@PathVariable String schoolname)
	{
		List<Student> studList = schoolDB.get(schoolname);
		return studList;
	}
	/*
	 APIs to access student service
	 http://localhost:1111/getStudentDetailsForSchool/abSchool
	 http://localhost:1111/getStudentDetailsForSchool/xy%20School
	 */
	 
}
