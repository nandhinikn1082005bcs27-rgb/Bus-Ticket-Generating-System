import java.awt.EventQueue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class MyFrame extends ConductorFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private FirstFrame frame;
	private JFrame f,f1;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton,btnNewButton_1;
	private JComboBox comboBox,comboBox_1;
	protected JLabel date;
	private JLabel resultLabel;
    private JLabel resultLabel1,resultLabel2,resultLabel3,lblNewLabel_4,lblNewLabel_5,lblNewLabel_6;
    
	private static final String[] Item1=new String[] {"Select From", "Sivakasi", "Reserve line", "Satchiyapuram", "Aj college", "Malli", "Court stop"};
	private static final String[] Item2=new String[] {"Select To", "Reserve line", "Satchiyapuram", "Aj college", "Malli", "Court stop", "Sirivi Bus Stand"};
	
	Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
	
	public MyFrame()
	{
		setTitle("Bus Ticket Generating System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1500,800);
		
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.PINK);
		
		JLabel lblNewLabel = new JLabel("NPJ BUS TRANSPORT");
		lblNewLabel.setBounds(632, 36, 254, 34);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);

		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		comboBox.setBounds(508, 227, 185, 22);
		comboBox.setModel(new DefaultComboBoxModel(Item1));
		comboBox.setSelectedIndex(0);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		comboBox_1.setBounds(508, 273, 185, 22);
		comboBox_1.setModel(new DefaultComboBoxModel(Item2));
		comboBox_1.setSelectedIndex(0);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("Date & Time:");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(508, 179, 120, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("No of Person:");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_2.setBounds(511, 318, 120, 20);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		textField.setBounds(616, 321, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
	    date = new JLabel("New label");
	    date.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		date.setBounds(620, 185, 192, 22);
		contentPane.add(date);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		date.setText(dtf.format(now));
		
		btnNewButton = new JButton("Next");
		btnNewButton.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		btnNewButton.setBounds(632, 375, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JLabel lblNewLabel_3 = new JLabel("TicketNo:");
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_3.setBounds(508, 131, 105, 22);
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		contentPane.add(lblNewLabel_3);
		
		resultLabel = new JLabel("No of Stops:");
		resultLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		resultLabel.setBounds(822, 187, 105, 18);
		contentPane.add(resultLabel);
		
		resultLabel1 = new JLabel("Ticket Price:Rs.");
		resultLabel1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		resultLabel1.setBounds(822, 227, 126, 22);
		contentPane.add(resultLabel1);
		
		resultLabel2 = new JLabel("Total Price:Rs.");
		resultLabel2.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		resultLabel2.setBounds(828, 277, 120, 14);
		contentPane.add(resultLabel2);
		
	    btnNewButton_1 = new JButton("Generate Ticket");
	    btnNewButton_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		btnNewButton_1.setBounds(871, 375, 185, 22);
		btnNewButton_1.setEnabled(false);
		contentPane.add(btnNewButton_1);
		
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblNewLabel_3);
		getContentPane().add(lblNewLabel_1 );
		getContentPane().add(date);
		getContentPane().add(comboBox);
		getContentPane().add(comboBox_1);
		getContentPane().add(lblNewLabel_2);
		getContentPane().add(textField);
		getContentPane().add(btnNewButton);
		getContentPane().add(btnNewButton_1);
		
		resultLabel3= new JLabel("New label");
		resultLabel3.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		resultLabel3.setBounds(592, 131, 192, 22);
		contentPane.add(resultLabel3);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_4.setBounds(930, 189, 50, 18);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_5.setBounds(940, 226, 65, 22);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_6.setBounds(940, 272, 65, 22);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton_2 = new JButton("View");
		btnNewButton_2.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		btnNewButton_2.setBounds(632, 436, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		btnNewButton_3.setBounds(874, 440, 89, 23);
		contentPane.add(btnNewButton_3);
		
		int billNumber = getNextBillNumber();
        String currentDate = getCurrentDate();
        
    	if (billNumber != -1) {
            resultLabel3.setText(currentDate+"TN"+billNumber);
        } else {
            resultLabel3.setText("Error fetching the bill number.");
        }
    	btnNewButton_2.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			f=new Frame();
    		}
    	});
		btnNewButton_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {		
				dispose();
				new ConductorFrame();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {			
				btnNewButton_1.setEnabled(true);
				
				String fromStop = (String) comboBox.getSelectedItem();
		        String toStop = (String) comboBox_1.getSelectedItem();
		        
		        
		        int stopCount = countBusStops(fromStop, toStop);
		        
		        // Update the result label based on the count
		        if (stopCount > 0) {
		            lblNewLabel_4.setText(""+ stopCount);
		            calculateTicketPrice();
		        } else {
		            JOptionPane.showMessageDialog(null,"Invalid selection. Ensure 'from' is before 'to'.");
		            btnNewButton_1.setEnabled(false);
		            lblNewLabel_4.setText(" ");
		            lblNewLabel_5.setText(" ");
		            lblNewLabel_6.setText(" ");
		        }
		        int from = comboBox.getSelectedIndex();
		        int to = comboBox_1.getSelectedIndex();

		        // Check if both combo boxes have valid selections
		        if (from > 0 && to > 0) {
		        }
		        else {
		            JOptionPane.showMessageDialog(null, "Please select Destination.");
		            btnNewButton_1.setEnabled(false);
		            lblNewLabel_4.setText(" ");
		            lblNewLabel_5.setText(" ");
		            lblNewLabel_6.setText(" ");
		        }
		    }
		});
		
        btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
		
				String dateTime = date.getText();
				String ns=lblNewLabel_4.getText();
				String amount=lblNewLabel_5.getText();
				String TotalAmount=lblNewLabel_6.getText();
				String ticket=resultLabel3.getText();
				String np =textField.getText();
				if(np.isEmpty())
					np="1";
			    String from = (String) comboBox.getSelectedItem();
			    String to=(String) comboBox_1.getSelectedItem();
			    f1 = new ThirdFrame(np,from,to,dateTime,ns,amount,TotalAmount,ticket);  // Open the second frame
	            f1.setVisible(true);	
	           // addTicket(ticket,dateTime,from,to,np,ns,amount,TotalAmount);
			}
        });
		setVisible(true);
	}
	
	private static int countBusStops(String fromStop, String toStop) {
        // Check if the from and to stops are from different arrays
        if (fromStop != null && toStop != null) {
            // Check if fromStop is in ITEM1 and toStop is in ITEM2
            int fromIndex = getIndex(fromStop, Item1);
            int toIndex = getIndex(toStop, Item2);

            // Calculate the number of stops between them
            if (fromIndex >= 0 && toIndex >= 0) {
                return (toIndex-fromIndex)+1; // Exclude 'from' and 'to' stops
            }
        }
        return -1; // Return -1 for invalid selection
    }

    // Helper method to find the index of a stop in the given array
    private static int getIndex(String stop, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(stop)) {
                return i;
            }
        }
        return -1; // Not found
    }
	 private void calculateTicketPrice() {
		    String fromStop = (String) comboBox.getSelectedItem();
	        String toStop = (String) comboBox_1.getSelectedItem();
	        int numberOfStops = countBusStops(fromStop, toStop);
	        int ticketPrice = 0;

	        if (numberOfStops <= 3) {
	            ticketPrice = 8; // Fixed amount for 6 or more stops
	        } else if (numberOfStops <= 6) {
	            ticketPrice = 12; // Fixed amount for 3 to 5 stops
	        }

	        // Additional charges for every 3 stops beyond 6
	        if (numberOfStops > 6) {
	            int additionalStops = numberOfStops - 6;
	            ticketPrice += (additionalStops / 3) * 4;
	        }
	        int numberOfPersons;
	        String personInput = textField.getText();
            
            if (personInput.isEmpty()) {
                numberOfPersons = 1;  // Default to 1 if no input
            } else {
                try {
                    numberOfPersons = Integer.parseInt(personInput);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,"Invalid number of persons.","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Calculate total amount
            int amount = ticketPrice * numberOfPersons;
	        lblNewLabel_5.setText(""+ticketPrice);
	        lblNewLabel_6.setText(""+amount);
	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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

	    private static String getCurrentDate() {
	        Calendar calendar = Calendar.getInstance();
	        return String.format("%04d-%02d-%02d", 
	                calendar.get(Calendar.YEAR), 
	                calendar.get(Calendar.MONTH) + 1, 
	                calendar.get(Calendar.DAY_OF_MONTH));
	    }
}
