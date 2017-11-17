import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Screen extends JPanel implements MouseListener {

    public static final int HEIGHT=600, WIDTH=800;
    ArrayList<Point> cp;

    public Screen() {
        this.setLayout(null);
        this.setFocusable(true);
        addMouseListener(this);

        cp = new ArrayList<Point>();
    }

    public static Curve catmullrom(Point p0, Point p1, Point p2, Point p3, double dt) {
        int n = (int) (1.0/dt);
        int[] x = new int[n];
        int[] y = new int[n];
        for (double t = 0.0; t<1.0; t+=dt) {
            int i = (int)(t/dt);
            x[i] = (int) (0.5 * ( (2*p1.x) + ((0 - p0.x + p2.x)*t) + (((2*p0.x) - (5*p1.x) + (4*p2.x) - p3.x)*(Math.pow(t, 2))) + (((-1*p0.x) + (3*p1.x) - (3*p2.x) + p3.x)*(Math.pow(t, 3)))));
            y[i] = (int) (0.5 * ( (2*p1.y) + ((0 - p0.y + p2.y)*t) + (((2*p0.y) - (5*p1.y) + (4*p2.y) - p3.y)*(Math.pow(t, 2))) + (((-1*p0.y) + (3*p1.y) - (3*p2.y) + p3.y)*(Math.pow(t, 3)))));
        }
        double t = 1.0;
        int i = n-1;
            x[i] = (int) (0.5 * ( (2*p1.x) + ((0 - p0.x + p2.x)*t) + (((2*p0.x) - (5*p1.x) + (4*p2.x) - p3.x)*(Math.pow(t, 2))) + (((-1*p0.x) + (3*p1.x) - (3*p2.x) + p3.x)*(Math.pow(t, 3)))));
            y[i] = (int) (0.5 * ( (2*p1.y) + ((0 - p0.y + p2.y)*t) + (((2*p0.y) - (5*p1.y) + (4*p2.y) - p3.y)*(Math.pow(t, 2))) + (((-1*p0.y) + (3*p1.y) - (3*p2.y) + p3.y)*(Math.pow(t, 3)))));
        Curve temp = new Curve(x, y, n);
        return temp;
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

        if (cp.size()>=4) {
            for (int i = 1; i<cp.size()-2; i++) {
                Curve curve = catmullrom(cp.get(i-1), cp.get(i), cp.get(i+1), cp.get(i+2), 0.1);
                g.drawPolyline(curve.x, curve.y, curve.n);
            }
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
