_Based on [this](https://github.com/OndraZizka/windup/commit/3574e81900cbad2b3a83d0bd6c263eb22c48e55e) discussion._

## Addon structure
Typical structure of an addon maven modules:
```
myaddon-parent
+--- myaddon, classifier: forge-addon, type: pom
+--- myaddon-api - jar
+--- myaddon-impl - jar
+--- myaddon-tests - jar
```

The `addon` pom can declare dependencies on other `forge-addon`s
These must have `<scope>provided</scope>`.
```xml
<dependency>
    <groupId>org.jboss.forge.furnace.container</groupId>
    <artifactId>cdi</artifactId>
    <classifier>forge-addon</classifier>
    <scope>provided</scope>
</dependency>
```

The `addon` POMs may also declare dependency on normal maven artifacts.
```xml
<dependency>
    <groupId>com.thinkaurelius.titan</groupId>
    <artifactId>titan-lucene</artifactId>
    <version>${version.titangraph.lucene}</version>
</dependency>
```
_One more question regrading dependencies. Is it advisable to depend on the `forge-addon` artifact from addon's subparts?_


## Addon sub-parts

Addon sub-parts declare dependency preferably on forge addons, 
```xml
<dependency>
    <groupId>org.jboss.forge.furnace.container</groupId>
    <artifactId>cdi</artifactId>
    <classifier>forge-addon</classifier>
    <scope>provided</scope>
</dependency>
```
but may instead depend on it's particular part, e.g. `api`. The latter makes keeping the code decoupled easier. However, the addon dependency still needs to be declared in the depending addon's POM.
```xml
<dependency>
    <groupId>org.jboss.forge.furnace.container</groupId>
    <artifactId>cdi-api</artifactId>
</dependency>
```
Declaring a `forge-addon` depencendy anywhere else than the `forge-addon` pom doesn't actually make furnace to take it into account. I.e. classes from the dep won't be available unless you declare it in the `forge-addon`'s pom.

Addon's sub-parts may also declare dependencies on other normal maven dependencies.

For test dependencies on addons:
Both addons and their sub-parts should use `<scope>test</scope>`. ???
```xml
<dependency>
    <groupId>org.jboss.windup.graph</groupId>
    <artifactId>windup-graph</artifactId>
    <version>${project.version}</version>
    <classifier>forge-addon</classifier>
    <scope>test</scope>
</dependency>
```

### Dependencies between addon sub-parts
Subpart may declare dependency on other subpart. E.g. `impl` typically depends on `api`.
In that case, the scope should be `compile`. (or `provided`???)
```xml
<dependency>
    <groupId>org.jboss.windup.graph</groupId>
    <artifactId>windup-graph-api</artifactId>
    <scope>compile</scope>
</dependency>
```

