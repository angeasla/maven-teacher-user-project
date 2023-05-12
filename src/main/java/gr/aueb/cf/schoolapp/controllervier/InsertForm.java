package gr.aueb.cf.schoolapp.controllervier;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.model.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.model.dao.exception.TeacherDAOExceptions;
import gr.aueb.cf.schoolapp.model.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.service.ITeacherService;
import gr.aueb.cf.schoolapp.model.service.TeacherServiceImpl;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.PreparedStatement;


import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class InsertForm extends JFrame {

        private static final long serialVersionUID = 1L;
        private ITeacherDAO teacherDAO = new TeacherDAOImpl();
        private ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

        private JPanel contentPane;
        private final JTextField frmSname;
        private final JTextField frmFname;
        private PreparedStatement p;

        /**
         * Launch the application.
         */

        /**
         * Create the frame.
         */
        public InsertForm() {
            addWindowListener((WindowListener) new WindowAdapter() {
                @Override
                public void windowActivated(WindowEvent e) {
                    setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
                    frmSname.setText("");
                    frmFname.setText("");
                }
            });

            setTitle("Εισαγωγή Εκπαιδευτή");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 557, 350);
            contentPane = new JPanel();
            contentPane.setBackground(SystemColor.inactiveCaptionBorder);
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel lblLastname = new JLabel("Επώνυμο");
            lblLastname.setForeground(Color.RED);
            lblLastname.setFont(new Font("Tahoma", Font.BOLD, 17));
            lblLastname.setBounds(41, 65, 97, 36);
            contentPane.add(lblLastname);

            JLabel lblFirstname = new JLabel("Όνομα");
            lblFirstname.setForeground(Color.RED);
            lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 17));
            lblFirstname.setBounds(41, 127, 97, 36);
            contentPane.add(lblFirstname);

            frmSname = new JTextField();
            frmSname.setBounds(144, 72, 150, 25);
            contentPane.add(frmSname);
            frmSname.setColumns(50);

            frmFname = new JTextField();
            frmFname.setColumns(50);
            frmFname.setBounds(144, 138, 150, 25);
            contentPane.add(frmFname);

            JButton btnClose = new JButton("Close");
            btnClose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Main.getSearchForm().setEnabled(true);
                    Main.getInsertForm().setVisible(false);
                }
            });

            btnClose.setForeground(SystemColor.textHighlight);
            btnClose.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnClose.setBounds(375, 231, 125, 43);
            contentPane.add(btnClose);

            JButton btnInsert = new JButton("Insert");

            btnInsert.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String inputLastname = frmSname.getText().trim();
                    String inputFirstname = frmFname.getText().trim();

                    if (inputLastname.equals("") || (inputFirstname.equals(""))) {
                        JOptionPane.showMessageDialog(null, "Not valid input", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                    try {
                        TeacherDTO teacherDTO = new TeacherDTO();
                        teacherDTO.setFirstname(inputFirstname);
                        teacherDTO.setLastname(inputLastname);

                        Teacher teacher = teacherService.insertTeacher(teacherDTO);

                        JOptionPane.showMessageDialog(null, " Teacher " + teacher + " was inserted"
                                , "INSERT", JOptionPane.PLAIN_MESSAGE);

                    } catch (TeacherDAOExceptions e1) {
                        String message = e1.getMessage();
                        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            btnInsert.setBounds(240, 231, 125, 43);
            contentPane.add(btnInsert);

            JSeparator separator = new JSeparator();
            separator.setBounds(41, 201, 412, 1);
            contentPane.add(separator);

            JPanel panel = new JPanel();
            panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            panel.setBounds(27, 37, 426, 154);
            contentPane.add(panel);
        }

    }
