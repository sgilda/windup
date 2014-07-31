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
To add a frame, a more preferred way is to use the GraphService.
```java
new GraphService<FooModel>(graph,FooModel.class).create();
```
However, if for some reason the GraphService is not convenient, you can create a vertex directly using graph context.
```java
graph.getFramed().addVertex(null, FooModel.class);
```

## Querying the graph

There are several ways - including Query API, Gremlin support, or GraphService methods.

### Querying inside the when() method of the rule
Building a rule consists of the method when(),
which is used to create a **condition**. Vertices that fulfill the condition, are passed to the perform() method.

For the queries in the when() method, class Query is used. 
There are several methods which you can use to specify the condition. For example:
* **find()** specifies the Model type of the vertex
* **as()** method specifies the name of the final list, that is passed to the perform() method
* **from(String name)** starts the query not on the all vertices, but only on the vertices already stored in the the given **name** (used to begin query on the result of the other one)
* **withProperty()** specify the property value of the given vertex

The simple query can look like this:
```java
Query.find(ApplicationReportModel.class).as(VAR_APPLICATION_REPORTS)
```

### Querying to get the model values
To query the graph to get the models of requested type, a GraphService<> class can be used.
The examples of using the GraphService are listed below. 
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