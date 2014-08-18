# Lifecycle

Windup executes rules in "single thread" - i.e. one by one, ordered by 1) Phases 2) Dependencies.

## Dependencies
Each rule may declare that it depends on other rules. That means, given rule will execute after all dependent rules will be executed.

```java
    @Override
    public List<Class<? extends WindupRuleProvider>> getClassDependencies() {
        return generateDependencies(AnalyzeJavaFilesRuleProvider.class);
    }
```

## Phases

Many rules will depend on some obvious dependencies, like, extracting of archives, scanning java files, scanning XML files, etc.

To relieve rule authors from declaring these, they can declare in which phase of the lifecycle the rule should be executed, by overriding `WindupRuleProvider.getPhase()`.

```java
    @Override
    public RulePhase getPhase() {
        return RulePhase.INITIAL_ANALYSIS;
    }
```

For graphical explanation of what happens when, see [this diagram](https://docs.google.com/drawings/d/1IMnds3Qu8Wwcf7_mr7NJ9a3YgtcGJ7dejl09EhWl7Vc/edit). (This may be a bit desynchronized with actual behavior, but roughly captures the idea.)


<dl>
<dt>DISCOVERY
<dd>Called during resource discovery. During this phase, static files are scanned by their basic properties - name, extension, location, evt. Java class name (from a file name). Archives are unzipped in this phase. Typically, rules that just put data into graph are executed at this phase. 

<dt>INITIAL_ANALYSIS
<dd>Called to perform basic analysis of the files content - e.g. extract all method names from class files, extract metadata (XML namespace, root element) from XML files, etc.


<dt>COMPOSITION
<dd>
     Perform high-level composition operations on the graph.
     For example, these may link items found in XML files to related Java classes, or references to server resources in Java classes, etc.

<dt>MIGRATION_RULES
<dd>
     Migration rules will attach data to the graph associated with migration. This could include:
     - Hints to migrators for manual migration
     - Automated migration of schemas or source segments
     - Blacklists to indicate vendor specific APIs.

<dt>REPORT_GENERATION
<dd>     Reporting visitors produce report data in the graph that will later be used by report rendering.


<dt>REPORT_RENDERING
<dd>     Actually renders the report.


<dt>FINALIZE
<dd>     Clean up resources and close streams.

</dl>

Additionally, there are `PRE_` and `POST_` phases around each phases above.