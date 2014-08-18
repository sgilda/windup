## Creating an addon
* Create a Maven project.
* Add `windup-rules-parent`.
```xml
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <version.windup>2.0.0-SNAPSHOT</version.windup>
    </properties>

    <parent>
        <groupId>org.jboss.windup.rules</groupId>
        <artifactId>windup-rules-parent</artifactId>
        <version>${version.windup}</version>
    </parent>
```

* Add Windup dependencies.
The dependencies you need depend on what Windup API you will use.

_Dependencies are still settling down and the classes location are subject to change._

Windup is a Forge/Furnace based application. As such, it has a modularized design, which requires a bit of additional knowledge.
Should you need any additional dependencies, refer to [Dev: Dependencies](Dev:-Dependencies).

```xml
    <dependencies>

        <!-- Windup deps -->
        <dependency>
            <groupId>org.jboss.windup.graph</groupId>
            <artifactId>windup-graph</artifactId>
            <classifier>forge-addon</classifier>
            <version>${version.windup}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.jboss.windup.config</groupId>
            <artifactId>windup-config</artifactId>
            <classifier>forge-addon</classifier>
            <version>${version.windup}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.jboss.windup.utils</groupId>
            <artifactId>utils</artifactId>
            <classifier>forge-addon</classifier>
            <version>${version.windup}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.jboss.windup.reporting</groupId>
            <artifactId>windup-reporting</artifactId>
            <version>${version.windup}</version>
            <classifier>forge-addon</classifier>
            <scope>provided</scope>
        </dependency>


        <dependency>
            <groupId>org.jboss.forge.furnace.container</groupId>
            <artifactId>cdi-api</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.jboss.forge.furnace.container</groupId>
            <artifactId>cdi</artifactId>
            <classifier>forge-addon</classifier>
            <scope>provided</scope>
        </dependency>


        <!-- Test dependencies -->
        <dependency>
            <groupId>org.jboss.forge.furnace.test</groupId>
            <artifactId>furnace-test-harness</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.jboss.forge.furnace.test</groupId>
            <artifactId>arquillian-furnace-classpath</artifactId>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.jboss.windup.exec</groupId>
            <artifactId>windup-exec</artifactId>
            <version>${version.windup}</version>
            <classifier>forge-addon</classifier>
            <scope>test</scope>
        </dependency>

    </dependencies>
```

* Make it a Forge addon.
```xml
        <plugins>
            <!-- These two make this artifact a Forge addon. -->
            <plugin>
                <groupId>org.jboss.forge.furnace</groupId>
                <artifactId>furnace-maven-plugin</artifactId>
                <version>${version.furnace}</version>
                <executions>
                    <execution>
                        <id>generate-dot</id>
                        <phase>prepare-package</phase>
                        <goals> <goal>generate-dot</goal> </goals>
                        <configuration> <attach>true</attach> </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <artifactId>maven-jar-plugin</artifactId>
                <executions>
                    <execution>
                        <id>create-forge-addon</id>
                        <phase>package</phase>
                        <goals> <goal>jar</goal> </goals>
                        <configuration> <classifier>forge-addon</classifier> </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
```

## Creating the Rules

* Read about [OCPrewrite](http://ocpsoft.org/rewrite/).
* Create a Java class, ending with `RuleProvider`.

```java
public class MyJavaHintsRuleProvider extends WindupRuleProvider
{
}
```

* Specify the phase it should run in.
Windup runs in phases, which essentially ensures that certain groups of rules are executed before this one.
See [Rules: Phases](Rules:-Phases) for explanation.
```java
    @Override
    public RulePhase getPhase() {
        return RulePhase.MIGRATION_RULES;
    }
```

For fine-grained control the order in which the rule is executed, specify it's dependencies.
Again, see [Rules: Phases](Rules:-Phases) for explanation.
```java
    @Override
    public List<Class<? extends WindupRuleProvider>> getClassDependencies() {
        return generateDependencies(AnalyzeJavaFilesRuleProvider.class);
    }
```

### High-level conditions and operations

* And finally, create the rule itself.
This is a specific high-level rule which uses high-level conditions (`JavaClass`) and operations (`Classification`). See their documentation for the details.
Excuse the formatting of some rules, caused by Eclipse formatter, but it's a project's requirement.

```java
    @Override
    public Configuration getConfiguration(GraphContext context)
    {
        return ConfigurationBuilder.begin()
            .addRule()
            .when(
                JavaClass.references("weblogic.servlet.annotation.WLServlet").at(TypeReferenceLocation.ANNOTATION).as("ann")
            )
            .perform(
                Iteration.over().perform(   
                    Classification.of("ann").as("WebLogic @WLServlet")
                       .with(Link.to("Java Ee 6 @WebServlet", "https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Operations_Network/3.1/html/Dev_Complete_Resource_Reference/JBossAS7-JBossAS7_Standalone_Server-JCA-Workmanager.html"))
                       .withEffort(0)
                    .and(Hint.in("ann").withText("Migrate to Java EE 6 @WebServlet.").withEffort(8))
                )
                .endIteration()
            );
    }
```

For more examples, see the [BaseConfig.java](https://github.com/windup/windup/blob/master/rules/app/java-ee/src/main/java/org/jboss/windup/rules/apps/legacy/java/BaseConfig.java#L53) rule.


## Low-level Conditions and Operations

As you can see, the conditions and operations above are Java-specific. They come with the `Java Basic` ruleset. The list of existing rulesets will be part of the project documentation. Each ruleset will be accompanied with a documentation for its `Condition`s and `Operation`s (and also `Model`s).

These high-level elements provided by rulesets may cover majority of cases, but not all. Then, you will need to dive into the mid-level Windup building elements.

## Mid-level

TBD.

* Models
    * [Frames](https://github.com/tinkerpop/frames/wiki)
    * [Existing Models](Rules:-Existing-Models)
* Rules
    * [OCPrewrite](http://ocpsoft.org/rewrite/)
    * Conditions, Operations
        * Variables
    * Inter-rule action
    * [Inter-rule dependency](Rules:-Lifecycle,-Phases-and-Inter-rule-Dependencies)
        * Short IDs - [WINDUP-216](https://issues.jboss.org/browse/WINDUP-217)
    * [Phases](Rules:-Lifecycle,-Phases-and-Inter-rule-Dependencies)
* [Existing Addons / Rulesets](Rulesets)
