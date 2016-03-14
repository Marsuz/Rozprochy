import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * Created by root on 14/03/16.
 */
public class Message {

    private static final char STOP = '#';
    private String nick;
    private String text;
    private String date;
    private int checksum;

    public Message(String nick, String text, String date) {
        this.nick = nick;
        this.text = text;
        this.date = date;
        //countChecksum();
    }

    private Message(String nick, String text, String date, int checksum) {
        this.nick = nick;
        this.text = text;
        this.date = date;
        this.checksum = checksum;
    }

    public static Message createMessage(String wholeMessage) {

        String[] words = wholeMessage.split(String.valueOf(STOP));
        return new Message(words[0], words[1], words[2], Integer.parseInt(words[3]));

    }
    
    public String getMessageAsString() {
        return nick + STOP + text + STOP + date + STOP + countChecksum() + STOP;
    }

    public int countChecksum() {
        int result = nick.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    public String getNick() {
        return nick;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public int getChecksum() {
        return checksum;
    }
}
