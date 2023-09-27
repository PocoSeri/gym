package com.gym.gym.gym;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.gym.enums.Roles;
import com.gym.gym.model.ActivityDto;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureDataMongo
@ActiveProfiles("test")
public class ActivityTests {

    private final String apiUrl = "/api/activities";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MongoTemplate mongoTemplateTest;


    @BeforeEach
    public void clearDatabase() {
        // Delete all documents from the MongoDB collection before each test method
        String COLLECTION_NAME = "activity";
        mongoTemplateTest.dropCollection(COLLECTION_NAME);
    }


    @Test
    void postSuccess() throws Exception {
        MvcResult mvcResult = postActivity();
        String newlySavedId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.output.id");
        mockMvc.perform(MockMvcRequestBuilders.get(apiUrl + "/" + newlySavedId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.output.name").value("SALA PESI")
                );
    }

    @Test
    void putSuccess() throws Exception {
        ActivityDto dto = ActivityDto.builder()
                .name("SALA PESI")
                .build();
        MvcResult mvcResult = postActivity();

        String newlySavedId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.output.id");
        mockMvc.perform(MockMvcRequestBuilders.get(apiUrl + "/" + newlySavedId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.output.name").value("SALA PESI"));
        dto.setName("CALISTHENICS");
        mockMvc.perform(MockMvcRequestBuilders.put(apiUrl +  "/" + newlySavedId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.output.name").value("CALISTHENICS"))
                .andReturn();
    }
    @Test
    void putFail_IdNotExist() throws Exception {
        ActivityDto dto = ActivityDto.builder()
                .name("SALA PESI")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put(apiUrl +  "/" + 3)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getList() throws Exception {
        postActivity();

        mockMvc.perform(MockMvcRequestBuilders.get(apiUrl))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.output", hasSize(1)));
    }

    @Test
    void deleteSuccess() throws Exception {
        MvcResult mvcResult = postActivity();
        String newlySavedId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.output.id");
        mockMvc.perform(MockMvcRequestBuilders.delete(apiUrl + "/" + newlySavedId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        status().isOk()
                );
    }

    private MvcResult postActivity() throws Exception {
        ActivityDto dto = ActivityDto.builder()
                .name("SALA PESI")
                .build();
        return mockMvc.perform(MockMvcRequestBuilders.post(apiUrl)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.output.name").value("SALA PESI")
                ).andReturn();
    }
}
