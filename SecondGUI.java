//package swingdemo;
import java.util.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.imageio.*;
import javax.imageio.ImageIO;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.net.*;  
import java.util.*;

public class SecondGUI
{
    public static void main(String[] args) throws Exception
    {
        Abc obj = new Abc();
          
        
        obj.setVisible(true);
        obj.setSize(400,400);
        
    }
}
    
    class Abc extends JFrame  //Card Layout
    {
     
        JLabel l,opLabel;
        //Char ipAddress[]=new Char[20];
        public Abc()
        {
             
             
              l=new JLabel("Image Receiving");
            opLabel=new JLabel("Status: Not Received");
       
            add(l);
            
            add(opLabel);
            
        //    FlowLayout text=new FlowLayout();
            
            
            //whenever button clicked ,it will call methid actionPerformed.
            
        setLayout(new FlowLayout());  //FlowLayout,GridLayout,NullLayout
       setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
           try{
                 ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        opLabel.setText("Reading: " + System.currentTimeMillis());

               File transferFile = new File("C:\\Users\\amit\\Desktop\\test14.jpg");
               byte[] byteArray=new byte[(int)transferFile.length()];
               FileInputStream fin=new FileInputStream(transferFile);
               BufferedInputStream bin=new BufferedInputStream(fin);
               bin.read(byteArray,0,byteArray.length);
               OutputStream os=socket.getOutputStream();
               os.write(byteArray,0,byteArray.length);
               os.flush();
               opLabel.setText("Received ");
               socket.close();
               
           }
               /*
               ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        
        InputStream inputStream = socket.getInputStream();

        opLabel.setText("Reading: " + System.currentTimeMillis());

        byte[] sizeAr = new byte[4];
        inputStream.read(sizeAr);
        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

        byte[] imageAr = new byte[size];
        inputStream.read(imageAr);

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

        opLabel.setText("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
        ImageIO.write(image, "jpg", new File("C:\\Users\\Jakub\\Pictures\\test14.jpg"));

        serverSocket.close();
            }*/
            catch(UnknownHostException e){
                opLabel.setText("Unknown Host Exception Error!");
          //      Logger logger = Logger.getAnonymousLogger();
            //    logger.log(Level.SEVERE, "an exception was thrown", e);
            }
            catch(IOException e){
                opLabel.setText("IOException Error!");
            //      Logger logger = Logger.getAnonymousLogger();
            //    logger.log(Level.SEVERE, "an exception was thrown", e);
            }
           
            
            
        
        }
    
    }
