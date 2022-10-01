/**
 REVISED EDITION:
  By Marco Tabora
  Course: CSCI 4311
  Using the source code with description below, this is a client-server environment in which this client part
  will send a user inputed file name to server, server will check if file name exists and send the file, client will
  receive the file, save it under new name. If file does not exist error message will be sent from server to client.
 ______________________________________________________
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
   /*
   public static void send() throws IOException
   { 
   }
*/
   public static int receive() throws IOException
   { 
    int value = sis.nextInt();
    return value;
   }
   
   public static void main(String args[])
   {
    //create scanner to take input(file name) from user.
    //send file name to server
    Scanner input = new Scanner(System.in);
    System.out.println("Please enter file you want to pull from server, in <FileName>.<FileExtension> format: ");
    File fileToSend = new File(input.nextLine());//new code

      Socket clt_skt = null;
      try {

              


            //client opening
	          clt_skt = new Socket("127.0.0.1", 1357); // "127.0.0.1" is "localhost"; so you can also try "localhost"

              ps = new PrintStream(clt_skt.getOutputStream());
              sis = new Scanner(clt_skt.getInputStream());
              //sending code
              //FileInputStream fileInputStream = new FileInputStream(fileToSend.getAbsolutePath());
              DataOutputStream dataOutputStream = new DataOutputStream(clt_skt.getOutputStream());

              String fileName = fileToSend.getName();//String of file name
              byte[] fileNameBytes = fileName.getBytes(); //String of file name converted to byte[]

              //byte[] fileContentBytes = new byte[(int) fileToSend.length()]; //file content in byte[]
              //fileInputStream.read(fileContentBytes); //reading file content bytes, puts data into byte[]
              //sending file name to server
              dataOutputStream.writeInt(fileNameBytes.length);
              dataOutputStream.write(fileNameBytes);

              //receive input from server
              String fileNameDownload = "";



              //if file exists, download file with next name
            if (receive() != -1) {
                    DataInputStream dataInputStream = new DataInputStream(clt_skt.getInputStream());
                    int fileNameLength = dataInputStream.readInt();
            
             if (fileNameLength > 0) {
                byte[] fileNameBytesDownload = new byte[fileNameLength];
                dataInputStream.readFully(fileNameBytesDownload, 0, fileNameBytesDownload.length);
                fileNameDownload = new String(fileNameBytesDownload);
              }



             int fileContentLength = dataInputStream.readInt();

              if (fileContentLength > 0) {
                byte[] fileContentBytes = new byte[fileContentLength];

                dataInputStream.readFully(fileContentBytes, 0 ,fileContentLength);

                File fileToDownload = new File("clt_" + fileNameDownload);
                FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                fileOutputStream.write(fileContentBytes);
                fileOutputStream.close();
              }
            } else {
                String errorMsg = sis.nextLine();
                System.out.println(errorMsg);
              }
              //else print error message to user

              //dataOutputStream.writeInt(fileContentBytes.length);
              //dataOutputStream.write(fileContentBytes);
              //end of sending code

   }
       catch (IOException e)
	    {System.out.println(" Error connecting: " + e); }
       try
        {
          //client closing
          clt_skt.close();
        }
       catch (IOException e)
         {System.out.println(" Error sending:" + e);}

        System.out.println("Client side communication ended successfully!");
   }

}

/*
sending code:

//sending code
              FileInputStream fileInputStream = new FileInputStream(fileToSend.getAbsolutePath());
              DataOutputStream dataOutputStream = new DataOutputStream(clt_skt.getOutputStream());

              String fileName = fileToSend.getName();//String of file name
              byte[] fileNameBytes = fileName.getBytes(); //String of file name converted to byte[]

              byte[] fileContentBytes = new byte[(int) fileToSend.length()]; //file content in byte[]
              fileInputStream.read(fileContentBytes); //reading file content bytes, puts data into byte[]

              dataOutputStream.writeInt(fileNameBytes.length);
              dataOutputStream.write(fileNameBytes);

              dataOutputStream.writeInt(fileContentBytes.length);
              dataOutputStream.write(fileContentBytes);
              //end of sending code

*/
