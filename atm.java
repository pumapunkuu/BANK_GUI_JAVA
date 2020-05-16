import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;

import java.awt.Font;

import java.awt.SystemColor;
import java.awt.Color;
import java.io.*;
import java.util.Arrays;


public class atm extends JFrame {

	private JPanel contentPane;
	private JPasswordField user_password;
	private JButton password_Conf;
	private JPanel password;
	private JPanel main_screen;
	private JLabel line01;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton but_Transfer;
	private JPanel acc_infs;
	private JPanel depo_Money;
	private JPanel w_Money;
	private JPanel transfer_Window;
	
	private String data_password;
	private double data_Balance;
	private String data_name_Surname;
	private String data_Account_Number;
	private int    data_i;
	
	private JLabel lblNewLabel;
	private JButton btnNewButton_2;
	private JButton btnNewButton_5;
	private JPanel password_changer;
	private JPasswordField old_password_field;
	private JPasswordField new_password_field;
	private JPasswordField field_conf_new_pass;
	private JLabel lbl_enter_old_pass;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JLabel lblname_Surname;
	private JLabel lbl_ac_no;
	private JLabel lblBalance;
	private JLabel label_name_Surname;
	private JLabel label_Account_Number;
	private JLabel label_Balance;
	private JTextField money_withdraw;
	private JTextField deposito_send;
	private JButton back_Button;
	private JLabel lblNewLabel_6;
	private JTextField no_card_text;
	private JButton btn_ok_001;
	private JLabel lbl_transfer_amount;
	private JTextField transfer_amount;
	private JButton btn_ok_002;
	private JButton btnNewButton_9;
	private JLabel w_Money_Balance;
	private JLabel d_Money_Balance;
	private JButton btn_zuruck;
	
	private static final String __filename__ = "veri.txt";
	
	private user[] users;
	private int user_Number;
	
	private JTextField txtAccount_Number;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					
					atm frame = new atm();
					frame.setVisible(true);
					
					frame.starting();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int read_from_file()
	{
		Arrays.fill(users, null);
		user_Number = 0;
		try{

	        File file = new File(__filename__);

	        if (!file.exists()) {
	            //return 0;
	        	file.createNewFile();
	        }

	        FileReader _Reader = new FileReader(file);

	        BufferedReader _Reader2 = new BufferedReader(_Reader);
	        
	        String row;
	        
	        
	        
	        int i=0;
	        int j=0;
	        
	        users[0] = new user();
	        
	        while ( true )
	        {
	        	
	        	row = _Reader2.readLine();

	        	if( row == null || row.equals("+") )
	        	{
	        		break;
	        	}
	        	
	        	
	        	
	        	if( row.equals("*")  )
	        	{

	        		i++;
	        		j=0;
	        		
	        		users[i] = new user();
	        		
	        	}
	        	else
	        	{
	        		if( j == 0 )
	        		{
	        			users[i].name_Surname = row;
	        		}
	        		else if(j == 1)
	        		{
	        			users[i].password = row;
	        		}
	        		else if(j == 2)
	        		{
	        			users[i].Balance = Double.parseDouble(row);
	        		}
	        		else if(j == 3)
	        		{
	        			users[i].Account_Number = row;
	        		}
	        		
	        		j++;
	        	}
	        	
	    	}
	        
	        user_Number = i;
	        
	        _Reader2.close();
	        
	        JOptionPane.showMessageDialog(null, users[0].name_Surname+" "+users[1].name_Surname , "USERS" , JOptionPane.INFORMATION_MESSAGE);
	        
	        
	    }
	    catch (Exception Missed){
	    	Missed.printStackTrace();
	    	
	        return 0;
	    }
		
		return 1;
	}
	
