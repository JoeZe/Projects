import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
/**
 * Create a GUI to display all the hot stock information
 * @author Joe
 *
 */
public class MainWindow extends JFrame {
	private JPanel contentPane;
	private JTextField companyNamelText;
	private JTable table_1;
	private JTextField priceText;
	private JTextField changeText;
	private JTextField changePctText;
	private JTextField yearToDateText;
	public JLabel lblChart;
	public static String symbol;
	public static JPanel panel_2;
	private JTextField SearchText;
	private static DefaultTableModel model;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MainWindow() throws SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1332, 797);
		setVisible(true);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		menuBar.setMargin(new Insets(2, 0, 2, 0));
		menuBar.setBorderPainted(false);
		menuBar.setBackground(SystemColor.activeCaption);
		setJMenuBar(menuBar);
		setResizable(false);
		JMenu mnFile = new JMenu("File    ");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Offline queries");
		mnFile.add(mntmOpen);
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OfflineQueriesFrame.main(null);
			}
		});
		
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmQuiz = new JMenuItem("Quiz");
		mntmQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuiz);
		
		JMenu mnSetting = new JMenu("Setting");
		menuBar.add(mnSetting);
		
		JMenuItem mntmSendReport = new JMenuItem("Send report");
		mnSetting.add(mntmSendReport);
		
		JMenuItem mntmRecovery = new JMenuItem("Recovery");
		mntmRecovery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				try {
					TranscationLog.recover();
					HashTable.retrieve(model);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnSetting.add(mntmRecovery);
		mntmSendReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendReportFrame.main(null);
			}
		});
		JSeparator separator = new JSeparator();
		menuBar.add(separator);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));			
		setContentPane(contentPane);
				
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(10, 4));
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel.setAutoscrolls(true);
		panel.setPreferredSize(new Dimension(10, 6));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel jLabel1=new JLabel("Stock Screener                      ");
		JLabel jLabel2=new JLabel("                  Welcome!                 ");
		JLabel jLabel3=new JLabel("          User:"+ SaveUserInfo.getUserName()+"       ");
		DateFormat df=DateFormat.getDateInstance(DateFormat.LONG);
		String dateString=df.format(new Date());
		JLabel jLabel4=new JLabel("                         Date: " +dateString);
		df=DateFormat.getTimeInstance(DateFormat.MEDIUM);
		dateString=df.format(new Date());
		JLabel jLabel5=new JLabel("                         Time: " +dateString);		
		jLabel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JLabel jLabel6=new JLabel("Account Type: "+ SaveUserInfo.getAccountType());
		jLabel6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		jLabel1.setSize(300, 100);
		jLabel2.setBounds(200,545, 130, 20);
		jLabel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jLabel3.setBounds(400,545, 130, 20);
		jLabel3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jLabel4.setBounds(590,545, 160, 20);
		jLabel4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jLabel5.setBounds(860,545, 130, 20);
		jLabel5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setLayout(new GridLayout(0, 6, 0, 0));
		panel.add(jLabel1);
		panel.add(jLabel2);
		panel.add(jLabel3);
		panel.add(jLabel6);
		panel.add(jLabel4);
		panel.add(jLabel5);
				
		JPanel panel_1 = new JPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		//captures the button name that is pressed by the user and execute the event to update the selected row
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model=(DefaultTableModel) table_1.getModel();
				int selectedRowIndex=table_1.getSelectedRow();
				model.fireTableDataChanged();
				if(selectedRowIndex>=0){	
					try {
						DataStorageController.updateSelectedDS(selectedRowIndex, model);//call this method to remove the row from data storage
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					System.out.println("The selected row index is less than 0!");;
				}	
			}
		});
		
		btnUpdate.setMinimumSize(new Dimension(61, 23));
		btnUpdate.setMaximumSize(new Dimension(61, 23));
		
		//captures the button name that is pressed by the user and execute the event to delete the selected row
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model=(DefaultTableModel) table_1.getModel();
				int selectedRowIndex=table_1.getSelectedRow();
				if(selectedRowIndex>=0){
					String select=(String) model.getValueAt(selectedRowIndex, 0);//get the selected row's first column string
					model.removeRow(selectedRowIndex);//call this method to remove the row from jtable
					try {
						DataStorageController.deleteSelectedDS(select);//call this method to remove the row from data storage
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("The selected row index is less than 0!");;
				}
			}
		});
		btnDelete.setMinimumSize(new Dimension(61, 23));
		btnDelete.setMaximumSize(new Dimension(61, 23));
		
		JSeparator separator_5 = new JSeparator();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1313, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1306, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(441)
					.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(258)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(442, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1306, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(separator_5, GroupLayout.DEFAULT_SIZE, 1316, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("List of stocks", null, scrollPane, null);
		//create a new table in the main frame
		table_1 = new JTable();
		table_1.setCellSelectionEnabled(true);
		table_1.setColumnSelectionAllowed(true);
		//create the model for the table and predefine the column name
		model=new DefaultTableModel(new String[] {
				"Symbol", "Company name", "Price", "Change", "% Change", "Year-to-Date", "URL", "File name","location"
			},0);
		
		table_1.setModel(model);
		if(DataStorageController.getStorage().equals("db")){
			Database.retrieve(model);	//retrieve data from database
		}else if(DataStorageController.getStorage().equals("ht")){
			HashTable.retrieve(model);//retrieve data from hashtable
		}else if(DataStorageController.getStorage().equals("bo") ){
			HashTable.retrieve(model);//retrieve data from hashtable
			Database.retrieve(model);	//retrieve data from database
		}else
			System.out.println("Invalid data storage");
		
		
		scrollPane.setViewportView(table_1);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Stock information", null, panel_2, null);
		panel_2.setLayout(null);
		
		lblChart = new JLabel("chart");
		setIcon();//get the stock chart and put it on the frame

		lblChart.setBounds(309, 214, 622, 253);
		panel_2.add(lblChart);
		
		companyNamelText = new JTextField();
		companyNamelText.setFont(new Font("Tahoma", Font.BOLD, 15));
		companyNamelText.setBounds(309, 42, 314, 30);
		panel_2.add(companyNamelText);
		companyNamelText.setColumns(10);
		
		priceText = new JTextField();
		priceText.setFont(new Font("Tahoma", Font.BOLD, 15));
		priceText.setBounds(309, 128, 107, 30);
		panel_2.add(priceText);
		priceText.setColumns(10);
		
		changeText = new JTextField();
		changeText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		changeText.setBounds(576, 128, 42, 30);
		panel_2.add(changeText);
		changeText.setColumns(10);
		
		changePctText = new JTextField();
		changePctText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		changePctText.setBounds(630, 128, 53, 30);
		panel_2.add(changePctText);
		changePctText.setColumns(10);
		
		yearToDateText = new JTextField();
		yearToDateText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yearToDateText.setBounds(830, 128, 100, 30);
		panel_2.add(yearToDateText);
		yearToDateText.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(309, 185, 622, 2);
		panel_2.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(309, 83, 622, 2);
		panel_2.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(309, 473, 622, 2);
		panel_2.add(separator_4);
		
		JLabel label = new JLabel("/");
		label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label.setBounds(621, 128, 11, 30);
		panel_2.add(label);
		
		JLabel lblYeartodate = new JLabel("Year-to-Date");
		lblYeartodate.setBounds(830, 160, 74, 14);
		panel_2.add(lblYeartodate);
		
		JLabel lblTodaysChange = new JLabel("Today's Change");
		lblTodaysChange.setBounds(577, 160, 95, 14);
		panel_2.add(lblTodaysChange);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(309, 160, 46, 14);
		panel_2.add(lblPrice);
		
		SearchText = new JTextField();
		SearchText.setColumns(10);
		
		JComboBox comboBox = new JComboBox(new String[]{"Symbol"});
			
		JButton btnSearch = new JButton("Search");
		//captures the button name that is pressed by the user and execute the event to perform a search in the data storage
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if(comboBox.getSelectedItem().toString().equals("Symbol")){
		
					if(DataStorageController.getStorage().equals("db")){//if user chose database as data storage
						try {
							WebpageReaderWithAgent.secondUrlParser(SearchText.getText(), Database.getURL(SearchText.getText()));//Extracting data from a stock that the user search for						
							displayDBInfor(SearchText.getText());//call this method to display the user queries's data on the GUI						
							Database.retrieve(model);//retrieve data from database and print display on the jtable						
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println("Error for searching! Check your spelling.");
						}
					}else if(DataStorageController.getStorage().equals("ht")){//if user chose hashtable as data storage
						try {
							WebpageReaderWithAgent.secondUrlParser(SearchText.getText(), HashTable.getURL(SearchText.getText()));//Extracting data from a stock that the user search for	
							displayHTInfor(SearchText.getText());//call this method to display the user queries's data on the GUI
							HashTable.retrieve(model);//retrieve data from hashtable and print display on the jtable
						} catch (Exception e1) {
							System.out.println("Error for searching! Check your spelling.");
							e1.printStackTrace();
						}
					}else{//if user chose database  and hashtable as data storage
						try {
							WebpageReaderWithAgent.secondUrlParser(SearchText.getText(), HashTable.getURL(SearchText.getText()));//Extracting data from a stock that the user search for
							WebpageReaderWithAgent.secondUrlParser(SearchText.getText(), Database.getURL(SearchText.getText()));//Extracting data from a stock that the user search for
							displayHTInfor(SearchText.getText());//call this method to display the user queries's data on the GUI										
							displayDBInfor(SearchText.getText());//call this method to display the user queries's data on the GUI
							Database.retrieve(model);//retrieve data from database and print display on the jtable
							HashTable.retrieve(model);//retrieve data from hashtable and print display on the jtable
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
							System.out.println("Error for searching! Check your spelling.");
						}
						
					}
					setIcon();//display the stock chart
					tabbedPane.setSelectedIndex(1);
				}
			}

		});
		
		JLabel lblSearchBy = new JLabel("Search by:");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(209)
					.addComponent(lblSearchBy)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SearchText, GroupLayout.PREFERRED_SIZE, 693, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnSearch)
					.addContainerGap(177, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(SearchText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSearchBy))
					.addGap(24))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		
	}//MainWindow

	/**
	 * this method is used to get the the stock symbol's information from the database
	 * @param symbol of the stock
	 */
	protected void displayDBInfor(String symbol) {
		Database.getInfor(symbol, companyNamelText, priceText, changeText, changePctText, yearToDateText);
	}//displayDBInfor

	/**
	 * this method is used to get the the stock symbol's information from the hashtable
	 * @param symbol of the stock
	 */
	protected void displayHTInfor(String symbol) {
		HashTable.getInfor(symbol, companyNamelText, priceText, changeText, changePctText, yearToDateText);	
	}//displayHTInfor
	
	/**
	 * add the stock chart on the GUI on a specific location
	 */
	public void setIcon() {
		String dir = System.getProperty("user.dir");//get the current directory in string
		lblChart.setIcon(new ImageIcon(dir+"/"+symbol+".jpg"));
		lblChart.setBounds(309, 214, 628, 253);
		panel_2.add(lblChart);		
	}//setIcon

	/**
	 * set the symbol
	 * @param s
	 */
	public static void setSymbol(String s) {
		symbol=s;		
	}//setSymbol
	
	/**
	 * get model to set the table
	 * @return DefaultTableModel
	 */
	public DefaultTableModel getModel(){
		return model;
	}//getModel
	
}//MainWindow
