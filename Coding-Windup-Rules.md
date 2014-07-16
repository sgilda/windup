Windup is a platform for creating migration rules.  We provide profilers, and you code rules to the profilers.  Some of the most important rules are **classifications** and **inline hints**.  

### Classifications
Classifications are important because they tell developers very quickly ***what*** the resource is, so that they can quickly identify resources of importance.  For example, say you are responsible for migrating an application from Weblogic to JBoss... wouldn't it be nice to know that project is a Spring project?  Wouldn't it be nice to have the Weblogic descriptors identified and called out to us?  This is what classifiers do.  They identify resources, and add a classification to the resource.

Classifications are displayed both at the 10,000 foot view, main report and also at the resource report level.

* [Java Classifications](...) TODO
* [XML Classifications](...) TODO

### Inline Hints
Windup takes the approach of profiling resources and providing syntactically highlighted resource reports.  The resource report will show the resource with potentially highlighted lines that may contain inline hints to help the developers migrate a piece of code.  

Adding inline hints for Java and XML are as simple as adding Regular Expressions or XPath Expressions to the Windup rules.
