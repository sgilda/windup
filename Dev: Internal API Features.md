### How to get

```java
WindupRuleProvider ruleProvider = (WindupRuleProvider) context.get(RuleMetadata.RULE_PROVIDER);
```

### In-memory Frames

```java
GraphService<FooModel> fooModelService = context.getService(FooModel.class);
FooModel inMemoryModel = fooModelService.create();
```