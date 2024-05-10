package projet;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MoveTo {
    public static void moveIn(voiture car , int yMAx){
        while (car.y<=yMAx){
            for (int i = 0 ; i!= 2 ; i++){
                if (voiture.state[i] && car.y < voiture.positionExitCar[i]){
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException ex) {
                        System.out.println("moveto 1");
                        Logger.getLogger(voiture.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                car.y+=20;
                car.setLocation(car.x,car.y);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("moveto 2");
                    Logger.getLogger(voiture.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public static void parking(voiture car)
    {
        System.out.println("parking car "+car.toString());
        while(car.x>30 && car.x <= 200 )
        {
            car.setIconParking(car.id);
            car.x-=20;
            car.setLocation(car.x, car.y);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(voiture.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void exitPark(voiture car){
        while (car.x <= 200 ){
            car.x+=20;
            car.setLocation(car.x,car.y);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public  static void moveOut(voiture car){
        while (car.y < 1000){   //edit her
            car.setIconExitParking(car.id);
            car.y+=20; //edit
            car.setLocation(car.x , car.y);

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        car.x = 200;
        car.y = 200;
    }
}
