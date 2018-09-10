package package_g;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

import java.awt.EventQueue;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Locale;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.util.concurrent.TimeUnit;

/*
 * STR_Calculator:
 * 
 * 
 * 
 */
public class STR_Calculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField stillTimeTextField;
	private JTextField stillLengthTextField;
	private JTextField timeAnswerTextField;
	private JTextField lengthAnswerTextField;
	private JTextField speedTextField;
	
	String sep = System.getProperty("line.separator");
	
	double tStill;
	double t;
	double lStill;
	double l;
	double gamma;
	double vms;
	double vkms;
	double c = 299792.458;
	private JTextField entryTextField;
	private JTextField answerTextField;

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					STR_Calculator frame = new STR_Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public STR_Calculator() {
		setIconImage(new ImageIcon(this.getClass().getResource("/icon_Newton.jpg")).getImage());

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 516);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Menu knapp
		JButton menuButton = new JButton(Language.getString("STR_Calculator.menuButton.text", "Menu")); //$NON-NLS-1$ //$NON-NLS-2$
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "menu");
			}
		});
		menuBar.add(menuButton);
		
		// Kalkylator knapp
		JButton calcButton = new JButton(Language.getString("STR_Calculator.calcButton.text", "Calculator")); //$NON-NLS-1$ //$NON-NLS-2$
		calcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "calc");
			}
		});
		menuBar.add(calcButton);
		
		// Info knapp
		JButton infoButton = new JButton(Language.getString("STR_Calculator.infoButton.text", "Info")); //$NON-NLS-1$ //$NON-NLS-2$
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "info");
			}
		});
		menuBar.add(infoButton);
		
		// Logg knapp
		JButton logButton = new JButton(Language.getString("STR_Calculator.btnLogg.text", "Logg")); //$NON-NLS-1$ //$NON-NLS-2$
		logButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "logScroll");
			}
		});
		menuBar.add(logButton);
		
		JButton btnKonverterare = new JButton(Language.getString("STR_Calculator.btnKonvaterare.text", "Konverterare")); //$NON-NLS-1$ //$NON-NLS-2$
		btnKonverterare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "conv");
			}
		});
		menuBar.add(btnKonverterare);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel menuPanel = new JPanel();
		contentPane.add(menuPanel, "menu");
		
		/*
		 * 
		 * Ska bort
		 */
		JButton btnNewButton = new JButton(Language.getString("STR_Calculator.btnNewButton.text", "New button")); //$NON-NLS-1$ //$NON-NLS-2$
		menuPanel.add(btnNewButton);
		
		JPanel calcPanel = new JPanel();
		contentPane.add(calcPanel, "calc");
		calcPanel.setLayout(null);
		
		/*
		 * Textrutor
		 * Ger meddelande om det �r text i rutorna
		 *
		 *L�ngd och tid
		 * 
		 */
		JLabel timeInvalidLabel = new JLabel(Language.getString("STR_Calculator.timeInvalidLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		timeInvalidLabel.setForeground(Color.RED);
		timeInvalidLabel.setBounds(0, 72, 130, 14);
		calcPanel.add(timeInvalidLabel);
		
		JLabel lengthInvalidLabel = new JLabel(Language.getString("STR_Calculator.lengthInvalidLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		lengthInvalidLabel.setForeground(Color.RED);
		lengthInvalidLabel.setBounds(180, 72, 130, 14);
		calcPanel.add(lengthInvalidLabel);
		
		JLabel speedInvalidLabel = new JLabel(Language.getString("STR_Calculator.speedInvalidLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		speedInvalidLabel.setForeground(Color.RED);
		speedInvalidLabel.setBounds(100, 183, 130, 14);
		calcPanel.add(speedInvalidLabel);
		
		stillTimeTextField = new JTextField();
		stillTimeTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			Double.parseDouble(stillTimeTextField.getText());
			timeInvalidLabel.setText("");
		} catch (NumberFormatException e1) {
			timeInvalidLabel.setText("Endast siffror och punkt");
		}
			}
		});
		
		
		
		stillTimeTextField.setToolTipText(Language.getString("STR_Calculator.stillTimeTextField.toolTipText", "T.ex 5 år")); //$NON-NLS-1$ //$NON-NLS-2$
		stillTimeTextField.setColumns(10);
		stillTimeTextField.setBounds(0, 42, 150, 31);
		calcPanel.add(stillTimeTextField);
		
		JScrollPane logScrollPane = new JScrollPane();
		contentPane.add(logScrollPane, "logScroll");
		
		JPanel logPanel = new JPanel();
		logScrollPane.setViewportView(logPanel);
		logPanel.setLayout(null);
		
		JTextPane textTime0Pane = new JTextPane();
		textTime0Pane.setBounds(0, 25, 126, 425);
		logPanel.add(textTime0Pane);
		
		JTextPane textLength0Pane = new JTextPane();
		textLength0Pane.setBounds(136, 25, 126, 425);
		logPanel.add(textLength0Pane);
		
		JTextPane textSpeedPane = new JTextPane();
		textSpeedPane.setBounds(272, 25, 126, 425);
		logPanel.add(textSpeedPane);
		
		JTextPane textTimePane = new JTextPane();
		textTimePane.setBounds(408, 25, 126, 425);
		logPanel.add(textTimePane);
		
		JTextPane textLengthPane = new JTextPane();
		textLengthPane.setBounds(544, 25, 126, 425);
		logPanel.add(textLengthPane);
		
		
		JButton answerButton = new JButton(Language.getString("STR_Calculator.answerButton.text", "Räkna ut")); //$NON-NLS-1$ //$NON-NLS-2$
		answerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setParameters();
				solveGamma();
				solveTime();
				solveLength();
				showTime();
				showLength();
				logEverything();
			}

			private void setParameters() {
				if (stillTimeTextField.getText().equals("")) {
					tStill = 0;
				} else {
					tStill = Double.parseDouble(stillTimeTextField.getText());
				}
				if (stillLengthTextField.getText().equals("")) {
					lStill = 0;
				} else {
					lStill = Double.parseDouble(stillLengthTextField.getText());
				}
				if (speedTextField.getText().equals("")) {
					vms = 0;
				} else {
					vms = Double.parseDouble(speedTextField.getText());
				}
				
				vkms = vms/1000;

			}

			private void showTime() {
				timeAnswerTextField.setText(t + "");
			}

			private void showLength() {
				lengthAnswerTextField.setText(l + "");
			}
/*
 * R�kna ut:
 * l
 * t
 * gamma
 * 
 */
			private void solveLength() {
				l = lStill / gamma;
			}

			private void solveTime() {
					t = tStill / gamma;
			}

			private void solveGamma() {
				gamma = (1 / (Math.sqrt(1 - (Math.pow(vkms, 2) / Math.pow(c, 2)))));
			}
			
			private void logEverything() {
			    textTime0Pane.setText(textTime0Pane.getText()+sep+tStill);
			    textLength0Pane.setText(textLength0Pane.getText()+sep+lStill);
			    textSpeedPane.setText(textSpeedPane.getText()+sep+vms);
			    textTimePane.setText(textTimePane.getText()+sep+t);
			    textLengthPane.setText(textLengthPane.getText()+sep+l);
			    
			}
		});
		answerButton.setBounds(100, 240, 130, 52);
		calcPanel.add(answerButton);
		
		/*
		 * Utrymme f�r linie som visar minskning
		 * 
		 * 
		 */
		Canvas lineCanvas = new Canvas();
		lineCanvas.setBackground(Color.RED);
		lineCanvas.setBounds(332, 184, 342, 267);
		calcPanel.add(lineCanvas);
		
		stillLengthTextField = new JTextField();
		stillLengthTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			Double.parseDouble(stillLengthTextField.getText());
			lengthInvalidLabel.setText("");
		} catch (NumberFormatException e1) {
			lengthInvalidLabel.setText("Endast siffror och punkt");
			
		}
			}
		});
		stillLengthTextField.setToolTipText(Language.getString("STR_Calculator.stillLengthTextField.toolTipText", "T.ex 30 m")); //$NON-NLS-1$ //$NON-NLS-2$
		stillLengthTextField.setColumns(10);
		stillLengthTextField.setBounds(180, 42, 150, 31);
		calcPanel.add(stillLengthTextField);
		
		timeAnswerTextField = new JTextField();
		timeAnswerTextField.setEditable(false);
		timeAnswerTextField.setColumns(10);
		timeAnswerTextField.setBounds(0, 305, 150, 52);
		calcPanel.add(timeAnswerTextField);
		
		lengthAnswerTextField = new JTextField();
		lengthAnswerTextField.setEditable(false);
		lengthAnswerTextField.setColumns(10);
		lengthAnswerTextField.setBounds(180, 305, 150, 52);
		calcPanel.add(lengthAnswerTextField);
		
		JSlider speedSlider = new JSlider(0, 299792458, 149896229);
		speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");
					
				}
				speedTextField.setText(String.valueOf(speedSlider.getValue()));
			}
		});
		speedSlider.setValue(149896229);
		speedSlider.setMaximum(299792458);
		speedSlider.setBounds(0, 119, 330, 26);
		calcPanel.add(speedSlider);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(Language.getString("STR_Calculator.textArea.text", "t0")); //$NON-NLS-1$ //$NON-NLS-2$
		textArea.setOpaque(false);
		textArea.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		textArea.setBounds(0, 15, 130, 22);
		calcPanel.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setText(Language.getString("STR_Calculator.textArea_1.text", "l0")); //$NON-NLS-1$ //$NON-NLS-2$
		textArea_1.setOpaque(false);
		textArea_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		textArea_1.setBounds(180, 15, 130, 26);
		calcPanel.add(textArea_1);
		
		/*
		 * TODO �ndra km/s till m/s
		 * 
		 */
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setText(Language.getString("STR_Calculator.textArea_2.text", "Objektets hastighet i m/s")); //$NON-NLS-1$ //$NON-NLS-2$
		textArea_2.setOpaque(false);
		textArea_2.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		textArea_2.setBounds(0, 92, 330, 22);
		calcPanel.add(textArea_2);
		
		speedTextField = new JTextField();
		speedTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");
				}
			
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		speedTextField.setToolTipText(Language.getString("STR_Calculator.speedTextField.toolTipText", "T.ex 290000000m/s")); //$NON-NLS-1$ //$NON-NLS-2$
		speedTextField.setText(Language.getString("STR_Calculator.speedTextField.text", "149896229")); //$NON-NLS-1$ //$NON-NLS-2$
		speedTextField.setColumns(15);
		speedTextField.setBounds(125, 156, 80, 25);
		calcPanel.add(speedTextField);
		
		JButton button_n1k = new JButton(Language.getString("STR_Calculator.button_n1.text", "-1k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n1k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-1000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
			
		});
		button_n1k.setMargin(new Insets(0, 0, 0, 0));
		button_n1k.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_n1k.setBounds(100, 156, 25, 25);
		calcPanel.add(button_n1k);
		
		JButton button_n10k = new JButton(Language.getString("STR_Calculator.button_n10.text", "-10k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n10k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-10000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_n10k.setMargin(new Insets(0, 0, 0, 0));
		button_n10k.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_n10k.setBounds(75, 156, 25, 25);
		calcPanel.add(button_n10k);
		
		JButton button_n100k = new JButton(Language.getString("STR_Calculator.button_n100.text", "-100k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n100k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-100000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_n100k.setMargin(new Insets(0, 0, 0, 0));
		button_n100k.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_n100k.setBounds(50, 156, 25, 25);
		calcPanel.add(button_n100k);
		
		JButton button_n1m = new JButton(Language.getString("STR_Calculator.button_n1000.text", "-1m")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n1m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-1000000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_n1m.setMargin(new Insets(0, 0, 0, 0));
		button_n1m.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_n1m.setBounds(25, 156, 25, 25);
		calcPanel.add(button_n1m);
		
		JButton button_n10m = new JButton(Language.getString("STR_Calculator.button_n10000.text", "-10m")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n10m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-10000000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_n10m.setMargin(new Insets(0, 0, 0, 0));
		button_n10m.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_n10m.setBounds(0, 156, 25, 25);
		calcPanel.add(button_n10m);
		
		JButton button_p10m = new JButton(Language.getString("STR_Calculator.button_p10000.text", "+10m")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p10m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+10000000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_p10m.setMargin(new Insets(0, 0, 0, 0));
		button_p10m.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_p10m.setBounds(305, 156, 25, 25);
		calcPanel.add(button_p10m);
		
		JButton button_p1m = new JButton(Language.getString("STR_Calculator.button_p1000.text", "+1m")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p1m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+1000000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_p1m.setMargin(new Insets(0, 0, 0, 0));
		button_p1m.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_p1m.setBounds(280, 156, 25, 25);
		calcPanel.add(button_p1m);
		
		JButton button_p100k = new JButton(Language.getString("STR_Calculator.button_p100.text", "+100k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p100k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+100000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_p100k.setMargin(new Insets(0, 0, 0, 0));
		button_p100k.setFont(new Font("Tahoma", Font.PLAIN, 7));
		button_p100k.setBounds(255, 156, 25, 25);
		calcPanel.add(button_p100k);
		
		JButton button_p10k = new JButton(Language.getString("STR_Calculator.button_p10.text", "+10k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p10k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+10000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_p10k.setMargin(new Insets(0, 0, 0, 0));
		button_p10k.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_p10k.setBounds(230, 156, 25, 25);
		calcPanel.add(button_p10k);
		
		JButton button_p1k = new JButton(Language.getString("STR_Calculator.button_p1.text", "+1k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p1k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+1000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_p1k.setMargin(new Insets(0, 0, 0, 0));
		button_p1k.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_p1k.setBounds(205, 156, 25, 25);
		calcPanel.add(button_p1k);
		
		
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);
		
		JPanel infoPanel = new JPanel();
		contentPane.add(infoPanel, "info");
		infoPanel.setLayout(null);
		
		JTextArea txtrLoremIpsumDolor_1 = new JTextArea();
		txtrLoremIpsumDolor_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtrLoremIpsumDolor_1.setWrapStyleWord(true);
		txtrLoremIpsumDolor_1.setLineWrap(true);
		txtrLoremIpsumDolor_1.setText(Language.getString("STR_Calculator.txtrLoremIpsumDolor_1.text", (String) null)); //$NON-NLS-1$
		txtrLoremIpsumDolor_1.setBounds(10, 54, 335, 360);
		infoPanel.add(txtrLoremIpsumDolor_1);
		
		JTextArea txtrLngdkontraktionFungerarS = new JTextArea();
		txtrLngdkontraktionFungerarS.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtrLngdkontraktionFungerarS.setWrapStyleWord(true);
		txtrLngdkontraktionFungerarS.setLineWrap(true);
		txtrLngdkontraktionFungerarS.setText(Language.getString("STR_Calculator.txtrLngdkontraktionFungerarS.text", "Längdkontraktion fungerar så här blblblblb Lorem ipsum dolor set ametLorem ipsum dolor set ametLorem ipsum dolor set amet")); //$NON-NLS-1$ //$NON-NLS-2$
		txtrLngdkontraktionFungerarS.setBounds(361, 52, 266, 362);
		infoPanel.add(txtrLngdkontraktionFungerarS);
		
		
		
		
		
		JLabel logTime0Label = new JLabel(Language.getString("STR_Calculator.lblNewLabel.text", (String) null)); //$NON-NLS-1$
		logTime0Label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		logTime0Label.setBounds(0, 0, 126, 25);
		logPanel.add(logTime0Label);
		
		JLabel logLength0Label = new JLabel(Language.getString("STR_Calculator.logLength0Label.text", "Objektets l\u00E4ngd")); //$NON-NLS-1$ //$NON-NLS-2$
		logLength0Label.setBounds(136, 0, 126, 25);
		logPanel.add(logLength0Label);
		
		JLabel logSpeedLabel = new JLabel(Language.getString("STR_Calculator.logSpeedLabel.text", "Objektets hastighet")); //$NON-NLS-1$ //$NON-NLS-2$
		logSpeedLabel.setBounds(272, 0, 126, 25);
		logPanel.add(logSpeedLabel);
		
		JLabel logTimeLabel = new JLabel(Language.getString("STR_Calculator.logTimeLabel.text", "Resultat - tid")); //$NON-NLS-1$ //$NON-NLS-2$
		logTimeLabel.setBounds(408, 0, 126, 25);
		logPanel.add(logTimeLabel);
		
		JLabel logLengthLabel = new JLabel(Language.getString("STR_Calculator.logLengthLabel.text", "Resultat - l\u00E4ngd")); //$NON-NLS-1$ //$NON-NLS-2$
		logLengthLabel.setBounds(544, 0, 126, 25);
		logPanel.add(logLengthLabel);
		
		JPanel convPanel = new JPanel();
		contentPane.add(convPanel, "conv");
		convPanel.setLayout(null);
		
		String[] converterOptions = {"Nanoseconds", "Microseconds", "Milliseconds", "Seconds", "Minutes", "Hours", "Days", "m/s", "km/s", "km/h"};
		
		JComboBox<String> entryBox = new JComboBox<>();
		entryBox.setMaximumRowCount(10);
		entryBox.setModel(new DefaultComboBoxModel<>(converterOptions));
		entryBox.setBounds(513, 11, 152, 57);
		convPanel.add(entryBox);
		
		JComboBox<String> answerBox = new JComboBox<>();
		answerBox.setModel(new DefaultComboBoxModel<>(converterOptions));
		answerBox.setMaximumRowCount(10);
		answerBox.setBounds(513, 112, 152, 57);
		convPanel.add(answerBox);
		
		JLabel warningBoxLabel = new JLabel(Language.getString("STR_Calculator.lblNewLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		warningBoxLabel.setForeground(Color.RED);
		warningBoxLabel.setBounds(513, 79, 152, 22);
		convPanel.add(warningBoxLabel);
		
		answerTextField = new JTextField();
		answerTextField.setColumns(10);
		answerTextField.setBounds(10, 112, 493, 57);
		convPanel.add(answerTextField);
		
		entryTextField = new JTextField();
		entryTextField.setBounds(10, 11, 493, 57);
		convPanel.add(entryTextField);
		entryTextField.setColumns(10);
		
		//TODO försök att fixa detta, själva konverteraren
		
		/*JButton btnNewButton_1 = new JButton(Language.getString("STR_Calculator.btnNewButton_1.text", "Convert")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (entryBox<>.getModel().equals("Nanoseconds")) {
					System.out.println("Vi har nano");
					if (answerBox.getModel().equals("Nanoseconds")) {
						warningBoxLabel.setText("Välj olika värdetyper!");
					} else if (answerBox.getModel().equals("Microseconds")) {
						answerTextField.setText((Double.parseDouble(entryTextField.getText())/1000) + "");
					} else if (answerBox.getModel().equals("Milliseconds")) {
						answerTextField.setText((Double.parseDouble(entryTextField.getText())/1000000) + "");
					} else if (answerBox.getModel().equals("Seconds")) {
						answerTextField.setText((Double.parseDouble(entryTextField.getText())/1000000000) + "");
					} else if (answerBox.getModel().equals("Minutes")) {
						answerTextField.setText((Double.parseDouble(entryTextField.getText())/1000000000/1000) + "");
					} else if (answerBox.getModel().equals("Hours")) {
						answerTextField.setText((Double.parseDouble(entryTextField.getText())/1000000000/60000) + "");
					} else if (answerBox.getModel().equals("Days")) {
						answerTextField.setText((Double.parseDouble(entryTextField.getText())/1000000000/1440000) + "");
					} else if (answerBox.getModel().equals("m/s")||answerBox.getModel().equals("km/s")||answerBox.getModel().equals("km/h")) {
						warningBoxLabel.setText("Välj värdetyper av samma typ");
					}
				}
			}
		});
		btnNewButton_1.setBounds(212, 79, 89, 23);
		convPanel.add(btnNewButton_1);*/

	}}
