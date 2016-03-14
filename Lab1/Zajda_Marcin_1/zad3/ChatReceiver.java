import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by root on 14/03/16.
 */
public class ChatReceiver extends Thread{

    private String nick;
    private DatagramSocket socket;
    private byte[] buffer = new byte[128];

    public ChatReceiver(String nick, DatagramSocket socket) {
        this.nick = nick;
        this.socket = socket;
    }


    @Override
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData());

                Message message1 = Message.createMessage(message);

                if (message1.countChecksum() != message1.getChecksum()) {
                    System.out.println("ERROR - Wrong checksum of packets");
                } else {
                    if (message1.getNick().equals(nick) == false) {
                        System.out.println("Received message: " + message1.getText());
                        System.out.println("From: " + message1.getNick() + " at: " + message1.getDate());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
