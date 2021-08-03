package ending;

import javax.swing.*;
import java.awt.*;

public class Lost {

    private JFrame frame=new JFrame();

    public void draw(){
        this.frame.setVisible(true);
        this.frame.getContentPane().setBackground(Color.BLACK);
        this.frame.setBounds(100,100,400,400);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());

        JLabel lost=new JLabel();
        lost.setVerticalAlignment(SwingConstants.CENTER);
        lost.setHorizontalAlignment(SwingConstants.CENTER);
        lost.setText("You lose");
        lost.setFont(new Font("MV Boli",Font.ITALIC | Font.BOLD,80));
        lost.setForeground(Color.RED);

        this.frame.add(lost);
    }
}
