package fotosynk;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import java.net.SocketException;
import java.io.IOException;

public class FotoSynk {

    private static String username;
    private static String password;

    public FotoSynk() {
        FTPClient f = new FTPClient();
        f.enterLocalPassiveMode();
        try {
            System.out.println("Connecting to storegate");
            f.connect("ftp.storegate.se");
            f.login(username, password);
            int reply = f.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                f.disconnect();
                System.out.println("Refused connection. Code: " + reply);
                return;
            }
            System.out.println("Logging in");
            System.out.println("Current dir is:" + f.printWorkingDirectory());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            username = args[0];
            password = args[1];
        }
        FotoSynk f = new FotoSynk();
    }
}
