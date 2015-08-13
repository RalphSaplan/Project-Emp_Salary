package assign;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Employee extends JFrame {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	//  Database credentials
	static final String USER = "APPS";
	static final String PASS = "MFG4GUAT";

	private static String getNetpay(String a) {
		
		String ret;
		long netMoney;
		   // JDBC driver name and database URL
		  try{
		   
		 Connection conn = null;
		   Statement stmt = null;
		  
		      //STEP 2: Register JDBC driver
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      String url = "jdbc:oracle:thin:@sc-oradbtest01.gnet.global.vpn:1551:GNETUAT"; 

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(url,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();

				//String sql ="select A.BonusPayment, B.Payrate, C.StartDate, C.EndDate from Salary B, Payroll C, BONUS A where A.Employee_ID=B.Employee_ID  and C.Employee_ID =100";
				String sql="select * from BONUS";
		      ResultSet result = stmt.executeQuery(sql);
		
	
				
				System.out.println(sql);
				
		        
				while(result.next())
				{
					String bon=result.getString(1);
				    int rate=result.getInt(2);
				    Date start= result.getDate(3);
				    Date end=result.getDate(4);
				   
				    long diff = Math.abs(start.getTime() - end.getTime());
				    long diffweek = diff / (24 * 60 * 60 * 1000)*7;
				    
				    netMoney= diffweek*rate+Integer.parseInt(bon);
				    System.out.println(netMoney);
				}
				conn.close();
			
        }
        catch(Exception e)
        {
        
        	System.out.println(e);
        	e.printStackTrace();
        }
		return "abc";     //netMoney	
	}
	
	 public Employee(String name) {
	        super(name);
	        setResizable(false);
	    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private  void createAndShowGUI() {
    	final String output=null;
    	 JPanel basic = new JPanel();
         basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
         add(basic);

         JPanel topPanel = new JPanel(new BorderLayout(0, 0));
         topPanel.setMaximumSize(new Dimension(450, 0));
         JLabel hint = new JLabel("Please Enter Employee ID");
         hint.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
         topPanel.add(hint);

         ImageIcon icon = new ImageIcon("java.png");
         JLabel label = new JLabel(icon);
         label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
         topPanel.add(label, BorderLayout.EAST);

         JSeparator separator = new JSeparator();
         separator.setForeground(Color.gray);

         topPanel.add(separator, BorderLayout.SOUTH);

         basic.add(topPanel);

         JPanel textPanel = new JPanel(new BorderLayout());
         textPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
         JTextField emplid= new JTextField();
         
         JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
         final JButton enter = new JButton("Submit");
         enter.setMnemonic(KeyEvent.VK_N);
         enter.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent event) {
                String input= emplid.getText();
                validate(input);
             //   output= getNetpay(input);
             }

            private void validate(String input) {
                    input=input.trim();
                    if(input.length()>=4)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Employee ID");
                       
                    }
            }
         });
         JButton close = new JButton("Close");
         close.setMnemonic(KeyEvent.VK_C);
         close.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent event) {
                 System.exit(0);
             }
         });
    
         bottom.add(enter);
         bottom.add(close);
       //  basic.add(bottom);
         
         
         
         
         JTextPane pane = new JTextPane();
         pane.setBorder(BorderFactory.createEmptyBorder(120	, 120, 120, 120));
         pane.setContentType("text/html");
         String text = "<p><b>Fetched data will be displayed here</p>"; //output
         pane.setText(text);
         pane.setEditable(false);
         textPanel.add(emplid, BorderLayout.NORTH);
        textPanel.add(bottom,BorderLayout.CENTER);
         textPanel.add(pane, BorderLayout.SOUTH);

         basic.add(textPanel);

         JPanel boxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));

         basic.add(boxPanel);

        

         bottom.setMaximumSize(new Dimension(450, 0));

         setTitle("Employee Salary Management");
         setSize(new Dimension(450, 350));
         setResizable(false);
         setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         setLocationRelativeTo(null);
         setVisible(true);
    }
    
   

	public static void main(String[] args) {
		final Employee obj= new Employee("Employee salary");
		  javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	obj.createAndShowGUI();
	            	
	            }
	        });
	
		
	}


	

}
