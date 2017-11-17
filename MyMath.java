public static class MyMath {
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

    public static int binomialCoefficient(int n, int k) {
        return (factorial(n) / (factorial(k) * factorial(n-k)));
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
}
