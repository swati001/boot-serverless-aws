package com.boot.serverless.api.controller;

import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.boot.serverless.api.ApiApplication;
import com.boot.serverless.api.handler.StreamDataHandler;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(JUnit4.class)
@ContextConfiguration(classes = { ApiApplication.class })
@WebAppConfiguration
public class ApiControllerTest {
    private MockLambdaContext lambdaContext;
    private SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    
    @Autowired 
    private ObjectMapper mapper;
 
    public ApiControllerTest() { 
       lambdaContext = new MockLambdaContext(); 
       this.handler = StreamDataHandler.handler; 
    }

    @Test 
    public void testGreetingApi() throws JsonParseException, JsonMappingException, IOException {
       AwsProxyRequest request = new AwsProxyRequestBuilder("/api", "GET").build(); 
       AwsProxyResponse response = handler.proxy(request, lambdaContext);
      
       Assert.assertEquals(response.getStatusCode(), 200); 
       //GreetingDto responseBody = mapper.readValue(response.getBody(), GreetingDto.class);
      // asserThat(responseBody.getMessage(), equalTo("Hello John")); 
    }
}
