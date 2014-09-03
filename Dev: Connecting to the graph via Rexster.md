1. Download the [Rexster](https://github.com/tinkerpop/rexster/wiki/Downloads) Server (2.5.0)
2. Download [Titan 0.5.0 (with Hadoop 2)](https://github.com/thinkaurelius/titan/wiki/Downloads)
3. Unzip both distributions
4. Create REXSTER_HOME/ext/titan
5. cp TITAN_HOME/target/titan-x.y.z-standalone/lib/*.* REXSTER_HOME/ext/titan
6. Edit REXSTER_HOME/config/rexster.xml and add the following text to the end of the "graphs" element (modifying the paths to match your environment):

---------------------------------------------------------------------------
	<graph>
		<graph-name>titan</graph-name>
		<graph-type>com.thinkaurelius.titan.tinkerpop.rexster.TitanGraphConfiguration</graph-type>
		<graph-location>/tmp/WindupReport/graph/titangraph</graph-location>
		<graph-read-only>false</graph-read-only>
		<properties>
			<storage.backend>berkeleyje</storage.backend>
			<storage.directory>/tmp/WindupReport/graph/titangraph</storage.directory>
			<index.search.backend>lucene</index.search.backend>
			<index.search.directory>/tmp/WindupReport/graph/graphsearch/</index.search.directory>
		</properties>
		<extensions>
			<allows>
				<allow>tp:gremlin</allow>
			</allows>
		</extensions>
	</graph>
---------------------------------------------------------------------------

7. Start rexster with REXSTER_HOME/bin/rexster.sh --start
8. Browse to http://localhost:8182/

