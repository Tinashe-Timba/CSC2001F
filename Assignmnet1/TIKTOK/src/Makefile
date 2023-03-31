# Makefile for automation of compilation and Javadoc generation

# Set the compiler
JC = javac

# Set the flags for the compiler
JFLAGS = -g

# Set the name of the output executable
EXECUTABLE = program

# Set the name of the main Java class
MAIN_CLASS = Main

# Set the path for Javadoc output
DOCS_DIR = docs

# Define the default rule to execute when 'make' is run
all: $(EXECUTABLE) javadoc

# Define the rule for compiling the Java program
$(EXECUTABLE): $(MAIN_CLASS).java
	$(JC) $(JFLAGS) $(MAIN_CLASS).java

# Define the rule for generating Javadoc
javadoc:
	C:\Program Files\Common Files\Oracle\Java\javapath\javac.exe

# Define a rule to clean up temporary files
clean:
	rm -f *.class
	rm -rf $(DOCS_DIR)
