JC =javac
SRCPATH=-sourcepath
JDOC=javadoc
JAR=jar cvfe
.PHONY : clean all

all : docs cls jeu.jar game.jar
docs:
	$(JDOC) $(SRCPATH) src -d docs -subpackages mazeGame

cls: 
	$(JC) $(SRCPATH) src -d classes src/mazeGame/*.java 

game.jar: cls
	$(JAR) jar/game.jar mazeGame.MainGame -C classes mazeGame


clean :
	rm -rf docs classes
	rm -rf jar/*.jar
