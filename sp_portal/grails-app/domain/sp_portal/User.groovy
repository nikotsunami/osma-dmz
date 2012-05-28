package sp_portal
import local.*;

class User {
    static constraints = {
        userName(unique:true)
        passwordHash(password:true)
        userEmail()
        standardizedPatient(nullable: true)
    }

    String userName;
    String userEmail;
    String passwordHash;
    boolean isActive;

    local.StandardizedPatient standardizedPatient;

    static hasMany = [roles:Role]


}
