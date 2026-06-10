import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class FirstFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JFrame conductor,admin;
	JLabel lblNewLabel;
	JButton btnNewButton,btnNewButton_1 ;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstFrame frame = new FirstFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FirstFrame() {
		setTitle("Bus Ticket Generating System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1500,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.PINK);
		
		lblNewLabel = new JLabel("NPJ BUS TRANSPORT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(533, 71, 453, 87);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("Conductor");
		btnNewButton.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		btnNewButton.setBounds(584, 230, 188, 50);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Admin");
		btnNewButton_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 26));
		btnNewButton_1.setBounds(584, 365, 188, 50);
		contentPane.add(btnNewButton_1);
		setVisible(true);
		getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conductor = new ConductorFrame(); 
                // Open the second frame
                dispose();
            }
        });
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 admin = new AdminFrame(); 
                // Open the second frame
                dispose();
            }
        });
    }
}
