@echo off
echo Start build single war.
echo Start Build API.
cd ims-webapp
call mvn clean package -Dmaven.test.skip=true
echo Finish Build API.
pause