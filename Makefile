CLASSPATH=".:/home/patrik/Downloads/junit-4.12.jar"
SRCFILES:= Avatar Backpack Book BuildWorld Course Creature EmptyFileException Item Key ListToString MudGame NonExistingRoomException Room Sfinx Student Teacher World
SRCFILES:= $(addprefix src/, $(SRCFILES))
SRCFILES:= $(addsuffix .java, $(SRCFILES))
all:
	javac $(SRCFILES)

test: 
	javac -cp $(CLASSPATH) -g src/*.java
	cd src && java -cp $(CLASSPATH):hamcrest-core-1.3.jar org.junit.runner.JUnitCore AvatarTest TestBackpack TestBook TestRoom


run:
	java -cp src MudGame

clean:
	rm -f src/*.class
	rm -f src/.java~

doc:
	javadoc -d javadoc/MudGame_doc/ $(SRCFILES)
	#lägg till -private efter javadoc för att generera doc för allt som är private

view:
	open javadoc/Mudgame_doc/index.html