	public int writeTOFile()
	{
		users[data_i].Balance = data_Balance;
		users[data_i].name_Surname = data_name_Surname;
		users[data_i].Account_Number = data_Account_Number;
		users[data_i].password = data_password;
		
		try{
	        File file = new File(__filename__);
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        FileWriter writer = new FileWriter(file,false);
	        BufferedWriter writer2 = new BufferedWriter(writer);
	        
	        for(int i=0; i < user_Number; i++)
	        {
	        	writer2.write(users[i].name_Surname);
	        	writer2.newLine();
	        	writer2.write(users[i].password);
	        	writer2.newLine();
	        	writer2.write( Double.toString( users[i].Balance ) );
	        	writer2.newLine();
	        	writer2.write(users[i].Account_Number);
	        	writer2.newLine();
	        	writer2.write("*");
	        	writer2.newLine();
	        }
	        
	        writer2.write("+");
	        
	        
	        writer2.close();
	        
	    }
	    catch (Exception Missed){
	        return 0;
	    }
		
		return 1;
	}

	/**
	 * Create the frame.
	 */
	public atm() {
		
		users = new user[500];
		
		if( user_ready() == 0 )
		{
			JOptionPane.showMessageDialog(null, "User Informations is not ready!", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		password = new JPanel();
		password.setForeground(Color.BLACK);
		contentPane.add(password, "name_29516457095479");
		 
		
		password_Conf = new JButton("OK");
		password_Conf.setBounds(169, 123, 89, 23);
		password_Conf.setForeground(SystemColor.desktop);
		password_Conf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				
				String ac_num = new String( txtAccount_Number.getText() );
				String pass = new String( user_password.getPassword() );
				int ok=0;
				for(int i=0; i < user_Number; i++)
				{
					if( users[i].Account_Number.equals(ac_num) && users[i].password.equals(pass)  )
					{
						data_password = users[i].password;
						data_Balance = users[i].Balance;
						data_Account_Number = users[i].Account_Number;
						data_name_Surname = users[i].name_Surname;
						data_i = i;
						
						ok = 1;
						break;
					}
				}
				
				if( ok == 1 )
				{
					password.setVisible(false);
					main_screen.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Wrong Password", "ERROR", JOptionPane.ERROR_MESSAGE);
					user_password.setText("");
				}
			}
		});
		password.setLayout(null);
		password.add(password_Conf);
		
