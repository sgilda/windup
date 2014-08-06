## Building Windup

To build the project, you need a patch that is not yet available upstream. Use the following steps to build the project.

1. Configure Maven to use the <http://repository.jboss.org/nexus/content/groups/public/> repository.
2. Create a local copy of the **frames** project
   * Clone the project to your local drive: 
            git clone https://github.com/jsight/frames
   * Check out the **classloaderresolver** branch and run `mvn clean install`
   * This creates and installs the `frames-2.6.0-SNAPSHOT.jar` in your local Maven respository.
3. Create a local copy of the **windup** project
   * If you plan to contribute, fork the project at `https://github.com/windup/windup`
   * Clone your fork to your local drive
            git clone https://github.com/YOUR_USERNAME/windup.git
   * Fetch and add the upstream repository
            git remote add -f upstream https://github.com/windup/windup.git
   * Checkout a local branch to work in
            git checkout -b BRANCH_NAME upstream/master
    * Modify the root pom.xml file to replace the com.tinkerpop version `2.6.0-jsight-SNAPSHOT` with your locally installed `2.6.0-SNAPSHOT` frames JAR .
5. Run `mvn clean install`

Enjoy!
