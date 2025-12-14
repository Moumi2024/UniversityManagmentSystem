package UniversityManagmentSystem;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    Thread t;
    JLabel img;

    public Splash() {

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.png"));
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        img = new JLabel(i3);
        add(img);

        t = new Thread(this);
        t.start();
        setVisible(true);

        // Opening Animation
        for (int i = 2, x = 1; i <= 600; i += 4, x++) {
            int xPos = 600 - ((i + x) / 2);
            int yPos = 350 - (i / 2);
            int width = i + 3 * x;
            int height = i + (x / 2);

            setLocation(xPos, yPos);
            setSize(width, height);

            try {
                Thread.sleep(10);  // animation speed
            } catch (Exception e) {
                System.err.println("Splash animation interrupted: " + e.getMessage());
            }
        }

        try {
            Thread.sleep(3000);  // holds the splash screen for 3 seconds
        } catch (InterruptedException e) {
            System.err.println("Post-animation hold interrupted: " + e.getMessage());
        }
    }

    public void run() {
        try {
            Thread.sleep(3000); // 3 seconds delay
            setVisible(false);
            new Login();       // open login page
        } catch (Exception e) {
            System.err.println("Splash thread interrupted: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
