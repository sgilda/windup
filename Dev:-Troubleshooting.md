### Logging
Logging is currently broken and will not be fixed any soon.
If you don't see any log messages, resort to `System.out.println()`.
If you see too much logging (especially from Forge), get used to it.
See also WINDUP-73.

### Weird exceptions
Exceptions in Surefire reports are broken due to Forge's way of wrapping exceptions combined with Surefire's way of handling them. You need to debug or rewrap using `TestUtil.rewrap(ex)`.
See also WINDUP-197.

### Classloading problems
It might get tricky to do the dependencies right in Forge-based project.
Check [this wiki](Build:-Dependencies) for some hints.