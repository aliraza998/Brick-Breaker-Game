
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.company.*;

public class Menu  {
    public static void main(String[] args) {
        JFrame j1 = new JFrame();
        j1.setBounds(10, 10, 700, 600);
        JLabel j2=new JLabel();
        j2.setIcon(new ImageIcon("src/pic 2.jpg"));
        j2.setBounds(50, 0, 600, 280);
        j2.setLocation(195, 5);
        j2.setVisible(true);
        j1.add(j2);
        j1.setResizable(false);
        j1.add(displayPlayButton(j1));
        j1.add(displayHelpButton(j1));
        j1.add(displayQuitButton(j1));
        j1.add(displayhighestButton(j1));
        j1.setLayout(null);
        j1.setVisible(true);
        j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }

    public static JButton playButton = new JButton("Play");
    public static JButton helpButton = new JButton("Help");
    public static JButton quitButton = new JButton("Quit");
    public static JButton highestButton = new JButton("Highest Score");

    public static JButton displayPlayButton(JFrame frame) {
        playButton.setBounds(300, 260, 115, 40);
        playButton.addActionListener(e->BrickBreaker.displayGame());
        return playButton;
    }

    public static JButton displayHelpButton(JFrame frame) {
        helpButton.setBounds(300, 310, 115, 40);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "Touch the screen to launch the ball and move your paddle.\n" +
                                "Click to launch the ball, then move your right left arrow keys to control the paddle.\n" +
                                "keep the ball above the paddle and vanish all the bricks.\n");
            }
        });
        return helpButton;
    }

    public static JButton displayhighestButton(JFrame frame) {
        highestButton.setBounds(300, 360, 115, 40);
        highestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Highest score is "+inGame.getMax());
            }
        });
        return highestButton;
    }

    public static JButton displayQuitButton(JFrame frame) {
        quitButton.setBounds(300, 410, 115, 40);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure to exit out?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        return quitButton;
    }
}
