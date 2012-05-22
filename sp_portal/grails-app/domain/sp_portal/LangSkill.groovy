package sp_portal

class LangSkill {
    static mapping = {
      datasources(['DEFAULT', 'original'])
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
}
