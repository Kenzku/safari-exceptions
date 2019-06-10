package exceptions;

import java.sql.SQLException;

public class Simple {
  public static String getText() throws Exception {
    if (Math.random() > 0.5) {
      return "Success";
    } else {
      throw new SQLException();
    }
  }

  public static void main(String[] args) {
    try {
      System.out.println("The text is " + getText());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
