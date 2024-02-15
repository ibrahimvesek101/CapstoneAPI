package ibrahim_API.TDD.DataProvider;

import ibrahim_API.pojo.Permission;
import ibrahim_API.utilities.ExcelUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestData {

    ExcelUtil excelUtil;


    @DataProvider(name = "name")
    public Object[][] getName() {
        excelUtil = new ExcelUtil("src/test/resources/TestData/dnm.xlsx", "Name");
        return excelUtil.getDataArrayWithoutFirstRow();
    }


    @Test(dataProvider = "name")
    public void test1(String nameData, int numbers) {
        Permission permission = new Permission(numbers, nameData, null, 0);

        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("auth","")
                .body(permission)
                .when()
                .post()
                .then();


    }


}
