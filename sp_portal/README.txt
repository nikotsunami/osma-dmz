DMZ server
-----------------------------------

The DMZ application was developed in Grails, which is a technology based on Java and especially Spring and Hibernate.
It can be built as a war file and deployed to any java servlet contained such as tomcat.


1. BUILD THE SYSTEM
=============================================================================

   1.1 To install grails follow these steps:

       - Download gralis installation package from http://grails.org/Download

       - create a GRAILS_HOME environment variable that points to the path of the Grails distribution (the folder contain this file).

       - add the bin folder in the Grails distribution to the PATH environment variable.

   1.2 Configure database config

           Configure database details in sp_portal/grails-app/conf/DataSource.groovy
            environments {
                production {
                    dataSource {
                         configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
                        dbCreate = "create"
                        driverClassName = "com.mysql.jdbc.Driver"
                        url = "jdbc:mysql://localhost/osce_public"
                        username = "<db username>"
                        password = "<db password>"
                    }

                    dataSource_original {
                        // see sections 2.3 and 2.4 below
                    }
            }

   1.3 To compile:

       - you can run the grails command, as follows:

       > grails compile

   1.4 To create war file:

       - you can run the grails command as follows:


       > grails war

   1.5 To run in development mode:

       - you can run the grails command as follows, this is very userful for testing your configuration:

       > grails -Dgrails.env=production run-app

   For more info checkout the Grails homepage at http://grails.org


2. LOGIN IN
=============================================================================

   2.1 To configure admin username and password:

    In the directory sp_portal \ grails-app \ conf, you can change ' Config ' file like this:

          sp_portal.admin.username = "admin"
          sp_portal.admin.password = "password123"

    Administrator username will become 'admin', and the password will become 'password123'.

   2.2 To log in:

      - run system with grails command (grails run-app).

      - Input the website in the browser localhost:8080/sp_portal/.

          - Enter your user name and password in the login page, then you will be logged in.

   2.3 To import users from original database:
        During development it was desirable to be able to import users from the original osce database directly, it may also be useful to do this
        in production before the server is placed in the DMZ. However users may also be pushed to the DMZ system from the osce system individually.

      - First you must configure the datasource_orig in sp_portal/grails-app/conf/DataSource.groovy
      in:
            environments {
                production {
                    dataSource_original {
                         configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
                        dbCreate = "create"
                        driverClassName = "com.mysql.jdbc.Driver"
                        url = "jdbc:mysql://localhost/osce"
                        username = "<db username>"
                        password = "<db password>"
                    }
            }
      - Then you have to log in as the administrator.

      - Then click Import Data button, in order to import users from original database.

   2.4 To disable original database:
   In  production use the dataSource_original

      - To set the dataSource_original to be an in memory db like this

              e.g:
            environments {
                production {
                    dataSource_original {
                        configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
                        dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
                        url = "jdbc:h2:mem:devDb;MVCC=TRUE"
                    }
            }

              It will set up the dataSource_original source as a database in memory and importing data will have no effect.

   2.5 To change user password:

           If you are a manager, you can enter user page, change all the password of user.

           If you are a normal user, you can login first, and then into the welcome page, the default password is null,

           And then to click on My Account menu, into the show page, click the Edit button, the password changing.





3. CONFIGURE OSCE SERVER
=============================================================================

   3.1 To configure osce server to connect to DMZ:

       You need to change the osce server web.xml file, In the XML document set DMZ_HOST_ADDRESS to the DMZ server address.

     3.2 To send StandarizedPatient to DMZ server(user name is email,password is socialInsurance):

            First you have to log in osce server, and then click Simulationspatienten menu,

            Then select a standarizedPatient, on the right, there is a send to DMZ button and click, You can

            send a StandarizedPatient to the DMZ server(an account will be created for them with user name as their email, and password as their socialInsurance number)
            If the user's socialInsurance number is empty then the current default password will be the string "null".


     3.3  To pull standardized patient back to osce server

    Again, just click on pull to DMZ button.


4. I18n
=============================================================================
    We have currently added full translations in English and Chinese other partial translations of default strings are available.
    To add futher translations property files should be editied or added in sp_portal/grails-app/i18n/









