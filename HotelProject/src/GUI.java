import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import com.toedter.calendar.JCalendar;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFormattedTextField;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		setTitle("EasyHotel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,500, 600);
		contentPane = new JPanel();

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{293, 293, 0};
		gbl_contentPane.rowHeights = new int[]{152, 152, 152, 152, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel cityLabel = new JLabel("City:");
		cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_cityLabel = new GridBagConstraints();
		gbc_cityLabel.fill = GridBagConstraints.VERTICAL;
		gbc_cityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cityLabel.gridx = 0;
		gbc_cityLabel.gridy = 0;
		contentPane.add(cityLabel, gbc_cityLabel);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextField.gridx = 1;
		gbc_formattedTextField.gridy = 0;
		contentPane.add(formattedTextField, gbc_formattedTextField);

		JLabel fromDateLabel = new JLabel("From Date:");
		fromDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_fromDateLabel = new GridBagConstraints();
		gbc_fromDateLabel.fill = GridBagConstraints.BOTH;
		gbc_fromDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fromDateLabel.gridx = 0;
		gbc_fromDateLabel.gridy = 1;
		contentPane.add(fromDateLabel, gbc_fromDateLabel);

		JCalendar calendar_1 = new JCalendar();
		GridBagConstraints gbc_calendar_1 = new GridBagConstraints();
		gbc_calendar_1.fill = GridBagConstraints.BOTH;
		gbc_calendar_1.insets = new Insets(0, 0, 5, 0);
		gbc_calendar_1.gridx = 1;
		gbc_calendar_1.gridy = 1;
		contentPane.add(calendar_1, gbc_calendar_1);

		JLabel toDateLabel = new JLabel("To Date:");
		toDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_toDateLabel = new GridBagConstraints();
		gbc_toDateLabel.fill = GridBagConstraints.BOTH;
		gbc_toDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_toDateLabel.gridx = 0;
		gbc_toDateLabel.gridy = 2;
		contentPane.add(toDateLabel, gbc_toDateLabel);

		JCalendar calendar = new JCalendar();
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.fill = GridBagConstraints.BOTH;
		gbc_calendar.insets = new Insets(0, 0, 5, 0);
		gbc_calendar.gridx = 1;
		gbc_calendar.gridy = 2;
		contentPane.add(calendar, gbc_calendar);


		JButton searchButton = new JButton("Search");
		searchButton.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.gridwidth = 2;
		gbc_searchButton.gridx = 0;
		gbc_searchButton.gridy = 3;
		contentPane.add(searchButton, gbc_searchButton);
	}
}
