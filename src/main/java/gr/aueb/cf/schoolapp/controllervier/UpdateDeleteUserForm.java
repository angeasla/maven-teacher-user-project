package gr.aueb.cf.schoolapp.controllervier;
import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.User;
import gr.aueb.cf.schoolapp.model.dao.IUserDao;
import gr.aueb.cf.schoolapp.model.dao.UserDaoImpl;
import gr.aueb.cf.schoolapp.model.dao.exception.UserDaoException;
import gr.aueb.cf.schoolapp.model.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.service.IUserService;
import gr.aueb.cf.schoolapp.model.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.model.service.exceptions.UserNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import java.util.List;
import java.util.Objects;

public class UpdateDeleteUserForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JTextField udfrm_uname;
    private final JTextField udfrm_pswd;
    private final JTextField udfrm_id;


    private final IUserDao userDao = new UserDaoImpl();
    private final IUserService userService = new UserServiceImpl(userDao);
    private int listPosition;
    private int listSize;
    List<User> users;

    public UpdateDeleteUserForm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    users = userService.getUserByUsername(Main.getSearchUser().getInputUserName());

                    listPosition = 0;
                    listSize = users.size();

                    if (listSize == 0) {
                        udfrm_id.setText("");
                        udfrm_pswd.setText("");
                        udfrm_uname.setText("");
                        return;
                    }

                    udfrm_id.setText(Integer.toString(users.get(listPosition).getId()));
                    udfrm_pswd.setText(users.get(listPosition).getPassword());
                    udfrm_uname.setText(users.get(listPosition).getUsername());
                } catch (UserDaoException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error in getting users", JOptionPane.ERROR_MESSAGE);
                }
//                catch (UserNotFoundException ex) {
//                    ex.printStackTrace();
//                }
            }
        });

        setBackground(SystemColor.activeCaption);
        setTitle("Χρήστες");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 340, 252);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        setContentPane(contentPane);

        JLabel lbl_ID = new JLabel("ID");
        lbl_ID.setForeground(new Color(153, 0, 0));
        lbl_ID.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_ID.setBounds(9, 13, 103, 16);
        contentPane.add(lbl_ID);

        JLabel lbl_uname = new JLabel("Username");
        lbl_uname.setForeground(new Color(153, 0, 0));
        lbl_uname.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_uname.setBounds(9, 48, 103, 16);
        contentPane.add(lbl_uname);

        JLabel lbl_pswd = new JLabel("Password");
        lbl_pswd.setForeground(new Color(153, 0, 0));
        lbl_pswd.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_pswd.setBounds(9, 83, 103, 16);
        contentPane.add(lbl_pswd);

        udfrm_id = new JTextField();
        udfrm_id.setEditable(false);
        udfrm_id.setBounds(120, 13, 56, 22);
        contentPane.add(udfrm_id);
        udfrm_id.setColumns(10);

        udfrm_uname = new JTextField();
        udfrm_uname.setBounds(120, 48, 189, 22);
        contentPane.add(udfrm_uname);
        udfrm_uname.setColumns(10);

        udfrm_pswd = new JTextField();
        udfrm_pswd.setBounds(120, 81, 189, 22);
        contentPane.add(udfrm_pswd);
        udfrm_pswd.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = udfrm_id.getText().trim();
                String inputUsername = udfrm_uname.getText().trim();
                String inputPassword = udfrm_pswd.getText().trim();

                if (inputUsername.equals("") || inputPassword.equals("") || id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Not valid input", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(Integer.parseInt(id));
                    userDTO.setUsername(inputUsername);
                    userDTO.setPassword(inputPassword);
                    User user = userService.updateUser(userDTO);
                    JOptionPane.showMessageDialog(null, "User "
                            + " was updated", "UPDATE", JOptionPane.PLAIN_MESSAGE);
                    System.out.println("Check updateForm Dao IN");
                } catch (UserDaoException  | UserNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int response;
                    String idStr = udfrm_id.getText();
                    int id;

                    if (idStr.equals("")) return;

                    id = Integer.parseInt(udfrm_id.getText());

                    response = JOptionPane.showConfirmDialog (null, "Είστε σίγουρος;",
                            "Warning", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION){
                        userService.deleteUser(id);
                        JOptionPane.showMessageDialog (null, "User was deleted successfully",
                                "DELETE", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (UserDaoException | UserNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog (null, message, "DELETE", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDelete.setBounds(9, 165, 89, 25);
        contentPane.add(btnDelete);
        btnUpdate.setBounds(91, 165, 92, 25);
        contentPane.add(btnUpdate);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchUser().setEnabled(true);
                Main.getUpdateDeleteUserForm().setVisible(false);
            }
        });
        btnClose.setBounds(180, 165, 95, 25);
        contentPane.add(btnClose);

        JButton btnFirst = new JButton("");
        btnFirst.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("First record.png"))));
        btnFirst.setBounds(9, 132, 39, 25);
        btnFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = 0;
                    udfrm_id.setText(String.format("%s", users.get(listPosition).getId()));
                    udfrm_uname.setText(users.get(listPosition).getUsername());
                    udfrm_pswd.setText(users.get(listPosition).getPassword());
                }
            }
        });

        contentPane.add(btnFirst);
        JButton btnPrev = new JButton("");
        btnPrev.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png"))));
        btnPrev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition > 0) {
                    listPosition--;
                    udfrm_id.setText(String.format("%s", users.get(listPosition).getId()));
                    udfrm_uname.setText(users.get(listPosition).getUsername());
                    udfrm_pswd.setText(users.get(listPosition).getPassword());
                }
            }
        });

        btnPrev.setBounds(44, 132, 39, 25);
        contentPane.add(btnPrev);

        JButton btnNext = new JButton("");
        btnNext.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Next_track.png"))));

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition <= listSize - 2) {
                    listPosition++;
                    udfrm_id.setText(String.format("%s", users.get(listPosition).getId()));
                    udfrm_uname.setText(users.get(listPosition).getUsername());
                    udfrm_pswd.setText(users.get(listPosition).getPassword());
                }
            }
        });

        JButton btnLast = new JButton("");
        btnLast.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
        btnLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = listSize - 1;
                    udfrm_id.setText(String.format("%s", users.get(listPosition).getId()));
                    udfrm_uname.setText(users.get(listPosition).getUsername());
                    udfrm_pswd.setText(users.get(listPosition).getPassword());
                }
            }
        });

        btnNext.setBounds(78, 132, 46, 25);
        contentPane.add(btnNext);
        btnLast.setBounds(120, 132, 39, 25);
        contentPane.add(btnLast);

        JSeparator separator = new JSeparator();
        separator.setBounds(9, 113, 305, 4);
        contentPane.add(separator);


    }
}


