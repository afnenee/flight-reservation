package reservation_vol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.beans.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import reservation_vol.Menu;

import java.sql.Statement;
public class Menu {

		JFrame frame;
		private JTextField txtfrom;
		private JTextField txtto;
		private JTextField txttotal;
		private JTextField txtprice;
		private JTextField txtdate;
		private JTable txttable;
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		private JTextField txtCode;

	
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Menu window = new Menu();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		public Menu() {
			initialize();
			Table();
		}
		
		public void Connect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/VOL","root","");
				System.out.println("connection etablie");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void initialize() {
			frame = new JFrame();
	        frame.setBounds(230, 30, 900, 650);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			//zone de title li men foog
			JPanel p1 = new JPanel();
			p1.setBackground(Color.WHITE);
			p1.setBorder(new LineBorder(new Color(0, 0, 0)));
			p1.setBounds(85, 10, 709, 84);
			frame.getContentPane().add(p1);
			p1.setLayout(null);
			
			JLabel ltitle = new JLabel("Flight reservation");
			ltitle.setBackground(Color.WHITE);
			ltitle.setForeground(new Color(0, 0, 255));
			ltitle.setFont(new Font("Tahoma", Font.BOLD, 26));
			ltitle.setHorizontalAlignment(SwingConstants.CENTER);
			ltitle.setBounds(33, 10, 676, 64);
			p1.add(ltitle);
			
			
			//zone l input
			JPanel p2 = new JPanel();
			p2.setBackground(Color.WHITE);
			p2.setBorder(new LineBorder(new Color(0, 0, 0)));
			p2.setBounds(85, 104, 709, 308);
			frame.getContentPane().add(p2);
			p2.setLayout(null); 	
			
			JLabel l0 = new JLabel("Flight code :");
			l0.setFont(new Font("Tahoma", Font.PLAIN, 14));
			l0.setBounds(20, 30, 73, 39);
			p2.add(l0);
			
			JLabel l1 = new JLabel("From :");
			l1.setBounds(20, 90, 73, 39);
			l1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			p2.add(l1);
			
			JLabel l2 = new JLabel("To :");
			l2.setBounds(20, 150, 73, 39);
			l2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			p2.add(l2);
			
			JLabel l3 = new JLabel("number of people :");
			l3.setBounds(20, 210, 73, 39);
			l3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			p2.add(l3);
			
			JLabel l4 = new JLabel("Total cost:");
			l4.setBounds(350, 30, 73, 39);
			l4.setFont(new Font("Tahoma", Font.PLAIN, 14));
			p2.add(l4);
			
			JLabel l5 = new JLabel("price :");
			l5.setBounds(350, 90, 73, 39);
			l5.setFont(new Font("Tahoma", Font.PLAIN, 14));
			p2.add(l5);
			
			JLabel l6 = new JLabel("Date flight :");
			l6.setBounds(350, 150, 73, 39);
			l6.setFont(new Font("Tahoma", Font.PLAIN, 14));
			p2.add(l6);
			
			
			txtfrom = new JTextField();
			txtfrom.setHorizontalAlignment(SwingConstants.CENTER);
			txtfrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtfrom.setBounds(140, 90, 177, 30);
			txtfrom.setColumns(10);
			txtfrom.setSize(150, 30);
			txtfrom.setBorder(new LineBorder(new Color(0, 0, 0)));

			p2.add(txtfrom);
			
			
			txtprice = new JTextField();
			txtprice.setHorizontalAlignment(SwingConstants.CENTER);
			txtprice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtprice.setBackground(Color.gray);
			txtprice.setBounds(440, 90, 177, 30);
			txtprice.setForeground(Color.white);
			txtprice.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtprice.setColumns(10);
			txtprice.setSize(150, 30);
			p2.add(txtprice);
	
			
			txtto = new JTextField();
			txtto.setHorizontalAlignment(SwingConstants.CENTER);
			txtto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtto.setBounds(140, 150, 177, 30);
			txtto.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtto.setColumns(10);
			txtto.setSize(150, 30);
			p2.add(txtto);
			
			
			txttotal = new JTextField();
			txttotal.setHorizontalAlignment(SwingConstants.CENTER);
			txttotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txttotal.setBackground(Color.gray);
			txttotal.setBounds(440, 30, 177, 30);
			txttotal.setForeground(Color.white);
			txttotal.setBorder(new LineBorder(new Color(0, 0, 0)));
			txttotal.setColumns(10);
			txttotal.setSize(150, 30);
			p2.add(txttotal);
	
			
			txtdate = new JTextField();
			txtdate.setHorizontalAlignment(SwingConstants.CENTER);
			txtdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtdate.setBackground(Color.GRAY);
			txtdate.setBounds(440, 150, 177, 30);
			txtdate.setForeground(Color.white);
			txtdate.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtdate.setColumns(10);
			txtdate.setSize(150, 30);
			p2.add(txtdate);
			
			
			txtCode = new JTextField();
			txtCode.setHorizontalAlignment(SwingConstants.CENTER);
			txtCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtCode.setColumns(10);
			txtCode.setBounds(140, 30, 177, 30);
			txtCode.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtCode.setSize(150, 30);
			p2.add(txtCode);
			
			
			JSpinner txtnb_person = new JSpinner();
			txtnb_person.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtnb_person.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtnb_person.setBounds(140, 210, 177, 30);
			txtnb_person.setSize(150, 30);
			p2.add(txtnb_person);
			txtnb_person.addChangeListener(new ChangeListener() {
			    public void stateChanged(ChangeEvent e) {
			        try {
			            double prix = Double.parseDouble(txtprice.getText());
			            int nb_person = Integer.parseInt(txtnb_person.getValue().toString());
			            int total = (int) (prix * nb_person);
			            txttotal.setText(String.valueOf(total));
			        } catch (NumberFormatException ex) {
			            ex.printStackTrace();
			        }
			    }
			});
	
			//button sauvgarderr
			JButton btnenrg = new JButton("Book");
			btnenrg.setBackground(Color.green);
			btnenrg.setForeground(Color.white);
			btnenrg.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnenrg.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnenrg.setBounds(440, 190, 177, 30);
			btnenrg.setSize(150, 30);
			p2.add(btnenrg);
			btnenrg.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					try {
						Connect();
						pst = con.prepareStatement("insert into reservation(code,`from`,`to`,nb_person,total,price,date) values (?,?,?,?,?,?,?)");
						pst.setString(1, txtCode.getText());
						pst.setString(2, txtfrom.getText());
						pst.setString(3, txtto.getText());
						pst.setString(4, txtnb_person.getValue().toString());
						pst.setString(5, txttotal.getText());
						pst.setString(6, txtprice.getText());
						pst.setString(7, txtdate.getText());
						pst.executeUpdate();
						con.close();
						JOptionPane.showMessageDialog(btnenrg, "Your reservation has been completed successfully");
						Table();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			});
			
		
			//button supprimer
			JButton btnsupp = new JButton("Cancel reservation");
			btnsupp.setBackground(Color.red);
			btnsupp.setForeground(Color.white);
			btnsupp.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnsupp.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnsupp.setBounds(440, 230, 177, 30);
			btnsupp.setSize(150, 30);
			p2.add(btnsupp);
			btnsupp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int selectedRow = txttable.getSelectedRow();
						String code = txttable.getValueAt(selectedRow, 1).toString();//(selectedRow, 1) me table l index ta3 l code fe table = 1
						Connect();
						pst = con.prepareStatement("DELETE FROM reservation WHERE code = ?");
						pst.setString(1, code);
						pst.executeUpdate();
						con.close();
						JOptionPane.showMessageDialog(null, "Successfully deleted book");
						Table();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			
			//button editttt
			JButton btnedit = new JButton("Edit");
			btnedit.setBackground(Color.orange);
			btnedit.setForeground(Color.white);
			btnedit.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnedit.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnedit.setBounds(440, 268, 177, 30);
			btnedit.setSize(150, 30);
			p2.add(btnedit);
			btnedit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    try {
				        String code = txtCode.getText();
				        
				        if(!code.isEmpty()) {
				            String from = txtfrom.getText();
				            String to = txtto.getText();
				            String nb_person = txtnb_person.getValue().toString();
				            String total = txttotal.getText();
				            String price = txtprice.getText();
				            String date = txtdate.getText();
				            
				            Connect();
				            pst = con.prepareStatement("UPDATE reservation SET `from`=?, `to`=?, nb_person=?, total=?, price=?, date=? WHERE code=?");
				            pst.setString(1, from);
				            pst.setString(2, to);
				            pst.setString(3, nb_person);
				            pst.setString(4, total);
				            pst.setString(5, price);
				            pst.setString(6, date);
				            pst.setString(7, code);
				            pst.executeUpdate();
				            con.close();
				            
				            JOptionPane.showMessageDialog(null, "Record updated successfully");
				            Table();
				        } else {
				            JOptionPane.showMessageDialog(null, "Please enter a code to edit");
				        }
				        
				    } catch (Exception ex) {
				        ex.printStackTrace();
				    }
				}
	
			});

			
			///////code de fliggghhhhttt 
			txtCode.addKeyListener(new KeyAdapter() {
			    @Override
			    public void keyReleased(KeyEvent e) {
			        String flightCode = txtCode.getText();
			        dep_arr(flightCode);
			    }
			});
			
			//creation de champs de table
			JScrollPane sp1 = new JScrollPane();
			sp1.setBorder(new LineBorder(new Color(0, 0, 0)));
			sp1.setBounds(85, 422, 707, 150);
			frame.getContentPane().add(sp1);
			txttable = new JTable();
			txttable.setForeground(Color.WHITE);
			sp1.setViewportView(txttable);
			txttable.setBackground(Color.GRAY);
			txttable.setBorder(new LineBorder(new Color(0, 0, 0)));
			
			
			//ressserverrr
			JButton btnReserve = new JButton("Print the ticket from here...");
	        btnReserve.setBounds(350, 575, 150, 30);
	        btnReserve.setSize(200, 30);
	        btnReserve.setForeground(Color.WHITE);
	        btnReserve.setBackground(Color.black);
	        frame.getContentPane().add(btnReserve);
	        
	        
	        btnReserve.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	///fileeeeeee
					JOptionPane.showMessageDialog(btnReserve, "imriiim");
	            }
	        });

		}
		
		//zone l output
		private void Table() {
		    try {
		        Connect();
		        String[] entet = {"Select", "Flight Code", "From", "To", "Number of people", "Price", "Total cost", "Date"};
		        Object[][] data = new Object[0][entet.length];
		        
		        DefaultTableModel model = new DefaultTableModel(data, entet) {
		            @Override
		            public Class<?> getColumnClass(int columnIndex) {
		                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
		            }

		            @Override
		            public boolean isCellEditable(int row, int column) {
		                return column == 0; 
		            }
		        };

		        String sql = "select * from reservation";
		        Statement st = con.createStatement();
		        rs = st.executeQuery(sql);
		        while (rs.next()) {
		            Object[] ligne = {
		                false, //Initialize checkbox
		                rs.getString("code"),
		                rs.getString("from"),
		                rs.getString("to"),
		                rs.getString("nb_person"),
		                rs.getString("price"),
		                rs.getString("total"),
		                rs.getString("date")
		            };
		            model.addRow(ligne);
		        }
		        txttable.setModel(model);

		        // nzido checkbox
		        txttable.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
		            private final JCheckBox checkBox = new JCheckBox();

		            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		                checkBox.setSelected((boolean) value);
		                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		                //action pour le checkbox
		                checkBox.addItemListener(e -> {
		                    boolean isChecked = checkBox.isSelected();
		                    if (isChecked) {
		                    	 String code = txttable.getValueAt(row, 1).toString();
		                    	 String from = txttable.getValueAt(row, 2).toString();
		                    	 String to = txttable.getValueAt(row, 3).toString();
		                    	 String price = txttable.getValueAt(row, 6).toString();
		                    	 String total = txttable.getValueAt(row, 5).toString();
		                    	 String date = txttable.getValueAt(row, 7).toString();
		                         txtCode.setText(code);  
		                         txtfrom.setText(from);
			    		         txtto.setText(to);
			    		         txtprice.setText(price);
			    		         txttotal.setText(total);
			    		         txtdate.setText(date);
		                         
		                    } else {
		                    	txtCode.setText("");
		                    	txtfrom.setText("");
		    		            txtto.setText("");
		    		            txtprice.setText("");
		    		            txttotal.setText("");
		    		            txtdate.setText("");
		                    }
		                });
		                return checkBox;
		            }
		        });
		        txttable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));

		        con.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    
		    
		    
		}


		public void showPage() {
	        frame.setVisible(true);
	    }

		//selection de information de flight dans the table apres avoir le code de flight
		private void dep_arr(String flightCode) {
		    try {
		        Connect();
		        String sql = "SELECT depart, arrivee, date, price FROM depart_arriver WHERE codee = ?";
		        pst = con.prepareStatement(sql);
		        pst.setString(1, flightCode);
		        ResultSet rs = pst.executeQuery();
		        if (rs.next()) {
		            String depart = rs.getString("depart");
		            String arrivee = rs.getString("arrivee");
		            String date = rs.getString("date");
		            String price = rs.getString("price");
		            txtfrom.setText(depart);
		            txtto.setText(arrivee);
		            txtdate.setText(date);
		            txtprice.setText(price);
		        } else {
		            txtfrom.setText("");
		            txtto.setText("");
		            txtdate.setText("");
		            txtprice.setText("");
		        }
		        con.close();
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		}
}