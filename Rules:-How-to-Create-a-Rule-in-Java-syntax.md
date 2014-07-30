Windup rules are, at the base, built on top of a Java API (which is based on the OCP Rewrite API).
They resemble natural english sentences.

## Concepts & philosophy
TBD; In short:

Windup is Rule-based. Users are able to write own rules according to their needs, constructs in their applications, custom APIs used, etc.

The individual rules should be decoupled, only expressing dependencies on each other, and "communicate" through the graph. Each rule will query the graph database, use the results for locating the candidates of it's interests, process them, and then write the results to the graph database.

### Graph database and Models (Frames)

As a graph db implementation, we use [TinkerPop](http://tinkerpop.com/) backed by [Titan](http://thinkaurelius.github.io/titan/) backed by BerkeleyDB.
For explanation of graph database concepts, see [Titan](https://github.com/thinkaurelius/titan/wiki/Beginner%27s-Guide).

Like there's ORM (like JPA) for JDBC, TinkerPop's BluePrints has [Frames](https://github.com/tinkerpop/frames/wiki). This allows you to access the graph data in form of your custom Java models (implemented as Java Proxies to the querying).

We use this concept heavily. Each ruleset will likely have it's own models. (But you can opt to use Blueprints API if you like).

## Rule code structure

```java
public class ExampleRuleProvider extends WindupRuleProvider
{
    @Override public RulePhase getPhase(){
        return RulePhase.DISCOVERY;
    }

    // @formatter:off
    @Override
    public Configuration getConfiguration(GraphContext context)
    {
        return ConfigurationBuilder.begin()
        .addRule()
        .when(
            Query.find(...)....
        )
       .perform(
            ...
       );
    }
    // @formatter:on
    // (@formatter:off/on prevents Eclipse to format the rules as the results are not nice.)
}
```
The rule must extend `WindupRuleProvider`.

Each rule must specify by `getPhase()` in which Windup lifecycle phase it should be executed. More about phases [here](Rules:-Phases).

Rules consist of the _when_ part, the _perform_, the _otherwise_ part, and some others, all of which are optional.

### .when()
In the `.when()` part, the rule typically queries the graph, using the `Query` API.
Results of the query are put on variables stack (`Variables`), many times indirectly through the querying API.

Other way to fill a when part is subclassing the `GraphCondition`. Actually, `Query` also implements it, and is only a convenient way to create a condition.

Last noticable but important feature is the ability to use [Gremlin](https://github.com/tinkerpop/gremlin/wiki) queries. See [Gremlin docs](http://gremlindocs.com/) for reference manual.

### .perform()
In the `.perform()` part, the rule typically scans the items of interest (Java files, XML files, querying services, ...), processes them, and writes the findings into the graph.

For that, various operations are available. These are subclasses of `GraphOperation`.
You can also implement your own. See [Rules: Operations](Rules:-Operations) for more info.
Again, there are several convenient implementations for constructs like iteration (`Iteration`).

#### Iteration
```java
.perform(
    Iteration.over(XmlMetaFacetModel.class, "xmlModels").as("xml")
    .when(...)
    .perform(
        new AbstractIterationOperation<XmlMetaFacetModel>(XmlMetaFacetModel.class, "xml"){
            public void perform(GraphRewrite event, EvaluationContext context, XmlMetaFacetModel xmlFacetModel)
            {
            }
        })
    .otherwise(
        new AbstractIterationOperation<XmlMetaFacetModel>(XmlMetaFacetModel.class, "xml"){
            public void perform(GraphRewrite event, EvaluationContext context, XmlMetaFacetModel payload)
                { ... }
        })
    .endIteration()
```


### `.otherwise()` et al.
Windup rules inherit the rule constructs from OCP Rewrite.
For example, `.otherwise()` Gives you a chance to perform something in case the conditions in `.when()` return false (e.g. they do not match anything). For more information, see [OCP Rewrite web](http://ocpsoft.org/rewrite/).