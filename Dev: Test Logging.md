To control Windup logging during unit/integration test execution, limit or increase the logging levels using the following steps:

### JUL (java.util.logging)
1. First, find your JRE. On Linux, you may run ``readlink -f `which java` ``.
   Example location is `/usr/lib/jvm/java-7-openjdk-amd64/jre`. On windows, find your %JAVA_HOME%.
2. Add this to `(JRE)/lib/logging.properties`:

```
# Valid levels are SEVERE, WARNING, INFO, FINE, FINER, FINEST, CONFIG

# To enable windup debug logging
org.jboss.windup.level = FINEST

org.jboss.forge.level = WARNING
# To prevent AddonLoader's "missing dependencies: ..."
org.jboss.forge.furnace.impl.addons.level = OFF

org.jboss.weld.level = WARNING
org.jboss.weld.event.level = SEVERE
org.jboss.weld.interceptor.util.InterceptionTypeRegistry.level = SEVERE

#com.thinkaurelius.titan.diskstorage.level = SEVERE
com.thinkaurelius.titan.level = SEVERE
```
### Slf4j (Weld, ...):
```
-Dorg.slf4j.simpleLogger.defaultLogLevel=ERROR
-Dorg.slf4j.simpleLogger.log.org.jboss.weld.event.ExtensionObserverMethodImpl=ERROR
```
See http://www.slf4j.org/apidocs/org/slf4j/impl/SimpleLogger.html    
Add these sys props to Surefire config:

```xml
            <!-- Surefire -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.17</version>
                <configuration>
                    <excludes>
                        <exclude>${testExcludeString}</exclude>
                    </excludes>
                    <systemPropertyVariables>
                        <org.slf4j.simpleLogger.defaultLogLevel>ERROR</org.slf4j.simpleLogger.defaultLogLevel>
                        <org.slf4j.simpleLogger.log.org.jboss.weld.event.ExtensionObserverMethodImpl>ERROR</org.slf4j.simpleLogger.log.org.jboss.weld.event.ExtensionObserverMethodImpl>
                    </systemPropertyVariables>
                    <argLine>-Xms512m -Xmx2048m -XX:MaxPermSize=512m</argLine>
                </configuration>
            </plugin>
```
