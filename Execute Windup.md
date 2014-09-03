## Install Windup into Forge

1. Install [Forge 2](http://forge.jboss.org/)

2. Build Windup

3. Install the Windup UI Addon and Rules addons:

`forge -b --install org.jboss.windup:ui,2.0.0-SNAPSHOT`

`forge -b --install org.jboss.windup.rules.apps:rules-java,2.0.0-SNAPSHOT`

`forge -b --install org.jboss.windup.rules.apps:rules-java-ee,2.0.0-SNAPSHOT`

> Installation completed successfully.

## Run Windup

1. Start Forge 2:

`forge`

2. Run Windup:

`[Desktop]$ windup-migrate-app --input /path/to/jee-example-app-1.0.0.ear --output /path/to/jee-example-app-1.0.0.ear.report --packages org.example.* com.example.*`

> ***SUCCESS*** Windup execution successful!

3. View the report in the directory specified on the command line: */path/to/jee-example-app-1.0.0.ear.report*