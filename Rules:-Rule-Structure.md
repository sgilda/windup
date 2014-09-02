Windup rules are, at the base, built on top of a Java API (which is based on the OCP Rewrite API).
They resemble natural english sentences.

TODO:

Howto for compound rules, nested rules, rules over multiple sources, negative queries (not matched by anything).

[How to estimate the migration effort with Story Points](Rules:-Story-Points)
  [WINDUP-255](https://issues.jboss.org/browse/WINDUP-255)


## Rule code structure

Rules are based on OCP Rewrite. That is a framework for events processing, like HTTP requests redirects.
Although, Windup has only one event, which spans across the whole lifecycle.

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
            // Some implementation of GraphCondition.
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

## Metadata
Rules can specify metadata.
Currently, the only appearing in some rules, and not actually used, is `RuleMetadata.CATEGORY`.
```java
.withMetadata(RuleMetadata.CATEGORY, "Basic")
```

`.withMetadata()` is basically putting key/value to a `Map<Object, Object>`.

## Available utilities
For a list of what key services and constructs can be used in the rule, see [Rules: Available Utilities](Rules:-Available-Utilities).