package sp_portal

class User {

    static mapping = {

        standardizedPatient table: 'standardized_patient' , column: 'id'
    }

    static constraints = {
        userName(unique:true)
        passwordHash(password:true)
        userEmail()
    }

    String userName;
    String userEmail;
    String passwordHash;
    boolean isActive;

    StandardizedPatient standardizedPatient;

    static hasMany = [roles:Role]


}
