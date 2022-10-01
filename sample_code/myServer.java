/**
 REVISED EDITION:
  By Marco Tabora
  Course: CSCI 4311
  Using the source code with description below, this is a client-server environment in which this server part
  will receive input file name from client, check if file exists in server directory if it exists it sends it, else
  send error message to client.
 ______________________________________________________

By Dr Md Tamjidul Hoque, University of New Orleans.
For course: CSCI-4311/5311.
In a client-server Environment, this is the server part, receiving a number (e.g., 6) and then sending the square of that to the client.
*/

//importing needed libraries
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class myServer{

   static PrintStream ps = null;     // to send message
   static Scanner sis = null;        // to receive message

  // possible method creation to simplify code
   public static void send(String fileName) throws IOException
   {
     ps.println (-1);
     ps.println ("The file " + fileName + " is not available."); // send message
     ps.flush(); // flush the message
    
   }
/*
   public static void receive() throws IOException
   { 
   }
    */
   public static void main(String args[])
   {
      //server opening
      ServerSocket svr_skt = null;
      Socket skt = null;
      String fileName = "";
      try {
              


	          svr_skt = new ServerSocket(1357); // "Server provides services via port - so port number is needed"
	          skt = svr_skt.accept();

              ps = new PrintStream(skt.getOutputStream());
              sis = new Scanner(skt.getInputStream());
            //receiving code
            DataInputStream dataInputStream = new DataInputStream(skt.getInputStream());
            int fileNameLength = dataInputStream.readInt();

            if (fileNameLength > 0) {
              ps.println (1);
              byte[] fileNameBytes = new byte[fileNameLength];
              dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
              fileName = new String(fileNameBytes);

              //creating file name and checking if it exists.
              //if so, send file.
              File fileToSend = new File(fileName);

            if (fileToSend.exists()) {
                //send file
                FileInputStream fileInputStream = new FileInputStream(fileToSend.getAbsolutePath());
                DataOutputStream dataOutputStream = new DataOutputStream(skt.getOutputStream());

                String fileName1 = fileToSend.getName();//String of file name
                byte[] fileNameBytesSend = fileName1.getBytes(); //String of file name converted to byte[]

                byte[] fileContentBytes = new byte[(int) fileToSend.length()]; //file content in byte[]
                fileInputStream.read(fileContentBytes); //reading file content bytes, puts data into byte[]

                dataOutputStream.writeInt(fileNameBytesSend.length);
                dataOutputStream.write(fileNameBytesSend);

                dataOutputStream.writeInt(fileContentBytes.length);
                dataOutputStream.write(fileContentBytes);
                //end of sending code

              }else {
                //send error message to user
                send(fileName);
              }
            
            }
        }
       catch (IOException e)
	    {System.out.println(" Error connecting: " + e); }
       try
        { 
          //server closing
         skt.close();
         svr_skt.close();
        }
       catch (IOException e)
         {System.out.println(" Error creating/downloading file:" + e);}

        System.out.println("Server side communication ended successfully!");
   }

}


/*
receiving code:

            DataInputStream dataInputStream = new DataInputStream(clt_skt.getInputStream());
            int fileNameLength = dataInputStream.readInt();
            
            if (fileNameLength > 0) {
              byte[] fileNameBytes = new byte[fileNameLength];
              dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
              String fileName = new String(fileNameBytes);
              }



            int fileContentLength = dataInputStream.readInt();

              if (fileContentLength > 0) {
                byte[] fileContentBytes = new byte[fileContentLength];

                dataInputStream.readFully(fileContentBytes, 0 ,fileContentLength);

                File fileToDownload = new File("clt_" + fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                fileOutputStream.write(fileContentBytes);
                fileOutputStream.close();

*/
