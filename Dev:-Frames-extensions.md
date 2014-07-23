We have several Frames extensions to satisfy our needs.

## Multiple types in a vertex
In the _type_ property, Frames only store 1 type. We have multiple types separated by '|'. All subparts are indexed.

## Map<String, String> handler. TBD.
To store a map in a single vertex'es properties.


## Map<String, WindupVertexFrame>
To store a map in as adjacent vertices.
```java
@TypeValue("MapModelMain")
public interface MapMainModel extends WindupVertexFrame
{
    @AdjacentMap(label = "map") void setMap(Map<String, MapValueModel> map);
    @AdjacentMap(label = "map") Map<String, MapValueModel> getMap();
}
```

## List<String> handler. TBD.
To store a list of strings in a single vertex'es properties.
The keys will be 0, 1, 2, 3, ...

## List<WindupVertexFrame> 
`WindupVertexListModel` offers a generic model for lists of other models, which are stored as adjacent vertices.