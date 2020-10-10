public class NBody {
    public static double readRadius(String a){
        In in = new In(a);
        int pl_num = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    public static Body[] readBodies(String a){
        In in = new In(a);
        Body[] ab = new Body[5];
        int pl_num = in.readInt();
        double radius = in.readDouble();
        int i = 0;
        while(i < ab.length){
            ab[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            i++;
        }
        
        return ab;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]); 
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        double time = 0;

        while(time <= T){

            StdDraw.enableDoubleBuffering();

            double[] x_forces = new double[5];
            double[] y_forces = new double[5];

            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for(int i = 0; i < bodies.length; i++){

                x_forces[i] = bodies[i].calcNetForceExertedByX(bodies);
                y_forces[i] = bodies[i].calcNetForceExertedByY(bodies);

                bodies[i].update(dt, x_forces[i], y_forces[i]);

                StdDraw.setScale(-radius, radius);

                bodies[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
           
        }

        
        
        
        
        // StdDraw.picture(bodies[0].xxPos, bodies[0].yyPos, filename);
        for(int i = 0; i < bodies.length; i++){
            bodies[i].draw();
        }
        StdDraw.show();

    }
    
}