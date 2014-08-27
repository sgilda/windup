1. Create an Arquillian test with a `@Deployment` method
    1. Add the test classes to the archive
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
```

A test which builds it's own runtime environment:
```java
@RunWith(Arquillian.class)
public class FreeMarkerIterationOperationTest extends AbstractTestCase
{

    @Deployment
    @Dependencies({
                @AddonDependency(name = "org.jboss.windup.config:windup-config"),
                @AddonDependency(name = "org.jboss.windup.graph:windup-graph"),
                @AddonDependency(name = "org.jboss.windup.reporting:windup-reporting"),
                @AddonDependency(name = "org.jboss.forge.furnace.container:cdi")
    })
    public static ForgeArchive getDeployment()
    {
        ForgeArchive archive = ShrinkWrap.create(ForgeArchive.class)
                    .addBeansXML()
                    .addClass(AbstractTestCase.class)
                    .addClass(FreeMarkerOperationRuleProvider.class)
                    .addAsResource(new File("src/test/resources/reports"))
                    .addAsAddonDependencies(
                                AddonDependencyEntry.create("org.jboss.windup.config:windup-config"),
                                AddonDependencyEntry.create("org.jboss.windup.graph:windup-graph"),
                                AddonDependencyEntry.create("org.jboss.windup.reporting:windup-reporting"),
                                AddonDependencyEntry.create("org.jboss.forge.furnace.container:cdi")
                    );
        return archive;
    }

    @Inject
    private GraphContext context;
    @Inject
    private FreeMarkerOperationRuleProvider provider;

    private Path tempFolder;

    @Test
    public void testApplicationReportFreemarker() throws Exception
    {
        GraphRewrite event = new GraphRewrite(context);
        DefaultEvaluationContext evaluationContext = createEvalContext(event);
        fillData(context);

        Configuration configuration = provider.getConfiguration(context);

        RuleSubset.evaluate(configuration).perform(event, evaluationContext);

        Path outputFile = tempFolder.resolve(provider.getOutputFilename());
        String results = FileUtils.readFileToString(outputFile.toFile());
        Assert.assertEquals("Test freemarker report", results);
    }

    private void fillData(final GraphContext context) throws Exception
    {
        WindupConfigurationModel cfgModel = context.getFramed().addVertex(null, WindupConfigurationModel.class);
        ...

        ApplicationReportModel appReportModel = context.getFramed().addVertex(null, ApplicationReportModel.class);
        ...
    }

    private DefaultEvaluationContext createEvalContext(GraphRewrite event)
    {
        final Variables varStack = Variables.instance(event);
        final DefaultEvaluationContext evaluationContext = new DefaultEvaluationContext();
        final DefaultParameterValueStore values = new DefaultParameterValueStore();
        evaluationContext.put(ParameterValueStore.class, values);
        event.getRewriteContext().put(Variables.class, varStack);
        return evaluationContext;
    }
}
```


TBD:

```java
            Query.find(FileModel.class).piped( new QueryGremlinCriterion() {
                @Override
                public void query( GraphRewrite event, GremlinPipeline<Vertex, Vertex> pipeline ) {
                    pipeline...
                }
            })
```

Testing a subset of rules:

See https://github.com/lincolnthree/windup/tree/WINDUP-133/rules/app/java/src/test/java/org/jboss/windup/rules/java
(TBD)

### What is the Module _DEFAULT_? What does it's classloader see?
That is the module of the current Forge Test Case.