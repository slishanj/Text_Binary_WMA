/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newPack;

import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Alert;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 *
 * @author Administrator
 */
class ReceiverThread extends Thread{

    AAA1 midlet;
    boolean endNow;

    public ReceiverThread(AAA1 midlet, boolean endNow) {
        this.midlet = midlet;
        this.endNow = endNow;
    }

    public void run() {
        try {
            midlet.mc = (MessageConnection) Connector.open("sms://:1234");
            midlet.mc.setMessageListener(midlet);
            TextMessage tm = (TextMessage) midlet.mc.receive();
            String address = tm.getAddress();
            String timeStamp = tm.getTimestamp().toString();
            while (midlet.mc != null && endNow == false) {
                String s = tm.getPayloadText();
                Alert a = new Alert("OK");
                a.setString(s);
                midlet.display.setCurrent(a);
                midlet.inbox.setString(s + " address : " + address + " timestamp : " + timeStamp);
                tm = (TextMessage) midlet.mc.receive();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
