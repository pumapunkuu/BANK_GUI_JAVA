import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

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

import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.Color;
import java.io.*;
import java.util.Arrays;


public class atm extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField kullanici_sifre;
	private JButton sifreOnay;
	private JPanel sifre;
	private JPanel anaekran;
	private JLabel yazi01;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnHavaleYap;
	private JPanel hesapBilgileri;
	private JPanel paraYatirma;
	private JPanel paraCekme;
	private JPanel havaleEkrani;
	
	private String data_sifre;
	private double data_bakiye;
	private String data_adsoyad;
	private String data_hesapno;
	private int    data_i;
	
	private JLabel lblNewLabel;
	private JButton btnNewButton_2;
	private JButton btnNewButton_5;
	private JPanel sifreDegistirme;
	private JPasswordField alan_eskisifre;
	private JPasswordField alan_yenisifre;
	private JPasswordField alan_ysifreonay;
	private JLabel lblEskiSifreniziGirin;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JLabel lblAdSoyad;
	private JLabel lblHesapNumaras;
	private JLabel lblBakiye;
	private JLabel label_adsoyad;
	private JLabel label_hesapno;
	private JLabel label_bakiye;
	private JTextField p_cekme;
	private JTextField yatirma;
	private JButton btnGeri_1;
	private JLabel lblNewLabel_6;
	private JTextField textField;
	private JButton btnTamam_1;
	private JLabel lblHavaleMiktar;
	private JTextField textField_2;
	private JButton btnTamam_2;
	private JButton btnNewButton_9;
	private JLabel pCekme_bakiye;
	private JLabel pYatirma_bakiye;
	private JButton btnGeri;
	
	private static final String _dosyaadi_ = "veri.txt";
	
	private kullanici[] kullanicilar;
	private int kullaniciSayisi;
	
	private JTextField txtHesapNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					
					atm frame = new atm();
					frame.setVisible(true);
					
					frame.basla();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int dosyadanOku()
	{
		Arrays.fill(kullanicilar, null);//dizinin  içini doldurmak için 
		kullaniciSayisi = 0;
		try{

	        File dosya = new File(_dosyaadi_);

	        if (!dosya.exists()) {
	            //return 0;
	        	dosya.createNewFile();
	        }

	        FileReader okuyucu = new FileReader(dosya);

	        BufferedReader oku = new BufferedReader(okuyucu);
	        
	        String satir;
	        
	        
	        
	        int i=0;
	        int j=0;
	        
	        kullanicilar[0] = new kullanici();
	        
	        while ( true )
	        {
	        	
	        	satir = oku.readLine();

	        	if( satir == null || satir.equals("+") )//satýr boþsa ya da + karekterini görünce break yapýp çýkýyor.Çünkü + karekterini görünce dosyadan okuma bitecek
	        	{
	        		break;
	        	}
	        	
	        	
	        	
	        	if( satir.equals("*")  )//Eðer satýr * karekterine gelirse i yi 1 arttýrýp diðer kullanýcýya geçecek.* karekteri diðer kullanýcýya geçtiðini belirtmek için bulunuyor
	        	{

	        		i++;
	        		j=0;
	        		
	        		kullanicilar[i] = new kullanici();//yeni kullanýcý kullanýcýlar[i] ye atanýyor
	        		
	        	}
	        	else//eðer yukarýdakilerin hiçbiri deðilse  ilk kullanýcýdaki ad soyad sifre bakiye hesap no bilgilerini tarýyor.j deðiþkeni de bu tarama iþelmini yapýyor
	        	{
	        		if( j == 0 )
	        		{
	        			kullanicilar[i].adsoyad = satir;
	        		}
	        		else if(j == 1)
	        		{
	        			kullanicilar[i].sifre = satir;
	        		}
	        		else if(j == 2)
	        		{
	        			kullanicilar[i].bakiye = Double.parseDouble(satir);//bakiye double oldugu için stringe çeviriyoruz  çünkü stringle double ayný tür deðiþken deðil
	        		}
	        		else if(j == 3)
	        		{
	        			kullanicilar[i].hesapno = satir;
	        		}
	        		
	        		j++;//j yi artýrýyor
	        	}
	        	
	    	}
	        
	        kullaniciSayisi = i;
	        
	        oku.close();
	        
	        JOptionPane.showMessageDialog(null, kullanicilar[0].adsoyad+" "+kullanicilar[1].adsoyad , "Kullanýcýlar" , JOptionPane.INFORMATION_MESSAGE);
	        
	        //System.out.println("Ekleme Ýþlemi Baþarýlý");
	        
	    }
	    catch (Exception hata){
	    	hata.printStackTrace();
	    	//JOptionPane.showMessageDialog(null, , "HATA", JOptionPane.ERROR_MESSAGE);
	    	
	        return 0;
	    }
		
		return 1;
	}
	
	public int dosyayaYaz()
	{
		kullanicilar[data_i].bakiye = data_bakiye;
		kullanicilar[data_i].adsoyad = data_adsoyad;
		kullanicilar[data_i].hesapno = data_hesapno;
		kullanicilar[data_i].sifre = data_sifre;
		
		try{
	        File dosya = new File(_dosyaadi_);
	        if (!dosya.exists()) {
	            dosya.createNewFile();
	        }
	        FileWriter yazici = new FileWriter(dosya,false);
	        BufferedWriter yaz = new BufferedWriter(yazici);
	        
	        for(int i=0; i < kullaniciSayisi; i++)
	        {
	        	yaz.write(kullanicilar[i].adsoyad);
	        	yaz.newLine();
	        	yaz.write(kullanicilar[i].sifre);
	        	yaz.newLine();
	        	yaz.write( Double.toString( kullanicilar[i].bakiye ) );
	        	yaz.newLine();
	        	yaz.write(kullanicilar[i].hesapno);
	        	yaz.newLine();
	        	yaz.write("*");
	        	yaz.newLine();
	        }
	        
	        yaz.write("+");
	        
	        
	        yaz.close();
	        //System.out.println("Ekleme Ýþlemi Baþarýlý");
	        
	    }
	    catch (Exception hata){
	        return 0;
	    }
		
		return 1;
	}

	/**
	 * Create the frame.
	 */
	public atm() {
		
		kullanicilar = new kullanici[500];
		
		if( kullaniciHazirla() == 0 )
		{
			JOptionPane.showMessageDialog(null, "Kullanýcý Bilgileri Hazýrlanamadý!", "HATA", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		sifre = new JPanel();
		sifre.setForeground(Color.BLACK);
		contentPane.add(sifre, "name_29516457095479");
		sifre.setLayout(null);
		 
		
		sifreOnay = new JButton("Tamam");
		sifreOnay.setForeground(SystemColor.desktop);
		sifreOnay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				
				String hn = new String( txtHesapNo.getText() );//hn hesap no olarak alýndý
				String ssiffre = new String( kullanici_sifre.getPassword() );
				int ok=0;//integer türünde ok deðiþkeni tanýmladýk
				for(int i=0; i < kullaniciSayisi; i++)//kullanýcý sayýsýný for dongusunde taradýk
				{
					if( kullanicilar[i].hesapno.equals(hn) && kullanicilar[i].sifre.equals(ssiffre)  )//Ýlk baþta açýlan sifre ve hesap no kýsmýndaki kullanýcý bilgilerini karþýlaþtýrarak doðruluyor
					{
						data_sifre = kullanicilar[i].sifre;
						data_bakiye = kullanicilar[i].bakiye;
						data_hesapno = kullanicilar[i].hesapno;//Hesap numaramýz
						data_adsoyad = kullanicilar[i].adsoyad;//Ýsim soyad
						data_i = i;
						
						ok = 1;
						break;
					}
				}
				
				if( ok == 1 )
				{
					sifre.setVisible(false);
					anaekran.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Hatalý þifre girdiniz!!", "HATA", JOptionPane.ERROR_MESSAGE);
					kullanici_sifre.setText("");
				}
			}
		});
		sifreOnay.setBounds(169, 123, 89, 23);
		sifre.add(sifreOnay);
		
		kullanici_sifre = new JPasswordField();
		kullanici_sifre.setText("123456");
		kullanici_sifre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if( arg0.getKeyCode() == KeyEvent.VK_ENTER )//enter ý kullanmak için keyevent.VK_ENTER KULLANDIK
				{
					sifreOnay.doClick();//MouseListener kullanmak için
				}
			}
		});
		kullanici_sifre.setBounds(94, 80, 236, 20);
		sifre.add(kullanici_sifre);
		
		yazi01 = new JLabel("Hesap Numaran\u0131z\u0131 ve \u015Eifrenizi Giriniz");
		yazi01.setHorizontalAlignment(SwingConstants.CENTER);
		yazi01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Þifreyi Gir buraya týklama", "MESAJ", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		yazi01.setBounds(0, 25, 424, 14);
		sifre.add(yazi01);
		
		lblNewLabel = new JLabel("Fake Banka Ho\u015Fgeldiniz");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 424, 14);
		sifre.add(lblNewLabel);
		
		JLabel lblNewLabel_8 = new JLabel("       FAKE BANK");
		lblNewLabel_8.setFont(new Font("Yu Mincho Demibold", Font.PLAIN, 17));
		lblNewLabel_8.setBounds(127, 134, 181, 75);
		sifre.add(lblNewLabel_8);
		
		txtHesapNo = new JTextField();
		txtHesapNo.setText("Hesap No");
		txtHesapNo.setBounds(94, 45, 236, 22);
		sifre.add(txtHesapNo);
		txtHesapNo.setColumns(10);
		
		anaekran = new JPanel();
		contentPane.add(anaekran, "name_29516465052206");
		anaekran.setLayout(null);
		
		btnNewButton = new JButton("Hesap Bilgileri\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				label_adsoyad.setText(data_adsoyad);
				label_hesapno.setText(data_hesapno);
				
				label_bakiye.setText(Double.toString(data_bakiye));
				
				
				
				anaekran.setVisible(false);//Anaekran kapatýlýyor setVisible ile
				hesapBilgileri.setVisible(true);//HesapBilgileri setVisibl ile açýlýyor
			}
		});
		btnNewButton.setBounds(0, 64, 125, 23);
		anaekran.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Para \u00C7ekme");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pCekme_bakiye.setText(Double.toString(data_bakiye));
				anaekran.setVisible(false);//Anaekran kapatýlýyor setVisible ile
				paraCekme.setVisible(true);//paracekme ekraný acýlýyor
			}
		});
		btnNewButton_1.setBounds(0, 128, 125, 23);
		anaekran.add(btnNewButton_1);
		
		btnNewButton_3 = new JButton("Para Yat\u0131rma");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				anaekran.setVisible(false);//Anaekran kapatýlýyor setVisible ile
				paraYatirma.setVisible(true);
				pYatirma_bakiye.setText( Double.toString(data_bakiye) );
			}
		});
		btnNewButton_3.setBounds(299, 128, 125, 23);
		anaekran.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("\u00C7\u0131k\u0131\u015F");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_4.setBounds(148, 201, 125, 23);
		anaekran.add(btnNewButton_4);
		
		btnHavaleYap = new JButton("Havale Yap");
		btnHavaleYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anaekran.setVisible(false);//Anaekran kapatýlýyor setVisible ile
				havaleEkrani.setVisible(true);
			}
		});
		btnHavaleYap.setBounds(299, 64, 125, 23);
		anaekran.add(btnHavaleYap);
		
		hesapBilgileri = new JPanel();
		contentPane.add(hesapBilgileri, "name_30701921819582");
		hesapBilgileri.setLayout(null);
		
		btnNewButton_2 = new JButton("Geri D\u00F6n");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hesapBilgileri.setVisible(false);
				anaekran.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(226, 204, 125, 23);
		hesapBilgileri.add(btnNewButton_2);
		
		btnNewButton_5 = new JButton("Sifre de\u011Fistirme");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hesapBilgileri.setVisible(false);
				sifreDegistirme.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(38, 204, 142, 23);
		hesapBilgileri.add(btnNewButton_5);
		
		lblAdSoyad = new JLabel("Ad Soyad");
		lblAdSoyad.setBounds(10, 23, 113, 14);
		hesapBilgileri.add(lblAdSoyad);
		
		lblHesapNumaras = new JLabel("Hesap Numaras\u0131");
		lblHesapNumaras.setBounds(10, 48, 113, 14);
		hesapBilgileri.add(lblHesapNumaras);
		
		lblBakiye = new JLabel("Bakiye");
		lblBakiye.setBounds(10, 73, 113, 14);
		hesapBilgileri.add(lblBakiye);
		
		label_adsoyad = new JLabel( ": " + data_adsoyad );
		label_adsoyad.setBounds(154, 23, 260, 14);
		hesapBilgileri.add(label_adsoyad);
		
		label_hesapno = new JLabel( ": " + data_hesapno );
		label_hesapno.setBounds(154, 48, 260, 14);
		hesapBilgileri.add(label_hesapno);
		
		label_bakiye = new JLabel( ": " + String.valueOf(data_bakiye) );
		label_bakiye.setBounds(154, 73, 260, 14);
		hesapBilgileri.add(label_bakiye);
		
		paraYatirma = new JPanel();
		contentPane.add(paraYatirma, "name_30747465011681");
		paraYatirma.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Yat\u0131r\u0131lcak Para Miktar\u0131");
		lblNewLabel_5.setBounds(12, 33, 139, 16);
		paraYatirma.add(lblNewLabel_5);
		
		yatirma = new JTextField();
		yatirma.setBounds(215, 30, 116, 22);
		paraYatirma.add(yatirma);
		yatirma.setColumns(10);
		
		JButton btnTamam = new JButton("TAMAM");//tamam butonunun calýsmasý ýcýn gereken algorýtma burada yazýlacak
		btnTamam.setBounds(225, 65, 97, 25);
		btnTamam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double deger = Double.parseDouble( yatirma.getText() );
				if(deger>10)
				{
				data_bakiye += deger;
				pYatirma_bakiye.setText( Double.toString(data_bakiye) );
				JOptionPane.showMessageDialog(null, "Hesabýnýza "+Double.toString(deger)+" TL Para Yatýrýldý", "BAÞARILI", JOptionPane.INFORMATION_MESSAGE);
				yatirma.setText("");//p_yatýrma jtext ekranýný bosaltýr
				btnGeri_1.doClick();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Girilen Miktar az", "HATA", JOptionPane.ERROR_MESSAGE);
					yatirma.setText("");
				}
				}
			}
		
		
				);
		paraYatirma.add(btnTamam);
		
		btnGeri_1 = new JButton("Geri");
		btnGeri_1.setBounds(225, 176, 97, 25);
		btnGeri_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paraYatirma.setVisible(false);//Anaekran kapatýlýyor setVisible ile
				anaekran.setVisible(true);
			}
		});
		paraYatirma.add(btnGeri_1);
		
		JLabel lblNewLabel_7 = new JLabel("Bakiye");
		lblNewLabel_7.setBounds(12, 145, 91, 16);
		paraYatirma.add(lblNewLabel_7);
		
		pYatirma_bakiye = new JLabel(Double.toString(data_bakiye));
		pYatirma_bakiye.setBounds(224, 145, 56, 16);
		paraYatirma.add(pYatirma_bakiye);
		
		paraCekme = new JPanel();
		contentPane.add(paraCekme, "name_30759846069170");
		paraCekme.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("\u00C7ekilecek Para Miktar\u0131:");
		lblNewLabel_3.setBounds(12, 25, 157, 16);
		paraCekme.add(lblNewLabel_3);
		
		p_cekme = new JTextField();
		p_cekme.setBounds(181, 22, 116, 22);
		paraCekme.add(p_cekme);
		p_cekme.setColumns(10);
		
		JButton btnNewButton_8 = new JButton("TAMAM");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double deger = Double.parseDouble( p_cekme.getText() );//burada double tipindeki deðer degiskenini stringe çevirdik
				/*
				 * p_cekme.getText() ile, kutunun içindeki deðeri alýyoruz ama bu deðer, strine þeklinde.
				 * Double.parseDouble , içine verdiðin string parametresinden double çekiyor ve geriye döndürüyor.
				 * böylece "deger" deðiþkeni, kutunun içindeki sayýyý tutmuþ oluyor.
				 */
				
				if( deger > 0 && deger <= data_bakiye ) // eðer girilen deðer 0 dan büyükse ve bakiyede yeterli miktar varsa
				{
					data_bakiye -= deger;//burada da databakiye=databakiye-deger yaparak bakiyeyi azaltýyoruz
					pCekme_bakiye.setText( Double.toString(data_bakiye) );
					JOptionPane.showMessageDialog(null, "Hesabýnýzdan "+Double.toString(deger)+" TL Para Çekildi", "BAÞARILI", JOptionPane.INFORMATION_MESSAGE);
					p_cekme.setText("");//metin kutusundaki yazan veriyi alýyor bu
					btnGeri.doClick();

					dosyayaYaz();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Girilen Miktar Hatalý", "HATA", JOptionPane.ERROR_MESSAGE);
					p_cekme.setText("");
				}
			}
		});
		btnNewButton_8.setBounds(191, 57, 97, 25);
		paraCekme.add(btnNewButton_8);
		
		JLabel lblNewLabel_4 = new JLabel("Kalan Bakiyeniz:");
		lblNewLabel_4.setBounds(12, 140, 157, 16);
		paraCekme.add(lblNewLabel_4);
		
		btnGeri = new JButton("Geri");
		btnGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paraCekme.setVisible(false);//Para cekme ekraný kapatýlýyor setVisible ile
				anaekran.setVisible(true);//ana ekrana geri dönülüyor
			}
		});
		btnGeri.setBounds(200, 205, 97, 25);
		paraCekme.add(btnGeri);
		
		pCekme_bakiye = new JLabel( Double.toString( data_bakiye ) ); // bakiye deðeri yazýlýyor.
		pCekme_bakiye.setBounds(181, 141, 116, 14);
		paraCekme.add(pCekme_bakiye);
		
		havaleEkrani = new JPanel();
		contentPane.add(havaleEkrani, "name_30803696805931");
		havaleEkrani.setLayout(null);
		
		lblNewLabel_6 = new JLabel("Kart Numaras\u0131n\u0131 Giriniz:");
		lblNewLabel_6.setBounds(12, 49, 148, 16);
		havaleEkrani.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(202, 46, 116, 22);
		havaleEkrani.add(textField);
		textField.setColumns(10);
		
		btnTamam_1 = new JButton("TAMAM");
		btnTamam_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTamam_1.setBounds(212, 93, 97, 25);
		havaleEkrani.add(btnTamam_1);
		
		lblHavaleMiktar = new JLabel("Havale Miktar\u0131");
		lblHavaleMiktar.setBounds(12, 169, 97, 16);
		havaleEkrani.add(lblHavaleMiktar);
		
		textField_2 = new JTextField();
		textField_2.setBounds(202, 166, 116, 22);
		havaleEkrani.add(textField_2);
		textField_2.setColumns(10);
		
		btnTamam_2 = new JButton("TAMAM");
		btnTamam_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTamam_2.setBounds(212, 205, 97, 25);
		havaleEkrani.add(btnTamam_2);
		
		btnNewButton_9 = new JButton("Geri");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				havaleEkrani.setVisible(false);//Anaekran kapatýlýyor setVisible ile
				anaekran.setVisible(true);
			}
		});
		btnNewButton_9.setBounds(12, 106, 97, 45);
		havaleEkrani.add(btnNewButton_9);
		
		sifreDegistirme = new JPanel();
		contentPane.add(sifreDegistirme, "name_33574075194167");
		sifreDegistirme.setLayout(null);
		
		alan_eskisifre = new JPasswordField();
		alan_eskisifre.setHorizontalAlignment(SwingConstants.CENTER);
		alan_eskisifre.setBounds(0, 64, 424, 20);
		sifreDegistirme.add(alan_eskisifre);
		
		alan_yenisifre = new JPasswordField();
		alan_yenisifre.setHorizontalAlignment(SwingConstants.CENTER);
		alan_yenisifre.setBounds(0, 123, 424, 20);
		sifreDegistirme.add(alan_yenisifre);
		
		alan_ysifreonay = new JPasswordField();
		alan_ysifreonay.setHorizontalAlignment(SwingConstants.CENTER);
		alan_ysifreonay.setBounds(0, 194, 424, 20);
		sifreDegistirme.add(alan_ysifreonay);
		
		lblEskiSifreniziGirin = new JLabel("Eski Sifrenizi Girin");
		lblEskiSifreniziGirin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEskiSifreniziGirin.setBounds(0, 50, 424, 14);
		sifreDegistirme.add(lblEskiSifreniziGirin);
		
		lblNewLabel_1 = new JLabel("Yeni sifrenizi girin");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 106, 424, 14);
		sifreDegistirme.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Yeni \u015Eifrenizi Onaylay\u0131n\u0131z");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 179, 424, 14);
		sifreDegistirme.add(lblNewLabel_2);
		
		btnNewButton_6 = new JButton("\u015Eifreyi De\u011Fi\u015Ftir");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String eskiSifre = new String( alan_eskisifre.getPassword() );//alan_eskisifre metoduna gidip içindeki bilgileri get ile çekiyor
				String yeniSifre = new String( alan_yenisifre.getPassword() );
				String sifreOnay = new String( alan_ysifreonay.getPassword() );
				
				if( eskiSifre.equals(data_sifre) )//equals.data eþitlik ifadelerinde kullanýlýr
				{
					if( yeniSifre.equals( sifreOnay ) && !yeniSifre.equals("") )//sifre onay yeni sifreye eþitse ve bosluk eþit deðil yeni þifreyse koþulu saglanmasý ýstenýyor
					{
						data_sifre = yeniSifre;//yukarýdaki kosul saglanýrsa ýlk sýfreyý yenýs sýfre yapýyor
						JOptionPane.showMessageDialog(null, "Þifreniz Baþarýyla Deðiþtirildi. Lütfen Yeniden Giriþ Yapýnýz", "BAÞARILI", JOptionPane.INFORMATION_MESSAGE);//hata veya basarýlý olma mesajlarý gosterýlmesý ýcýn
						sifreDegistirme.setVisible(false);//burada da sýfre degýstýrme ekraný setVýsýble(false) komutuyla kapatýlýyor
						sifre.setVisible(true);//burada ise sifre deðistigi icin bizden yeniden giris istenecegi icin set visible(true) komutuyla sifre ekranýna geçiliyor
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Yeni Þifreler Birbiriyle Ayný Deðil", "HATA", JOptionPane.ERROR_MESSAGE);//yeniSifre.equals lý olan if yapýsýndaki kosul saglanmazsa
						alan_eskisifre.setText("");//alan_eskisifre alanýný setText komutuyla bos hale getýrýyor 
						alan_yenisifre.setText("");//alan_yenisifre alanýný setText komutuyla bos hale getýrýyor 
						alan_ysifreonay.setText("");//alan_ysifreonay alanýný setText komutuyla bos hale getýrýyor 
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Eski Þifrenizi Yanlýþ Girdiniz", "HATA", JOptionPane.ERROR_MESSAGE);//Buradaki else ise sifre degistirilirken eski sifreyi yanlýs gýrersek kontrol etmke icin
					alan_eskisifre.setText("");
					alan_yenisifre.setText("");
					alan_ysifreonay.setText("");
				}
				
				
			}
		});
		btnNewButton_6.setBounds(81, 229, 114, 23);
		sifreDegistirme.add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("Geri D\u00F6n");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sifreDegistirme.setVisible(false);
				hesapBilgileri.setVisible(true);
			}
		});
		btnNewButton_7.setBounds(228, 229, 114, 23);
		sifreDegistirme.add(btnNewButton_7);
		anaekran.setVisible(false);
	}
	
	public void basla()
	{
		sifre.setVisible(true);//Ýlk ekraný true yaparak sifre ekraný geliyor karsýmýza
	}
	
	private int kullaniciHazirla()//Private kullandýk çünkü bu bigiler kullanýcý ekranýnda gorunecek o yuzden once ký dýger 2 ekran asamasýný gecmesý gerekýyor kodun
	{
		
		if( dosyadanOku() == 0 )
			return 0;
		
		/*
		data_sifre = "123456";//Sifremiz
		data_bakiye = 50;//Bakiyemiz
		data_hesapno = "213698742";//Hesap numaramýz
		data_adsoyad = "Ali Veli";//Ýsim soyad
		*/
		return 1;
	}
}
