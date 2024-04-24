package reservation_vol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;




public class depart_arriver {
    private JFrame frame;
    private JTextField departField, arriveeField;
    private DefaultTableModel tableModel;
    private JTable resultTable;
    private JScrollPane scrollPane;
    private Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textCode;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    depart_arriver window = new depart_arriver();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public depart_arriver() {
        con = Connect();
        initialize();
    }

    //connexion ll baseee
    public static Connection Connect() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/vol";
        String username = "root";
        String password = "";
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded successfully");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
		
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(230, 50, 800, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel p = new JPanel();
        p.setBounds(100, 0, 600, 100);
        p.setLayout(new BorderLayout());
        frame.getContentPane().add(p);


        JLabel lblTitle = new JLabel("Welcome");
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        lblTitle.setForeground(Color.black);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(lblTitle, BorderLayout.SOUTH);

        //deparrrrrt
        JLabel depart = new JLabel("Departure :");
        depart.setBounds(220, 150, 100, 40);
        frame.getContentPane().add(depart);

        departField = new JTextField();
        departField.setBounds(350, 150, 200, 40);
        departField.setBorder(new LineBorder(new Color(0, 0, 0)));
        frame.getContentPane().add(departField);
        departField.setColumns(10);

        ////arriveeeeeee
        JLabel arrivee = new JLabel("Arrival :");
        arrivee.setBounds(220, 200, 100, 40);
        frame.getContentPane().add(arrivee);
        
        arriveeField = new JTextField();
        arriveeField.setBounds(350, 200, 200, 40);
        arriveeField.setBorder(new LineBorder(new Color(0, 0, 0)));
        frame.getContentPane().add(arriveeField);
        
        //btnnn
        JButton btnrech = new JButton("Find a flight ...");
        btnrech.setBounds(300, 250, 150, 30);
        btnrech.setBackground(Color.gray);
        btnrech.setForeground(Color.black);
        btnrech.setBorder(new LineBorder(new Color(0, 0, 0)));
//        btnrech.setFont(new Font("Tahoma", Font.BOLD, 26));
        frame.getContentPane().add(btnrech);
        
        //affffichage tableau
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Departure");
        tableModel.addColumn("Arrival");
        tableModel.addColumn("Date flight");

        resultTable = new JTable(tableModel);

        //btn reserverrrrrr ta7et e table
        scrollPane = new JScrollPane(resultTable);
        scrollPane.setBounds(180, 300, 400, 100);
        frame.getContentPane().add(scrollPane);
        
        JButton btnReserve = new JButton("Book flight");
        btnReserve.setBounds(300, 410, 150, 30);
        btnReserve.setBackground(Color.orange);
        btnReserve.setForeground(Color.black);
        btnReserve.setBorder(new LineBorder(new Color(0, 0, 0)));
        frame.getContentPane().add(btnReserve);
        btnReserve.setVisible(false);
        
        btnrech.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String depart = departField.getText();
                String arrivee = arriveeField.getText();
                if (recherche(depart, arrivee)) {
                    btnReserve.setVisible(true);
                } else {
                    btnReserve.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Sorry MR/MME No flights found for this departure and arrival");
                }
            }
        });
     
        btnReserve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();
                Menu menu = new Menu();
                menu.showPage(); 
            }
        });

        btnrech.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String depart = departField.getText();
                String arrivee = arriveeField.getText();
            }
        });
    }
    	
	private boolean recherche(String depart, String arrivee) {
	    con = Connect();

	    try {
	        pst = con.prepareStatement("SELECT * FROM depart_arriver WHERE depart=? AND arrivee=?");
	        pst.setString(1, depart);
	        pst.setString(2, arrivee);
	        rs = pst.executeQuery();

	        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
	        model.setRowCount(0);

	        while (rs.next()) {
	            String code = rs.getString("codee");
	            String departVol = rs.getString("depart");
	            String arriveeVol = rs.getString("arrivee");
	            String datee=rs.getString("date");
	            
                model.addRow(new Object[]{departVol, arriveeVol, datee});
            }

	        return model.getRowCount() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	public void showPage() {
        frame.setVisible(true);
    }
}