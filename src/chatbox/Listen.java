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
        chatWindow = cw;
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.name = name;
    
    }


    @Override
    public void run() {
        
         while (true) {
            try {
                Object txt = null;
                try {
                    txt = in.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Message received");
                
                //      What to do after message is received
                
                this.chatWindow.MessageReceived((String) txt);
                
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }    
    }
}

