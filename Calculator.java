import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField input;
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        // Frame settings
        setTitle("Simple Calculator");
        setSize(400, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set background color for the frame
        getContentPane().setBackground(new Color(240, 240, 240));

        // Text field
        input = new JTextField();
        input.setFont(new Font("Arial", Font.BOLD, 24));
        input.setEditable(false);
        input.setBackground(Color.WHITE);
        input.setForeground(Color.BLACK);
        add(input, BorderLayout.NORTH);

        // Panel for buttons with Grid Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(240, 240, 240));
        
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        // Adding buttons with custom styles
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(new Color(200, 200, 255)); // Light blue color
            btn.setForeground(Color.BLACK);  // Text color
            btn.setFocusPainted(false);  // Remove focus border
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);

        // Make the calculator visible
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String btnText = e.getActionCommand();

        if (btnText.charAt(0) >= '0' && btnText.charAt(0) <= '9') {
            input.setText(input.getText() + btnText);
        } else if (btnText.equals("C")) {
            input.setText("");
            num1 = num2 = result = 0;
        } else if (btnText.equals("=")) {
            num2 = Double.parseDouble(input.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 == 0) {
                        input.setText("Error");
                        return;
                    }
                    result = num1 / num2; break;
            }
            input.setText("" + result);
        } else {
            num1 = Double.parseDouble(input.getText());
            operator = btnText.charAt(0);
            input.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
