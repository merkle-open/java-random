[![Build Status][travis-image]][travis-url]
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.namics.oss/java-random/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.namics.oss/java-random)

# Advanced Random Test Data Utils

This modules aims to provide a universal mechanism to create random test dummies of java objects.


## Usage

### Maven Dependency (Latest Version in `pom.xml`):

	<dependency>
		<groupId>com.namics.oss</groupId>
		<artifactId>java-random</artifactId>
		<version>1.0.0</version>
	</dependency>
	
### Requirements	

Java: JDK 8            	 

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

There is a basic support for Collections.


This requires a registered instance of  `com.namics.commons.random.generator.RandomGenerator<SupportedType>` for the requested type.
There is a basic list of generators registered by default to support most basic type.
See [basic generators](src/main/java/com/namics/commons/random/generator/basic) for complete list. 


#### Register custom generators `RandomGenerator<SupportedType>` 

There are several ways to register custom `RandomGenerator<SupportedType>`s:

- auto discovery: 
	- place `RandomGenerator<SupportedType>` in same package as type to be generated
	- add custom scan package `RandomData.addRandomGenerators(String scanpackage )`	 
- explicit registration:
    - Register generator class `RandomData.addRandomGenerator(Class<? extends RandomGenerator> generatorClass )`
    - Register generator instance `RandomData.addRandomGenerator(RandomGenerator generator)`
    - Ein zu scannendes Package registrieren: 

[travis-image]: https://travis-ci.org/namics/java-random.svg?branch=master
[travis-url]: https://travis-ci.org/namics/java-random