Each rule specifies when it should be executed, by overriding `WindupRuleProvider.getPhase()`.

## Phases

For graphical explanation of what happens when, see [this diagram](https://docs.google.com/drawings/d/1IMnds3Qu8Wwcf7_mr7NJ9a3YgtcGJ7dejl09EhWl7Vc/edit).


<dl>
<dt>DISCOVERY
<dd>Called during resource discovery

<dt>INITIAL_ANALYSIS
<dd>Called to perform basic analysis (extract all method names from class files, extract metadata from xml files, etc)


<dt>COMPOSITION
<dd>
     Perform high-level composition operations on the graph.
     Eg, these may attach metadata from XML files to related Java classes, or perform other high-level graph
     operations, now that all metadata has been extracted

<dt>MIGRATION_RULES
<dd>
     Migration rules will attach data to the graph associated with migration. This could include:
     Hints to migrators for manual migration - Automated migration of schemas or source segments - Blacklists to indicate vendor specific APIs

<dt>REPORT_GENERATION
<dd>     Reporting visitors produce report data in the graph that will later be used by report rendering


<dt>REPORT_RENDERING
<dd>     Actually renders the report into the expected


<dt>FINALIZE
<dd>     Clean up resources and close streams.

</dl>
