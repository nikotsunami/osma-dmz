package sp_portal

class Role {


    static constraints = {
       roleName column:'role_name'
       roleDescription column: 'role_description'
    }

    String roleName = "";
    String roleDescription = "";

    static belongsTo = User;
    static hasMany = [users:User];

    public String toString(){
        return this.roleName;
    }

}
