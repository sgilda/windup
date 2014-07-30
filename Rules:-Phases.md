Each rule specifies when it should be executed, by overriding `WindupRuleProvider.getPhase()`.

## Phases

<dl>
<dt>DISCOVERY
<dd>Called during resource discovery

<dt>INITIAL_ANALYSIS
<dd>Called to perform basic analysis (extract all method names from class files, extract metadata from xml files, etc)

</dl>

COMPOSITION

     * Perform high-level composition operations on the graph.
     * 
     * Eg, these may attach metadata from XML files to related Java classes, or perform other high-level graph
     * operations, now that all metadata has been extracted

MIGRATION_RULES(400),

     * Migration rules will attach data to the graph associated with migration. This could include:
     * 
     * - Hints to migrators for manual migration - Automated migration of schemas or source segments - Blacklists to
     * indicate vendor specific APIs

REPORT_GENERATION
     * Reporting visitors produce report data in the graph that will later be used by report rendering


REPORT_RENDERING
     * Actually renders the report into the expected


FINALIZE
     * Clean up resources and close streams.

