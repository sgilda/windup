TBD.

## Creating an addon
* Learn about Forge / Furnace.
* Create a Maven project.
* Add `windup-rules-parent`.
```java
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

## High-level
Hints/classifications/iterations stuff

## Low-level

* Models
** [Frames](https://github.com/tinkerpop/frames/wiki)
** Existing Models
* Rules
** [OCPrewrite](http://ocpsoft.org/rewrite/)
** Conditions, Operations
*** Variables
** Inter-rule action
** Inter-rule dependency
** Short IDs
** Phases
* Addons / Rulesets
