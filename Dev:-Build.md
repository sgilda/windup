## Build Windup from Source

This information is provided for developers who plan to contribute code to the Windup open source project.

### System Requirements to Build Windup

1. Java 1.7. 

    You can choose from the following:

        OpenJDK
        Oracle Java SE

2. Maven 3.1.1 or newer

    If you have not yet installed or configured Maven, see [Install and Configure Maven](https://github.com/windup/windup/wiki/Install-and-Configure-Maven) for details.

    If you have installed Maven, you can check the version by typing the following in a command prompt:

        mvn --version 
3. IDE requirements

    If you prefer, you can work within an IDE as long as it provides support for Groovy. The following IDEs are recommended.

    * [Red Hat JBoss Developer Studio 7.1.1](http://www.jboss.org/products/devstudio/download/) or newer
    * [Eclipse 4.3 (Kepler)](https://www.eclipse.org/downloads/) or newer

    You must also make sure the IDE embeds Maven 3.1.1 or later. See [Install and Configure Maven](https://github.com/windup/windup/wiki/Install-and-Configure-Maven) for details.

### Build Windup Using Maven Command Line

To build the project, you need a patch that is not yet available upstream. Follow these steps to build the project.

1. Create a local copy of the **frames** project.
   * Clone the project to your local drive.
 
            git clone https://github.com/jsight/frames
   * Navigate to the newly created `frames/` directory.

            cd frames/
   *Check out the **classloaderresolver** branch.

            git checkout -b classloaderresolver origin/classloaderresolver
   * Build the project.

            mvn clean install
   * This creates and installs the `frames-2.6.0-SNAPSHOT.jar` in your local Maven respository.
2. Create a local copy of the **windup** project.
   * If you plan to contribute, fork the project at <https://github.com/windup/windup>.
   * Clone your fork to your local drive.

            git clone https://github.com/YOUR_USERNAME/windup.git
   * Navigate to the newly created `windup/` directory.

            cd windup/
   * Fetch and add the upstream repository

            git remote add -f upstream git@github.com:windup/windup.git
   * Checkout a local copy of the branch.

            git checkout -b BRANCH_NAME upstream/master
    * Modify the root `pom.xml` file to replace the `2.6.0-jsight-SNAPSHOT` version of the `<groupId>com.tinkerpop</groupId>` with your locally installed `2.6.0-SNAPSHOT` frames JAR .

            <!-- Jess Slighter's Frames classloading patch. https://github.com/jsight/frames/tree/classloaderresolver -->
            <dependency>
                <groupId>com.tinkerpop</groupId>
                <artifactId>frames</artifactId>
                <!-- <version>2.6.0-jsight-SNAPSHOT</version> -->
                <version>2.6.0-SNAPSHOT</version>
            </dependency>
3. Make sure you have configured Maven as described here: [Install and Configure Maven](https://github.com/windup/windup/wiki/Install-and-Configure-Maven). 
4. Build the project.

        mvn clean install

   You can also build the project without the tests.

        mvn clean install -DskipTests

### Build Windup Using Red Hat JBoss Developer Studio or Eclipse

1. Make sure you have configured the Maven installation in your IDE as described here: [Install and Configure Maven](https://github.com/windup/windup/wiki/Install-and-Configure-Maven). 
2. Start JBoss Developer Studio or Eclipse.
3. From the menu, select `File` → `Import`.
4. In the selection list, choose `Maven` → `Existing Maven Projects`, then click Next. 
5. Click `Browse` and navigate to the root directory of the Windup project, then click `OK`.
6. After all projects are listed, click `Next`. Ignore any Maven build or dependency errors and click `Finish`. If you get a dialog titled _Incomplete Maven Goal Execution_, ignore it and click `OK` to continue.
4. In the Project Explorer tab, find the `windup_parent` project in the list, right-mouse, and choose `Run As` --> `Maven install`.

