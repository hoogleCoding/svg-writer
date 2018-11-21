# A simple scala svg writer

Include the library in your build.sbt file.

```
lazy val root = (project in file(".")).dependsOn(svgWriter)

lazy val svgWriter = RootProject(uri("https://github.com/hoogleCoding/svg-writer.git"))
```
