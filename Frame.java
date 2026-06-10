import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

public class Frame extends MyFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection conn;
    PreparedStatement stmt1;
    ResultSet rs1;
    private JTextArea textArea;
    private JFrame Frame;

	public Frame() {
		setTitle("Bus Ticket Generating System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 1500,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.PINK);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 116, 987, 320);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
		contentPane.add(scrollPane);
		JButton btnNewButton = new JButton("Back");
	     btnNewButton.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		 btnNewButton.setBounds(815, 491, 89, 23);
		 contentPane.add(btnNewButton);
		 btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MyFrame();
			} 
		 });
	
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		 Calendar calendar = Calendar.getInstance();
	     String d = String.format("%04d-%02d-%02d", 
	                calendar.get(Calendar.YEAR), 
	                calendar.get(Calendar.MONTH) + 1, 
	                calendar.get(Calendar.DAY_OF_MONTH));
		String query1= "SELECT `from`, SUM(CAST(np AS UNSIGNED)) AS total_people FROM ticket " +
                "WHERE DATE(dateTime) = ? GROUP BY `from` ORDER BY total_people DESC";

		 try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "a1b2c3d4");
			 PreparedStatement stmt1 = conn.prepareStatement(query1)) {
			 stmt1.setString(1,d+ "%");
			 rs1= stmt1.executeQuery();
			 textArea.setText("Today Records:"+"\n");
			 
			 JLabel lblNewLabel = new JLabel("Records");
			 lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 35));
			 lblNewLabel.setBounds(719, 51, 112, 38);
			 contentPane.add(lblNewLabel);
			 
			 JLabel date = new JLabel("New label");
			 date.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
			 date.setBounds(243, 69, 144, 23);
			 contentPane.add(date);
			 
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			date.setText(dtf.format(now));
			
			JLabel lblNewLabel_1 = new JLabel("Date:");
			lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(201, 69, 81, 23);
			contentPane.add(lblNewLabel_1);
			 
			 
			 while(rs1.next()) {
				  String place = rs1.getString("from");
		          int totalPeople = rs1.getInt("total_people");  // The sum of np for each place
		          textArea.append("\nFrom " + place + ": " + totalPeople + " people");
	         }if (textArea.getText().isEmpty()) {
	                textArea.append("No tickets found for the given date.\n");
	         }
	     } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error fetching ticket data: " + e.getMessage());
	     } 
	}
}
