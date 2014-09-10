Draft

To make Windup logging a bit readable, limit the logging levels:

### JUL
<JVM>/lib/logging.properties:
```
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
Add this sys prop to Surefire config:

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
