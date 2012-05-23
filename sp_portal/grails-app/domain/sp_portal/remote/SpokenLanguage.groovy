package sp_portal.remote
import remote.*;

class SpokenLanguage {

    static mapping = {
      datasources([ 'original'])
    }

    Integer version
    Long id

    String languageName

    static hasMany = [langSkills: LangSkill]

    static constraints = {
        languageName nullable: true, maxSize: 40
        version nullable: true
    }
}
