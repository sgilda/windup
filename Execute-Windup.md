Currently, Windup 2.0 is only invoked from tests. Check back later for normal CLI!

## Install Windup into Forge

1. Install [Forge 2](http://forge.jboss.org/)

2. Build Windup

3. Install the Windup UI Addon:

` forge --install org.jboss.windup:ui,2.0.0-SNAPSHOT `

` Using Forge at /home/jsightler/javadevtools/forge/2.7.2.Final/forge-distribution-2.7.2.Final`

` Installation request for [org.jboss.windup:ui,2.0.0-SNAPSHOT] will: `
` Deploy: [org.jboss.windup.exec:windup-exec,2.0.0-SNAPSHOT]`
` Deploy: [org.jboss.windup.utils:utils,2.0.0-SNAPSHOT]`
` Deploy: [org.jboss.windup.graph:windup-graph,2.0.0-SNAPSHOT]`
` Deploy: [org.jboss.windup.config:windup-config,2.0.0-SNAPSHOT]`
` Deploy: [org.jboss.windup:ui,2.0.0-SNAPSHOT]`

` Confirm installation [Y/n]? y`

` Installation completed successfully.`

4. Install the Decompiler Addon (this will install the rules and reporting dependencies):

`forge --install org.jboss.windup.rules.apps:java-decompiler,2.0.0-SNAPSHOT`

`Using Forge at /home/jsightler/javadevtools/forge/2.7.2.Final/forge-distribution-2.7.2.Final`
`Installation request for [org.jboss.windup.rules.apps:java-decompiler,2.0.0-SNAPSHOT] will: `
`Deploy: [org.jboss.windup.tools:decompiler-procyon,2.0.0-SNAPSHOT]`
`Deploy: [org.jboss.windup.ext:windup-config-groovy,2.0.0-SNAPSHOT]`
`Deploy: [org.jboss.windup.reporting:windup-reporting,2.0.0-SNAPSHOT]`
`Deploy: [org.jboss.windup.rules.apps:rules-java,2.0.0-SNAPSHOT]`
`Deploy: [org.jboss.windup.rules.apps:java-decompiler,2.0.0-SNAPSHOT]`

`Confirm installation [Y/n]? y`

`Installation completed successfully.`

5. Start Forge 2:

`forge`

6. Run Windup:

`JBoss Forge, version [ 2.7.2.Final ] - JBoss, by Red Hat, Inc. [ http://forge.jboss.org ]`

`[wildfly]$ windup-migrate-app`

`Input (Input File or Directory (a Directory is required for source mode)):  /path/to/inputarchive.war`

`Output (Output Directory (WARNING: any existing files will be removed)):  /tmp/WindupReport`

`Scan Java Packages (A list of java package name prefixes to scan (eg, com.myapp)):  com`

`Scan Java Packages (A list of java package name prefixes to scan (eg, com.myapp)):  `

`***SUCCESS*** Windup execution successful!`

7. View the report in /tmp/WindupReport/index.html
