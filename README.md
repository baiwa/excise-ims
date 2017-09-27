# excise-ims
Excise Integrated Management System (Web)

### How to build back and front to single war.

	cd ims-client
	ng build --env=prod --base-href /ims-webapp/

	cd ims-webapp
	mvn clean package -Dmaven.test.skip=true
  
Once the build is complete, take the generated war file from `cd ims-client/target` then deploy on any servlet container and have fun!
