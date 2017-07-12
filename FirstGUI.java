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

public class FirstGUI
{
    public static void main(String[] args) throws Exception
    {
        Abc obj = new Abc();
          
        
        obj.setVisible(true);
        obj.setSize(400,400);
        
    }
}
    
    class Abc extends JFrame implements ActionListener //Card Layout
    {
        JTextField t1,t2,t3;
        JButton b;
        JLabel label,l,input1JTF,input2JTF,input3JTF,opLabel;
        //Char ipAddress[]=new Char[20];
        public Abc()
        {
             input1JTF=new JLabel("Receiver's IP:");
             t1=new JTextField(20);
            
            input2JTF=new JLabel("Port No:");
             t2=new JTextField(20);
            
            input3JTF=new JLabel("File Path");
            t3=new JTextField(20);
                
            label=new JLabel("Result");
             b=new JButton("Send Image");
             
              l=new JLabel("Image Sending");
            opLabel=new JLabel("");
        add(l);
            add(input1JTF);
            add(t1);
            add(input2JTF);
            add(t2);
            add(input3JTF);
            add(t3);
            add(label);
            add(b);
            add(opLabel);
            
        //    FlowLayout text=new FlowLayout();
            
            b.addActionListener(this);  
            //whenever button clicked ,it will call methid actionPerformed.
            
        setLayout(new FlowLayout());  //FlowLayout,GridLayout,NullLayout
       
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        
        public void actionPerformed(ActionEvent ae)
        {
            String ip=t1.getText();
            String tempPath=t3.getText();
           // String filePath=tempPath.replace("/","//");
            int port=Integer.parseInt(t2.getText());
                label.setText(ip+":"+port+":"+tempPath);
            
            try{
                
                int fileSize=500000;
            int bytesRead;
            int currentTot=0;
            Socket socket =new Socket(ip,port);
                byte[] byteArray =new byte[fileSize];
                InputStream is=socket.getInputStream();
                FileOutputStream fos = new FileOutputStream(tempPath);
                BufferedOutputStream bos=new BufferedOutputStream(fos);
                bytesRead =is.read(byteArray,0,byteArray.length);
                currentTot=bytesRead;
                do{
                    bytesRead=is.read(byteArray,currentTot,(byteArray.length-currentTot));
                    if(bytesRead>0)
                        currentTot+=bytesRead;
                } while(bytesRead > -1);
                
                
                
                    bos.write(byteArray,0,currentTot);
                    bos.flush();
                    opLabel.setText("Flushed: " + System.currentTimeMillis());
                    Thread.sleep(120000);
                    opLabel.setText("Closing: " + System.currentTimeMillis());
                    bos.close();
                    socket.close();
                
            }
            
            
            catch(UnknownHostException e){
                opLabel.setText("Unknown Host Exception Error!");
          //      Logger logger = Logger.getAnonymousLogger();
            //    logger.log(Level.SEVERE, "an exception was thrown", e);
            }
            catch(IOException e){
                opLabel.setText("IOException Error!");
               e.printStackTrace();
            }
            catch(InterruptedException e){
                opLabel.setText("Interrupted Exception Error!");
             //     Logger logger = Logger.getAnonymousLogger();
            //    logger.log(Level.SEVERE, "an exception was thrown", e);
            }
            
            
            
        }  
    }
