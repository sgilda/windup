### Accessing the Graph reference

Depending on where you "are", either 

```java
@Inject GraphContext graph;
```
or call 

```java
GraphRewrite.getGraphContext()
GraphService.getConfigurationModel( event.getGraphContext() );
```
To get Frames:

```java
graph.getFramed();
```

### Other frequent injectables

```java
    @Inject private GraphTypeManager graphTypeManager;    
    @Inject private GraphContext graph;
    @Inject private Furnace furnace;
```

### Adding a type to a Vertex (specializing the vertex)

```java
 JavaClassModel classModel = GraphUtil.addTypeToModel(event.getGraphContext(), frame, JavaClassModel.class);
```

### Adding a Frame
```java
graph.getFramed().addVertex(null, FooModel.class);
```

## Querying the graph

There are several ways - including Query API, Gremlin support, or GraphService methods.
```java
Query.find(ApplicationReportModel.class).as(VAR_APPLICATION_REPORTS)
```
```java
FooModel foo = new GraphService<>(graphContext, FooModel.class).getUnique();
```
```java
FooModel foo = new GraphService<>(graphContext, FooModel.class).getUniqueByProperty("size", 1);
```


### Iteration

```java
ConfigurationBuilder.begin().addRule()
    .when(
        GraphSearchConditionBuilderGremlin.create("javaFiles", new ArrayList())
        .V().framedType( JavaFileModel.class ).has("analyze")
    )
    .perform(
        // For all java files...
        Iteration.over("javaFiles").var("javaFile").perform(
```

### Nested Iteration

```java
// For all java files...
Iteration.over("javaFiles").var("javaFile").perform(
    // A nested rule.
    GraphSubset.evaluate(
        ConfigurationBuilder.begin().addRule()
        .when(...)
        .perform(
            Iteration.over("regexes").var(RegexModel.class, "regex").perform(
                new AbstractIterationOperator<RegexModel>( RegexModel.class, "regex" ) {
                    public void perform( GraphRewrite event, EvaluationContext context, RegexModel regex ) {

                        VarStack sf = VarStack.instance(event);
                        JavaFileModel javaFile = sf.getCurrentPayload( JavaFileModel.class, "javaFile");

                        getRegexMatches( javaFile.getFilePath(), regex.getRegex() );
                    }
                }
            )
            .endIteration()
        )// perform()
    )
)
```