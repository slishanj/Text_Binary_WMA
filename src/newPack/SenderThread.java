/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newPack;

import java.io.IOException;
import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 *
 * @author Administrator
 */
class SenderThread extends Thread{
    String no;
    String msg;
    
    public SenderThread(String no, String msg) {
        this.no=no;
        this.msg=msg;
    }
    public void run(){
        try {
            MessageConnection mc = (MessageConnection) Connector.open("sms://"+no+":1234");
            TextMessage tm = (TextMessage) mc.newMessage(mc.TEXT_MESSAGE);
            tm.setPayloadText(msg);
            mc.send(tm);
            mc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
