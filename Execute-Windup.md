Currently, Windup 2.0 is only invoked from tests. Check back later for normal CLI!

## Install Windup into Forge

1. Install [Forge 2](http://forge.jboss.org/)

2. Build Windup

3. Install the Windup UI Addon:

` forge --install org.jboss.windup:ui,2.0.0-SNAPSHOT `

> Using Forge at ./forge-distribution-2.7.2.Final
> Installation request for [org.jboss.windup:ui,2.0.0-SNAPSHOT] will: 
> Deploy: [org.jboss.windup.exec:windup-exec,2.0.0-SNAPSHOT]
> Deploy: [org.jboss.windup.utils:utils,2.0.0-SNAPSHOT]
> Deploy: [org.jboss.windup.graph:windup-graph,2.0.0-SNAPSHOT]
> Deploy: [org.jboss.windup.config:windup-config,2.0.0-SNAPSHOT]
> Deploy: [org.jboss.windup:ui,2.0.0-SNAPSHOT] 

> Confirm installation [Y/n]? y
> Installation completed successfully.

4. Install the Decompiler Addon (this will install the rules and reporting dependencies):

`forge --install org.jboss.windup.rules.apps:java-decompiler,2.0.0-SNAPSHOT`

> Using Forge at /home/jsightler/javad> evtools/forge/2.7.2.Final/forge-distribution-2.7.2.Final
> Installation request for [org.jboss.windup.rules.apps:java-decompiler,2.0.0-SNAPSHOT] will: 
> Deploy: [org.jboss.windup.tools:decompiler-procyon,2.0.0-SNAPSHOT]
> Deploy: [org.jboss.windup.ext:windup-config-groovy,2.0.0-SNAPSHOT]
> Deploy: [org.jboss.windup.reporting:windup-reporting,2.0.0-SNAPSHOT]
> Deploy: [org.jboss.windup.rules.apps:rules-java,2.0.0-SNAPSHOT]
> Deploy: [org.jboss.windup.rules.apps:java-decompiler,2.0.0-SNAPSHOT]

> Confirm installation [Y/n]? y
> Installation completed successfully.

5. Start Forge 2:

`forge`

6. Run Windup:

`[Desktop]$ windup-migrate-app --input /path/to/jee-example-app-1.0.0.ear --output /path/to/jee-example-app-1.0.0.ear.report --packages com org freemarker net`

> ***SUCCESS*** Windup execution successful!

7. View the report in the directory specified on the command line: */path/to/jee-example-app-1.0.0.ear.report*