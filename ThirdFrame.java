import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class ThirdFrame extends MyFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	Connection conn;
    PreparedStatement stmt;
    ResultSet rs;
	private String dateTime;
	private String ticket;
	private String from;
	private String to;
	private String np;
	private String ns;
	private String amount;
	private String TotalAmount;

	public ThirdFrame(String np,String from,String to,String dateTime,String ns,String amount,String TotalAmount,String ticket) {
		setTitle("Bus Ticket Generating System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 1500,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.PINK);
		
		JLabel lblNewLabel = new JLabel("NPJ BUS TRANSPORT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(698, 42, 225, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Selected From : " + from);
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(539, 219, 257, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Selected To : " + to);
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_2.setBounds(539, 266, 241, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("No of Person : " + np);
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_3.setBounds(539, 312, 210, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Date & Time :"+dateTime );
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_4.setBounds(546, 172, 289, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("No of Stops:"+ns);
		lblNewLabel_5.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_5.setBounds(885, 163, 210, 23);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Ticket Price:Rs."+amount);
		lblNewLabel_6.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_6.setBounds(885, 219, 177, 23);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Total amount:Rs."+TotalAmount);
		lblNewLabel_7.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_7.setBounds(885, 276, 210, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Ticket No:" + ticket);
		lblNewLabel_8.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_8.setBounds(546, 128, 235, 23);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int billNumber = getNextBillNumber();
		        String currentDate = getCurrentDate();
				saveTicketBill(billNumber, currentDate);
			Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "a1b2c3d4");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String query = "INSERT INTO ticket (ticket, dateTime, `from`, `to`, np, ns, amount, TotalAmount) " +
		               "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
			    stmt.setString(1,ticket);
				stmt.setString(2,dateTime);
				stmt.setString(3,from);
	            stmt.setString(4, to);
	            stmt.setString(5,np);
	            stmt.setString(6,ns);
	            stmt.setString(7, amount);
	            stmt.setString(8, TotalAmount);
	            int rowsInserted = stmt.executeUpdate();
	            if (rowsInserted > 0) {
	                JOptionPane.showMessageDialog(null, "Ticket added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error adding Ticket! Please check the details.", "Error", JOptionPane.ERROR_MESSAGE);
	        }           
			dispose();
			new MyFrame();
			}
		});
		btnNewButton.setBounds(675, 398, 89, 26);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		btnNewButton_1.setBounds(893, 401, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MyFrame();
			}
		});
	
	}
	 private static int getNextBillNumber() {
	        int nextBillNumber = -1;
	        String query = "SELECT MAX(bill_number) AS last_bill FROM ticket_bills";

	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "a1b2c3d4");
	             PreparedStatement stmt = conn.prepareStatement(query);
	             ResultSet rs = stmt.executeQuery()) {

	            if (rs.next()) {
	                nextBillNumber = rs.getInt("last_bill") + 1; // Increment the last bill number
	            } else {
	                nextBillNumber = 1; // If there are no records, start from 1
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return nextBillNumber;
	    }

	   
	    private static String getCurrentDate() {
	        Calendar calendar = Calendar.getInstance();
	        return String.format("%04d-%02d-%02d", 
	                calendar.get(Calendar.YEAR), 
	                calendar.get(Calendar.MONTH) + 1, 
	                calendar.get(Calendar.DAY_OF_MONTH));
	    }
	 private static void saveTicketBill(int billNumber, String currentDate) {
	        String insertQuery = "INSERT INTO ticket_bills (bill_number, currentDate) VALUES (?, ?)";

	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "a1b2c3d4");
	             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

	            pstmt.setInt(1, billNumber);
	            pstmt.setString(2,currentDate);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
