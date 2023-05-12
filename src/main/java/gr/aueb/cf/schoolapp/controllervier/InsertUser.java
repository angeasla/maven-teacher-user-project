package gr.aueb.cf.schoolapp.controllervier;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.User;
import gr.aueb.cf.schoolapp.model.dao.IUserDao;
import gr.aueb.cf.schoolapp.model.dao.UserDaoImpl;
import gr.aueb.cf.schoolapp.model.dao.exception.UserDaoException;
import gr.aueb.cf.schoolapp.model.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.service.IUserService;
import gr.aueb.cf.schoolapp.model.service.UserServiceImpl;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

    public class InsertUser extends JFrame {

        private static final long serialVersionUID = 1L;
        private IUserDao userDao = new UserDaoImpl();
        private IUserService userService = new UserServiceImpl(userDao);
        private JPanel contentPane;
        private final JTextField txtUsername;
        private final JPasswordField txtPassword;
        private PreparedStatement p;


        /**
         * Launch the application.
         */

        /**
         * Create the frame.
         */
        public InsertUser() {
            addWindowListener((WindowListener) new WindowAdapter() {
                @Override
                public void windowActivated(WindowEvent e) {
                    setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
            });

            setTitle("Εισαγωγή Χρήστη");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 557, 350);
            contentPane = new JPanel();
            contentPane.setBackground(SystemColor.inactiveCaptionBorder);
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel lblUsername = new JLabel("Username");
            lblUsername.setForeground(Color.RED);
            lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
            lblUsername.setBounds(41, 65, 97, 36);
            contentPane.add(lblUsername);

            JLabel lblPassword = new JLabel("Password");
            lblPassword.setForeground(Color.RED);
            lblPassword.setFont(new Font("Tahoma", Font.BOLD, 17));
            lblPassword.setBounds(41, 127, 97, 36);
            contentPane.add(lblPassword);

            txtUsername = new JTextField();
            txtUsername.setBounds(144, 72, 173, 25);
            contentPane.add(txtUsername);
            txtUsername.setColumns(50);

            JButton btnClose = new JButton("Close");
            btnClose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Main.getSearchUser().setEnabled(true);
                    Main.getInsertUser().setVisible(false);
                }
            });
            btnClose.setForeground(SystemColor.textHighlight);
            btnClose.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnClose.setBounds(375, 231, 125, 43);
            contentPane.add(btnClose);

            JButton btnInsert = new JButton("Insert");
            btnInsert.setForeground(SystemColor.textHighlight);
            btnInsert.setFont(new Font("Tahoma", Font.BOLD, 15));

            btnInsert.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String inputUsername = txtUsername.getText().trim();
                    String inputPassword = txtPassword.getPassword().toString();

                    if (inputUsername.equals("") | inputPassword.equals("")) {
                        JOptionPane.showMessageDialog(null, "Not valid input", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);

                    }
                    try {
                        UserDTO userDTO = new UserDTO();
                        userDTO.setUsername(inputUsername);
                        userDTO.setPassword(inputPassword);

                        User user = userService.insertUser(userDTO);
                        JOptionPane.showMessageDialog(null, " User " + user + " was inserted"
                                , "INSERT", JOptionPane.PLAIN_MESSAGE);
                    }
                    catch (UserDaoException e1) {
                        String message = e1.getMessage();
                        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }});
            btnInsert.setBounds(240, 231, 125, 43);
            contentPane.add(btnInsert);

            JSeparator separator = new JSeparator();
            separator.setBounds(10, 201, 490, 1);
            contentPane.add(separator);

            txtPassword = new JPasswordField();
            txtPassword.setBounds(144, 133, 173, 25);
            contentPane.add(txtPassword);

            JPanel panel = new JPanel();
            panel.setBounds(10, 24, 450, 167);
            contentPane.add(panel);
        }
    }
