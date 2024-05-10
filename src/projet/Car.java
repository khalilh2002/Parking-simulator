/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.Dimension;
import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class  Car extends JLabel implements Runnable {
   
    ImageIcon car ;
    int id ;
    int x;
    int y;
    static boolean[]  state ; // table kaystocke  wach xi car kharja mn parking
    static int[] postionExitCar ;  //table kaystocke position d car li kharja
     public Car()
    {
        this.id=1;
        this.x=-200 ;
        this.y=160 ;
        car=new ImageIcon("src/img/car"+this.id+".png");
        this.setIcon(car);
        Dimension size = this.getPreferredSize();
        setBounds(x, y, size.width, size.height);
        state=new boolean[2];
        postionExitCar=new int[2];

        for(int i=0 ; i<state.length;i++)
         {
               state[i]=false ; 
         }


        for(int i=0 ; i<postionExitCar.length;i++)
         {
               postionExitCar[i]=50 ;
         }
    }
       public Car(int id)
    {
        this.id=id;
        this.x=-200 ;
        this.y=160 ;
        car=new ImageIcon("src/img/car"+this.id+".png");
        this.setIcon(car);
        Dimension size = this.getPreferredSize();
        setBounds(x, y, size.width, size.height);
        state=new boolean[4];
        postionExitCar=new int[4];
        for(int i=0 ; i<state.length;i++)
         {
               state[i]=false ; 
         }
        for(int i=0 ; i<postionExitCar.length;i++)
         {
               postionExitCar[i]=-300 ; 
         }
    }
     
 
   public void setIconParking(int i)
   {
       car=new ImageIcon("src/img/car"+i+"_"+i+".png");
       this.setIcon(car);
   }
    public void setIconExitParking(int i)
   {
        car=new ImageIcon("src/img/car"+this.id+".png");
        this.setIcon(car);
   }
    
    
     public void  entrePark()
    {

        System.out.println("car "+this.id+" is Parking");
        int[] c=new int[2] ;

        for(int i =0 ; i<c.length;i++) // initialise to empty
        {
            c[i]=0;
        }

        for(int i=0 ; i<2 ; i++) // check if blocs is empty
        {
            if(!Parking.blocEtat[i])
            {
               c[i]=1;
            }

        }

        int b=0 ; // le nombre de block vide
         for(int i=0 ; i<2 ; i++)
        {
            if(c[i]==1)
            {
               b++;
            }

        }

        if(b==0) { // le nombre de bloc vide
            for(int i=0 ; i<state.length;i++)
         {
            if(state[i])
                {
                    try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                }

               }
         }

            System.out.println("Empty blocs : "+b);
            Animation.moveIN(this,1080);
        }

        if(b==1){
            if(c[0]==1){
                System.out.println("Empty bloc : 1 ");
                System.out.println("Target bloc : 1 ");
                Animation.moveIN(this,  290);
                Animation.parking(this);
                Parking.blocEtat[0]=true;
            }
            else if(c[1]==1)
            {
                 System.out.println("Empty bloc : 2 ");
                 System.out.println("Target bloc : 2 ");
                 Animation.moveIN(this, 430);
                 Animation.parking(this);
                 Parking.blocEtat[1]=true;
            }

        }


        if(b==2) {
            int x = (int) (Math.random() * 2) + 1;
            // System.out.println("x: "+x);
            if (c[0] == 1 && c[1] == 1) { //1 and 2
                System.out.println("Empty blocs : 1 , 2 ");
                if (x == 1) {
                    System.out.println("Target bloc : 1 ");
                    Animation.moveIN(this, 290);
                    Animation.parking(this);
                    Parking.blocEtat[0] = true;
                } else {
                    System.out.println("Target bloc : 2 ");
                    Animation.moveIN(this, 430);
                    Animation.parking(this);
                    Parking.blocEtat[1] = true;
                }
            }

        }



    }
     public void sortiPark()
    {
        System.out.println("car "+this.id+" is going out");
        
          
           if(this.x<=300) // bloc 1
           {   
              state[0]=true ;
              postionExitCar[0]=this.x;
              Animation.exitParking(this);
              state[0]=false ;
              postionExitCar[0]=-300;
              Parking.blocEtat[0]=false;
              Animation.moveOut(this);
             
           }
            if(this.x>=290 && this.x<=450) //bloc 2
           {  
              state[1]=true ;
              postionExitCar[1]=this.x;
              Animation.exitParking(this);
              state[1]=false ;
              postionExitCar[1]=-300;
              Parking.blocEtat[1]=false;
              Animation.moveOut(this);    
              
           }

             
           
             
     }

    @Override
     public void run() {
   
        try {
            
           // Random random = new Random();
            int x= (int)(Math.random() * 10000) + 6000 ;
            System.out.println("car : "+this.id+" Parking Time(s) : "+x/1000);  
           
            Parking.semaphoreEntree.acquire(); 
            this.entrePark();
            Parking.semaphoreEntree.release();
           
            Thread.sleep(x);
            
            Parking.semaphoreSortie.acquire();
            this.sortiPark();
           // Thread.sleep(6000);
            Parking.semaphoreSortie.release();
            
            // Thread.sleep(6000);
            
            // if(random.nextBoolean()) Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


}

