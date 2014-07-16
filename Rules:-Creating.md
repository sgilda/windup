## Accessing the Graph reference

## Adding a type to a Vertex (specializing the vertex)

```java
 JavaClassModel classModel = GraphUtil.addTypeToModel(event.getGraphContext(), frame, JavaClassModel.class);
```

## Adding a frame

## Iteration

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

## Nested Iteration

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