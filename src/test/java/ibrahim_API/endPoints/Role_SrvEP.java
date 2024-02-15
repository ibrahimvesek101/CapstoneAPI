package ibrahim_API.endPoints;

import ibrahim_API.TDD.Util_TDD.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Role_SrvEP {

    private static final String GET_APPLICATION_ROLE = "/auth/api/application/{appId}/role";
    private static final String GET_ROLE = "/auth/api/role";
    private static final String PUT_ROLE = "/auth/api/role";
    private static final String POST_ROLE = "/auth/api/role";
    private static final String GET_ROLE_BY_ID = "/auth/api/role/{id}";
    private static final String DELETE_ROLE_BY_ID = "/auth/api/role/{id}";


    /**
     * GET_APPLICATION_ROLE = "/auth/api/application/{appId}/role"
     */
    public static Response getRolesByAppID(int appID) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", BaseClass.token)
                .pathParam("appId", appID)
                .when()
                .get(GET_APPLICATION_ROLE);
        return response;
    }


    /**
     * POST_ROLE = "/auth/api/role";
     */
    public static Response postRole(Object payload) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", BaseClass.token)
                .body(payload)
                .when()
                .post(POST_ROLE);
        return response;
    }


    /**
     * GET_ROLE_BY_ID = "/auth/api/role/{id}";
     */
    public static Response getRoleByID(int roleID) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id", roleID)
                .header("Authorization", BaseClass.token)
                .when()
                .get(GET_ROLE_BY_ID);
        return response;
    }

    /**
     * DELETE_ROLE_BY_ID = "/auth/api/role/{id}"
     */
    public static Response deleteRoleByID(int app_id, int roleID) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id", roleID)
                .header("Authorization", BaseClass.token)
                .queryParams("app_id",app_id)
                .when()
                .delete(DELETE_ROLE_BY_ID);
        return response;
    }


}
