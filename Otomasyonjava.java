import java.awt.Color;

import java.awt.Container;

import java.awt.Dimension;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.swing.ImageIcon;

import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JMenuBar;

import javax.swing.JMenuItem;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTable;

import javax.swing.JTextArea;

import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import java.sql.*;
import javax.swing.table.JTableHeader;

 

public class Otomasyonjava extends JFrame {

 

    private Connection connection = null; 

    private String url = "jdbc:mysql://localhost:3306/stokotomasyonodevi?useUnicode=true&amp;amp;characterEncoding=utf8"; 

    private String userName = "root"; 

    private String driver = "com.mysql.jdbc.Driver";

    private String password = "root"; 

    private ResultSet result; 

    private Statement statement; 

    public class SQLiteDene {

    	  public Connection getConnection() {
    	    try {
    	      //Class.forName("org.sqlite.JDBC");
    	      return DriverManager.getConnection("jdbc:sqlite:otomasyon_java.db");
    	    } catch (SQLException e) {
    	      e.printStackTrace();
    	    }
			return connection;
    	  }
    	}

 
    private JMenuBar menuBar; 

 

    private JMenuItem anaSayfa;

    private JMenuItem menuYeniKayit;

    private JMenuItem menuGuncelle;

    private JMenuItem menuSil;

    private JMenuItem menuListele;

    private JMenuItem menuArama;

    private JMenuItem hakkinda;
 
    private JPanel anaPanel;

    private JPanel yeniPanel;

    private Container container;
 
    private JLabel urunNumarasi;

    private JLabel urunIsmi;

    private JLabel urunTuru;

    private JLabel urunFiyati;

    private JLabel urunMiktari;    private JTextField txtUrunNumarasi;     private JTextField txtUrunIsmi;
    
    private JTextField txtUrunTuru;

    private JTextField txtUrunFiyati;

    private JTextField txtUrunMiktari;

 

    private JTextField txtGuncelle;

    private JTextArea hosgeldiniz; 

    private JTable tablo;  

     private JButton btnKaydet;

    private JButton guncelleSec;
 

    public Otomasyonjava(){

        super("Stok Otomasyon Programi");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(400, 500);

        this.setResizable(false); 

        acilisEkrani();
 

        try{

            statement = baglanti();

            result = statement.executeQuery("SELECT * FROM stokProje"); 

        }catch(Exception e){

            JOptionPane.showConfirmDialog(null, "Baglanti basarisiz", "Veritabani baglantisi", JOptionPane.PLAIN_MESSAGE); 

        }

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        this.setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2); 

       menuBar = new JMenuBar();

        this.setJMenuBar(menuBar);

        anaSayfa = new JMenuItem("AnaSayfa");

        menuBar.add(anaSayfa);

