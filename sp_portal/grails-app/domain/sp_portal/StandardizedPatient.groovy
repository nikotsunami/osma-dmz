package sp_portal

class StandardizedPatient {

   static mapping = {
      datasources(['DEFAULT', 'original'])
  //    version column: 'version', sqlType: 'int', length:11
        description column: 'descriptions'
        profession column: 'profession'
   }


    Integer version
    Long id
    Date birthday
    String city
    String email
    Integer gender
    Integer height
    String immagePath
    Integer maritalStatus
    String mobile
    String name
    Integer postalCode
    String preName
    String socialInsuranceNo
    String street
    String telephone
    String telephone2
    String videoPath
    Integer weight
    Integer workPermission
 //   AnamnesisForm anamnesisForm
    Description description
    Profession profession
 //   Nationality nationality
  //  Bankaccount bankaccount

   // static hasMany = [langSkills: LangSkill]


    static constraints = {
        birthday nullable: true, maxSize: 19
        city nullable: true, maxSize: 30
        email nullable: true, maxSize: 40
        gender nullable: true
        height nullable: true
        immagePath nullable: true
        maritalStatus nullable: true
        mobile nullable: true, maxSize: 30
        name nullable: true, maxSize: 40
        postalCode nullable: true
        preName nullable: true, maxSize: 40
        socialInsuranceNo nullable: true, maxSize: 13
        street nullable: true, maxSize: 60
        telephone nullable: true, maxSize: 30
        telephone2 nullable: true, maxSize: 30
        videoPath nullable: true
        weight nullable: true
        workPermission nullable: true
    }
}
