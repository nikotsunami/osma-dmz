dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {

        // DEVELOPMENT ONLY
        dataSource {
            configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE"
        }

        dataSource_original {
            configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
           dbCreate = "validate"
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/osce"
            username = "root"
            password = ""
        }

    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE"
        }
    }
    production {

        // DMZ database datasource
        dataSource_original {
            configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
            dbCreate = "validate"
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/osce"
            username = "root"
            password = "r00ty"
        }

        // DMZ database datasource
        dataSource {
            configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
            dbCreate = "validate"
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/osce_public"
            username = "root"
            password = "r00ty"
        }

    }
}
