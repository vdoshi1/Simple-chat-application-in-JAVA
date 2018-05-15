package com.vishva;

import java.io.*;
import java.net.*;
import java.util.*;

public class chat_server
{
	ArrayList clientoutputstreams;

	public class ClientHandler implements Runnable
	{

		BufferedReader reader;
		Socket sock;
		String name;

			public ClientHandler(Socket socketc)
			{
				try
				{
					sock = socketc;
					InputStreamReader isreader = new InputStreamReader(sock.getInputStream());
					reader = new BufferedReader(isreader);
					name = reader.readLine();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}

		public void run()
		{
			String message;
			try
			{
				while((message= reader.readLine())!=null)
				{
					tellEveryone(name+" : "+message);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args)
	{
		new chat_server().begin();
	}

	void begin()
	{
		clientoutputstreams = new ArrayList();

		try
		{
			ServerSocket servsocket = new ServerSocket(10000);

			while(true)
			{
				Socket clientsocket = servsocket.accept();
				System.out.println("Client connected successfully");
				PrintWriter writer = new PrintWriter(clientsocket.getOutputStream());
				clientoutputstreams.add(writer);

				new Thread(new ClientHandler(clientsocket)).start();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void tellEveryone(String message)
	{
		Iterator it = clientoutputstreams.iterator();
		while(it.hasNext())
		{
			try
			{
				PrintWriter writer = (PrintWriter)it.next();
				writer.println(message);
				writer.flush();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
}