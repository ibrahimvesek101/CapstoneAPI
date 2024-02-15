package ibrahim_API.TDD.Util_TDD;


public class Dummy {
   private static int permissionID;
   private static int roleID;




   public static int getRoleID() {
      return roleID;
   }

   public static void setRoleID(int roleID) {
      Dummy.roleID = roleID;
   }

   public static int getPermissionID() {
      return permissionID;
   }

   public static void setPermissionID(int permissionID) {
      Dummy.permissionID = permissionID;
   }
}
