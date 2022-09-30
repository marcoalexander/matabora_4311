/**
By Dr Md Tamjidul Hoque, University of New Orleans.
For course: CSCI-4311/5311.
In a client-server Environment, this is the server part, receiving a number (e.g., 6) and then sending the square of that to the client.
*/

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class myServer{

   static PrintStream ps = null;     // to send message
   static Scanner sis = null;        // to receive message
   static int ComputedValue =0;

   public static void send(int myint) throws IOException
   { ps.println (myint); // send message
     ps.flush(); // flush the message
     System.out.println("I (Server) sent the Client: " + myint);
   }

   public static void receive() throws IOException
   { int value= sis.nextInt();
     System.out.println("I (Server) received: " + value);
     ComputedValue = value * value;
   }

   public static void main(String args[])
   {
      ServerSocket svr_skt = null;
      Socket skt = null;
      try {
	          svr_skt = new ServerSocket(1357); // "Server provides services via port - so port number is needed"
	          skt = svr_skt.accept();
	          sis = new Scanner(skt.getInputStream());
	          ps = new PrintStream(skt.getOutputStream());
        }
       catch (IOException e)
	    {System.out.println(" Error connecting: " + e); }
       try
        {receive(); // Sending a value to the server to get square of that value, for example
         send(ComputedValue);
         skt.close();
         svr_skt.close();
        }
       catch (IOException e)
         {System.out.println(" Error sending:" + e);}

        System.out.println("Server side communication ended successfully!");
   }

}


