package com.chinamobile.iot.controller;

import com.chinamobile.iot.model.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by szl on 2017/1/9.
 */
@RestController
public class StudentController {

    @RequestMapping(value = "student", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@RequestParam("name") String name){
        Student reponse = new Student();
        reponse.setId(1);
        reponse.setName("zhangsan");
        reponse.setAge(12);
        reponse.setCls("二年级");
        reponse.setAddress("重庆市大竹林");
        reponse.setSex("男");
        return reponse;
    }

    @RequestMapping(value = "student", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addStudent(@RequestBody Student student){
        return ;
    }
}
