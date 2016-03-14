import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by root on 14/03/16.
 */
public class ChatSender extends Thread{

    private String nick;
    private InetAddress group;
    private DatagramSocket socket;

    public ChatSender(String nick, InetAddress group, DatagramSocket socket) {
        this.nick = nick;
        this.group = group;
        this.socket = socket;
    }


    @Override
    public void run() {

        Scanner in = new Scanner(System.in);
        String input;
        while(in.hasNext()) {
            input = in.nextLine();
            Message message = new Message(nick, input, new Date().toString());
            String toSend = message.getMessageAsString();
            try {
                socket.send(new DatagramPacket(toSend.getBytes(), toSend.getBytes().length, group, ChatClient.PORT));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

    }
}
