package common;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public class UI {
  public static JButton button(String label, ActionListener l) {
    JButton button = new JButton(label);
    if (l != null) button.addActionListener(l);
    return button;
  }
}
