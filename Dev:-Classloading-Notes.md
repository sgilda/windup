_Page contains temporary notes, to be edited._

### Which classloader is used? Always the one of the current code?
Depends, if you are in a Furnace Service, then yes, it will use the current code's addon's classloader.

### How do I know if I am in a Furnace service?
Every furnace service is wrapped in a proxy.
If it came from another addon (not your current addon) and was not instantiated with new Blah(), you are in a furnace service. So if it was @Injected or retrieved via addonRegistry.getServices(...)


```
(16:32:40) jsightler: ozizka: I do like a Resource.open(...) type API -- that sounds like a good idea.
(16:32:57) LincolnBaxter: ozizka: unless you use the TCCL (which you shouldn't in a modular environment), then using the class's own classloader is the best solution for that situation
(16:33:41) LincolnBaxter: ozizka: using a shared Resource.open() APi will actually be sort of a no-op, and just introduce another layer, since you generally need to be able to specify a classloader anyway
(16:33:53) jsightler: lincolnthree: Well...
(16:34:09) LincolnBaxter: ozizka: unless I'm misunderstanding the point of this
(16:34:10) jsightler: lincolnthree: In windup we frequently need resources from the caller's addon
(16:34:18) LincolnBaxter: ozizka: ah wait
(16:34:27) LincolnBaxter: ozizka: the *caller*'s classloader
(16:34:35) LincolnBaxter: ozizka: not the CL of the current operation
(16:34:44) LincolnBaxter: ozizka: that's different. hmm
(16:35:11) ozizka-FN: Right
(16:35:18) ozizka-FN: Actually that's what I wanted to ask -
(16:35:21) jsightler: We deal with this in an ad-hoc way at the moment... it might be nice to have a centralized service that does it.
(16:35:25) ozizka-FN: which classloader is used?
(16:35:33) ozizka-FN: Always the one of the current code?
(16:35:44) ozizka-FN: Implicitly
(16:35:54) LincolnBaxter: ozizka: depends, if you are in a Furnace Service, then yes, it will use the current code's addon's classloader
(16:36:00) ozizka-FN: Ah
(16:36:11) ozizka-FN: How do I know if I am in a Furnace service?
(16:36:14) LincolnBaxter: ozizka: because every furnace service is wrapped in a proxy
(16:36:31) LincolnBaxter: ozizka: basically if it came from another addon (not your current addon), you are in a furnace service
(16:36:43) LincolnBaxter: ozizka: and if you didn't instantiate with new Blah()
(16:37:00) LincolnBaxter: ozizka: so if it was @Injected or retrieved via addonRegistry.getServices(...)
(16:37:43) LincolnBaxter: jsightler: ozizka: mbriskar: why do we need the classloader of the calling addon? just curious
(16:37:54) ozizka-FN: So here we would use TCCL right?
LincolnBaxter lincolnthree 
(16:38:28) ozizka-FN: lincolnthree:  To load it's resources
(16:38:31) ozizka-FN: from other addon
(16:38:32) ozizka-FN: .
(16:38:35) ozizka-FN: Or is there other way?
(16:38:42) LincolnBaxter: ozizka: that's a vague statement ;)
(16:38:48) LincolnBaxter: ozizka: where is *here*?
(16:38:56) LincolnBaxter: ozizka: the best way is to pass the actual classloader you want to use
(16:39:01) ozizka-FN: For this use case. For the Resource.open()
LincolnBaxter lincolnthree 
(16:39:21) ozizka-FN: lincolnthree, right, but well, putting classloading code into rules...
(16:39:25) ozizka-FN: Not user friendly
```

### Resources loading
```
(16:48:30) ozizka-FN: lincolnthree:  Just a note - the potential collisions caused  by addon's CL span is not considered as a risk?
(16:49:25) ozizka-FN: I mean, it could load the file from it's deps.
(16:49:36) ozizka-FN: * file = resource
(16:49:50) LincolnBaxter: ozizka1: so the way it works is actually very controlled
(16:50:06) LincolnBaxter: ozizka1: classloader will always attempt to load its own resources before loading from another addon
(16:50:23) LincolnBaxter: ozizka1: at which point the order is determined by the order of the addon dependency in the POM

(16:53:26) ozizka-FN: Q: Unwrapping proxies caused the code in invoke() not actually being called?
(16:53:32) ozizka-FN: Since the method calls were not intercepted?
(16:54:09) LincolnBaxter: ozizka1: exactly :)

(16:54:10) ozizka-FN: lincolnthree, Is that impl a real TCCL or just TCCL-like concept?
(16:54:29) LincolnBaxter: ozizka1: what do you mean "real" ?
(16:54:51) ozizka-FN: Not sure about how TCCL is implemented, I thought you'd need to call something like 
(16:54:52) ozizka-FN: Thread.currentThread().setContextClassLoader(classLoader);
(16:54:56) LincolnBaxter: ozizka1: also if you are interested in where the resource loading / ordering is controlled —> https://github.com/forge/furnace/blob/master/container/src/main/java/org/jboss/forge/furnace/impl/modules/AddonModuleLoader.java#L194
(16:55:01) jsightler: lincolnthree: I wondered that too :)
(16:55:30) LincolnBaxter: ozizka1: jsightler, actually it does: https://github.com/forge/furnace/blob/master/proxy/src/main/java/org/jboss/forge/furnace/proxy/ClassLoaderInterceptor.java#L103
(16:55:55) LincolnBaxter: ozizka1: jsightler: https://github.com/forge/furnace/blob/master/container-api/src/main/java/org/jboss/forge/furnace/util/ClassLoaders.java#L27

(16:56:57) ozizka-FN: Hm, ok so I guess if we dug deeper, it would eventually come to Thread.currentThread().setContextClassLoader(classLoader); ?
(16:57:15) LincolnBaxter: ozizka1: yep: https://github.com/forge/furnace/blob/master/container-api/src/main/java/org/jboss/forge/furnace/util/SecurityActions.java#L71
```

### Furnace Proxies:
https://github.com/forge/furnace/blob/master/proxy/src/main/java/org/jboss/forge/furnace/proxy/Proxies.java#L15
