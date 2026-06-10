import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;

public class AdminNextFrame extends AdminFrame {

	private static final long serialVersionUID = 1L;
	private static final TableModel DefaultTableModel = null;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JScrollPane scrollPane;

	Connection conn;
    PreparedStatement stmt,stmt1;
    ResultSet rs,rs1;
    private JTextArea textArea;
    private JButton btnNewButton_1;
    
	public AdminNextFrame() {
		setTitle("Bus Ticket Generating System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1500,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.PINK);
		lblNewLabel = new JLabel("Enter date(yyyy-mm-dd):");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel.setBounds(485, 88, 230, 29);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		textField.setBounds(688, 91, 169, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
	    btnNewButton = new JButton("Search");
	    btnNewButton.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		btnNewButton.setBounds(530, 137, 86, 23);
		contentPane.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(38, 38, 390, 176);
		contentPane.add(textArea);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(230, 182, 759, 289);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
        btnNewButton_1.setBounds(688, 140, 89, 23);
        contentPane.add(btnNewButton_1);
        
        btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminFrame();
			}
        });
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String date = textField.getText();
                if (isValidDate(date)) {
                    fetchTicketData(date);
                    //number(date);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid date.");
                }
			}
		});
	}
	private void number(String date) {
	    // Update the query to sum np (number of people) for each 'from' location
	    String query1 = "SELECT `from`, SUM(CAST(np AS UNSIGNED)) AS total_people FROM ticket " +
	                    "WHERE DATE(dateTime) = ? GROUP BY `from` ORDER BY total_people DESC";

	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "a1b2c3d4");
	         PreparedStatement stmt1 = conn.prepareStatement(query1)) {

	        stmt1.setString(1, date + "%");  // Use the date parameter with a wildcard for time
	        rs1 = stmt1.executeQuery();

	        // Display the results for each location
	        while (rs1.next()) {
	            String place = rs1.getString("from");
	            int totalPeople = rs1.getInt("total_people");  // The sum of np for each place
	            textArea.append("\nFrom " + place + ": " + totalPeople + " people");
	        }

	        if (textArea.getText().isEmpty()) {
	            textArea.append("No tickets found for the given date.\n");
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error fetching ticket data: " + e.getMessage());
	    }
	}

	private void fetchTicketData(String date) {
	    textArea.setText("");  // Clear text area before populating it

	    String query = "SELECT * FROM ticket WHERE DATE(dateTime) = ?";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "a1b2c3d4");
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, date + "%");  // Use the date parameter with a wildcard for time
	        rs = stmt.executeQuery();

	        int dayCollection = 0;
	        int nt = 0;

	        while (rs.next()) {
	            // Append ticket details
	            textArea.append("Ticket: " + rs.getString("ticket") + "\n");
	            textArea.append("DateTime: " + rs.getString("dateTime") + "\n");
	            textArea.append("From: " + rs.getString("from") + "\n");
	            textArea.append("To: " + rs.getString("to") + "\n");
	            textArea.append("NP: " + rs.getString("np") + "\n");
	            textArea.append("NS: " + rs.getString("ns") + "\n");
	            textArea.append("Amount: " + rs.getString("amount") + "\n");
	            textArea.append("TotalAmount: " + rs.getString("TotalAmount") + "\n");
	            textArea.append("----------------------------------------------------\n");

	            nt += 1;
	            dayCollection += rs.getInt("TotalAmount");
	        }

	        if (nt != 0 && dayCollection != 0) {
	            textArea.append("No of Tickets sold: " + nt + "\n");
	            textArea.append("Day Collection: " + dayCollection + "\n");
	            number(date);  // Call the updated number() method to fetch the total people
	        }

	        if (textArea.getText().isEmpty()) {
	            textArea.append("No tickets found for the given date.\n");
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error fetching ticket data: " + e.getMessage());
	    }
	}

	private boolean isValidDate(String date) {
        // Regular expression to match yyyy-mm-dd
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!Pattern.matches(datePattern, date)) {
            return false;
        }

        // Check if the date is a valid calendar date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Disallow lenient parsing
        try {
            sdf.parse(date);  // Try to parse the date
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
