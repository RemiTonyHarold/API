package com.remirobert;

import com.mongodb.util.JSON;
import javafx.beans.binding.When;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sun.nio.cs.Surrogate.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiRssApplicationTests {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    static String email;
    static String password;
    static JSONObject token;

    @Before
    public void setup() {
        email = UUID.randomUUID().toString() + "@test.com";
        password = "123456";
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void newsRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/news");
        mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void signup() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post("/signup")
                .param("email", email)
                .param("password", password);
        String content = mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.user.email").value(email))
                .andExpect(jsonPath("$.token.accessToken").exists())
                .andExpect(jsonPath("$.token.refreshToken").exists())
                .andDo(print()).andReturn().getResponse().getContentAsString();
        JSONObject object = new JSONObject(content);
        token = object.getJSONObject("token");
    }

    @Test
    public void categories() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/categories");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void newsAuth() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/news")
                .header("RSS-TOKEN", token.getString("accessToken"));
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void authRoute() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/hello")
                .header("RSS-TOKEN", token.getString("accessToken"));
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful());
    }
}
