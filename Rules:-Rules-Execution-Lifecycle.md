## Rules Execution Lifecycle

### Rules Lifecycle

Windup executes each rule in a single threaded mode. The following sections describe the phases of rule execution and how rules may specify that one or more other rules must be executed before or after they are run. 

For graphical overview of rule processing, see [this diagram](https://docs.google.com/drawings/d/1IMnds3Qu8Wwcf7_mr7NJ9a3YgtcGJ7dejl09EhWl7Vc/edit).

### Rule Phases

By default, a rule runs during the `Migration Rules` phase. However, a rule may require certain processing or actions to occur before the it executes, such as the extraction of archives and scanning of java or XML files. 

Rule phases provide an way for rule authors to specify and control in which phase of the rule lifecycle the rule should execute. This is done by overriding the `WindupRuleProvider.getPhase()` method. The following example specifies this rule should execute during the `INITIAL_ANALYSIS` rule phase.

```java
    @Override
    public RulePhase getPhase() {
        return RulePhase.INITIAL_ANALYSIS;
    }
```

The following is the list of rule phases as defined in the `RulePhase` enum class. Note that there are also `PRE_` processing and `POST_` processing phases for each of the following rule phases.

<dl>
<dt>DISCOVERY</dt>
<dd>This phase is called during resource discovery. Static files are scanned by their basic properties, for example, the name, extension, location, and fully qualified Java class name. Archives are unzipped in this phase. Typically, any rule that only puts data into the graph is executed during this phase.</dd>

<dt>INITIAL_ANALYSIS</dt>
<dd>This phase is called to perform a basic analysis of the files content. It extracts all method names from class files, extracts metadata, such as the XML namespace and root element, from XML files.</dd>


<dt>COMPOSITION</dt>
<dd>This phase is called to perform high-level composition operations on the graph. For example, it may link items found in XML files to the related Java classes or references to server resources in Java classes.</dd>

<dt>MIGRATION_RULES</dt>
<dd>During this phase, migration rules attach data to the graph associated with migration. This could include:
     - Hints to migrators for manual migration
     - Automated migration of schemas or source segments
     - Blacklists to indicate vendor specific APIs.</dd>

<dt>REPORT_GENERATION</dt>
<dd>During this phase, reporting visitors produce report data in the graph that will later be used by thereport rendering phase.</dd>


<dt>REPORT_RENDERING</dt>
<dd>This is the phase that renders the report.</dd>


<dt>FINALIZE</dt>
<dd>This phase is called to clean up resources and close streams.</dd>

</dl>


### Execute Before and Run After

A rule may specify that one or more rules must be executed before it is run. In this case, all named rules will be fired in the order specified before executing the the current rule.

```java
    List<Class<? extends WindupRuleProvider>> executeBeforeList = new ArrayList<>();

    @Override
    public List<Class<? extends WindupRuleProvider>> getExecuteBefore()
    {
       return executeBeforeList.add(RuleToFireBefore.class);
    }

```
A rule may also specify that one or more rules must be executed after it is run. In this case,  all named rules will be fired in the order specified after executing the the current rule.

```java
    List<Class<? extends WindupRuleProvider>> executeAfterList = new ArrayList<>();

    @Override
    public List<Class<? extends WindupRuleProvider>> getExecuteBefore()
    {
        return executeAfterList.add(RuleToFireBefore.class);
    }

```
