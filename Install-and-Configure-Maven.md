### Download and Install Maven

If you plan to use Eclipse Luna (4.4) to build Windup, you can skip this step. This version of Eclipse embeds Maven 3.2.1 so you do not need to install it separately. Skip to the section entitled [Configure Maven to Build Windup](#configure-maven-to-build-windup).

If you plan to run Maven using the command line or plan to use Red Hat JBoss Developer Studio 7.1.1 or an older version of Eclipse, you must install Maven 3.1.1. or later. 

1. Go to [Apache Maven Project - Download Maven](http://maven.apache.org/download.html) and download the latest distribution for your operating system. 
2. See the Maven documentation for information on how to download and install Apache Maven for your operating system.

### Configure the Maven Installation in Your IDE

JBoss Developer Studio 7.1.1 is built upon Eclipse Kepler (4.3), which embeds Maven 3.0.4. If you plan to use JBoss Developer Studio 7.1.1 or an Eclipse version earier than Eclipse Luna (4.4), you must replace the embedded 3.0.4 version of Maven with this newer version.

1. From the menu, choose `Window` --> `Preferences`.
2. Expand `Maven` and click on `Installations`.
3. Uncheck `Embedded (3.0.4/1.4.0.20130531-2315)`
4. Click `Add` and navigate to your Maven install directory. Select it and click `OK`.
5. Make sure the new external Maven installation is checked and click `OK` to return to JBoss Developer Studio. 

_Note: If you use another IDE, refer to the product documentation to update the Maven installation._

### Configure Maven to Build Windup

Windup uses artifacts located in the [JBoss Nexus](http://repository.jboss.org/nexus/content/groups/public/) repository. You must configure Maven to use this repository before you build Windup.

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
                <enabled>true</enabled>
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
                <enabled>true</enabled>
              </snapshots>
            </pluginRepository>
          </pluginRepositories>
        </profile>

3. Copy the following XML prior to the ending `</activeprofiles>` element to make the profile active.

            <activeProfile>jboss-nexus-repository</activeProfile> 



