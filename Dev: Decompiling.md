Draft

## Procyon

* `ProcyonConfiguration` contains `DecompilerSettings`. TODO: Merge these two.
* `DecompilerSettings` contains `outputDir`. TODO: Make that a parameter to method call?

### `ITypeLoader`s

* `JarTypeLoader` - iterates a `JarFile`, expects a .jar `File` as input.
* `ClasspathTypeLoader` - loads classes from a list of classes.
    * `ClasspathTypeLoader("some/path:other/path")`
    * `ClasspathTypeLoader()` -> sysprop("java.class.path") : sysprop("sun.boot.class.path")
* `InputTypeLoader`
* `CompoundTypeLoader` - 2 typeloaders, queried in succession
TODO: Create a simple `FileSystemTypeLoader`.