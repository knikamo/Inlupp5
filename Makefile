CLASSPATH=".:/home/patrik/Downloads/junit-4.12.jar"

all:
	javac src/*.java

test: 
	javac -cp $(CLASSPATH) -g src/*.java
	cd src && java -cp $(CLASSPATH):hamcrest-core-1.3.jar org.junit.runner.JUnitCore AvatarTest TestBackpack TestBook TestRoom


run:
	java -cp src MudGame

clean:
	rm -f src/*.class
	rm -f src/.java~

doc:
	javadoc -d javadoc/MudGame_doc/ -sourcepath src/*.java
	#lägg till -private efter javadoc för att generera doc för allt som är private

view:
	open javadoc/Mudgame_doc/index.html

#test:
#	javac src/*.java
#	java -ea -cp src Tests

#-ea är för att assert ska fungera
