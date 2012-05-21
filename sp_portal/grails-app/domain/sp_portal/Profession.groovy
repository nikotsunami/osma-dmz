package sp_portal

class Profession {
    static mapping = {
      datasources(['DEFAULT', 'original'])
   }

    String profession


    static constraints = {
        profession nullable: true, maxSize: 60
    }
}
