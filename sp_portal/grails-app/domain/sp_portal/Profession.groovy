package sp_portal

class Profession {
    static mapping = {
      datasources(['DEFAULT', 'original'])
   }

    Integer version
    Long id

    String profession


    static constraints = {
        profession nullable: true, maxSize: 60
    }
}
