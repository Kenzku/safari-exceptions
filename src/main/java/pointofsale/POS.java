package pointofsale;

import com.sun.net.httpserver.Authenticator;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

class ModemDidNotConnectException extends Exception {}

public class POS {
  public static class NoMoneyException extends Exception {
    public NoMoneyException() {
    }

    public NoMoneyException(String message) {
      super(message);
    }

    public NoMoneyException(String message, Throwable cause) {
      super(message, cause);
    }

    public NoMoneyException(Throwable cause) {
      super(cause);
    }

    public NoMoneyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
    }
  }
  public static class RetryLaterException extends Exception {
    public RetryLaterException() {
    }

    public RetryLaterException(String message) {
      super(message);
    }

    public RetryLaterException(String message, Throwable cause) {
      super(message, cause);
    }

    public RetryLaterException(Throwable cause) {
      super(cause);
    }

    public RetryLaterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
    }
  }

  public static void dialModem() throws ModemDidNotConnectException {

  }
  public static boolean USE_MODEM = true;
  public static void authorizePayment(int ccnum, long money)
      throws NoMoneyException, RetryLaterException {
//      throws ModemDidNotConnectException, IOException {
//      throws Exception {
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
        throw new NoMoneyException();
      } catch (IOException | ModemDidNotConnectException me) {
        retries--;
        if (retries == 0) {
          throw new RetryLaterException("CC temporary failure", me);
          // throw me; can still say throws two specific exceptions
        }
      }
//      } catch (IOException se) {
//      } catch (ModemDidNotConnectException me) {
//        retries --;
//        if (retries == 0) {
//          throw new RetryLaterException("CC temporary failure", me);
//        }
//      } catch (IOException se) {
//        // retries...
//        throw new RetryLaterException("CC temporary failure", se);
//      }
//    }
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
