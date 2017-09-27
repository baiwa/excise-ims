@echo off
echo Start build single war.
echo 1) Start Build Front.
cd ims-client
call ng build --env=prod --base-href /ims-webapp/
echo Finish Build Front.
echo 2) Start Build API.
cd ..
cd ims-webapp
call mvn clean package -Dmaven.test.skip=true
echo Finish Build API.
pause