DMZ server
-----------------------------------

1. BUILD THE SYSTEM
=============================================================================
   
   1.1 To install grails follow these steps:
       
       - Download gralis installation package from http://www.google.com.hk/url?sa=t&rct=j&q=grails&source=web&cd=2&ved=0CGcQFjAB&url=http%3A%2F%2Fgrails.org%2FDownload&ei=JXzMT63yNeeoiAfKrdnfBg&usg=AFQjCNGJ9lxtzfREZNjtGEeRqCSX2YrsZA&cad=rjt

       - create a GRAILS_HOME environment variable that points to the path of the Grails distribution (the folder contain this file).

       - add the bin folder in the Grails distribution to the PATH environment variable.
   
   1.2 To compile:
     
       - you can run the grails command, as follows:
        
       > grails compile

   1.3 To create war file:
   
       - you can run the grails command as follows:
       
       > grails war
   
   1.4 To run:
       
       - you can run the grails command as follows:
       
       > grails run-app

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

      - First you have to log in as the administrator.

      - Then click Import Data button, in order to import users from original database.

   2.4 To disable original original database:

      - To set the datasource to be an in memory db or to the osce_public db, you need to comment DataSource files down osce_public of configuration like this:
        
              e.g:
              
        /*  dataSource {
              configClass = "org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration"
              dbCreate = "create"
              driverClassName = "com.mysql.jdbc.Driver"
              url = "jdbc:mysql://localhost/osce_public"
              username = "root"
              password = "admin"
            }
         */

              It will set up the data source is a database in memory.

   2.5 To change user password:

           If you are a manager, you can enter user page, change all the password of user.

           If you are a normal user, you can login first, and then into the welcome page, the default password is null,

           And then to click on My Account menu, into the show page, click the Edit button, the password changing.





3. CONFIGURE OSCE SERVER
=============================================================================

   3.1 To configure osce server to connect to DMZ:

       You need to change the osce server web.xml document , In the XML document DMZ_HOST_ADDRESS to oneself of the DMZ server address.

     3.2 To send StandarizedPatient to DMZ server(user name is email,password is socialInsurance):
	
            First you have to log in osce server, and then click Simulationspatienten menu,

            Then select a standarizedPatient, on the right, there is a send to DMZ button and click, You can 

            send a StandarizedPatient to the DMZ server(user name is email,password is socialInsurance).
            

     3.3  To pull standardized patient back to osce server

	Again, just click on pull to DMZ button.













