/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbox;

//import static chatbox.Chatbox.chatwindow;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */

public class Listen implements Serializable,Runnable {
    
    private Socket socket = null;
    ObjectInputStream in = null;
    public String name;
    public ChatWindow chatWindow = null;

    Listen (Socket socket, String name, ChatWindow cw) {
        
        this.socket = socket;
        this.chatWindow = cw;
        try {
            System.out.println("in stream creation");
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("in stream created");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.name = name;
    
    }


    @Override
    public void run() {
        
         while (true) {
            try {
                MSG x = new MSG();
                
                String txt = null;
                try {
                    x = (MSG) in.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Message received");
                
                //      What to do after message is received
                
                this.chatWindow.MessageReceived(x.txt);
                
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }    
    }
}

