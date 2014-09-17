Draft

## Procyon

### `ITypeLoader`s

* `ClasspathTypeLoader` - loads classes from a list of classes.
    `ClasspathTypeLoader()` -> sysprop("java.class.path") : sysprop("sun.boot.class.path")
    

* `InputTypeLoader`
* `CompoundTypeLoader` - 2 typeloaders, queried in succession
* `JarTypeLoader` - iterates a `JarFile`, expects a .jar `File` as input.
