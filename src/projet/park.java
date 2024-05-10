package projet;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class park extends JPanel {
    ImageIcon imgPark;
    static boolean[] blocEtat ; //true=full & false=empty
    static Semaphore semaphoreEntree ;
    static Semaphore semaphoreSortie ;

    public park(){
        semaphoreEntree = new Semaphore(1,true);
        semaphoreSortie = new Semaphore(1,false);
        blocEtat = new boolean[2];
        Arrays.fill(blocEtat, false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        imgPark =new ImageIcon("src/img/parking.png");
        imgPark.paintIcon(this, g, WIDTH,WIDTH);



    }

}
