An example of how to create API shortcut using operation API:

```java
                     .addRule()
                     .when(
                        JavaClass.references("commonj.work.Work").at(TypeReferenceLocation.EXTENDS_TYPE).as("refs")
                     )
                     .perform(
                        Iteration.over("refs").as("ref").perform(   
                           Classification.of("#{ref.fileModel}").as("Commonj Work")
                              .with(Link.to("JBoss JCA WorkManager", "https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Operations_Network/3.1/html/Dev_Complete_Resource_Reference/JBossAS7-JBossAS7_Standalone_Server-JCA-Workmanager.html"))
                              .withEffort(0)
                           .and(Hint.in("#{ref.fileModel}").at("ref").withText("Migrate to JBoss JCA WorkManager").withEffort(8))
                        )
                        .endIteration()
                     )
```

For the implementation of the operation, see [here](https://github.com/lincolnthree/windup/blob/WINDUP-133/rules/app/java-ee/src/main/java/org/jboss/windup/rules/apps/legacy/java/BaseConfig.java#L60).