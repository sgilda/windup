_How Windup starts up, finds the resources, loads the rules and starts executing them_

## Frames creation

In the `GraphContextImpl` constructor.
For additonal method Handlers, see 

```java
        final Module addModules = new Module()
        {
            @Override
            public Graph configure(Graph baseGraph, FramedGraphConfiguration config)
            {
                config.setFrameClassLoaderResolver(fclr);
                // Java Handlers
                config.addMethodHandler(new FrameMapHandler());
                config.addMethodHandler(new MapInPropertiesHandler());
                config.addMethodHandler(new MapInAdjacentPropertiesHandler());
                return baseGraph;
            }
        };
```