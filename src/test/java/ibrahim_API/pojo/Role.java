package ibrahim_API.pojo;

import ibrahim_API.TDD.Util_TDD.Dummy;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Role {

    private int id;
    private String name;
    private int app_id;
    private String description;
    private Permission[] permissions;


    public static Role createRole() {
        Role role = new Role();
        role.setName("Driver");
        role.setApp_id(2);
        role.setDescription("Roads never end");
//        Permission[] permission = new Permission[1];
//        permission[0] = Permission.createPermission();
        role.setPermissions(new Permission[]{Permission.createPermissionByID(Dummy.getPermissionID())});
        return role;
    }


}
