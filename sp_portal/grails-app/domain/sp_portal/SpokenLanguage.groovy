package sp_portal

class SpokenLanguage {

    static mapping = {
      datasources(['DEFAULT', 'original'])
    }

    Integer version
    Long id
    String languageName

    static hasMany = [langSkills: LangSkill]

    static constraints = {
        languageName nullable: true, maxSize: 40
    }
}
