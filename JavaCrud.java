//JavaCrud source code. Application completed



import java.awt.EventQueue;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JavaCrud {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
        }
        catch (ClassNotFoundException ex) 
        {
          ex.printStackTrace();
        }
        catch (SQLException ex) 
        {
               ex.printStackTrace();
        }
    }
	
	public void table_load()
	{
	    try 
	    {
	    pst = con.prepareStatement("select * from book");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	} 
	    catch (SQLException e) 
	     {
	        e.printStackTrace();
	  } 
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 822, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(327, 36, 185, 43);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(54, 109, 431, 255);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Book title");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(63, 50, 95, 31);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Edition");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(63, 112, 95, 31);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(63, 171, 95, 31);
		panel.add(lblNewLabel_3);

		textField = new JTextField();
		textField.setBounds(186, 52, 221, 31);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(186, 114, 221, 31);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(186, 173, 221, 31);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.setBounds(54, 367, 129, 68);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("CLEAR");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_1.setBounds(204, 367, 129, 68);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("SAVE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname, edition, price;
				
				 bname = textField.getText();
				 edition = textField_1.getText();
				 price = textField_2.getText();
				 
				 try {
				        pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
				        pst.setString(1, bname);
				        pst.setString(2, edition);
				        pst.setString(3, price);
				        pst.executeUpdate();
				        JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
				        table_load();             
				        textField.setText("");
				        textField_1.setText("");
				        textField_2.setText("");
				        textField.requestFocus();
				       }
				    catch (SQLException e1) 
				        {            
				       e1.printStackTrace();
				    }
				
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton_2.setBounds(356, 367, 129, 68);
		frame.getContentPane().add(btnNewButton_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(496, 113, 287, 245);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnNewButton_3 = new JButton("DELETE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		           
		            try {
		                   pst = con.prepareStatement("delete from book where id =?");
		         
		                   pst.executeUpdate();
		                   JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		                   table_load();
		                  
		                   textField.setText("");
		                   textField_1.setText("");
		                   textField_2.setText("");
		                   
		               }
		               catch (SQLException e1) {
		                   
		                   e1.printStackTrace();
		               }
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton_3.setBounds(531, 367, 85, 21);
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("UPDATE");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               String bname,edition,price;
                
                
                bname = textField.getText();
                edition = textField_1.getText();
                price = textField_2.getText();
                
                
                 try {
                        pst = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");
                        pst.setString(1, bname);
                        pst.setString(2, edition);
                        pst.setString(3, price);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Record Update!!!!!");
                        table_load();
                       
                        textField.setText("");
                        textField_1.setText("");
                        textField_2.setText("");
                        
                    }
                    catch (SQLException e1) {
                        
                        e1.printStackTrace();
                    }
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton_4.setBounds(645, 367, 85, 21);
		frame.getContentPane().add(btnNewButton_4);
	}
}
