![Windup Logo](images/windup-logo-wiki-header.jpg)

As one of the possible decompilers, Windup can use the Jad Decompiler + JadRetro combo.
However, since both are non-java applications and their license permits redistribution, they need some prior installation.

JadRetro is a command-line utility that helps to successfully decompile Java classes created by the Java compilers of Java 1.4, Java 1.5 or later.

JadRetro operates by transforming the specified Java class files (if needed) into ones which could be processed correctly by an old Java decompiler (designed to work with classes of Java 1.3 or earlier).
JadRetro is not a decompiler itself, it is a class transformer helping some old (but good) Java decompilers to convert more class files and/or generate more correct source code.

[http://jadretro.sourceforge.net/](http://jadretro.sourceforge.net/)

A decompiler adapter has been added to JBoss Windup that is facade for the Jadretro library's main method and the Jad decompiler execution. In this adapter the Jadretro library will convert the Java class into a format that can be processed by the Jad decompiler. The Jad decompiler is then run against the Java class file.    

Based on this requirement it is necessary to install the Jad decompiler on the environment for which JBoss Windup is run in order to use this adapter. 

The Jad decompiler can be downloaded from [http://www.varaneckas.com/jad/](http://www.varaneckas.com/jad/).

Download the files specific to your environment.

**For Windows:** Jad 1.5.8g for Windows 9x/NT/2000 on Intel platform (Note: Works fine with later versions of Windows)

**For Linux:** Jad 1.5.8e for Linux on Intel platform or Jad 1.5.8e for Linux  

The path to the directory containing the Jad executable/binary must be added to the PATH environment variable for the environment for which JBoss Windup is being run. 