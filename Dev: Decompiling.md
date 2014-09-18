Draft

## Procyon

### Config
* `windup.ProcyonConfiguration` contains `DecompilerSettings`. TODO: Merge these two.
* `procyon.DecompilerSettings` contains `outputDir` (the others are not much important). TODO: Make that a parameter to method call?
* `windup.ProcyonConfiguration` should extend abstract windup.DecompilerConfig<T>, which would contain the configurables common to all decompilers. In case of Procyon, `outputDir` would be delegated to `DecompilerSettings`.
* `DecompilerConfig` should also contain `Filter<String>` (or maybe `Filter<TypeReference>`) to decide whether to decompile the class or not.


### `ITypeLoader`s

* `JarTypeLoader` - iterates a `JarFile`, expects a .jar `File` as input.
* `ClasspathTypeLoader` - loads classes from a list of classes.
    * `ClasspathTypeLoader("some/path:other/path")`
    * `ClasspathTypeLoader()` -> sysprop("java.class.path") : sysprop("sun.boot.class.path")
* `InputTypeLoader`
* `CompoundTypeLoader` - 2 typeloaders, queried in succession
TODO: Create a simple `FileSystemTypeLoader`.