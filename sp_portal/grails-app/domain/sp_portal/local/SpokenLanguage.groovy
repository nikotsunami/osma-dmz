package sp_portal.local
import local.*;

class SpokenLanguage {

    static mapping = {
      datasources(['DEFAULT'])
    }

    Integer version
    Long id
    Long origId
    String languageName

    static hasMany = [langSkills: LangSkill]

    static constraints = {
        languageName nullable: true, maxSize: 40
        version nullable: true
    }
}
