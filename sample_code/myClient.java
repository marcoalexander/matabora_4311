/**
By Dr Md Tamjidul Hoque, University of New Orleans.
For course: CSCI-4311/5311.
In a client-server Environment, this is the client part, sending a number (e.g., 6) to the server to receive the square of that sent number.
*/

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class myClient{

   static PrintStream ps = null;     // to send message
   static Scanner sis = null;        // to receive message

   public static void send(int myint) throws IOException
   { ps.println (myint); // send message
     ps.flush(); // flush the message
     System.out.println("I (Client) sent the Server: " + myint);
   }

   public static void receive() throws IOException
   { int value= sis.nextInt();
     System.out.println("Server sent me (Client): " + value);
   }

   public static void main(String args[])
   {
      Socket clt_skt = null;
      try {
	          clt_skt = new Socket("127.0.0.1", 1357); // "127.0.0.1" is "localhost"; so you can also try "localhost"
	          ps = new PrintStream(clt_skt.getOutputStream());
              sis = new Scanner(clt_skt.getInputStream());
   }
       catch (IOException e)
	    {System.out.println(" Error connecting: " + e); }
       try
        {send(6); // Sending a value to the server to get square of that value for example
         receive();
         clt_skt.close();
        }
       catch (IOException e)
         {System.out.println(" Error sending:" + e);}

        System.out.println("Client side communication ended successfully!");
   }

}


