_For the purposes of porting WindRide functionality to Windup. Will be removed when done._

## Some classes mapping
```java
@ConfigPartDescriptor = @ReportItem
MigrationReportJaxbBean = 
DeploymentInfo = ApplicationModel
```

## Server-side code to be moved

* ServerInfo
  * ComparisonResult
  * FileHashComparer
* IMigrationAction