all:
	javac src/*.java

run:
	java -cp src MudGame

clean:
	rm -f src/*.class
	rm -f src/.java~

doc:
	javadoc -d javadoc/MudGame_doc/ -sourcepath src/*.java

viewdoc:
	open javadoc/Mudgame_doc/index.html

test:
	javac src/*.java
	java -ea -cp src Tests

#-ea är för att assert ska fungera
