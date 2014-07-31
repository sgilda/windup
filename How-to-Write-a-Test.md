1. Create an Arquillian test with a `@Deployment` method
    1.1. Add the test classes to the archive
2. `@Inject GraphContext`
3. Add all windup addons you'll need

For rules testing, we will have a special harness in the future.

```java
@RunWith(Arquillian.class)
public class MapInPropertiesTest
{
    @Deployment
    @Dependencies({
        @AddonDependency(name = "org.jboss.windup.graph:windup-graph"),
        @AddonDependency(name = "org.jboss.windup.utils:utils"),
        @AddonDependency(name = "org.jboss.forge.furnace.container:cdi")
    })
    public static ForgeArchive getDeployment()
    {
        ForgeArchive archive = ShrinkWrap.create(ForgeArchive.class)
            .addBeansXML()
            .addPackage("org.jboss.windup.graph.typedgraph.mapinprops")
            .addAsAddonDependencies(
                AddonDependencyEntry.create("org.jboss.windup.graph:windup-graph"),
                AddonDependencyEntry.create("org.jboss.windup.utils:utils"),
                AddonDependencyEntry.create("org.jboss.forge.furnace.container:cdi")
            );
        return archive;
    }

    @Inject private GraphContext context;

    @Test public void testMapHandling() throws Exception {
        ....
    }
}
