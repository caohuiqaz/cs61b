public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet (double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;	
	}

	public double calcDistance(Planet p){
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		double rSquard = (dx*dx) + (dy*dy);
		return Math.sqrt(rSquard); 
	}

	public double calcForceExertedBy(Planet p){
		double r = calcDistance(p);
		double G = 6.67 * Math.pow(10, -11);
		double F = G * this.mass * p.mass / (r*r);
		return F;
	}

	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - this.xxPos;
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		double Fx = F*dx/r;
		return Fx;
	}

	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - this.yyPos;
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		double Fy = F*dy/r;
		return Fy;
	}

	public double calcNetForceExertedByX(Planet [] Planets){
		double netForceX = 0;
		for (Planet p : Planets){
			if (p == this){
				continue;
			}
			else{
				netForceX += calcForceExertedByX(p);
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Planet [] Planets){
		double netForceY = 0;
		for (Planet p : Planets){
			if (p == this){
				continue;
			}
			else{
				netForceY += calcForceExertedByY(p);
			}
		}
		return netForceY;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + aX*dt;
		this.yyVel = this.yyVel + aY*dt;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}

	public void draw(){
		String imageToDraw = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);
	}





}
