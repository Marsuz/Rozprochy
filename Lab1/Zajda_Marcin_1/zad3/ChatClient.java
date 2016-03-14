import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Created by root on 14/03/16.
 */



public class ChatClient{

    public static String IP = "224.1.2.3";
    public static int PORT = 6546;

    public ChatClient() {
        try {
            System.out.print("Your nick: ");
            Scanner in = new Scanner(System.in);
            String nick = in.nextLine();
            MulticastSocket socket = new MulticastSocket(PORT);
            InetAddress group = InetAddress.getByName(IP);
            socket.joinGroup(group);
            System.out.println("Sender registered as: " + nick);
            System.out.println("Receiver registered as: " + nick);
            new ChatSender(nick, group, socket).start();
            new ChatReceiver(nick, socket).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        if(args.length != 0) {
            if (args.length == 2) {
                if (args[0].startsWith("224") == false) {
                    System.out.println("Not a multicast address");
                    return;
                }
                IP = args[0];
                PORT = Integer.parseInt(args[1]);
            } else {
                System.out.println("Arguments: <IP> <Port>");
                return;
            }
        }
        new ChatClient();
    }

}
