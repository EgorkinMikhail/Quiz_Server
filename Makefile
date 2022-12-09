JAVA_SOURCE = $(shell pwd)
JAVA_HOME = /Users/Mikhail_Egorkin/Library/Java/JavaVirtualMachines/openjdk-19.0.1/Contents/Home/bin/java
FLAG1 = -Dmaven.multiModuleProjectDirectory=
FLAG2 = "-Dmaven.home=/Applications/IntelliJ IDEA CE.app/Contents/plugins/maven/lib/maven3"
FLAG3 = "-Dclassworlds.conf=/Applications/IntelliJ IDEA CE.app/Contents/plugins/maven/lib/maven3/bin/m2.conf"
FLAG4 = "-javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=65497:/Applications/IntelliJ IDEA.app/Contents/bin"
FLAG5 = -Dfile.encoding=UTF-8 -classpath  "/Applications/IntelliJ IDEA CE.app/Contents/plugins/maven/lib/maven3/boot/plexus-classworlds-2.5.2.jar"
FLAG6 = org.codehaus.classworlds.Launcher -DskipTests=true package

all_19: clean build_19 copy create_image save_image

clean:
	rm -rf ./target
	rm -rf ./image

build_19:
	$(JAVA_HOME) $(FLAG1)$(JAVA_SOURCE) $(FLAG2) $(FLAG3) $(FLAG4) $(FLAG5) $(FLAG6)

copy:
	cp ./../prometheus/config.yml ./target/
	cp ./../prometheus/jmx_prometheus_javaagent-0.12.0.jar ./target/

create_image:
	docker build . -t raiffeisen/proleum-ed-service

save_image:
	mkdir image
	docker save mikhailegorkin/quiz-server > ./image/quiz-server.tar
	echo 'docker load -i quiz-server.tar' > ./image/load_image_quiz-server.sh
	chmod +x ./image/load_image_quiz-server.sh

docker-run:
	docker run -it --network=my_net --name=quiz-server mikhailegorkin/quiz-server