JAVAC=		javac
JAVA=		java

LIB_DIR=	../lib
SRC_DIR=	../src

LBFGS=		$(LIB_DIR)/LBFGS.jar

MODELS_DIR=		../models
MODEL_CRFTAGGER=	$(MODELSDIR)/CrfTagger
MODEL_CRFCHUNKER=	$(MODELSDIR)/CrfChunker
MODEL_SENSEGMENTER=	$(MODELSDIR)/SenSegmenter

SAMPLES_DIR=		../samples

SRC_FLEXCRFS=		flexcrfs
SRC_JMAXENT=		jmaxent
SRC_SENSEGMENTER=	sensegmenter
SRC_PENNTOKENIZER=	penntokenizer
SRC_CRFTAGGER=		crftagger
SRC_CRFCHUNKER=		crfchunker
SRC_JTEXTPRO=		jtextpro

JAR_FLEXCRFS=		$(LIB_DIR)/flexcrfs.jar
JAR_JMAXENT=		$(LIB_DIR)/jmaxent.jar
JAR_SENSEGMENTER=	$(LIB_DIR)/sensegmenter.jar
JAR_PENNTOKENIZER=	$(LIB_DIR)/penntokenizer.jar
JAR_CRFTAGGER=		$(LIB_DIR)/crftagger.jar
JAR_CRFCHUNKER=		$(LIB_DIR)/crfchunker.jar
JAR_JTEXTPRO=		$(LIB_DIR)/jtextpro.jar

CLASSPATH=	$(JAR_JTEXTPRO):.

all:
	$(JAVAC) $(SRC_FLEXCRFS)/Model.java
	$(JAVAC) $(SRC_FLEXCRFS)/Prediction.java
	$(JAVAC) -classpath $(LBFGS):. $(SRC_JMAXENT)/Trainer.java
	$(JAVAC) $(SRC_JMAXENT)/Classification.java
	$(JAVAC) -classpath $(JAR_JMAXENT):. $(SRC_SENSEGMENTER)/SenSegmenter.java
	$(JAVAC) $(SRC_PENNTOKENIZER)/PennTokenizer.java
	$(JAVAC) -classpath $(JAR_FLEXCRFS):. $(SRC_CRFTAGGER)/CRFTagger.java
	$(JAVAC) -classpath $(JAR_FLEXCRFS):. $(SRC_CRFCHUNKER)/CRFChunker.java
	$(JAVAC) -classpath $(JAR_SENSEGMENTER):$(JAR_PENNTOKENIZER):$(JAR_CRFTAGGER):$(JAR_CRFCHUNKER):. $(SRC_JTEXTPRO)/JTextPro.java
	$(JAVAC) $(SRC_JTEXTPRO)/JTextProcessor.java
	$(JAVAC) $(SRC_JTEXTPRO)/JNEExtractor.java
	jar -cf $(JAR_JTEXTPRO) $(SRC_FLEXCRFS)/*.class $(SRC_JMAXENT)/*.class $(SRC_SENSEGMENTER)/*.class $(SRC_PENNTOKENIZER)/*.class $(SRC_CRFTAGGER)/*.class $(SRC_CRFCHUNKER)/*.class $(SRC_JTEXTPRO)/*.class

test:
	$(JAVA) -mx512M -classpath $(CLASSPATH) jtextpro.JTextProcessor $(MODELS_DIR) $(SAMPLES_DIR)/google-microsoft.txt
	$(JAVA) -mx512M -classpath $(CLASSPATH) jtextpro.JTextProcessor $(MODELS_DIR) $(SAMPLES_DIR)/google-yahoo.txt
	$(JAVA) -mx512M -classpath $(CLASSPATH) jtextpro.JTextProcessor $(MODELS_DIR) $(SAMPLES_DIR)/microsoft-novell.txt
	$(JAVA) -mx512M -classpath $(CLASSPATH) jtextpro.JTextProcessor $(MODELS_DIR) $(SAMPLES_DIR)/oracle-microsoft.txt

clean:
	rm $(JAR_JTEXTPRO)
	rm $(SRC_FLEXCRFS)/*.class
	rm $(SRC_JMAXENT)/*.class
	rm $(SRC_SENSEGMENTER)/*.class
	rm $(SRC_PENNTOKENIZER)/*.class
	rm $(SRC_CRFTAGGER)/*.class
	rm $(SRC_CRFCHUNKER)/*.class
	rm $(SRC_JTEXTPRO)/*.class
	rm $(SAMPLES_DIR)/*.out

