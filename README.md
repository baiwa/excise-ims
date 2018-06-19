# excise-ims
Excise Integrated Management System (Web)

### Build as a single war
```
~/excise-ims> cd ims-client
~/excise-ims/ims-client> ng build --env=prod
~/excise-ims> cd ..
~/excise-ims> cd ims-webapp
~/excise-ims/ims-webapp> mvn clean package
```

## To do above need below config
```
<build>
	<plugins>
		<plugin>
			<artifactId>maven-resources-plugin</artifactId>
			<executions>
				  <execution>
					  <id>copy-resources</id>
					  <phase>validate</phase>
					  <goals><goal>copy-resources</goal></goals>
					  <configuration>
						  <outputDirectory>${basedir}/target/classes/static/</outputDirectory>
						  <resources>
							  <resource>
								  <directory>${basedir}/../ims-client/dist</directory>
							  </resource>
						  </resources>
					  </configuration>
				  </execution>
			</executions>
		</plugin>
	</plugins>
</build>
```

Thank you Amit Rathi for [special guide](https://dzone.com/articles/angular-2-and-spring-boot-development-environment).

