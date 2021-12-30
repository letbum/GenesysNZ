package com.norbertzambori;

import com.utils.logger;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestRestApi {

    logger logger;

    @BeforeMethod
    public void setUp(Method method) {
        logger = new logger();
        logger.logToFile("Starting test - " + method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        String tcName = result.getName();
        if(result.getStatus() == ITestResult.SUCCESS) {
            logger.logToFile(tcName  + " has passed");
        } else if(result.getStatus() == ITestResult.FAILURE) {
            logger.logToFile(tcName  + " has failed");
        } else if(result.getStatus() == ITestResult.SKIP ){
            logger.logToFile(tcName  + " has skipped");
        }
    }

    @Test()
    public void RESTAPITesting() throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet request = new HttpGet("https://jsonplaceholder.typicode.com/users");

            try (CloseableHttpResponse response = httpClient.execute(request)) {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    JSONParser parser = new JSONParser();
                    Object my_json = parser.parse(result);
                    String first_email = ((JSONObject) ((JSONArray) my_json).get(0)).get("email").toString();
                    int json_size = ((JSONArray) my_json).size();
                    for (int i = 0; i < json_size; i++) {
                        var name = ((JSONObject) ((JSONArray) my_json).get(i)).get("name");
                        var email = ((JSONObject) ((JSONArray) my_json).get(i)).get("email");
                        logger.logToFile("%s | %s".formatted(name, email));
                    }
                    Assert.assertTrue(first_email.contains("@"));
                }

            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
