all:  compilarcliente compilarserver


compilarserver: 
	javac ./src/Server/Server.java -sourcepath ./src/Server  -d ./bin/Server/

compilarcliente: 
	javac ./src/Cliente/Client.java -sourcepath ./src/Cliente  -d ./bin/Cliente/	

runserver:
	cd bin/Server;	java Server


runcliente:
	cd bin/Cliente; java Client

clean:
	rm -R bin/Cliente/*.class
	rm -R bin/Server/*.class

