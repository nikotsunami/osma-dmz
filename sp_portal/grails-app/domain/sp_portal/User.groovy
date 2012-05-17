package sp_portal

class User {

    static constraints = {
        userName(unique:true)
        passwordHash(password:true)
        userEmail()
    }

    String userName;
    String userEmail;
    String passwordHash;
    boolean isActive;


    static hasMany = [roles:Role]


}
