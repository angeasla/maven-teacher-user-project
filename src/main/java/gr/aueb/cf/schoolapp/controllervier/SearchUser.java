package gr.aueb.cf.schoolapp.controllervier;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.model.dao.IUserDao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

public class SearchUser extends JFrame {
    private final static long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private String inputUserName;


    /**
     * Create the frame.
     */
    public SearchUser() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Αναζήτηση Χρηστών");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 524, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(new Color(128, 0, 0));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblUsername.setBounds(186, 28, 130, 45);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(113, 85, 257, 33);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        JButton btnSearch = new JButton("Αναζήτηση");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputUserName = txtUsername.getText().trim();
                Main.getSearchForm().setEnabled(false);
                Main.getUpdateDeleteUserForm().setVisible(true);
            }
        });
        btnSearch.setForeground(Color.BLUE);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSearch.setBounds(178, 144, 130, 45);
        contentPane.add(btnSearch);

        JButton btnInsert = new JButton("Εισαγωγή");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchUser().setEnabled(false);
                Main.getInsertUser().setVisible(true);
            }
        });

        btnInsert.setForeground(Color.BLUE);
        btnInsert.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnInsert.setBounds(178, 227, 130, 45);
        contentPane.add(btnInsert);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnClose.setForeground(Color.BLUE);
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnClose.setBounds(299, 299, 130, 45);
        contentPane.add(btnClose);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(68, 10, 361, 193);
        contentPane.add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(68, 213, 361, 76);
        contentPane.add(panel_1);
    }


    public String getInputUserName() {
        return inputUserName;
    }


}
