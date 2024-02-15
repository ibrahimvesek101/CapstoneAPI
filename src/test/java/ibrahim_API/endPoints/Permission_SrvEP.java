package ibrahim_API.endPoints;

import ibrahim_API.TDD.Util_TDD.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class Permission_SrvEP {

    private static final String GET_APP_PERMISSION = "/auth/api/application/{appId}/permission";
    private static final String GET_PERMISSION = "/auth/api/permission";
    private static final String PUT_PERMISSION = "/auth/api/permission";
    private static final String POST_PERMISSION = "/auth/api/permission";
    private static final String GET_PERMISSION_BY_ID = "/auth/api/permission/{id}";
    private static final String DELETE_PERMISSION_BY_ID = "/auth/api/permission/{id}";


    /**
     * GET_APP_PERMISSION = "/auth/api/application/{appId}/permission"
     */
    public static Response getPermissionByAppID(int appID) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("appId", appID)
                .header("Authorization", BaseClass.token)
                .when()
                .get(GET_APP_PERMISSION);
        return response;
    }

    /**
     * POST_PERMISSION = "/auth/api/permission"
     */
    public static Response postPermission(Object payload) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", BaseClass.token)
                .body(payload)
                .when()
                .post(POST_PERMISSION);
        return response;
    }

    /**
     * GET_PERMISSION_BY_ID = "/auth/api/permission/{id}
     */
    public static Response getPermissionByID(int permissionID) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id", permissionID)
                .header("Authorization", BaseClass.token)
                .when()
                .get(GET_PERMISSION_BY_ID);
        return response;
    }

    /**
     * DELETE_PERMISSION_BY_ID = "/auth/api/permission/{id}
     */
    public static Response deletePermissionByID(int permissionID) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id", permissionID)
                .header("Authorization", BaseClass.token)
                .when()
                .delete(DELETE_PERMISSION_BY_ID);
        return response;
    }




}
