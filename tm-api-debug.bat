cd E:\Repositories\trymee\trymee-api
call mvn clean package
java -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 -jar target\trymee-swarm.jar