		user_password = new JPasswordField();
		user_password.setBounds(94, 80, 236, 20);
		user_password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if( arg0.getKeyCode() == KeyEvent.VK_ENTER )
				{
					password_Conf.doClick();
				}
			}
		});
		password.add(user_password);
		
		line01 = new JLabel("Enter your account number and Password");
		line01.setBounds(0, 25, 424, 14);
		line01.setHorizontalAlignment(SwingConstants.CENTER);
		line01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Enter your password ", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		password.add(line01);
		
		lblNewLabel = new JLabel("Welcome to the Bank");
		lblNewLabel.setBounds(0, 0, 424, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		password.add(lblNewLabel);
		
		JLabel lblNewLabel_8 = new JLabel("              BANK ");
		lblNewLabel_8.setBounds(114, 141, 181, 75);
		lblNewLabel_8.setFont(new Font("Yu Mincho Demibold", Font.PLAIN, 17));
		password.add(lblNewLabel_8);
		
		txtAccount_Number = new JTextField();
		txtAccount_Number.setBounds(94, 45, 236, 22);
		password.add(txtAccount_Number);
		txtAccount_Number.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Account Number");
		lblNewLabel_9.setBounds(10, 48, 72, 16);
		password.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Password");
		lblNewLabel_10.setBounds(20, 82, 56, 16);
		password.add(lblNewLabel_10);
		
		main_screen = new JPanel();
		contentPane.add(main_screen, "name_29516465052206");
		main_screen.setLayout(null);
		
		btnNewButton = new JButton("Account Info's\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				label_name_Surname.setText(data_name_Surname);
				label_Account_Number.setText(data_Account_Number);
				
				label_Balance.setText(Double.toString(data_Balance));
				
				
				
				main_screen.setVisible(false);
				acc_infs.setVisible(true);
			}
		});
		btnNewButton.setBounds(0, 64, 125, 23);
		main_screen.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Withdraw Money");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				w_Money_Balance.setText(Double.toString(data_Balance));
				main_screen.setVisible(false);
				w_Money.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(0, 128, 125, 23);
		main_screen.add(btnNewButton_1);
		
		btnNewButton_3 = new JButton("Deposit Amount");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				main_screen.setVisible(false);
				depo_Money.setVisible(true);
				d_Money_Balance.setText( Double.toString(data_Balance) );
			}
		});
		btnNewButton_3.setBounds(299, 128, 125, 23);
		main_screen.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("\u00C7\u0131k\u0131\u015F");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_4.setBounds(148, 201, 125, 23);
		main_screen.add(btnNewButton_4);
		
		but_Transfer = new JButton("Transfer");
		but_Transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_screen.setVisible(false);
				transfer_Window.setVisible(true);
			}
		});
		but_Transfer.setBounds(299, 64, 125, 23);
		main_screen.add(but_Transfer);
		
		acc_infs = new JPanel();
		contentPane.add(acc_infs, "name_30701921819582");
		acc_infs.setLayout(null);
		
		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acc_infs.setVisible(false);
				main_screen.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(226, 204, 125, 23);
		acc_infs.add(btnNewButton_2);
		
		btnNewButton_5 = new JButton("Change Password");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acc_infs.setVisible(false);
				password_changer.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(38, 204, 142, 23);
		acc_infs.add(btnNewButton_5);
		
		lblname_Surname = new JLabel("Name-S_Name");
		lblname_Surname.setBounds(10, 23, 113, 14);
		acc_infs.add(lblname_Surname);
	
		
		lbl_ac_no = new JLabel("Account No");
		lbl_ac_no.setBounds(10, 48, 113, 14);
		acc_infs.add(lbl_ac_no);
		
		lblBalance = new JLabel("Balance");
		lblBalance.setBounds(10, 73, 113, 14);
		acc_infs.add(lblBalance);
		
		label_name_Surname = new JLabel( ": " + data_name_Surname );
		label_name_Surname.setBounds(154, 23, 260, 14);
		acc_infs.add(label_name_Surname);
		
		label_Account_Number = new JLabel( ": " + data_Account_Number );
		label_Account_Number.setBounds(154, 48, 260, 14);
		acc_infs.add(label_Account_Number);
		
		label_Balance = new JLabel( ": " + String.valueOf(data_Balance) );
		label_Balance.setBounds(154, 73, 260, 14);
		acc_infs.add(label_Balance);
		
		depo_Money = new JPanel();
		contentPane.add(depo_Money, "name_30747465011681");
		depo_Money.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Money Amount");
		lblNewLabel_5.setBounds(12, 33, 139, 16);
		depo_Money.add(lblNewLabel_5);
		
		deposito_send = new JTextField();
		deposito_send.setBounds(215, 30, 116, 22);
		depo_Money.add(deposito_send);
		deposito_send.setColumns(10);
		
		JButton ok_Buton = new JButton("OKAY");
		ok_Buton.setBounds(225, 65, 97, 25);
		ok_Buton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value1 = Double.parseDouble( deposito_send.getText() );
				if(value1>10)
				{
				data_Balance += value1;
				d_Money_Balance.setText( Double.toString(data_Balance) );
				JOptionPane.showMessageDialog(null, "To your account "+Double.toString(value1)+"is deposited", "Perfect!", JOptionPane.INFORMATION_MESSAGE);
				deposito_send.setText("");
				back_Button.doClick();
				writeTOFile();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Amount is not enough", "ERROR", JOptionPane.ERROR_MESSAGE);
					deposito_send.setText("");
				}
				}
			}
		
		
				);
		depo_Money.add(ok_Buton);
		
		back_Button = new JButton("BACK");
		back_Button.setBounds(225, 176, 97, 25);
		back_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depo_Money.setVisible(false);
				main_screen.setVisible(true);
			}
		});
		depo_Money.add(back_Button);
		
		JLabel lblNewLabel_7 = new JLabel("Balance");
		lblNewLabel_7.setBounds(12, 145, 91, 16);
		depo_Money.add(lblNewLabel_7);
		
		d_Money_Balance = new JLabel(Double.toString(data_Balance));
		d_Money_Balance.setBounds(224, 145, 56, 16);
		depo_Money.add(d_Money_Balance);
		
		w_Money = new JPanel();
		contentPane.add(w_Money, "name_30759846069170");
		w_Money.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Amount of money");
		lblNewLabel_3.setBounds(12, 25, 157, 16);
		w_Money.add(lblNewLabel_3);
		
		money_withdraw = new JTextField();
		money_withdraw.setBounds(181, 22, 116, 22);
		w_Money.add(money_withdraw);
		money_withdraw.setColumns(10);
		
		JButton btnNewButton_8 = new JButton("OK");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double value1 = Double.parseDouble( money_withdraw.getText() );
			
				if( value1 > 0 && value1 <= data_Balance ) 
				{
					data_Balance -= value1;
					w_Money_Balance.setText( Double.toString(data_Balance) );
					JOptionPane.showMessageDialog(null, "From your account "+Double.toString(value1)+"is withdrawn", "Perfect!", JOptionPane.INFORMATION_MESSAGE);
					money_withdraw.setText("");
					btn_zuruck.doClick();

					writeTOFile();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Given Amount is Wrong", "Error", JOptionPane.ERROR_MESSAGE);
					money_withdraw.setText("");
				}
			}
		});
		btnNewButton_8.setBounds(191, 57, 97, 25);
		w_Money.add(btnNewButton_8);
		
		JLabel lblNewLabel_4 = new JLabel("Last Balance:");
		lblNewLabel_4.setBounds(12, 140, 157, 16);
		w_Money.add(lblNewLabel_4);
		
		btn_zuruck = new JButton("BACK");
		btn_zuruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				w_Money.setVisible(false);
				main_screen.setVisible(true);
			}
		});
		btn_zuruck.setBounds(200, 205, 97, 25);
		w_Money.add(btn_zuruck);
		
		w_Money_Balance = new JLabel( Double.toString( data_Balance ) ); 
		w_Money_Balance.setBounds(181, 141, 116, 14);
		w_Money.add(w_Money_Balance);
		
		transfer_Window = new JPanel();
		contentPane.add(transfer_Window, "name_30803696805931");
		transfer_Window.setLayout(null);
		
		lblNewLabel_6 = new JLabel("Enter your card no:");
		lblNewLabel_6.setBounds(12, 49, 148, 16);
		transfer_Window.add(lblNewLabel_6);
		
		no_card_text = new JTextField();
		no_card_text.setBounds(202, 46, 116, 22);
		transfer_Window.add(no_card_text);
		no_card_text.setColumns(10);
		
		btn_ok_001 = new JButton("OK");
		btn_ok_001.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btn_ok_001.setBounds(212, 93, 97, 25);
		transfer_Window.add(btn_ok_001);
		
		lbl_transfer_amount = new JLabel("Transfer Amount");
		lbl_transfer_amount.setBounds(12, 169, 97, 16);
		transfer_Window.add(lbl_transfer_amount);
		
		transfer_amount = new JTextField();
		transfer_amount.setBounds(202, 166, 116, 22);
		transfer_Window.add(transfer_amount);
		transfer_amount.setColumns(10);
		
		btn_ok_002 = new JButton("OK");
		btn_ok_002.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_ok_002.setBounds(212, 205, 97, 25);
		transfer_Window.add(btn_ok_002);
		
		btnNewButton_9 = new JButton("BACK");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transfer_Window.setVisible(false);
				main_screen.setVisible(true);
			}
		});
		btnNewButton_9.setBounds(12, 106, 97, 45);
		transfer_Window.add(btnNewButton_9);
		
		password_changer = new JPanel();
		contentPane.add(password_changer, "name_33574075194167");
		password_changer.setLayout(null);
		
		old_password_field = new JPasswordField();
		old_password_field.setHorizontalAlignment(SwingConstants.CENTER);
		old_password_field.setBounds(0, 64, 424, 20);
		password_changer.add(old_password_field);
		
		new_password_field = new JPasswordField();
		new_password_field.setHorizontalAlignment(SwingConstants.CENTER);
		new_password_field.setBounds(0, 123, 424, 20);
		password_changer.add(new_password_field);
		
		field_conf_new_pass = new JPasswordField();
		field_conf_new_pass.setHorizontalAlignment(SwingConstants.CENTER);
		field_conf_new_pass.setBounds(0, 194, 424, 20);
		password_changer.add(field_conf_new_pass);
		
		lbl_enter_old_pass = new JLabel("Enter your old password");
		lbl_enter_old_pass.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_enter_old_pass.setBounds(0, 50, 424, 14);
		password_changer.add(lbl_enter_old_pass);
		
		lblNewLabel_1 = new JLabel("Enter your new password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 106, 424, 14);
		password_changer.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Confirm your new password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 179, 424, 14);
		password_changer.add(lblNewLabel_2);
		
		btnNewButton_6 = new JButton("Change the password");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String old_pass_val = new String( old_password_field.getPassword() );//old_password_field metoduna gidip i�indeki bilgileri get ile �ekiyor
				String new_pass_value = new String( new_password_field.getPassword() );
				String password_Conf = new String( field_conf_new_pass.getPassword() );
				
				if( old_pass_val.equals(data_password) )//equals.data e�itlik ifadelerinde kullan�l�r
				{
					if( new_pass_value.equals( password_Conf ) && !new_pass_value.equals("") )//password onay yeni passwordye e�itse ve bosluk e�it de�il yeni �ifreyse ko�ulu saglanmas� �sten�yor
					{
						data_password = new_pass_value;//yukar�daki kosul saglan�rsa �lk s�frey� yen�s s�fre yap�yor
						JOptionPane.showMessageDialog(null, "Password succesfully changed enter again", "Good", JOptionPane.INFORMATION_MESSAGE);//Missed veya basar�l� olma mesajlar� goster�lmes� �c�n
						password_changer.setVisible(false);//burada da s�fre deg�st�rme ekran� setV�s�ble(false) komutuyla kapat�l�yor
						password.setVisible(true);//burada ise password de�istigi icin bizden yeniden giris istenecegi icin set visible(true) komutuyla password ekran�na ge�iliyor
						writeTOFile();
					}
					
					
					else
					{
						JOptionPane.showMessageDialog(null, "New password not matching", "ERROR", JOptionPane.ERROR_MESSAGE);//new_pass_value.equals l� olan if yap�s�ndaki kosul saglanmazsa
						old_password_field.setText("");//old_password_field alan�n� setText komutuyla bos hale get�r�yor 
						new_password_field.setText("");//new_password_field alan�n� setText komutuyla bos hale get�r�yor 
						field_conf_new_pass.setText("");//field_conf_new_pass alan�n� setText komutuyla bos hale get�r�yor 
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Old Password is Wrong", "ERROR", JOptionPane.ERROR_MESSAGE);//Buradaki else ise password degistirilirken eski passwordyi yanl�s g�rersek kontrol etmke icin
					old_password_field.setText("");
					new_password_field.setText("");
					field_conf_new_pass.setText("");
				}
				
				
			}
		});
		btnNewButton_6.setBounds(81, 229, 114, 23);
		password_changer.add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("BACK");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				password_changer.setVisible(false);
				acc_infs.setVisible(true);
			}
		});
		btnNewButton_7.setBounds(228, 229, 114, 23);
		password_changer.add(btnNewButton_7);
		main_screen.setVisible(false);
	}
	
	public void starting()
	{
		password.setVisible(true);
	}
	
	private int user_ready()
	{
		
		if( read_from_file() == 0 )
			return 0;
		
		return 1;
	}
}
