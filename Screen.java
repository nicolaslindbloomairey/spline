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


    public static Curve bezier(int[] x, int[] y) {
        ArrayList<Integer> xresult = new ArrayList<Integer>();
        ArrayList<Integer> yresult = new ArrayList<Integer>();
        int n = x.length -1;

        for (int i = 0; i<=n; i++) {
            xresult.add( (
        }

    }


    public static Curve cubicbezier(Point p0, Point p1, Point p2, Point p3, double dt) {
        int n = (int) (1.0/dt);
        int[] x = new int[n];
        int[] y = new int[n];
        for (double t = 0.0; t<1.0; t+=dt) {
            int i = (int)(t/dt);
            x[i] = (int) ( (Math.pow((1.0 - t), 3) * p0.x) + (3 * Math.pow((1.0-t), 2) * t * p1.x) + (3 * Math.pow(t, 2) * (1.0-t) * p2.x) + (Math.pow(t, 3) * p3.x));
            y[i] = (int) ( (Math.pow((1.0 - t), 3) * p0.y) + (3 * Math.pow((1.0-t), 2) * t * p1.y) + (3 * Math.pow(t, 2) * (1.0-t) * p2.y) + (Math.pow(t, 3) * p3.y));
        }
        double t = 1.0;
        int i = n-1;
        x[i] = (int) ( (Math.pow((1.0 - t), 3) * p0.x) + (3 * Math.pow((1.0-t), 2) * t * p1.x) + (3 * Math.pow(t, 2) * (1.0-t) * p2.x) + (Math.pow(t, 3) * p3.x));
        y[i] = (int) ( (Math.pow((1.0 - t), 3) * p0.y) + (3 * Math.pow((1.0-t), 2) * t * p1.y) + (3 * Math.pow(t, 2) * (1.0-t) * p2.y) + (Math.pow(t, 3) * p3.y));
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
        if (e.get
        cp.add(new Point(e.getX(), e.getY()));
        System.out.println(e.getX() + " " + e.getY());
        repaint();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
