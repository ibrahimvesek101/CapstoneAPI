package ibrahim_API.TDD.Util_TDD;

import ibrahim_API.endPoints.Permission_SrvEP;
import ibrahim_API.endPoints.Role_SrvEP;
import ibrahim_API.pojo.Permission;
import ibrahim_API.utilities.API_Utility;
import ibrahim_API.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Hooks extends BaseClass {

    @BeforeClass()
    public void setUp() {
        RestAssured.baseURI = ConfigurationReader.get("url");
        token = API_Utility.getToken();
    }

    @AfterClass
    public void tearDown() {

    }

    @AfterMethod(onlyForGroups = "deletePermission")
    public void deletePermissionHook() {
        System.out.println("deletePermission working ");
        Permission_SrvEP.deletePermissionByID(Dummy.getPermissionID());
        response = Permission_SrvEP.getPermissionByID(Dummy.getPermissionID());
        response.then().statusCode(404);
    }

    @BeforeMethod(onlyForGroups = "createPermission")
    public void createPermissionHook() {
        System.out.println("createPermission working ");
        response = Permission_SrvEP.postPermission(Permission.createPermission());
        int id = response.path("id");
        Dummy.setPermissionID(id);
    }

    @AfterMethod(onlyForGroups = "deleteRole")
    public void deleteRoleHook() {
        System.out.println("deleteRole working");
        Role_SrvEP.deleteRoleByID(2, Dummy.getRoleID());
        response = Role_SrvEP.getRoleByID(Dummy.getRoleID());
        response.prettyPeek();
    }




}
