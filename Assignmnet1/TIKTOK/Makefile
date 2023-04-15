JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
LIBDIR=lib

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR):$(LIBDIR)/forms_rt.jar $<

CLASSES=Account.class BT.class Database.class TokTik.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)
clean:
	rm $(BINDIR)/*.class
run: $(CLASS_FILES)
	java -cp bin:lib/maforms_rt.jar TokTik