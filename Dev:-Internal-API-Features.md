### How to know what `RuleProvider` provided a `Rule`

`Rule` implements `Context` which can keep metadata.
```java
WindupRuleProvider ruleProvider = (WindupRuleProvider) context.get(RuleMetadata.RULE_PROVIDER);
```

### In-memory Frames

```java
GraphService<FooModel> fooModelService = context.getService(FooModel.class);
FooModel inMemoryModel = fooModelService.create();
```