import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationFrame extends JFrame {
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtMobile;
    private JButton btnRegister;

    public UserRegistrationFrame() {
        setTitle("User Registration");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Mobile:"));
        txtMobile = new JTextField();
        panel.add(txtMobile);

        btnRegister = new JButton("Register");
        panel.add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hämta användarinformation från textfälten
                String name = txtName.getText();
                String email = txtEmail.getText();
                String mobile = txtMobile.getText();

                // Skapa en användarprofil
                UserProfile userProfile = new UserProfile(name, email, mobile);

                // Spara användarprofilen i databasen
                DatabaseHandler.saveUserProfile(userProfile);

                // Återställ textfälten efter registrering
                txtName.setText("");
                txtEmail.setText("");
                txtMobile.setText("");
            }
        });

        add(panel);
    }
}
