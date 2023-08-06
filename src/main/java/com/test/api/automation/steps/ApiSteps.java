package com.test.api.automation.steps;

import com.test.api.automation.impl.ApiImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Map;


public class ApiSteps {


    @Given("I send GET request to endpoint {string} with {string} {string} url")
    public void iSendGETRequestToEndpointWithUrl(String endpoint, String component, String urlType) throws Throwable{
        ApiImpl impl = new ApiImpl();
        impl.sendApiGetRequest(endpoint, component, urlType);
    }


    @Then("I validate the status code {int}")
    public void iValidateTheStatusCode(int statusCode) {
        ApiImpl impl = new ApiImpl();
        impl.validateResponse(statusCode);

    }


    @Then("I validate the response contains following:")
    public void iValidateTheResponseContainsFollowing(Map<String, String> expectedDetails) {
        ApiImpl impl = new ApiImpl();
        impl.validateResponse(expectedDetails);


    }

    @Given("I send POST request to endpoint {string} with {string} {string} url:")
    public void iSendPOSTRequestToEndpointWithUrl(String endpoint, String component, String urlType, Map<String, String> expectedDetails) {
        ApiImpl impl = new ApiImpl();
        impl.sendApiPostRequest(endpoint, component, urlType, expectedDetails);

    }
}