        anaSayfa.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                 acilisEkrani(); 

            }

        });

 
        menuYeniKayit = new JMenuItem("Yeni Kayit");

        menuBar.add(menuYeniKayit);

        menuYeniKayit.addActionListener(new ActionListener() {

 

            public void actionPerformed(ActionEvent e) {

                yeniKayitMenusu();

            }

        });

 

        menuGuncelle = new JMenuItem("Guncelle");

        menuBar.add(menuGuncelle);

        menuGuncelle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                guncellemeMenusu();
            }

        }); 

        menuListele = new JMenuItem("Listele");

        menuBar.add(menuListele);

        menuListele.addActionListener(new ActionListener() {

  

            public void actionPerformed(ActionEvent e) {

                try {

                    listelemeYap("SELECT * FROM stokProje");

                } catch (SQLException ex) {

                    JOptionPane.showConfirmDialog(null, "Baðlantý baþarýsýz", "Veritabaný baglantýsý", JOptionPane.PLAIN_MESSAGE);

                }

            }

        });

 

        menuArama = new JMenuItem("Arama");

        menuBar.add(menuArama);

        menuArama.addActionListener(new ActionListener() {

 

            public void actionPerformed(ActionEvent e) {

                aramaMenusu();

            }

        });

         menuSil = new JMenuItem("Sil");

        menuBar.add(menuSil);

        menuSil.addActionListener(new ActionListener() {
 

            public void actionPerformed(ActionEvent e) {

                urunSil();

            }
        });

 
        anaPanel = new JPanel(null);

        anaPanel.setSize(400, 450);        anaPanel.setBounds(0, 50, 400, 450);

        this.getContentPane().add(anaPanel);

 

 

    }
    public void urunNumarasiOlustur(){

        urunNumarasi = new JLabel("Urun Numarasi");

        urunNumarasi.setSize(100, 30);

        urunNumarasi.setBounds(10, 50, 100, 30);

        yeniPanel.add(urunNumarasi);

 

        txtUrunNumarasi = new JTextField();

        txtUrunNumarasi.setSize(250, 20);

        txtUrunNumarasi.setBounds(115, 58, 250, 20);

        yeniPanel.add(txtUrunNumarasi);

    }

    public void urunIsmiOlustur(){

        urunIsmi = new JLabel("Urun ismi");

        urunIsmi.setSize(100, 30);

        urunIsmi.setBounds(10, 80, 100, 30);

        yeniPanel.add(urunIsmi);

 

        txtUrunIsmi = new JTextField();

        txtUrunIsmi.setSize(250, 20);

        txtUrunIsmi.setBounds(115, 88, 250, 20);

        yeniPanel.add(txtUrunIsmi);

    }

    public void urunTuruOlustur(){

        urunTuru = new JLabel("Urun turu");

        urunTuru.setSize(100, 30);

        urunTuru.setBounds(10, 110, 100, 30);

        yeniPanel.add(urunTuru);

 

        txtUrunTuru = new JTextField();

        txtUrunTuru.setSize(250, 20);

        txtUrunTuru.setBounds(115, 118, 250, 20);

        yeniPanel.add(txtUrunTuru);

    }

    public void urunFiyatiOlustur(){

        urunFiyati = new JLabel("Urun fiyati");

        urunFiyati.setSize(100, 30);

        urunFiyati.setBounds(10, 140, 100, 30);

        yeniPanel.add(urunFiyati);

 

        txtUrunFiyati = new JTextField();

        txtUrunFiyati.setSize(250, 20);

        txtUrunFiyati.setBounds(115, 148, 250, 20);

        yeniPanel.add(txtUrunFiyati);

    }

    public void urunMiktariOlustur(){

        urunMiktari = new JLabel("Urun miktari");

        urunMiktari.setSize(100, 30);

        urunMiktari.setBounds(10, 170, 100, 30);

        yeniPanel.add(urunMiktari);

 

        txtUrunMiktari = new JTextField();

        txtUrunMiktari.setSize(250, 20);

        txtUrunMiktari.setBounds(115, 178, 250, 20);

        yeniPanel.add(txtUrunMiktari);

 

    }

    public void yeniKayitMenusu(){

        container = getContentPane();

        container.removeAll();

 

        yeniPanel = new JPanel(null);

 

        yeniPanel.setSize(400, 490);

        yeniPanel.setBounds(0, 0, 400, 490); 

        yeniPanel.setBackground(Color.yellow); 

        urunNumarasiOlustur(); 

        urunIsmiOlustur();

        urunTuruOlustur();

        urunFiyatiOlustur();

        urunMiktariOlustur();

 

        btnKaydet = new JButton("Kaydet"); //

        btnKaydet.setSize(100, 30);

        btnKaydet.setBounds(261, 207, 100, 30);

        btnKaydet.addActionListener(new ActionListener() {

 

            public void actionPerformed(ActionEvent e) {

                urunYeniKayit();

            }

        });

        yeniPanel.add(btnKaydet);

 

        container.add(yeniPanel);

 

        invalidate();

        repaint(); 

    }

    public void aramaMenusu(){

        container = getContentPane();

        container.removeAll();

        yeniPanel = new JPanel(null);


        yeniPanel.setSize(400, 490);

        yeniPanel.setBounds(0, 0, 400, 490);

        yeniPanel.setBackground(Color.yellow);

        String[] secenekler ={"Numaraya Göre Ara", "Ürün Ýsmine Göre Ara","Türüne Göre Ara", "Ürün FiyatÄ±na Göre Ara", "MiktarÄ±na Göre Ara"};

        final JComboBox aramaSecenekleri = new JComboBox(secenekler);

        aramaSecenekleri.setSize(250, 25);

        aramaSecenekleri.setBounds(10, 10, 250, 25);

        yeniPanel.add(aramaSecenekleri);

        JButton sec = new JButton("Seç");

        sec.setSize(70, 25);

        sec.setBounds(270, 10, 70, 25);

        sec.addActionListener(new ActionListener() {

 

            public void actionPerformed(ActionEvent e) {

 

                switch(aramaSecenekleri.getSelectedIndex()){

                    case 0:

                        icerikListeleme("Ürün numarasýný giriniz: ", "urunNumarasi");

                        break;

                    case 1:

                        icerikListeleme("Ürün ismini giriniz: ", "urunIsmi");

                        break;

                    case 2:

                        icerikListeleme("Ürün Türünü giriniz: " , "urunTuru");

                        break;

                    case 3:

                        icerikListeleme("Ürün fiyatýný giriniz: ", "urunFiyati");

                        break;

                    case 4:

                        icerikListeleme("Ürün miktarý giriniz: ", "urunMiktari");

                        break;
                }            }

        });

        yeniPanel.add(sec);

        container.add(yeniPanel);

 

        invalidate();

        repaint();

    }

    public void icerikListeleme(String mesaj, final String sutun){

        container = getContentPane();                               

        container.removeAll();

        icerikArama(mesaj);

 

        guncelleSec.addActionListener(new ActionListener() {

 

            public void actionPerformed(ActionEvent e) {

                try {

                    listelemeYap("SELECT * FROM stokProje WHERE "+ sutun + "= \""  + txtGuncelle.getText() +"\"");

                } catch (SQLException ex) {

                    JOptionPane.showConfirmDialog(null, "Baðlantý Baþarýsýz", "Veritabaný baðlantýsý", JOptionPane.PLAIN_MESSAGE);

                }}}

        );

 

        yeniPanel = new JPanel(null);

        yeniPanel.setSize(400, 490);

        yeniPanel.setBounds(0, 0, 400, 490);

        yeniPanel.setBackground(Color.yellow);

        container.add(yeniPanel);

        invalidate();

        repaint();

 

    }

    public void listelemeYap(String mysqlKodu) throws SQLException{

        container = getContentPane();

        container.removeAll();

 

        yeniPanel = new JPanel(null);

 

        yeniPanel.setSize(400, 490);

        yeniPanel.setBounds(0, 0, 400, 490);

        yeniPanel.setBackground(Color.yellow);

        String[] tabloBasliklari = {"No", "Ýsim", "Tür", "Fiyat", "Miktar"};

        result = statement.executeQuery(mysqlKodu);

        result.last();

        int stokSayisi = result.getRow();

        result.first();

        String[][] veri = new String[stokSayisi][5];

        for(int i=0; i<stokSayisi; i++){

            veri[i][0] = Integer.toString(result.getInt("urunNumarasi"));

            veri[i][1] = result.getString("urunIsmi");

            veri[i][2] = result.getString("urunTuru");

            veri[i][3] = Double.toString(result.getDouble("urunFiyati"));

            veri[i][4] = Integer.toString(result.getInt("urunMiktari"));

            result.next();       

        }

 

        tablo = new JTable(stokSayisi, 5);

 

        tablo.setBackground(Color.yellow);

 

        tablo.setSize(393, 442);

        tablo.setBounds(0, 0, 393, 442);

        tablo.setModel(new DefaultTableModel(veri, tabloBasliklari));

 

        JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.setBounds(0, 0, 393, 442);     

        panel.getViewport().add(tablo);

 

 

        JTableHeader baslik = tablo.getTableHeader();
        
       yeniPanel.add(panel);

        yeniPanel.setBackground(Color.yellow);

        container.add(yeniPanel);

 
        invalidate();

        repaint();

    }

    public void icerikArama(String etiketMesaj){

        container = getContentPane();

        container.removeAll();

        yeniPanel = new JPanel(null);


     yeniPanel.setSize(400, 490);

        yeniPanel.setBounds(0, 0, 400, 490);

        yeniPanel.setBackground(Color.yellow);

 

        JLabel guncelle = new JLabel(etiketMesaj);

        guncelle.setSize(200, 30);

        guncelle.setBounds(10, 5, 188, 30);

        yeniPanel.add(guncelle);

 
        txtGuncelle = new JTextField();

        txtGuncelle.setSize(50, 30);

        txtGuncelle.setBounds(200, 10, 50, 20);

        yeniPanel.add(txtGuncelle);

 

        guncelleSec = new JButton("Seç");

        guncelleSec.setSize(70, 20);

        guncelleSec.setBounds(255, 10, 70, 20);

        yeniPanel.add(guncelleSec);

 

        container.add(yeniPanel);

        invalidate();

        repaint();

    }

    public void guncellemeMenusu(){

 

        icerikArama("Guncellenecek ürün numarasý");

        guncelleSec.addActionListener(new ActionListener() {

 

            public void actionPerformed(ActionEvent e) {

                try {

                    container = getContentPane();

                    container.removeAll();

 

                    yeniPanel = new JPanel(null);

 

                    yeniPanel.setSize(400, 490);

                    yeniPanel.setBounds(0, 0, 400, 490);

                    yeniPanel.setBackground(Color.yellow);

                    result = statement.executeQuery("SELECT * FROM stokProje WHERE urunNumarasi = \""  + txtGuncelle.getText() +"\"");

                    result.first();

 

                    urunNumarasiOlustur();

                    txtUrunNumarasi.setEditable(false);

                    txtUrunNumarasi.setText(txtGuncelle.getText());

                    urunIsmiOlustur();

                    txtUrunIsmi.setText(result.getString("urunIsmi"));

                    urunTuruOlustur();

                    txtUrunTuru.setText(result.getString("urunTuru"));

                    urunFiyatiOlustur();

                    txtUrunFiyati.setText(Double.toString(result.getDouble("urunFiyati")));

                    urunMiktariOlustur();

                    txtUrunMiktari.setText(Integer.toString(result.getInt("urunMiktari")));

 

                    JButton btnGuncelle = new JButton("Güncelle");

                    btnGuncelle.setSize(70, 30);

                    btnGuncelle.setBounds(261, 207, 100, 30);

                    yeniPanel.add(btnGuncelle);

 

                    btnGuncelle.addActionListener(new ActionListener() {

 

                        public void actionPerformed(ActionEvent e) {

                            try {

 

                                result.updateString("urunIsmi", txtUrunIsmi.getText());

                                result.updateString("urunTuru", txtUrunTuru.getText());

                                result.updateDouble("urunFiyati", Double.parseDouble(txtUrunFiyati.getText()));

                                result.updateInt("urunMiktari", Integer.parseInt(txtUrunMiktari.getText()));

                                result.updateRow();

 

                                result = statement.executeQuery("SELECT * FROM stokProje");

                                JOptionPane.showMessageDialog(null, "kayýt güncellendi", "Sonuç" , JOptionPane.INFORMATION_MESSAGE);

 

                            } catch (SQLException ex) {

                                JOptionPane.showConfirmDialog(null, "Güncelleme sýrasýnda hata oluþtu.", "Sonuc", JOptionPane.PLAIN_MESSAGE);

                            }               }     });
 

                    container.add(yeniPanel);

                    invalidate();

                    repaint();

 

                } catch (SQLException ex) {

                    Logger.getLogger(Otomasyonjava.class.getName()).log(Level.SEVERE, null, ex);

              }
           }
        });
    }

 

 

    public void urunYeniKayit (){

         try{

             result.first();

             result.moveToInsertRow();

      

             result.updateInt("urunNumarasi", Integer.parseInt(txtUrunNumarasi.getText()));

             result.updateString("urunIsmi", txtUrunIsmi.getText());

             result.updateString("urunTuru", txtUrunTuru.getText());

             result.updateDouble("urunFiyati", Double.parseDouble(txtUrunFiyati.getText()));

             result.updateInt("urunMiktari", Integer.parseInt(txtUrunMiktari.getText()));

             result.insertRow(); 
 

             txtUrunNumarasi.setText("");

             txtUrunIsmi.setText("");

             txtUrunTuru.setText("");

             txtUrunFiyati.setText("");

             txtUrunMiktari.setText("");

             JOptionPane.showMessageDialog(null, "Kayýt Eklendi", "Sonuç" , JOptionPane.INFORMATION_MESSAGE);

         }catch(Exception e){

             JOptionPane.showConfirmDialog(null, "Kayýt Eklenemedi", "Sonuç", JOptionPane.PLAIN_MESSAGE);

         }

     }

    public void urunSil(){

         icerikArama("Silinecek urun numarasi");

         guncelleSec.addActionListener(new ActionListener() {

 

 

             public void actionPerformed(ActionEvent e) {

                 try {

                     result = statement.executeQuery("SELECT * FROM stokProje WHERE urunNumarasi = \""  + txtGuncelle.getText() +"\"");

                     result.first();

                     result.deleteRow();

                     result = statement.executeQuery("SELECT * FROM stokProje");

 

 

                     JOptionPane.showMessageDialog(null, "Kayýt Silindi", "Sonuç" , JOptionPane.INFORMATION_MESSAGE);

                 } catch (SQLException ex) {

                     Logger.getLogger(Otomasyonjava.class.getName()).log(Level.SEVERE, null, ex);

                 }
            }         });

 

        JLabel tumSil = new JLabel("Tüm ürünleri sil");

        tumSil.setSize(200, 30);

        tumSil.setBounds(10, 40, 200, 30);

        yeniPanel.add(tumSil);

 

        JButton btnTumKayitlariSil = new JButton("Hepsini Sil");

        btnTumKayitlariSil.setSize(100, 30);

        btnTumKayitlariSil.setBounds(200, 40, 100, 30);

 

        yeniPanel.add(btnTumKayitlariSil);

        btnTumKayitlariSil.addActionListener(new ActionListener() {

 

             public void actionPerformed(ActionEvent e) {

                 try {

                     statement.executeUpdate("DELETE FROM stokProje");

                 } catch (SQLException ex) {

                     Logger.getLogger(Otomasyonjava.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }         });

     }

    public void acilisEkrani(){

        container = getContentPane();

        container.removeAll();

        ImageIcon resim = new ImageIcon("resim.jpg");

 

        yeniPanel = new JPanel(null);

 

        yeniPanel.setSize(400, 490);

        yeniPanel.setBounds(0, 0, 400, 490);

 
 

        JLabel resimEkle = new JLabel(resim);

        resimEkle.setSize(400, 250);

        resimEkle.setBackground(Color.yellow);

        resimEkle.setBounds(0, 220, 400, 250); 

        yeniPanel.add(resimEkle);
   

        container.add(yeniPanel);

        invalidate();

        repaint();

    }
    public Statement baglanti() throws Exception{

         Class.forName(driver).newInstance();

         connection = DriverManager.getConnection(url, userName, password);

         return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

    }
    public static void main(String[] args){

        Otomasyonjava stok = new Otomasyonjava();

        stok.setVisible(true);

    }
}