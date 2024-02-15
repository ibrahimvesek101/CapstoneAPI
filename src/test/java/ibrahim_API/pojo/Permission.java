package ibrahim_API.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@AllArgsConstructor
public class Permission {


    private int id;
    private String resource;
    private String action;
    @JsonProperty("app_id")
    private int app_id;

    public Permission() {
    }

    public static Permission createPermission() {
        Permission permission = new Permission();
        permission.setResource("Book for test");
        permission.setAction("test,etc...");
        permission.setApp_id(2);
        return permission;
    }

    public static Permission createPermissionByID(int permissionID) {
        Permission permission = new Permission();
        permission.setId(permissionID);
        return permission;
    }
}
