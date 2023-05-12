package gr.aueb.cf.schoolapp.controllervier;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {


    private final static long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblTeachers;

    /**
     * Create the frame.
     */
    public Menu() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("eduv2.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
        setTitle("Διαχείριση Εκπαιδευτικού Συστήματος");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 517, 361);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblQuality1 = new JLabel("Ποιότητα στην Εκπαίδευση");
        lblQuality1.setForeground(new Color(0, 0, 0));
        lblQuality1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblQuality1.setBounds(83, 13, 319, 57);
        contentPane.add(lblQuality1);

        JButton btnTeachers = new JButton("");
        btnTeachers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(false);
                Main.getSearchForm().setVisible(true);
            }
        });
        btnTeachers.setBounds(22, 156, 60, 57);
        contentPane.add(btnTeachers);

        JButton btnStudents = new JButton("");
        btnStudents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(false);
				Main.getSearchForm().setVisible(true);
            }
        });
        btnStudents.setBounds(22, 234, 60, 57);
        contentPane.add(btnStudents);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 101, 476, 1);
        contentPane.add(separator);

        lblTeachers = new JLabel("Εκπαιδευτές");
        lblTeachers.setForeground(new Color(128, 0, 0));
        lblTeachers.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTeachers.setBounds(92, 156, 113, 57);
        contentPane.add(lblTeachers);

        JLabel lblStudents = new JLabel("Εκπαιδευόμενοι");
        lblStudents.setForeground(new Color(128, 0, 0));
        lblStudents.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblStudents.setBounds(92, 234, 141, 57);
        contentPane.add(lblStudents);

        JLabel lblQuality2 = new JLabel("Ποιότητα στην Εκπαίδευση");
        lblQuality2.setForeground(new Color(0, 100, 0));
        lblQuality2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblQuality2.setBounds(82, 11, 319, 57);
        contentPane.add(lblQuality2);
    }



}
