package ending;

import javax.swing.*;
import java.awt.*;

public class Win {

    private JFrame frame=new JFrame();

    public void draw(){
        this.frame.setVisible(true);
        this.frame.getContentPane().setBackground(Color.BLACK);
        this.frame.setBounds(100,100,400,400);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());

        JLabel win=new JLabel();
        win.setVerticalAlignment(SwingConstants.CENTER);
        win.setHorizontalAlignment(SwingConstants.CENTER);
        win.setText("You win");
        win.setFont(new Font("MV Boli",Font.ITALIC | Font.BOLD,80));
        win.setForeground(Color.GREEN);

        this.frame.add(win);
    }
}
