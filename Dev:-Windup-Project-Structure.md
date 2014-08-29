## Windup Project Structure

The Windup 2.0 project consists of the following subprojects.

### config/

This project is for the engine that runs the rules and abstracts the graph operations.

### decompiler/

Thie subproject contains an API that wraps calls to the decompiler. There is currently only one decompiler: _Procyon_

### exec/

This subproject contains the bootstrap code to run the Windup application.

### ext/

This subproject is for code extensions. It currently only contains the Groovy rules syntax. Eventually it will contain any code that is not related to the rules or the core code base.

### graph/

This subproject contains the datastore and Frames extensions.

### logging/

This is the logging subproject. Depending on WINDUP-49, it might be removed in the future.

### reporting/

This subproject is for code that does reporting.

### rules/

This subproject is for all the rules.

### test-files/

This subproject contains the demo applications that are used as test input.

### tests/

This subproject contains the integration test suite.

### tinkerpop/

This subproject contains a code fix for Titan NPE issues. 
<!-- Jesse’s fix for Titan issues (NPE) -->

### ui/

This subproject is for experimental Forge UI code.

### utils/

This subproject is used to contain all utility code.

