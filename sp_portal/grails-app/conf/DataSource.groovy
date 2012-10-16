dataSource {
    pooled = true
    driverClassName = " com.mysql.jdbc.Driver"
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
        dataSource {
            configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
            dbCreate = "create-drop"
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/osce_public"
            username = "root"
            password = ""
        }
    
        dataSource_original {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE"
        }
       
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE"
        }
    }
    production {
        dataSource {
            configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
            driverClassName = "com.mysql.jdbc.Driver"
            dbCreate = "validate"
            url = ""
            username = ""
            password = ""
        }

        dataSource_original {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE"
        }
       
    }
}
