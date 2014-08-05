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