package fotosynk;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.net.SocketException;
import java.io.IOException;

public class FotoSynk {

    public FotoSynk() {
	FTPClient f = new FTPClient();
	f.enterLocalPassiveMode();
      	try {
      	    System.out.println("Connecting to storegate");
	    f.connect("ftp.storegate.se");
	    f.enterRemotePassiveMode();
	    int reply = f.getReplyCode();
	    if (!FTPReply.isPositiveCompletion(reply)) {
		f.disconnect();
		System.out.println("Refused connection. Code: " + reply);
		return;
	    }
	    System.out.println("Logging in");
	    f.login("segesten", "flodhast");
	    System.out.println("Current dir is:" + f.printWorkingDirectory());
	    //for(FTPFile file: f.listFiles()) {
	    //System.out.println(file.getName());
	    //}
	    
	} catch (SocketException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	FotoSynk f = new FotoSynk();
    }

}
