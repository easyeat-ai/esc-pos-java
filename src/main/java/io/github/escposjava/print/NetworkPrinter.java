package io.github.escposjava.print;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkPrinter implements Printer {
   private OutputStream printer = null;
   private final String address;
   private final int port;

   public NetworkPrinter(String address, int port){
      this.address = address;
      this.port = port;
   }

   public void open(){
      try {
         Socket socket = new Socket(this.address, this.port);
         socket.setSoTimeout(3000);
         printer = socket.getOutputStream();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void write(byte[] command) {
      try {
         // Check if the printer object is properly initialized before invoking methods on it
         if (printer != null) {
               printer.write(command);
         } else {
               // System.out.println("Printer object is not properly initialized.");
               // You might want to throw a custom exception or take other appropriate action here
         }
      } catch (IOException e) {
         // Handle the exception
         e.printStackTrace();
         // You could log the exception details or show a more user-friendly error message here
      }
   }

   public void close(){
      try {
         if (printer != null) {
            printer.close();
         } else {
               // System.out.println("Printer object is not properly initialized.");
               // You might want to throw a custom exception or take other appropriate action here
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
