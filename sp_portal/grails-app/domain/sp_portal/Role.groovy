package sp_portal

class Role {


    static constraints = {
    }

    String roleName = "";
    String roleDescription = "";

    static belongsTo = User;
    static hasMany = [users:User];

    public String toString(){
        return this.roleName;
    }

}
