package ibrahim_API.TDD;

import ibrahim_API.TDD.Util_TDD.Dummy;
import ibrahim_API.TDD.Util_TDD.Hooks;
import ibrahim_API.endPoints.Permission_SrvEP;
import ibrahim_API.pojo.Permission;
import ibrahim_API.utilities.API_Utility;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class Permission_Service extends Hooks {

    @Test()
    public void getAllPermissionsBYAppID() {
        response = Permission_SrvEP.getPermissionByAppID(2);
        response.then()
                .statusCode(200)
                .body("", instanceOf(List.class))
                .body("", hasSize(greaterThanOrEqualTo(1)))
                .body("", everyItem(hasKey("id")))
                .body("", everyItem(hasKey("resource")))
                .body("", everyItem(hasKey("action")))
                .body("", everyItem(hasKey("app_id")))
                .body(API_Utility.schemaValidator("SchemaValidation/Permissions.json"))
                .body("", everyItem(hasKey("app_id")));
        response.prettyPeek();
    }

    @Test(testName = "Create permission as expected", groups = "deletePermission")
    public void createPermission2() {
        Permission permission = Permission.createPermission();
        response = Permission_SrvEP.postPermission(permission);
        // API_Utility.requestBodyPrintOuter(permission); Payload view ornek
        int responseID = response.path("id");
        Dummy.setPermissionID(responseID);
        response.prettyPeek();
    }

    @Test(testName = "Get permission by id")
    public void getPermissionByID() {
        response = Permission_SrvEP.getPermissionByID(690);
        response.prettyPeek();
    }

    @Test(testName = "Delete created permission by id")
    public void deletePermissionByID() {
        response = Permission_SrvEP.deletePermissionByID(685);
        response.prettyPeek();
    }


}

