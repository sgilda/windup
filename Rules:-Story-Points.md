## What is Story points and who is it for
Story points is a simple way Windup uses to express the effort needed to migrate particular application constructs, and in a sum, the application as a whole.
Mainly for people skilled in migration.

## How to estimate Story Points in your rule
TBD.

This could contain relative examples like:
* Pure copy'n'paste - when we know for sure it will need no migration: 0.
* When we don't know if it will need a migration: Could depend on number of unknown imports, size of the file, ...

* Changing parameter name, or value units: 2.
* Porting one web page (or similar concept) from one web framework to the other:
  Could depend on complexity, e.g. number of components. E.g. 20 per component.
* Porting MyBatis to JPA: 20 per query.
