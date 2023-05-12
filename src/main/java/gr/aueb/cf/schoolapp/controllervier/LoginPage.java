package gr.aueb.cf.schoolapp.controllervier;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.model.service.util.DBUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginPage extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage frame = new LoginPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginPage() {
        setTitle("User Login");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 502, 340);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(SystemColor.textHighlight);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblUsername.setBounds(68, 68, 127, 32);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(SystemColor.textHighlight);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblPassword.setBounds(68, 129, 127, 32);
        contentPane.add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.setBounds(192, 68, 178, 32);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBounds(30, 195, 448, 1);
        contentPane.add(separator);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputUsername = txtUsername.getText().trim();
                String inputPassword = String.valueOf(txtPassword.getPassword()).trim();
                String password = System.getenv("TS_PASSWORD");
                String hashedPassword;

                if (inputUsername.equals("") || inputPassword.equals("")) {
                    return;
                }

                if (inputUsername.equals("admin") && (inputPassword.equals(password))) {
                    Main.getLoginPage().setVisible(false);
                    Main.getSearchUser().setVisible(true);
                } else {
                    String sql = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";

                    try (Connection conn = DBUtil.getConnection();
                         PreparedStatement p = conn.prepareStatement(sql)) {

                        p.setString(1, inputUsername);
                        ResultSet rs = p.executeQuery();

                        if (rs.next()) {
                            hashedPassword = rs.getString("PASSWORD");
                        } else {
                            JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        if (BCrypt.checkpw(inputPassword, hashedPassword)) {
                            Main.getMenu().setVisible(true);
                            Main.getLoginPage().setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error in password", "Login Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogin.setBounds(325, 225, 136, 42);
        contentPane.add(btnLogin);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(192, 129, 178, 32);
        contentPane.add(txtPassword);
    }
}

