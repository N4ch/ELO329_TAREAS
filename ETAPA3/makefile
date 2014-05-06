JFLAGS = -encoding ISO8859_1
JC = javac
JVM = java
FILE =  

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	PhysicsLab.java \
	MyWorld.java \
	PhysicsElement.java \
	Ball.java \
	Spring.java  \
	SpringAttachable.java
	

MAIN = PhysicsLab

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class 
	$(JVM) $(MAIN) $(FILE)

clean:
	$(RM) *.class
