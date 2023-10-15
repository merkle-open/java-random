# Advanced Random Test Data Utils

[Project Documentation](http://merkle-open.github.io/java-random)

System        | Status
--------------|------------------------------------------------        
Dependency    | [![Maven Central][maven-central-version]][maven-central]

This modules aims to provide a universal mechanism to create random test dummies of java objects.

## Usage

### Maven Dependency (Latest Version in `pom.xml`):

	<dependency>
		<groupId>com.namics.oss</groupId>
		<artifactId>java-random</artifactId>
		<version>1.3.0</version>
	</dependency>

[![Maven Central][maven-central-version]][maven-central]	

### Requirements	

#### Minumum Java Version

- Java: JDK 8  

#### Java 9 Notes

- Module is not prepared for java 9 yet.
- Due to heavy use of reflection in this lib it might not be that simple to adopt java 9 
          	 

## Create random values

### Basic values

The  `com.namics.commons.random.RandomData` util provides a lot of convenience methods to create basic random values.

### Java beans and advanced types

Use the generic method `com.namics.commons.random.RandomData.random(Class<?> type)` to create random instance of the object.

This works for types with available generators 
```java 
Integer random = RandomData.random(Integer.class);
``` 

But also for Java beans:

```java
 public class Demo {
 	public static class Person{
 		private String name;
 		public String getName() {return name;}
 		public void setName(String name) {this.name = name;}
 	}
 
 	@Test
 	public void simplePerson() throws Exception {
 		Person person = RandomData.random(Person.class);
 		System.out.println(person.getName()); // Leonel Bowers
 	}
 } 
```

This requires a registered instance of  `com.namics.commons.random.generator.RandomGenerator<SupportedType>` for the requested type.
There is a basic list of generators registered by default to support most basic type.
See [basic generators](src/main/java/com/namics/commons/random/generator/basic) for complete list.

There is a basic support for Collections.

#### Register custom generators `RandomGenerator<SupportedType>` 

There are several ways to register custom `RandomGenerator<SupportedType>`s:

- auto discovery: 
	- place `RandomGenerator<SupportedType>` in same package as type to be generated
	- add custom scan package `RandomData.addRandomGenerators(String scanpackage )`	 
- explicit registration:
    - Register generator class `RandomData.addRandomGenerator(Class<? extends RandomGenerator> generatorClass )`
    - Register generator instance `RandomData.addRandomGenerator(RandomGenerator generator)`
    - Ein zu scannendes Package registrieren: 

### Accented chars in random names

You may enable international names by setting either

- system property "random.names.international" to "true"
- environment variable "RANDOM_NAMES_INTERNATIONAL" to "true"

if not enabled, only names without accents are used.

[maven-central-version]: https://maven-badges.herokuapp.com/maven-central/com.namics.oss/java-random/badge.svg
[maven-central]: https://maven-badges.herokuapp.com/maven-central/com.namics.oss/java-random
