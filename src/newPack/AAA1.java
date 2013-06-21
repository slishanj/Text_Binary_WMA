/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newPack;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;

/**
 * @author Administrator
 */
public class AAA1 extends MIDlet implements CommandListener, MessageListener{
    Form form;
    TextField no;
    TextField outbox;
    TextField inbox;
    Command send;
    Command exit;
    Display display;
    MessageConnection mc;
    boolean endNow;
    
    public AAA1(){
        form = new Form("SMS");
        no = new TextField("number", null, 32, TextField.PHONENUMBER);
        outbox = new TextField("outbox", null, 300, TextField.ANY);
        inbox = new TextField("inbox", null, 300, TextField.ANY);
        send = new Command("send", Command.EXIT, 0);
        exit = new Command("exit", Command.OK, 0);
        form.append(no);
        form.append(outbox);
        form.append(inbox);
        form.addCommand(send);
        form.addCommand(exit);
        form.setCommandListener(this);
        display = Display.getDisplay(this);
        
        
    }
    
    public void startApp() {
        ReceiverThread rt = new ReceiverThread(this,false);
        rt.start();
        display.setCurrent(form);
    }
    
    public void pauseApp() {
        
    }
    
    public void destroyApp(boolean unconditional) {
        try {
            endNow = true;
            mc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void commandAction(Command c, Displayable d) {
        if(c==exit){
            destroyApp(true);
            notifyDestroyed();
        }
        if(c==send){
            SenderThread st = new SenderThread(no.getString(),outbox.getString());
            st.start();
        }
    }

    public void notifyIncomingMessage(MessageConnection mc) {
        System.out.println("AAAAAA");
    }
}
