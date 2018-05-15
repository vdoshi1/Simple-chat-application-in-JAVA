package com.vishva;

import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class chat_client
{
	JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader,bufferRead;
	PrintWriter writer;
	Socket socket;

	public static void main(String[] args)
	{
		chat_client client = new chat_client();
		client.begin();
	}

	public void begin()
	{
		JFrame frame = new JFrame("Chat client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainpanel = new JPanel();
		incoming = new JTextArea(15,50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane scroller = new JScrollPane(incoming);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		outgoing = new JTextField(20);
		JButton sendbutton = new JButton("Send");
		sendbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev)
			{
				try
				{
					writer.println(outgoing.getText());
					writer.flush();
				}

				catch(Exception ex)
				{
					ex.printStackTrace();
				}

				outgoing.setText("");
				outgoing.requestFocus();
			}
		});
		mainpanel.add(scroller);
		mainpanel.add(outgoing);
		mainpanel.add(sendbutton);
		network_setup();
		System.out.print("\n\nEnter your name: ");
	    try
		{
	       	String s = bufferRead.readLine();
	       	writer.println(s);
	       	writer.flush();
	       	bufferRead.close();
		}

	    catch(IOException e)
	    {
	        e.printStackTrace();
	    }
	    
		Thread readthread = new Thread(new Runnable(){
			public void run()
			{
				String message;
				try
				{
					while((message=reader.readLine())!=null)
					{
						incoming.append(message+"\n");
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		readthread.start();

		frame.getContentPane().add(BorderLayout.CENTER,mainpanel);
		frame.setSize(400,500);
		frame.setVisible(true);
	}

	public void network_setup()
	{
		try
		{
	    	System.out.print("Enter your server's IP address : ");
	    	bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    String ip = bufferRead.readLine();
	       	socket = new Socket(ip,10000);
	       	InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(streamreader);
			writer = new PrintWriter(socket.getOutputStream());
			System.out.println("\nNetwork connection success");
		}

		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}

}