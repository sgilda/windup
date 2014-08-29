## Create JavaDoc for Windup

Use the following procedure to create JavaDoc for Windup.

1. Open a command prompt and navigate to the root of the Windup project directory.
2. Type the following Maven command:

        mvn javadoc:aggregate -PjavadocDist
3. The JavaDoc is created in the Windup `target/site/apidocs` subdirectory.