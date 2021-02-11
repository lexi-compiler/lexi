# Lexi Compiler

A multi-language and multi-target modular research compiler designed for easy modification with first-class plugin support.

## Documentation

Read all about Lexi at [lexi-compiler.io](https://lexi-compiler.io)

## Run Tests

## Quick Dev Guide

Make sure you have `sbt` version `1.4.x`.

### Running Tests

To execute all compiler tests: `sbt test`

### Build Compiler 

Build Java jar file which will generate a jarfile under `target/scala-3.0.0-M3/lexi-assembly-0.1.0-SNAPSHOT.jar`:

```shell
sbt assembly
````

Or build native image via GraalVM native-image under `target/graalvm-native-image/lexi`:

```shell
sbt graalvm-native-image:packageBin
```

To test the compiler build was successful:

```shell
$ java -jar target/scala-3.0.0-M3/lexi-assembly-0.1.0-SNAPSHOT.jar -lang kotlin "val x: Int = 5"
$ Some(IrFile(None,Some(Vector(IrTopLevelObject(Some(IrDeclaration(Some(IrProperty(Some(x),Some(5),Some(Int))),None)))))))
```

```shell
$ target/graalvm-native-image/lexi -lang kotlin "val x: Int = 5"
$ Some(IrFile(None,Some(Vector(IrTopLevelObject(Some(IrDeclaration(Some(IrProperty(Some(x),Some(5),Some(Int))),None)))))))
```