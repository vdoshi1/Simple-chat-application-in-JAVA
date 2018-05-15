Setting up the chat server:

Download executable server.jar on your Desktop. In order to connect to the server, each client must be on the same local network as the server and must know the IP address of the computer on which server is set up. To find out the IP address of the server, open command line(terminal in Linux) and type ipconfig(ifconfig) and note the IP address(under WLAN if connected on wifi router). Then use cd command and make Desktop as your working directory and type:

  java -jar server.jar

Nothing will appear initially.

Connecting client to the server:

Now on one of the machines on the same network, download client.jar on Desktop. Open command line and use cd to make Desktop as working directory and type:

  java -jar client.jar

It will prompt you to enter IP address of server. Enter the IP address we found using ipconfig. After that you will be asked to enter your name. Enter that and it will open a GUI window. Maximize the GUI window for best results. Anything you type in the text field will appear on all client's screen in the text area along with your name after pressing SEND.
