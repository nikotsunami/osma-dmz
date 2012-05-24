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
        dataSource {
            configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE"
        }

/*
        dataSource {
            configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
            dbCreate = "create"
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/osce_public"
            username = "root"
            password = "admin"
        }
*/
       dataSource_original {
            configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
           dbCreate = "validate"
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/osce"
            username = "root"
            password = "admin"
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
            dbCreate = "update"
            url = "jdbc:h2:prodDb;MVCC=TRUE"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
