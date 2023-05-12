package gr.aueb.cf.schoolapp.controllervier;

import gr.aueb.cf.schoolapp.Main;

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


public class SearchStudentsForm extends JFrame {
    private final static long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JTextField txt_lastname;
    private String inputLastName;


    /**
     * Create the frame.
     */
    public SearchStudentsForm() {

        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Εισαγωγή / Αναζήτηση Μαθητών");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 524, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLastname = new JLabel("Επώνυμο");
        lblLastname.setForeground(new Color(128, 0, 0));
        lblLastname.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblLastname.setBounds(186, 28, 130, 45);
        contentPane.add(lblLastname);

        txt_lastname = new JTextField();
        txt_lastname.setBounds(113, 85, 257, 33);
        contentPane.add(txt_lastname);
        txt_lastname.setColumns(10);

        JButton btnSearch = new JButton("Αναζήτηση");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputLastName = txt_lastname.getText().trim();
                Main.getSearchForm().setEnabled(false);
                Main.getUpdateDeleteForm().setVisible(true);
            }
        });
        btnSearch.setForeground(Color.BLUE);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSearch.setBounds(178, 144, 130, 45);
        contentPane.add(btnSearch);

        JButton btnInsert = new JButton("Εισαγωγή");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchForm().setEnabled(false);
                Main.getInsertForm().setVisible(true);
            }
        });
        btnInsert.setForeground(Color.BLUE);
        btnInsert.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnInsert.setBounds(178, 227, 130, 45);
        contentPane.add(btnInsert);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(true);
                Main.getSearchForm().setVisible(false);
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
}
