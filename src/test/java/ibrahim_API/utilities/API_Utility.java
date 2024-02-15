package ibrahim_API.utilities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class API_Utility {

    public static String getTokenNormal() {
        Driver.getDriver().get("https://a3m-dev.rxdrugshub.com");
        Driver.getDriver().findElement(By.xpath("//*[@id='username']")).sendKeys(ConfigurationReader.get("email"));
        Driver.getDriver().findElement(By.xpath("//*[@id='password']")).sendKeys(ConfigurationReader.get("password"), Keys.ENTER);
        Driver.getDriver().navigate().to("https://dev.rxdrugshub.com/auth/userinfo");
        String text = Driver.getDriver().findElement(By.xpath("//pre")).getText();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(text, JsonObject.class);
        String token = jsonObject.get("access_token").getAsString();

        Driver.closeDriver();

        return "Bearer " + token;
    }

    public static String getToken() {
        String token = ConfigurationReader.get("token");
        if (tokenTimeCounter() > 30) {
            Driver.getDriver().get(ConfigurationReader.get("url"));
            Driver.getDriver().findElement(By.xpath("//*[@id='username']")).sendKeys(ConfigurationReader.get("email"));
            Driver.getDriver().findElement(By.xpath("//*[@id='password']")).sendKeys(ConfigurationReader.get("password"), Keys.ENTER);
            Driver.getDriver().navigate().to(ConfigurationReader.get("tokenURL"));
            String text = Driver.getDriver().findElement(By.xpath("//pre")).getText();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(text, JsonObject.class);
            token = jsonObject.get("access_token").getAsString();

            Driver.closeDriver();
            ConfigurationReader.set("token", token);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ConfigurationReader.set("previousDateTime", LocalDateTime.now().format(formatter));
        }

        return "Bearer " + token;
    }

    public static long tokenTimeCounter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime previousDateTime = LocalDateTime.parse(ConfigurationReader.get("previousDateTime"), formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(previousDateTime, currentDateTime);
        return duration.toMinutes();
    }

    public static JsonSchemaValidator schemaValidator(String path) {
        JsonSchemaValidator jsonSchemaValidator = JsonSchemaValidator
                .matchesJsonSchemaInClasspath(path);
        return jsonSchemaValidator;
    }

    public static String ObjectToJsonFormatter(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String objectJson = gson.toJson(object);
        System.out.println(objectJson); // kullandiktan sonra kapat
        return objectJson;
    }

    public static void requestBodyPrintOuter(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String payLoad = null;
        try {
            payLoad = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(payLoad);
    }

    /**
     * update json file
     */

    public static <T> void updateJsonFile(String filePath, String key, T value) {
        String jsonString = null;
        try {
            jsonString = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DocumentContext context = JsonPath.parse(jsonString.toString());
        context.set("$." + key, value);
        try {
            Files.write(Paths.get(filePath), context.jsonString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
