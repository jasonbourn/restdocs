package com.chinamobile.iot;

import com.alibaba.fastjson.JSON;
import com.chinamobile.iot.model.Student;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by szl on 2017/1/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Documentation {
    @Autowired
    private WebApplicationContext context;
    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
    private MockMvc mockMvc;
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation).operationPreprocessors().withResponseDefaults(prettyPrint()))

                .build();
    }

    @Test
    public void TestApi() throws Exception{
        mockMvc.perform(get("/student").param("name", "szl")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("getStudent",
                        relaxedResponseFields(fieldWithPath("address").description("The user's email address"),
                                fieldWithPath("name").description("The user's name"),fieldWithPath("id").description("id"))));

        Student student = new Student();
        student.setName("szl");
        student.setAge(23);
        student.setAddress("湖北麻城");
        student.setCls("二年级");
        student.setSex("男");

        mockMvc.perform(post("/student").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(document("addStudent"));
    }


}
