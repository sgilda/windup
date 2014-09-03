## Install Windup into Forge

1. Install [Forge 2](http://forge.jboss.org/)

2. [Build Windup](./Dev: Build Windup from Source)

3. Install the Windup UI Addon and Rules addons:
```
forge -b --install org.jboss.windup:ui,2.0.0-SNAPSHOT
forge -b --install org.jboss.windup.rules.apps:rules-java,2.0.0-SNAPSHOT
forge -b --install org.jboss.windup.rules.apps:rules-java-ee,2.0.0-SNAPSHOT
```
> Installation completed successfully.

## Run Windup

### Interactive run

1. Start Forge 2:

`forge`

2. Run Windup:

`[Desktop]$ windup-migrate-app --input /path/to/jee-example-app-1.0.0.ear --output /path/to/jee-example-app-1.0.0.ear.report --packages org.example.* com.example.*`

> `***SUCCESS*** Windup execution successful!`

3. View the report in the directory specified on the command line: */path/to/jee-example-app-1.0.0.ear.report*

### Batch run (for a shell script)

1. Run `forge --evaluate "windup-migrate-app --input ... --output ... --packages com.foo org.foo"`
    Run `forge` and then `man windup-migrate-app` to see the possible parameters.