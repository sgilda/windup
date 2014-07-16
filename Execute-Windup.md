## Running Windup
First, let's talk about what Windup can run against...

Windup can run against both Source projects, and also Compiled projects.

### Source Projects
There are no prerequisites to run against Source Projects.  Just make sure to include the command line argument `-source true` and point the `-input` parameter at the directory containing your source!  Make sure to include an `output` directory for the Windup Report! 


## Command Line Arguments

* From the command line:  `java -jar windup-cli.jar`

### Required Arguments
<table>
<tr><th>Command Line Argument</th><th>Description</th></tr>
<tr><td>-input &lt;file&gt; or &lt;dir&gt;</td><td>file / directory of files to generate windup reports (required)</td></tr>
<tr><td>-javaPkgs &lt;string&gt;</td><td>client Java packages to target for inspection, separate packages with ":" (required)</td></tr>
<tr><td>-output &lt;dir&gt;</td><td>directory where to generate windup report</td></tr>
<tr><td>-source &lt;boolean&gt;</td><td>whether to run in recursive folder mode, against Java, JSP, and XML files.  By default, Windup tries to run on compiled Class files.  This allows Windup to run against Java files.  The output parameter is required for this setting.</td></tr>
</table>

### Optional Arguments
<table>
<tr><th>Command Line Argument</th><th>Description</th></tr>
<tr><td>-captureLog &lt;boolean&gt;</td><td>persist to file</td></tr>
<tr><td>-logLevel &lt;enum&gt;</td><td>INFO, DEBUG, WARN, ERROR, FATAL</td></tr>
<tr><td>-fetchRemote &lt;boolean&gt;</td><td>fetch remote POMs for unknown JAR files; 
if you are running Windup without an internet connection, this can be set to false.</td></tr>
</table>

## Examples:

### Running Windup On Compiled Projects
**Batch Run (most common):** The following will run Windup against all Zip type archives in the folder following "-input", and will analyze classes in the org.example and org.another packages. **Note:** the "-output" parameter is ignored in batch mode.

```
java -jar windup-cli.jar -input /Users/bdavis/Projects/migrations/example -javaPkgs org.example:org.another
```

**Single Run:** The following will run Windup against the specific Zip following the "-input", and the report will be output in the "-output" directory.

```
java -jar windup-cli.jar -input /Users/bdavis/Projects/migrations/example.ear 
        -output /Users/bdavis/Projects/migrations/example/example-doc -javaPkgs org.example:org.another
```

## Testing New Rules:
======================
Adding new rules to Windup is easy!  Just follow the directions at [Windup Extensions](4.0-Extend-Windup-Rules)!

[Previous Page: Build Windup](Build-Windup)

[Next Page: Extend Windup Rules](Extend-Windup-Rules)