Windup rules are, at the base, built on top of a Java API (which is based on the OCP Rewrite API).
They resemble natural english sentences.

## Concepts & philosophy



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

## .when()
In the `.when()` part, the rule typically queries the graph, using the `Query` API.
Results of the query are put on variables stack (`Variables`), many times indirectly through the querying API.

Other way to fill a when part is subclassing the `GraphCondition`. Actually, `Query` also implements it, and is only a convenient way to create a condition.

## .perform()
In the `.perform()` part, the rule typically scans the items of interest (Java files, XML files, querying services, ...), processes them, and writes the findings into the graph.

For that, various operations are available. These are subclasses of `GraphOperation`.
You can also implement your own. See [Rules: Operations](Rules:-Operations) for more info.
Again, there are several convenient implementations for constructs like iteration (`Iteration`).

### Iteration
```java
.perform(
    Iteration.over(XmlMetaFacetModel.class, "xmlModels").as("xml")
    .when(...)
    .perform(
        new AbstractIterationOperation<XmlMetaFacetModel>(XmlMetaFacetModel.class, "xml"){
            public void perform(GraphRewrite event, EvaluationContext context,
                            XmlMetaFacetModel xmlFacetModel)
                {
                    typeSearchResults.add(xmlFacetModel);
                    if (xmlRootNames.contains(xmlFacetModel.getRootTagName()))
                    {
                        Assert.fail("Tag found multiple times");
                    }
                    xmlRootNames.add(xmlFacetModel.getRootTagName());
                }
            })
            .otherwise(new AbstractIterationOperation<XmlMetaFacetModel>(XmlMetaFacetModel.class,
                        "xml")
            {
                @Override
                public void perform(GraphRewrite event, EvaluationContext context,
                            XmlMetaFacetModel payload)
                {
                    typeSearchResults.add(payload);
                    if (excludedXmlRootNames.contains(payload.getRootTagName()))
                    {
                        Assert.fail("Tag found multiple times");
                    }
                    excludedXmlRootNames.add(payload.getRootTagName());
                }
            })
            .endIteration()
```


## `.otherwise()` et al.
Windup rules inherit the rule constructs from OCP Rewrite.
For example, `.otherwise()` Gives you a chance to perform something in case the conditions in `.when()` return false (e.g. they do not match anything).