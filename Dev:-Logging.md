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
```
(add this sys prop to Surefire config)