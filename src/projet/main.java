/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.ArrayList;
import javax.swing.*;

public class main {


    public static void main_(String[] args) {
    
        
        JFrame frame = new JFrame("Khalil El Houssine ");
        Parking panel = new Parking();
        frame.setContentPane(panel);
        panel.setLayout(null);
        frame.setSize(1090, 1090);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Car> cars=new ArrayList<Car>();
        

      for(int i=1 ; i<=2 ; i++)
      {   
          Car c = new Car(i); 
          cars.add(c);
          panel.add(c);
       } 
      
      for(Car c : cars)
      {
          Thread t=new Thread(c);
          t.start();
      }
      frame.setVisible(true);
       
    }

}
