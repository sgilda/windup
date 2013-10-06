To use WindUp to create a report from an archive:

## Include the dependencies.

        <!-- WindUp -->
        <dependency>
            <groupId>org.jboss.windup</groupId>
            <artifactId>windup-engine</artifactId>
            <version>0.7.0</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.jboss.windup</groupId>
            <artifactId>windup-rules</artifactId>
            <version>0.7.0</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.jboss.windup</groupId>
            <artifactId>windup-reporting</artifactId>
            <version>0.7.0</version>
            <scope>compile</scope>
        </dependency>

* `windup-engine` extracts the data from the archives.
* `windup-reporting` creates the HTML reports.
* `windup-rules` is a package which contains all the static files with rules, essentially giving you "full profile" of WindUp.

## The code

    // WindUp  
    final WindupEnvironment windupEnv = new WindupEnvironment();  
    //final WindupEngine windupEng = new WindupEngine( windupEnv );  
    final ReportEngine windupReport = new ReportEngine( windupEnv );  
      
    windupReport.process( depl, reportDir );

