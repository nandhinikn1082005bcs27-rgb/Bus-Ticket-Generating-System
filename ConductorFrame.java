import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConductorFrame extends FirstFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField1;
	private JFrame Frame;
	
	private String userRole = "";
    private String expectedRole = "";
    
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    private JPasswordField passwordField;
	
	public ConductorFrame() {
		setTitle("Bus Ticket Generating System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 1500, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.PINK);
		JLabel lblNewLabel = new JLabel(" Username:(number)");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel.setBounds(544, 187, 190, 23);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		textField.setBounds(781, 191, 140, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:(letter&umber)");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(544, 270, 205, 25);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		btnNewButton.setBounds(594, 354, 89, 23);
		contentPane.add(btnNewButton);
		
		passwordField1 = new JPasswordField();
		passwordField1.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 26));
		passwordField1.setBounds(781, 275, 140, 20);
		contentPane.add(passwordField1);
		
		JLabel lblNewLabel_2 = new JLabel("   Conductor Login");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(680, 88, 256, 25);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		btnNewButton_1.setBounds(801, 357, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	new FirstFrame();
            }
		});
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String userId =textField.getText();
            	String password = new String(passwordField1.getPassword());
            	if (userId.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, 
                        "Both username and password must be entered!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            	}
            	else if (validateCredentials(userId, password)) {
                    Frame = new MyFrame();
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Login. Please try again.");
                }
            }
        });
	}
	private boolean validateCredentials(String userId, String password) {
        boolean isValid = false;
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "a1b2c3d4");
             PreparedStatement stmt = conn.prepareStatement("SELECT role FROM staff WHERE userno = ? AND password = ?")) {
			stmt.setString(1,userId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userRole = rs.getString("role");
                expectedRole ="conductor";
				isValid = userRole.equals(expectedRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
