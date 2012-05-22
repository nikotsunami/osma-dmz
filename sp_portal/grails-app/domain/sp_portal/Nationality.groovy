package sp_portal

class Nationality {
    static mapping = {
      datasources(['DEFAULT', 'original'])
   }

    Integer version
    Long id
    String nationality 


    static constraints = {
        nationality nullable: true, maxSize: 40
    }
}
