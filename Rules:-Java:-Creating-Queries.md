## Query for all frames of some Model
```java
Query.find(FileModel.class).as("result")
```

## Query by property
```java
Query.find(FileModel.class)
    .withProperty(FileModel.PROPERTY_IS_DIRECTORY, true)
    .as("res")
```

## Gremlin query

```java
Query.find(FileModel.class).piped(new QueryGremlinCriterion() {
    public void query( GraphRewrite event, GremlinPipeline<Vertex, Vertex> pipeline ) {
       throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
})
.withProperty(FileModel.PROPERTY_IS_DIRECTORY, true)
.as("res")
```

## Custom query
The `Query` API is just a convenience implementation.
For a custom query, implement your own `GraphCondition`:
```java
        .when(
            new GraphCondition() {
                public boolean evaluate( GraphRewrite event, EvaluationContext context ) {
                    event.getGraphContext(); //...
                }
            }
        )
```