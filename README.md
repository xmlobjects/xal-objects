![build](https://img.shields.io/github/actions/workflow/status/xmlobjects/xal-objects/xal-objects-build.yml?logo=Gradle)
![release](https://img.shields.io/github/v/release/xmlobjects/xal-objects?display_name=tag)
[![maven](https://maven-badges.herokuapp.com/maven-central/org.xmlobjects.xal/xal-objects/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.xmlobjects.xal/xal-objects)
[![license](https://img.shields.io/badge/license-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# xal-objects
xal-objects is a Java mapping for the [OASIS eXtensible Address Language (xAL)](http://docs.oasis-open.org/ciq/v3.0/cs02/specs/ciq-specs-v3-cs2.html).

## License
xal-objects is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
See the `LICENSE` file for more details.

## Latest release
The latest stable release of xal-objects is 1.1.4.

Download the latest xal-objects release binaries [here](https://github.com/xmlobjects/xal-objects/releases/latest).
Previous releases are available from the [releases section](https://github.com/xmlobjects/xal-objects/releases).

## Contributing
* To file bugs found in the software create a GitHub issue.
* To contribute code for fixing filed issues create a pull request with the issue id.
* To propose a new feature create a GitHub issue and open a discussion.

## Building
xal-objects requires Java 17 or higher. The project uses [Gradle](https://gradle.org/) as build system. To build the
library from source, clone the repository to your local machine and run the following command from the root of the
repository.

    > gradlew installDist

The script automatically downloads all required dependencies for building the module. So make sure you are connected
to the internet.

The build process creates the output files in the folder `build/install/xal-objects`. Simply put the
`xal-objects-<version>.jar` library file and its mandatory dependencies from the `lib` folder on your modulepath to
start developing with xal-objects. Have fun :-)

## Maven artifact
xal-objects is also available as Maven artifact from the
[Maven Central Repository](https://search.maven.org/artifact/org.xmlobjects.xal/xal-objects). To add xal-objects to your
project with Maven, add the following code to your `pom.xml`. You may need to adapt the xal-objects version number.

```xml
<dependency>
  <groupId>org.xmlobjects.xal</groupId>
  <artifactId>xal-objects</artifactId>
  <version>1.1.4</version>
</dependency>
```

Here is how you use xal-objects with your Gradle project:

```gradle
repositories {
  mavenCentral()
}

dependencies {
  compile 'org.xmlobjects.xal:xal-objects:1.1.4'
}
```
