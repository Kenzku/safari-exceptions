package pointofsale;

import java.io.IOException;
import java.net.Socket;

class ModemDidNotConnectException extends Exception {}

public class POS {
  public static void dialModem() throws ModemDidNotConnectException {

  }
  public static boolean USE_MODEM = true;
  public static void authorizePayment(int ccnum, long money)
//      throws ModemDidNotConnectException, IOException {
      throws Exception {
    int retries = 3;
    while (retries > 0) {
      try {
        if (USE_MODEM) {
          dialModem();
        } else {
          Socket s = new Socket("server", 1234);
        }
        // send messages...
        // if success great return ...
        // else ... no money
      } catch (ModemDidNotConnectException me) {
        retries --;
        if (retries == 0) {
          throw me;
        }
      }
    }
  }

  public static void main(String[] args) {
    try {
      authorizePayment(1234, 100);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
