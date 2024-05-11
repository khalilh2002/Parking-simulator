package projet;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class voiture extends JLabel implements Runnable{
    ImageIcon img;
    int id;

    int x;
    int y;

    static boolean[] state;
    static  int[] positionExitCar;

    public voiture(int id , ImageIcon icon ){


        this.x = 200;
        this.y = 0;
        this.id=id;

        this.img = icon;
        this.setIcon(img);

        Dimension size = getPreferredSize();
        setBounds(x,y,150 , 141);

        state = new boolean[2];
        positionExitCar = new int[2];

        for (int i=0 ; i< state.length ; i++){
            positionExitCar[i]=200;
        }
    }
    //edit for asset
    public void setIconExitParking(int i)
    {
        img=new ImageIcon("asset/car"+i+"_"+i+".png");
        this.setIcon(img);
    }
    public void setIconParking(int i)
    {
        img=new ImageIcon("asset/car"+this.id+".png");
        this.setIcon(img);
    }


    public void entrePark(){
        int[] c = new int[2];

        for (int i = 0 ; i != c.length ; i++){
            if (!park.blocEtat[i]){
                c[i]=1;
            }
        }
        int blockVide = 0;
        for (int i=0 ; i!=2;i++){
            if (c[i]==1){
                blockVide++;
            }
        }
        System.out.println("blok"+blockVide);
        if(blockVide == 2){
            if((c[0]==1)){
                System.out.println("Empty bloc : 1 ");
                System.out.println("Target bloc : 1 ");
                MoveTo.moveIn(this,  190);
                System.out.println("line 70");
                MoveTo.parking(this);
                System.out.println("line 74");

                park.blocEtat[0]=true;
            }
            else
            {
                System.out.println("Empty bloc : 2 ");
                System.out.println("Target bloc : 2 ");
                MoveTo.moveIn(this, 390);

                MoveTo.parking(this);
                park.blocEtat[1]=true;
            }

        } else if (blockVide==1) {
            int index = (c[0]==1)? 0 : 1 ;
            int valueTo = (index==0)? 190 : 390 ;
            MoveTo.moveIn(this, valueTo);

            MoveTo.parking(this);
            park.blocEtat[index]=true;

        }else {
            for(int i=0 ; i<state.length;i++) {
                if (state[i]) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(voiture.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }MoveTo.moveIn(this,1300);
        }
    }

    public void sortiPark(){
        System.out.println("you out "+this.toString());
        if (this.y <= 230  ){
            System.out.println("you in 1 ");

            state[0]=true ;
            positionExitCar[0]=this.y;
            MoveTo.exitPark(this);
            state[0]=false ;
            positionExitCar[0]=0;
            park.blocEtat[0]=false;
            MoveTo.moveOut(this);



        } else if (this.y <= 410 ) {
            state[1]=true ;
            positionExitCar[1]=this.y;
            MoveTo.exitPark(this);
            state[1]=false ;
            positionExitCar[1]=0;
            park.blocEtat[1]=false;
            MoveTo.moveOut(this);
        }






    }














    @Override
    public void run() {
        System.out.println("ok");
        try {
            int x= 2000 ;

            park.semaphoreEntree.acquire();
            this.entrePark();
            park.semaphoreEntree.release();

            Thread.sleep(x);

            park.semaphoreSortie.acquire();
            this.sortiPark();
            park.semaphoreSortie.release();

        }catch (InterruptedException ex) {
            System.out.println("you ara in run");
            Logger.getLogger(voiture.class.getName()).log(Level.SEVERE, "oh you are nodd in 157", ex);
        }
    }

    public static void main(String[] args ){



        System.out.println("hello agian voiture !!!");
        ImageIcon car=new ImageIcon("asset/car1_1.png");
        ImageIcon car2=new ImageIcon("asset/car2_2.png");

        JFrame frame = new JFrame("Khalil El Houssine ");
        park panel = new park();
        frame.setContentPane(panel);
        panel.setLayout(null);
        frame.setSize(400,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        voiture f = new voiture(1,car);
        panel.add(f);

        voiture f2 = new voiture(2,car2);
        panel.add(f2);

        Thread thread = new Thread(f);
        thread.start();

        Thread thread1 = new Thread(f2);
        thread1.start();

        frame.setVisible(true);

    }

    @Override
    public String toString() {
        return "voiture to String \n x: "+ this.x+"\n"+"y "+ this.y;
    }
}


