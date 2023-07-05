import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Login() {
        setTitle("Login Page");
        setSize(400, 250); // Increase the window size
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Add spacing between components

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try
                {
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    String name = usernameField .getText();
                    String pass = passwordField.getText();
                    String sql = "select * from logintbl where username='"+name+"' and password='"+pass+"' ";
                    pst = con.prepareStatement(sql);
                    rs = pst.executeQuery();
                    
                    if(rs.next())
                    {
                        JOptionPane.showMessageDialog(null, "Success");
                        con.close();
                        MainFrame t  = new MainFrame();
                        t.setVisible(true);
                        
                        
                        
                        
                    }
                    else
                    {
                    	usernameField.setText("");
                    	passwordField.setText("");
                        JOptionPane.showMessageDialog(Login.this, "Invalid username or password");
                    }
                }
                catch(SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                    
                    
                }

                
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        // Add empty borders to create spacing around the panel
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login();
            }
        });
    }
}
