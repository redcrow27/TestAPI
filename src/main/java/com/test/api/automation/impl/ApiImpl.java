package com.test.api.automation.impl;

import com.test.api.automation.config.ConfigLoader;
import com.test.api.automation.utils.CucumberLogUtils;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;

public class ApiImpl {

    private static Response response;
    private static Map<String, Object> responseAsMap;


    /**
     *  Getting base url from saml.properties file
     * @param component
     * @param urlType
     * @return base_url
     */
    private String getBaseUrl(String component, String urlType){
        ConfigLoader configLoader = new ConfigLoader();
        String base_url = configLoader.readProperty(component + "_" + urlType + "_url","src/main/resources/libs/saml.properties");

        System.out.println("base_url: " + base_url);
        return base_url;
    }


    public void sendApiGetRequest(String endpoint, String component, String urlType){
        endpoint =  getBaseUrl(component, urlType) + endpoint;

        response = given().relaxedHTTPSValidation()
                .when().get(endpoint);

        responseAsMap = response.jsonPath().getMap("$");

        CucumberLogUtils.logPass("Successfully got a response from endpoint: " + endpoint);
        CucumberLogUtils.logInfo("Response: " + responseAsMap);

    }


    public void validateResponse(int statusCode) {

        response.then().statusCode(statusCode);
        CucumberLogUtils.logPass("The status code matches with expected: " + statusCode);

    }


    public void validateResponse(Map<String, String> expectedDetails) {
        for(String key : expectedDetails.keySet()) {

            String actual = (String) responseAsMap.get(key).toString();
            String expected = expectedDetails.get(key);

            if(expectedDetails.equals("no value")) {
                if(!actual.equals("")){
                    CucumberLogUtils.logFail("Actual did NOT match with the expected for " + key + " | expected: " + expected + " | actuel: " + responseAsMap.get(key) );
                }
            }else{
                if(!actual.contains(expected)){
                    CucumberLogUtils.logFail("Actual did NOT match with the expected for " + key + " | expected: " + expected + " | actuel: " + responseAsMap.get(key) );
                }
            }

            CucumberLogUtils.logInfo("Response details matched with the expected for " + key + " | expected: " + expected + " | actuel: " + responseAsMap.get(key) );
        }

        CucumberLogUtils.logPass("Response details matched with the expected. " );

    }


    public void sendApiPostRequest(String endpoint, String component, String urlType, Map<String, String> requestDetails) {
        endpoint = getBaseUrl(component, urlType) + endpoint;

        JSONParser parser = new JSONParser();
        JSONObject body = null;

        try{
            String value = requestDetails.get("Body");
            body =(JSONObject) parser.parse(value);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        response =  given().relaxedHTTPSValidation().body(body)
                .when().post(endpoint);

        response = response.then().extract().response();
        responseAsMap = response.jsonPath().getMap("$");


        CucumberLogUtils.logPass("Successfully submitted POST request to endpoint: " + endpoint);
        CucumberLogUtils.logInfo("Response: " + responseAsMap);

    }



}
