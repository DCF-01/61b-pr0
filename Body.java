class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // pocetni pozicii, vel, masa

    public Body(double xPos, double yPos, double xVel, double yVel, double m, String imgFile) {
        this.xxPos = xPos;
        this.yyPos = yPos;
        this.xxVel = xVel;
        this.yyVel = yVel;
        this.mass = m;
        this.imgFileName = imgFile;
    }

    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
        
    }

    public double calcDistance(Body b) {
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r2 = Math.pow(dx, 2) + Math.pow(dy, 2);
        double r = Math.sqrt(r2);

        return r;
    }

    public double calcForceExertedBy(Body b){
        double r = this.calcDistance(b);
        double g = 6.673 * Math.pow(10, -11);

        double f = (g * b.mass * this.mass) / Math.pow(r, 2);

        return f;
    }

    public double calcForceExertedByX(Body b){
        double dx = this.xxPos - b.xxPos;
        double f = this.calcForceExertedBy(b);
        double Fx = (f * dx) / this.calcDistance(b);

        return Fx;
    }

    public double calcForceExertedByY(Body b){
        double dy = this.yyPos - b.yyPos;
        double f = this.calcForceExertedBy(b);
        double Fy = (f * dy) / this.calcDistance(b);

        return Fy;
    }

    public double calcNetForceExertedByX(Body[] b){
        double F_net_x = 0;
        for(int i = 0; i < b.length; i++){
            if(this.equals(b[i])){
                continue;
            }
            else {
            F_net_x -= this.calcForceExertedByX(b[i]);
            }
        }
        return F_net_x;
    } 

    public double calcNetForceExertedByY(Body[] b){
        double F_net_y = 0;
        for(int i = 0; i < b.length; i++){
            if(this.equals(b[i])){
                continue;
            }
            else {
                F_net_y -= this.calcForceExertedByY(b[i]);
            }
        }
        return F_net_y;
    }

    public void update(double dt, double fX, double fY) {
        double a_net_x = fX / this.mass;
        double a_net_y = fY / this.mass;

        double v_new_x = this.xxVel + (dt * a_net_x);
        double v_new_y = this.yyVel + (dt * a_net_y);

        double p_new_x = this.xxPos + (dt * v_new_x);
        double p_new_y = this.yyPos + (dt * v_new_y);

        this.xxPos = p_new_x;
        this.yyPos = p_new_y;

        this.xxVel = v_new_x;
        this.yyVel = v_new_y;
        
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
        
    }

    
} 