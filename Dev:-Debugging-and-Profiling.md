## Debugging a test using NetBeans
* Click the `Debug Test File` button, or `Menu > Debug > Debug Test File`.

## Profiling a test using NetBeans
* Likely, you'll need to set NetBeans memory higher.
  * Edit `<NetBeans>/etc/netbeans.conf` and add `-J-Xmx5200m -J-XX:MaxPermSize=1024m` to `netbeans_default_options="..."`.
* Restart NetBeans.
* Click `Menu > Profile > Profile Test File`.
* In the dialog which appeared, edit `exec.args=-Djboss.modules.system.pkgs=org.netbeans`.

## Profiling forge run using YourKit

* Download and unzip YourKit and get the trial license.
* Run `forge`, refer to [Profiling Forge](http://forge.jboss.org/1.x/docs/using/profiling-forge.html) but skip the first 2 points (installing the .jar's) - Forge 2 already contains those.
* Run `windup-analyze-app`.