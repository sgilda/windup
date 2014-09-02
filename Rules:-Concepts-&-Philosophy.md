## Concepts & philosophy

Windup is Rule-based. Users are able to write own rules according to their needs, constructs in their applications, custom APIs used, etc.

The individual rules should be decoupled, only expressing dependencies on each other, and "communicate" through the graph. Each rule will query the graph database, use the results for locating the candidates of it's interests, process them, and then write the results to the graph database.

### Graph database and Models (Frames)

As a graph db implementation, we use [TinkerPop](http://tinkerpop.com/) backed by [Titan](http://thinkaurelius.github.io/titan/) backed by BerkeleyDB.
For explanation of graph database concepts, see [Titan](https://github.com/thinkaurelius/titan/wiki/Beginner%27s-Guide).

Like there's ORM (like JPA) for JDBC, TinkerPop's BluePrints has [Frames](https://github.com/tinkerpop/frames/wiki). This allows you to access the graph data in form of your custom Java models (implemented as Java Proxies to the querying).

We use this concept heavily. Each ruleset will likely have it's own models. (But you can opt to use Blueprints API if you like).

See also the list of [existing models](Existing-Models).
