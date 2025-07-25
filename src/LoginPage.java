import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Logare");
    JButton resetButton = new JButton("Resetare");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Utilizator:");
    JLabel userPasswordLabel = new JLabel("Parolă:");
    JLabel messageLabel = new JLabel();

    HashMap<String,String> logininfo = new HashMap<>();
    HashMap<String,String> userRoles = new HashMap<>();

    LoginPage(HashMap<String,String> loginInfoOriginal){
        IDandPassw idandpassw = new IDandPassw();
        logininfo = loginInfoOriginal;
        userRoles = idandpassw.getUserRoles();

        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);

        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);

        loginButton.setBounds(125,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(225,200,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == resetButton){
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if(e.getSource() == loginButton){
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if(logininfo.containsKey(userID)){
                if(logininfo.get(userID).equals(password)){
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Logare cu succes");
                    frame.dispose();

                    if(userRoles.get(userID).equals("Student")){
                        new StudentWelcomePage(userID);
                    } else if(userRoles.get(userID).equals("Profesor")){
                        new ProfesorWelcomePage(userID);
                    }
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Parolă greșită");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Utilizator inexistent");
            }
        }
    }
}
