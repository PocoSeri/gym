package com.gym.gym.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.gym.enums.Roles;
import com.gym.gym.model.CustomerDto;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureDataMongo
@ActiveProfiles("test")
public class CustomerTests {

    private final String apiUrl = "/api/customers";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MongoTemplate mongoTemplateTest;


    @BeforeEach
    public void clearDatabase() {
        // Delete all documents from the MongoDB collection before each test method
        String COLLECTION_NAME = "customer";
        mongoTemplateTest.dropCollection(COLLECTION_NAME);
    }


    @Test
    void postSuccess() throws Exception {
        MvcResult mvcResult = postCustomer();
        String newlySavedId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.output.id");
        mockMvc.perform(MockMvcRequestBuilders.get(apiUrl + "/" + newlySavedId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.output.name").value("filippo1"),
                        jsonPath("$.output.surname").value("occhiali"),
                        jsonPath("$.output.email").value("occhialini@gmail.com"),
                        jsonPath("$.output.age").value(15),
                        jsonPath("$.output.isActive").value(true),
                        jsonPath("$.output.role").value("ADMIN")
                );
    }

    @Test
    void putSuccess() throws Exception {
        CustomerDto dto = CustomerDto.builder()
                .age(15)
                .name("filippo1")
                .surname("occhiali")
                .email("occhialini@gmail.com")
                .isActive(true)
                .role(Roles.ADMIN)
                .build();
        MvcResult mvcResult = postCustomer();

        String newlySavedId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.output.id");
        mockMvc.perform(MockMvcRequestBuilders.get(apiUrl + "/" + newlySavedId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.output.email").value("occhialini@gmail.com"));
        dto.setEmail("occhialini@gmail.it");
        mockMvc.perform(MockMvcRequestBuilders.put(apiUrl +  "/" + newlySavedId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.output.email").value("occhialini@gmail.it"))
                .andReturn();
    }
    @Test
    void putFail_IdNotExist() throws Exception {
        CustomerDto dto = CustomerDto.builder()
                .age(15)
                .name("filippo1")
                .surname("occhiali")
                .email("occhialini@gmail.com")
                .isActive(true)
                .role(Roles.ADMIN)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put(apiUrl +  "/" + 3)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getList() throws Exception {
        postCustomer();

        mockMvc.perform(MockMvcRequestBuilders.get(apiUrl))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.output", hasSize(1)));
    }

    @Test
    void deleteSuccess() throws Exception {
        MvcResult mvcResult = postCustomer();
        String newlySavedId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.output.id");
        mockMvc.perform(MockMvcRequestBuilders.delete(apiUrl + "/" + newlySavedId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        status().isOk()
                );
    }

    private MvcResult postCustomer() throws Exception {
        CustomerDto dto = CustomerDto.builder()
                .age(15)
                .name("filippo1")
                .surname("occhiali")
                .email("occhialini@gmail.com")
                .isActive(true)
                .role(Roles.ADMIN)
                .build();
        return mockMvc.perform(MockMvcRequestBuilders.post(apiUrl)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.output.name").value("filippo1"),
                        jsonPath("$.output.surname").value("occhiali"),
                        jsonPath("$.output.email").value("occhialini@gmail.com"),
                        jsonPath("$.output.age").value(15),
                        jsonPath("$.output.isActive").value(true),
                        jsonPath("$.output.role").value("ADMIN")
                ).andReturn();
    }
}
