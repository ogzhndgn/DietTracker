package com.diettracker.webapp.service.api;

import com.diettracker.webapp.config.RandomOrgConfig;
import com.diettracker.webapp.exception.impl.TokenNotGeneratedException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author the Poet <dogan_oguzhan@hotmail.com> 10.04.2016.
 */
@Component
public class RandomOrgService {
    public static final String REQUEST_ID = String.valueOf(System.currentTimeMillis());
    @Autowired
    RandomOrgConfig randomOrgConfig;
    private final Logger logger = LogManager.getLogger(RandomOrgService.class);

    public String getGeneratedToken() throws TokenNotGeneratedException {
        JsonObject request = this.getRequestObject();
        return this.postRequest(request);
    }

    private JsonObject getRequestObject() {
        String requestId = REQUEST_ID;
        JsonObject request = new JsonObject();
        request.addProperty("jsonrpc", "2.0");
        request.addProperty("method", randomOrgConfig.getApiMethod());
        request.add("params", this.getParamsObject());
        request.addProperty("id", requestId);
        logger.info("Request for id: " + requestId + " " + request.toString());
        return request;
    }

    private JsonObject getParamsObject() {
        JsonObject params = new JsonObject();
        params.addProperty("apiKey", randomOrgConfig.getApiKey());
        params.addProperty("n", randomOrgConfig.getCount());
        params.addProperty("length", randomOrgConfig.getLength());
        params.addProperty("characters", randomOrgConfig.getCharacters());
        return params;
    }

    private String postRequest(JsonObject request) throws TokenNotGeneratedException {
        try {
            HttpsURLConnection con = (HttpsURLConnection) new URL(randomOrgConfig.getApiUrl()).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            dos.writeBytes(request.toString());
            dos.flush();
            dos.close();
            int responseCode = con.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                return this.getResult(con);
            } else {
                logger.error("ResponseCode is " + responseCode + " for random.org request with request id " + REQUEST_ID);
                throw new TokenNotGeneratedException();
            }
        } catch (IOException | TokenNotGeneratedException e) {
            logger.error("An error occured while posting request to random.org", e);
            throw new TokenNotGeneratedException();
        }
    }

    private String getResult(HttpsURLConnection con) throws TokenNotGeneratedException {
        try {
            StringBuilder response = this.getResponse(con);
            logger.info("random.org response for request with request id " + REQUEST_ID + " " + response);
            JsonObject responseJson = new JsonParser().parse(response.toString()).getAsJsonObject();
            this.hasError(responseJson);
            JsonArray dataArray = responseJson.get("result").getAsJsonObject().get("random").getAsJsonObject().get("data").getAsJsonArray();
            return dataArray.get(0).getAsString();
        } catch (IOException | TokenNotGeneratedException e) {
            throw new TokenNotGeneratedException();
        }
    }

    private StringBuilder getResponse(HttpsURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    private void hasError(JsonObject responseJson) throws TokenNotGeneratedException {
        JsonElement errorElement = responseJson.get("error");
        if (errorElement != null) {
            JsonObject errorObject = errorElement.getAsJsonObject();
            String code = errorObject.get("code").getAsString();
            String message = errorObject.get("message").getAsString();
            logger.error("Error at random.org response: " + code + " - " + message + " for request with request id " + REQUEST_ID);
            throw new TokenNotGeneratedException();
        }
    }
}