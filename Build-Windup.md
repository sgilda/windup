## Build Windup

This information is provided for developers who plan to contribute code to the Windup open source project.

### System Requirements to Build Windup

1. Java 1.7. You can choose from the following:

        OpenJDK
        Oracle Java SE
        Oracle JRockit

2. Maven 3.1.1 or newer

    If you have not yet installed Maven, see [Download and Install Maven](https://github.com/windup/windup/wiki/Download-and-Install-Maven) for details.

    If you have installed Maven, you can check the version by typing the following in a command prompt:

    mvn --version 
3. If you prefer, you can work within your preferred IDE if it provides support for Groovy. The following IDEs are recommended.

    * [Red Hat JBoss Developer Studio 7.1.1](http://www.jboss.org/products/devstudio/download/) or newer
    * [Eclipse 4.3 (Kepler)](https://www.eclipse.org/downloads/) or newer

### Configure Maven to Build Windup

To build Windup, you must configure Maven to use the <http://repository.jboss.org/nexus/content/groups/public/> repository.

1. Open your `${user.home}/.m2/settings.xml` file for editing.
2. Copy the following `jboss-nexus-repository` profile XML prior to the ending `</profiles>` element.

        <profile>
          <id>jboss-nexus-repository</id>
          <repositories>
            <repository>
              <id>jboss-nexus-repository</id>
              <url>http://repository.jboss.org/nexus/content/groups/public/</url>
              <releases>
                <enabled>true</enabled>
              </releases>
              <snapshots>
                <enabled>false</enabled>
              </snapshots>
            </repository>
          </repositories>
          <pluginRepositories>
            <pluginRepository>
              <id>jboss-nexus-plugin-repository</id>
              <url>http://repository.jboss.org/nexus/content/groups/public/</url>
              <releases>
                <enabled>true</enabled>
              </releases>
              <snapshots>
                <enabled>false</enabled>
              </snapshots>
            </pluginRepository>
          </pluginRepositories>
        </profile>

3. Copy the following XML prior to the ending `</activeprofiles>` element to make the profile active.

            <activeProfile>jboss-nexus-repository</activeProfile> 


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
    * Modify the root pom.xml file to replace the com.tinkerpop version `2.6.0-jsight-SNAPSHOT` with your locally installed `2.6.0-SNAPSHOT` frames JAR .
3. Build the project.

        mvn clean install


### Build Windup Using Eclipse


