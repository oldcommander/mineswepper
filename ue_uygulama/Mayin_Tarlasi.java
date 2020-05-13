package ue_uygulama;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Mayin_Tarlasi extends JFrame implements ActionListener, MouseListener{


    JFrame kullanici_arayuz = new JFrame();               
    JButton yenile = new JButton("YENİLE");      
    JButton bitir = new JButton("BİTİR");     
    JPanel oyun_butonu = new JPanel();       
    Container sınır = new Container();           
    JButton[][] buton;                       
    int boyut;                              
        
    final int Mayın = 10;

    int[][] sayim;                             
   
    public Mayin_Tarlasi(int boyut){
     super("Mayın Tarlası");                       

     this.boyut = boyut;   
     sayim = new int[boyut][boyut];
     buton = new JButton[boyut][boyut];  

     kullanici_arayuz.setSize(1500,900);                       
     kullanici_arayuz.setLayout(new BorderLayout());           
     kullanici_arayuz.add(oyun_butonu,BorderLayout.SOUTH);     
     yenile.addActionListener(this);                 
     bitir.addActionListener(this);                


     sınır.setLayout(new GridLayout(boyut,boyut));    

     for(int a = 0; a < buton.length; a++)
     {
         for(int b = 0; b < buton[0].length; b++)
         {
             buton[a][b] = new JButton();            
             buton[a][b].addActionListener(this);     
             sınır.add(buton[a][b]);                  
         }
     }
     

     oyun_butonu.add(yenile);                        
     oyun_butonu.add(bitir);       

     kullanici_arayuz.add(sınır,BorderLayout.CENTER);   
     Mayin_olustur(boyut);                         

     kullanici_arayuz.setLocationRelativeTo(null);      
     kullanici_arayuz.setDefaultCloseOperation(EXIT_ON_CLOSE);     
     kullanici_arayuz.setVisible(true);

    }
   

    public void takeTheL(int m){

        for(int x = 0; x < boyut; x++)
        {
            for(int y = 0; y < boyut; y++)
            {
                if(buton[x][y].isEnabled())          
                {
                    if(sayim[x][y] != Mayın)
                    {
                        buton[x][y].setText(""+ sayim[x][y]);                    
                    }

                    else
                    {
                        
                        Icon icon = new ImageIcon ("C:\\Users\\tr\\Documents\\NetBeansProjects\\ue_uygulama\\src\\resimler\\indir.png"); 
                        //Lütfen kodu çalıştırmadan önce verilen resmin bilgisayarınızdaki yolunu bu alana yazın.
                        
                       
                                buton[x][y].setIcon(icon);
                        

                    }
                    buton[x][y].setEnabled(false);
                }
            }
        }
    JOptionPane.showMessageDialog(null, m==1? "Mayına Bastınız :( ":"Kaybettiniz", "Oyun Bitti", JOptionPane.ERROR_MESSAGE);
    } 
    

    public void q() {
       boolean kazandı = true;
       for(int i = 0; i < boyut; i++)
       {
           for(int j = 0; j < boyut; j++)
           {
               if(sayim[i][j] != Mayın && buton[i][j].isEnabled())
               {
                   kazandı = false;
               }
           }
       }
       if(kazandı) 
       {
            JOptionPane.showMessageDialog(null,"Kazandınız :) ", "Tebrik Ederim ",JOptionPane.INFORMATION_MESSAGE);
       }   
    }



    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == yenile)              
        {
            for(int x = 0; x < boyut; x++)
            {
                for(int y = 0; y < boyut; y++)
                {
                    buton[x][y].setEnabled(true);
                    buton[x][y].setText("");
                }
            }
            Mayin_olustur(30);  
        }

        else if(ae.getSource() == bitir) 
        {
                   takeTheL(0); 
        }

        else 
        {
                for(int x = 0; x < boyut; x++)
                {
                    for( int y = 0; y < boyut; y++)
                    {
                        if(ae.getSource() == buton[x][y])
                        {
                            switch (sayim[x][y]) {
                                case Mayın:
                                    buton[x][y].setForeground(Color.RED);
                                    buton[x][y].setIcon(new ImageIcon("")); 
                                    takeTheL(1);                                   
                                    break;
                                case 0:
                                    buton[x][y].setText(sayim[x][y] +"");
                                    buton[x][y].setEnabled(false);
                                    ArrayList<Integer> clear = new ArrayList<>();    
                                    clear.add(x*100+y);
                                    buton_açılmasi(clear); 
                                    q();
                                    break;
                                default:
                                    buton[x][y].setText(""+sayim[x][y]);
                                    buton[x][y].setEnabled(false);
                                    q();                                          
                                    break;
                            }
                        }    
                    }
                }
        }


    }

    public void Mayin_olustur(int s){
    ArrayList<Integer> list  = new ArrayList<>();  
        for(int x = 0; x < s; x++)
        {
            for(int y = 0; y < s; y++)
            {
                list.add(x*100+y);                       
            }
        }
        sayim = new int[s][s];   
        
        

        for(int a = 0; a < (int)(s * 1.5); a++)
        {
            int seç = (int)(Math.random() * list.size());
            sayim [list.get(seç) / 100] [list.get(seç) % 100] = Mayın;      
            list.remove(seç);                                                                           
        }
        

        for(int x = 0; x < s; x++)
        {
           for(int y = 0; y < s; y++)
           {
            if(sayim[x][y] != Mayın)
            {
                int neighbor = 0;
                if( x > 0 && y > 0 && sayim[x-1][y-1] == Mayın) 
                {
                    neighbor++;
                }
                if( y > 0 && sayim[x][y-1] == Mayın) 
                {
                    neighbor++;
                }
                if( y < boyut - 1 && sayim[x][y+1] == Mayın) 
                {
                    neighbor++;
                }
                if( x < boyut - 1 && y > 0 && sayim[x+1][y-1] == Mayın) 
                {
                    neighbor++;
                }
                if( x > 0 && sayim[x-1][y] == Mayın) 
                {
                    neighbor++;
                }
                if( x < boyut - 1 && sayim[x+1][y] == Mayın)
                {
                    neighbor++;
                }
                if( x > 0 && y < boyut - 1 &&sayim[x-1][y+1] == Mayın) 
                {
                    neighbor++;
                }
                if( x < boyut - 1 && y < boyut - 1 && sayim[x+1][y+1] == Mayın) 
                {
                    neighbor++;
                }
                sayim[x][y] = neighbor;                      
            }
           }
        }
    }



    public void buton_açılmasi(ArrayList<Integer> sil){
        if(sil.isEmpty())
            return;                         

        int x = sil.get(0) / 100;       
        int y = sil.get(0) % 100;       
        sil.remove(0);                  
            if(sayim[x][y] == 0)
            {                              

                if( x > 0 && y > 0 && buton[x-1][y-1].isEnabled()) 
                {
                    buton[x-1][y-1].setText(sayim[x-1][y-1] + "");
                    buton[x-1][y-1].setEnabled(false);
                    if(sayim[x-1][y-1] == 0)
                    {
                        sil.add((x-1)*100 + (y-1));     
                    }
                }
                if( y > 0 && buton[x][y-1].isEnabled()) 
                {
                    buton[x][y-1].setText(sayim[x][y-1] + "");
                    buton[x][y-1].setEnabled(false);
                    if(sayim[x][y-1] == 0)
                    {
                        sil.add(x*100 + (y-1));
                    }

                }
                if( y < boyut - 1 && buton[x][y+1].isEnabled()) 
                {
                    buton[x][y+1].setText(sayim[x][y+1] + "");
                    buton[x][y+1].setEnabled(false);
                    if(sayim[x][y+1] == 0)
                    {
                        sil.add(x*100 + (y+1));
                    }

                }
                if( x < boyut - 1 && y > 0 && buton[x+1][y-1].isEnabled())
                {
                    buton[x+1][y-1].setText(sayim[x+1][y-1] + "");
                    buton[x+1][y-1].setEnabled(false);
                    if(sayim[x+1][y-1] == 0)
                    {
                        sil.add((x+1)*100 + (y-1));
                    }

                }
                if( x > 0 && buton[x-1][y].isEnabled()) 
                {
                    buton[x-1][y].setText(sayim[x-1][y] + "");
                    buton[x-1][y].setEnabled(false);
                    if(sayim[x-1][y] == 0)
                    {
                        sil.add((x-1)*100 + y);
                    }

                }
                if( x < boyut - 1 && buton[x+1][y].isEnabled())
                {
                    buton[x+1][y].setText(sayim[x+1][y] + "");
                    buton[x+1][y].setEnabled(false);
                    if(sayim[x+1][y] == 0)
                    {
                        sil.add((x+1)*100 + y);
                    }

                }
                if( x > 0 && y < boyut- 1 && buton[x-1][y+1].isEnabled()) 
                {
                    buton[x-1][y+1].setText(sayim[x-1][y+1] + "");
                    buton[x-1][y+1].setEnabled(false);
                    if(sayim[x-1][y+1] == 0)
                    {
                        sil.add((x-1)*100 + (y+1));
                    }

                }
                if( x < boyut - 1 && y < boyut - 1 && buton[x+1][y+1].isEnabled()) 
                {
                    buton[x+1][y+1].setText(sayim[x+1][y+1] + "");
                    buton[x+1][y+1].setEnabled(false);
                    if(sayim[x+1][y+1] == 0)
                    {
                        sil.add((x+1)*100 + (y+1));
                    }

                }
            }
            buton_açılmasi(sil);      
    }


    public static void main(String[] args){
        
        System.out.println("Kolay Derece: 1");
        System.out.println("Orta Derece: 2");
        System.out.println("Zor Derece: 3");
        System.out.println("Lütfen Oynamak İstediğiniz Zorluk Derecesini Giriniz: ");
        Scanner sc = new Scanner(System.in);
        
        int a=sc.nextInt();
        
        if(a==1){
        
        new Mayin_Tarlasi(10);
        
       
        }else if(a==2){
        
            new Mayin_Tarlasi(20);
        }else if(a==3){
        
            new Mayin_Tarlasi(30);
        }else{
            System.err.println("Yanlış Bir Değer Girdiniz Lütfen Oyunu Tekrar Başlatın!");
        }
        


    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (SwingUtilities.isRightMouseButton(me)){
            
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
     
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
      
    }

    private String getIconImage(String df) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
