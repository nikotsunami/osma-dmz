package sp_portal.remote
import remote.*;

class LangSkill {
    static mapping = {
      datasources(['original'])
      standardizedPatient column: 'standardizedpatient'
      spokenLanguage column: 'spokenlanguage'
   }

    Integer version
    Long id
    Integer skill
    StandardizedPatient standardizedPatient
    SpokenLanguage spokenLanguage


    static hasMany = [standardizedPatient: StandardizedPatient,
                     spokenLanguage: SpokenLanguage]

    static mappedBy = [standardizedPatient: 'id',
                      spokenLanguage: 'id'
                       ]

    static constraints = {
        version nullable: true
        skill nullable: true
        standardizedPatient nullable: true
        spokenLanguage nullable: true
    }
}
