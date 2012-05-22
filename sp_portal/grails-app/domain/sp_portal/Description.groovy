package sp_portal

class Description {

    Integer version
    Long id


    static mapping = {
      datasources(['DEFAULT', 'original'])
   }

    String description
    
    static hasMany = [standardizedPatients: StandardizedPatient]

    static constraints = {
        description nullable: true, maxSize: 999
    }
}
