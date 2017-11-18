import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Screen extends JPanel implements MouseListener, KeyListener {

    public static final int HEIGHT=600, WIDTH=800;
    ArrayList<Point> cp;

    public Screen() {
        this.setLayout(null);
        this.setFocusable(true);
        addMouseListener(this);
        addKeyListener(this);

        cp = new ArrayList<Point>();
    }

    public Dimension getPreferredSize() {
        return new Dimension(Screen.WIDTH,Screen.HEIGHT);
    }

    public void paintComponent(Graphics g) {
        //draw background
        g.setColor(Color.white);
        g.fillRect(0,0,Screen.WIDTH,Screen.HEIGHT);

        //set up font
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.blue);

        for (int i = 0; i<cp.size(); i++) {
            g.drawOval((int)(cp.get(i).x)-2, (int)(cp.get(i).y)-2, 4, 4);
        }

        /* Catmull Rom drawing
        if (cp.size()>=4) {
            for (int i = 1; i<cp.size()-2; i++) {
                Curve curve = MyMath.catmullrom(cp.get(i-1), cp.get(i), cp.get(i+1), cp.get(i+2), 0.1);
                g.drawPolyline(curve.x, curve.y, curve.n);
            }
        }
        */

        double[] x = new double[cp.size()];
        double[] y = new double[cp.size()];
        for (int i = 0; i<cp.size(); i++) {
            x[i] = cp.get(i).x;
            y[i] = cp.get(i).y;
        }
        Curve curve = MyMath.bezier(x, y, 0.01);
        g.drawPolyline(curve.x, curve.y, curve.n);
        
    }

    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            System.out.println("enter");
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
        cp.add(new Point(e.getX(), e.getY()));
        System.out.println(e.getX() + " " + e.getY());
        repaint();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
