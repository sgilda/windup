![Windup Logo](images/windup-logo-wiki-header.jpg)
***

JBoss Windup is a tool to simplify application migrations.  Running from the command line, the tool reads EAR, WAR, and JAR files and produces an HTML report detailing the inner workings of the Java application to simplify migration efforts.  It seeks to make migrating from other containers to JBoss a piece of cake.

### Windup 2.0 vs. Windup 0.7.x
Windup 2.0 aims to deliver the same functionality as legacy Windup.
The internal architecture, however, [is very different](Architecture), and so are [the rules](Rules Authoring).

## How does Windup simplify migrations?
Windup is comprised of resource interrogators.  These interrogators look for common resources and highlight technologies and known “trouble spots” in migrating applications.  The goal of Windup is to provide a high level view into relevant technologies in use within the application, and provide a consumable report for organizations to estimate, document, and migrate JEE applications to JBoss.

Below is a list of the interrogators included with JBoss Windup:

<table>
<tr><th>Interrogators</th><th>Description</th>
<tr>
<td>Java Interrogator</td>
<td>Reads compiled Java files; determined if blacklisted classes are imported, and if so it will continue to profile the resource.</td>
</tr>
<tr><td>JSP Interrogator</td><td>Reads JSP files; extracts JSP import and taglibs in use and continues to profile the resource.</td></tr>
<tr><td>XML Interrogator</td><td>Reads XML files; reads the XML into a DOM object and continues to profile the resource.</td></tr>
</table>

## Follow Windup on Twitter!
Follow Windup on [Twitter](https://twitter.com/jbosswindup) [@JBossWindup](https://twitter.com/jbosswindup) for updates and more!

[Next Page: How to Build](Build-Windup)