# cargo-properties-merger for cargo-maven2-plugin

For some reason there is no properties merger, so I wrote one.

## Usage 

### pom.xml 

If you would like to use it in your project, you have to clone the project, install it with mvn clean install and reference it in the following way:

```xml
<plugin>
    <groupId>org.codehaus.cargo</groupId>
    <artifactId>cargo-maven2-plugin</artifactId>
    <extensions>true</extensions>
    <configuration>
        <descriptor>src/assemble/merge.xml</descriptor>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>hu.magic.cargo</groupId>
            <artifactId>cargo-properties-merger</artifactId>
            <version>0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>
</plugin> 
```

### merge.xml 

In the merge.xml, you have to reference the PropertiesMerger:

```xml
<?xml version="1.0"?>
<uberwar>
    <wars>
        <war>war1</war>
        <war>war2</war>
    </wars>
    <merges>
        <merge>
            <file>WEB-INF/classes/Language.properties</file>
            <classname>hu.magic.cargo.properties.PropertiesMerger</classname>
        </merge>
        <!-- 
        	other merge configs
        -->
    </merges>
</uberwar>
```

That's all.