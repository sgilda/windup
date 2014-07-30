Windup rules are, at the base, built on top of a Java API (which is based on the OCP Rewrite API).
They resemble natural english sentences.

## Rule structure

Rules consist of the _when_ part, the _perform_, the _otherwise_ part, and some others, all of which are optional.

```java
public class XmlExampleRuleProvider3 extends WindupRuleProvider
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
            Query.find(XmlMetaFacetModel.class)....
        )
       .perform(
            ...
       );
    }
    // @formatter:on
}
```
