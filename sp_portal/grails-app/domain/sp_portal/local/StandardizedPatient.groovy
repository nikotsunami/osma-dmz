package sp_portal.local
import local.*;
import org.joda.time.LocalDate
class StandardizedPatient {

   static mapping = {
      datasources(['DEFAULT'])
  //    version column: 'version', sqlType: 'int', length:11
        description column: 'descriptions'
        profession column: 'profession'
        bankaccount column: 'bank_account'
        anamnesisForm column: 'anamnesis_form'
        nationality column: 'nationality'
   }


    Integer version
    Long id
    Long origId
    LocalDate birthday
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
    AnamnesisForm anamnesisForm
    Description description
    Profession profession
    Nationality nationality
    Bankaccount bankaccount


   static hasOne = [anamnesisForm: AnamnesisForm,
                    bankaccount: Bankaccount,
                    description: Description]

   static hasMany = [profession: Profession,
                     nationality: Nationality,
					 ]

   static mappedBy = [anamnesisForm: 'id',
                      profession: 'id',
                      nationality: 'id',
                      bankaccount: 'id',
                      description: 'id'
					]


    static constraints = {
        birthday nullable: true, maxSize: 19
        city nullable: true, maxSize: 30
        email nullable: true, maxSize: 40, email:true
        gender nullable: true
        height nullable: true
        immagePath nullable: true,maxSize:253
        maritalStatus nullable: true
        mobile nullable: true, maxSize: 30
        name nullable: true, maxSize: 40
        postalCode nullable: true
        preName nullable: true, maxSize: 40
        socialInsuranceNo nullable: true, maxSize: 13,matches: "[0-9]{13,13}"
        street nullable: true, maxSize: 60
        telephone nullable: true, maxSize: 30
        telephone2 nullable: true, maxSize: 30
        videoPath nullable: true,maxSize:253
        weight nullable: true
        workPermission nullable: true
        version nullable: true
        anamnesisForm nullable: true
        description nullable: true
        profession nullable: true
        nationality nullable: true
        bankaccount nullable: true

     }

  /*  String toString(){
      return "[ version = "+ version+ " id = " + id+ " birthday = " + birthday+ " city = " + city +" email = "+ email +
" gender = " + gender + " height = " + height + " immagePath =" +immagePath+" maritalStatus = "+maritalStatus +" mobile = " + mobile +" ]";
   }
*/

	  String toString(){
		return email
	  }

}
