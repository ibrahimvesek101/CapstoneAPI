package ibrahim_API.TDD;

import ibrahim_API.TDD.Util_TDD.Dummy;
import ibrahim_API.TDD.Util_TDD.Hooks;
import ibrahim_API.endPoints.Role_SrvEP;
import ibrahim_API.pojo.Role;
import org.testng.annotations.Test;

public class Role_Service extends Hooks {


    @Test(testName = "get all role by app id")
    public void getRolesByAppID() {
        response = Role_SrvEP.getRolesByAppID(2);
        response.prettyPeek();
    }

    @Test(testName = "get role by role id")
    public void getRoleByID() {
        response = Role_SrvEP.getRoleByID(17);
        response.prettyPeek();
    }

    @Test(testName = "create role as expected values", groups = {"deleteRole","createPermission", "deletePermission"})
    public void createRole() {
        response = Role_SrvEP.postRole(Role.createRole());
        int roleID = response.path("id");
        Dummy.setRoleID(roleID);
       // response.prettyPeek();
    }

    @Test
    public void deleteRole() {
        response = Role_SrvEP.deleteRoleByID(2, 66);
        response.prettyPeek();
        response = Role_SrvEP.getRoleByID(66);
        response.prettyPeek();
    }


}
