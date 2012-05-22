package sp_portal

class Scar {
   static mapping = {
      datasources(['DEFAULT', 'original'])
   }

    Integer version
    Long id
    String bodypart
    Integer traitType


    static constraints = {
        bodypart nullable: true, maxSize: 60
    }
}
