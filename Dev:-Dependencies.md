_Based on [this](https://github.com/OndraZizka/windup/commit/3574e81900cbad2b3a83d0bd6c263eb22c48e55e) discussion._

## Addon structure
Typical structure of an addon maven modules:
```
myaddon-parent
+--- myaddon, classifier: forge-addon - jar
+--- myaddon-api - jar
+--- myaddon-impl - jar
+--- myaddon-tests - jar
```

The `addon` pom can declare dependencies on other `forge-addon`s
Use `<optional>true</optional> to prevent exporting the addon to dependents.
```xml
<dependency>
    <groupId>org.jboss.forge.furnace.container</groupId>
    <artifactId>cdi</artifactId>
    <classifier>forge-addon</classifier>
    <optional>true</optional>
</dependency>
```

The `addon` POMs may also declare dependency on normal maven artifacts.
Use `<optional>true</optional> to prevent exporting the addon to dependents.
```xml
<dependency>
    <groupId>com.thinkaurelius.titan</groupId>
    <artifactId>titan-lucene</artifactId>
    <version>${version.titangraph.lucene}</version>
</dependency>
```
_One more question regrading dependencies. Is it advisable to depend on the `forge-addon` artifact from addon's subparts?_

Addons should reference the `<classifier>forge-addon</classifier>` artifact from other addons.


## Addon sub-parts

Addon sub-parts declare dependency preferably on forge addon APIs, not on the addons themselves, wherever possible (wherever an addon has an explicit API). These dependencies must be marked as `provided` scope. 
```xml
<dependency>
    <groupId>org.jboss.forge.furnace.container</groupId>
    <artifactId>cdi-api</artifactId>
    <classifier>forge-addon</classifier>
    <scope>provided</scope>
</dependency>
```
but may instead depend on the primary addon dependency, e.g. `forge-addon`. The latter makes the poms simpler, but can be confusing, because the addon dependency still needs to be declared in the depending addon's POM.
```xml
<dependency>
    <groupId>org.jboss.forge.furnace.container</groupId>
    <artifactId>cdi-api</artifactId>
</dependency>
```
Declaring a `forge-addon` depencendy anywhere else than the `forge-addon` pom doesn't actually cause Furnace to establish an addon dependency. I.e. classes from the dep won't be available unless you declare it in the `forge-addon`'s pom.

Addon's sub-parts may also declare dependencies on other normal maven dependencies, and these are treated as normal.

Unaffiliated note:
> Addon depends on API , scope `compile`
> Addon depends on Impl , scope `compile` with `<optional>true</optional>` - prevents exporting this dependency to consumers of the addon.

## Test dependencies
For test dependencies on addons:
Any addon/sub-part requiring an addon for testing purposes should use `<scope>test</scope>`.

```xml
<dependency>
    <groupId>org.jboss.windup.graph</groupId>
    <artifactId>windup-graph</artifactId>
    <version>${project.version}</version>
    <classifier>forge-addon</classifier>
    <scope>test</scope>
</dependency>
```

### Dependencies between sub-parts within the same addon
Subpart may declare dependency on other subpart. E.g. `impl` typically depends on `api`.
In that case, the scope should be set appropriately for the given situation - typically `provided` or `compile` scope.
```xml
<dependency>
    <groupId>org.jboss.windup.graph</groupId>
    <artifactId>windup-graph-api</artifactId>
    <scope>compile</scope>
</dependency>
```

