package reservation_vol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.*;

public class Login {
	  private depart_arriver depart_arriver;

	    private JFrame frame;
	    private JTextField usernameField;
	    private JPasswordField passwordField;

	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {

	                try {
	                    depart_arriver depart_arriver = new depart_arriver();
	                    Login window = new Login(depart_arriver);
	                    window.frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

	    public Login(depart_arriver depart_arriver) {
	        this.depart_arriver = depart_arriver;
	        initialize();
	    }

	    private Connection con;
	    private PreparedStatement pst;
	    private ResultSet rs;

	    public void Connect() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/vol";
	            con = DriverManager.getConnection(url, "root", "");
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }


	    private boolean authenticateUser(String username, String password) {
	        Connect();

	        try {
	            pst = con.prepareStatement("SELECT * FROM utilisateur WHERE username=? AND password=?");
	            pst.setString(1, username);
	            pst.setString(2, password);
	            rs = pst.executeQuery();

	            return rs.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    private void initialize() {
	        frame = new JFrame();
	        frame.setBounds(290, 100, 580, 400);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().setLayout(null);

	        JPanel panel = new JPanel();
	        panel.setBounds(0, 0, 600, 100);
	        panel.setLayout(new BorderLayout());
	        frame.getContentPane().add(panel);



	        JLabel lblTitle = new JLabel("Welcome");
	        lblTitle.setFont(new Font("Verdana", Font.BOLD, 24));
	        lblTitle.setForeground(Color.black);
	        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	        panel.add(lblTitle, BorderLayout.SOUTH);

	        JLabel lblUsername = new JLabel("Username :");
	        lblUsername.setBounds(100, 150, 100, 40);
	        lblUsername.setForeground(Color.getHSBColor(217, 180, 33));
	        frame.getContentPane().add(lblUsername);

	        usernameField = new JTextField();
	        usernameField.setBounds(210, 150, 200, 40);
	        frame.getContentPane().add(usernameField);
	        usernameField.setColumns(10);

	        JLabel lblPassword = new JLabel("Password :");
	        lblPassword.setBounds(100, 200, 100, 40);
	        lblPassword.setForeground(Color.getHSBColor(217, 180, 33));
	        frame.getContentPane().add(lblPassword);

	        passwordField = new JPasswordField();
	        passwordField.setBounds(210, 200, 200, 40);
	        frame.getContentPane().add(passwordField);

	        JButton btnLogin = new JButton("Login");
	        btnLogin.setBounds(260, 250, 100, 30);
	        btnLogin.setBackground(Color.lightGray);
	        frame.getContentPane().add(btnLogin);

	        btnLogin.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String username = usernameField.getText();
	                String password = new String(passwordField.getPassword());

	                if (authenticateUser(username, password)) {
	                    frame.dispose();
	                    depart_arriver depart_arriver = new depart_arriver();
	                    depart_arriver.showPage(); 
	                } else {
	                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
	                }
	            }
	        });
	    }
	}
