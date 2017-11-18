import java.util.ArrayList;

public class MyMath {
    public static int factorial(int f) {
        if (f == 0) {
            return 1;
        }
        int fact = 1;
        for (int i = 1; i<=f; i++) {
            fact *= i;
        }
        return fact;
    }

    //pascals triangle coefficients
    public static int binomialCoefficient(int n, int k) {
        return (factorial(n) / (factorial(k) * factorial(n-k)));
    }

    //recursive defintion of bezier curve of degree n for n+1 points
    public static Curve bezier(double[] x, double[] y, double dt) {
        ArrayList<Double> xresult = new ArrayList<Double>();
        ArrayList<Double> yresult = new ArrayList<Double>();
        int n = x.length -1;

        for (double t = 0.0; t<1.0; t+=dt) {

            double xt = 0., yt = 0.;
            for (int i = 0; i<=n; i+=1) {
                xt += (binomialCoefficient(n, i) * Math.pow(1.0-t, n-i) * Math.pow(t, i) * x[i] );
                yt += (binomialCoefficient(n, i) * Math.pow(1.0-t, n-i) * Math.pow(t, i) * y[i] );
            }
            xresult.add(xt);
            yresult.add(yt);
        }

        int[] xarray = new int[xresult.size()];
        int[] yarray = new int[yresult.size()];

        for (int i = 0; i<xresult.size(); i++) {
            xarray[i] = (int)Math.ceil(xresult.get(i));
            yarray[i] = (int)Math.ceil(yresult.get(i));
        }
        int points = (int) (1.0/dt);
        Curve curve = new Curve(xarray,yarray, points);
        return curve;
    }

    //only works with four points
    public static Curve cubicbezier(Point p0, Point p1, Point p2, Point p3, double dt) {
        int n = (int) (1.0/dt);
        int[] x = new int[n];
        int[] y = new int[n];
        for (double t = 0.0; t<=1.0; t+=dt) {
            int i = (int)(t/dt);
            x[i] = (int) ( (Math.pow((1.0 - t), 3) * p0.x) + (3 * Math.pow((1.0-t), 2) * t * p1.x) + (3 * Math.pow(t, 2) * (1.0-t) * p2.x) + (Math.pow(t, 3) * p3.x));
            y[i] = (int) ( (Math.pow((1.0 - t), 3) * p0.y) + (3 * Math.pow((1.0-t), 2) * t * p1.y) + (3 * Math.pow(t, 2) * (1.0-t) * p2.y) + (Math.pow(t, 3) * p3.y));
        }
        Curve temp = new Curve(x, y, n);
        return temp;
    }

    //catmullrom cubic spline
    public static Curve catmullrom(Point p0, Point p1, Point p2, Point p3, double dt) {
        int n = (int) (1.0/dt);
        int[] x = new int[n];
        int[] y = new int[n];
        for (double t = 0.0; t<=1.0; t+=dt) {
            int i = (int)(t/dt);
            x[i] = (int) (0.5 * ( (2*p1.x) + ((0 - p0.x + p2.x)*t) + (((2*p0.x) - (5*p1.x) + (4*p2.x) - p3.x)*(Math.pow(t, 2))) + (((-1*p0.x) + (3*p1.x) - (3*p2.x) + p3.x)*(Math.pow(t, 3)))));
            y[i] = (int) (0.5 * ( (2*p1.y) + ((0 - p0.y + p2.y)*t) + (((2*p0.y) - (5*p1.y) + (4*p2.y) - p3.y)*(Math.pow(t, 2))) + (((-1*p0.y) + (3*p1.y) - (3*p2.y) + p3.y)*(Math.pow(t, 3)))));
        }
        Curve temp = new Curve(x, y, n);
        return temp;
    }
}
