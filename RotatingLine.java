import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotatingLine extends JPanel implements ActionListener {
    private Timer timer;
    private double angle;
    private double radius;
    private int centerX;
    private int centerY;
    private int lineLength;

    public RotatingLine() {
        timer = new Timer(10, this);
        timer.start();

        angle = 0;
        radius = 100;
        centerX = 250;
        centerY = 250;
        lineLength = 200;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int x1 = (int) (centerX + Math.cos(angle) * radius);
        int y1 = (int) (centerY + Math.sin(angle) * radius);
        int x2 = (int) (x1 + Math.cos(angle + Math.PI) * lineLength);
        int y2 = (int) (y1 + Math.sin(angle + Math.PI) * lineLength);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle += 0.01; // Зміна кута обертання

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotating Line");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        RotatingLine rotatingLine = new RotatingLine();
        frame.add(rotatingLine);

        frame.setVisible(true);
    }
}
