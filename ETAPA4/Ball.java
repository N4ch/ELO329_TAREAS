import java.util.*;

public class Ball extends PhysicsElement implements SpringAttachable {
   private static int id=0;  // Ball identification number
   private final double mass;
   private final double radius;
   private double pos_t;     // current position at time t
   private double pos_tPlusDelta;  // next position in delta time in future
   private double speed_t;   // speed at time t
   private double speed_tPlusDelta;   // speed in delta time in future
   private double a_t;    // acceleration at time t
   private double a_tMinusDelta;  // acceleration delta time ago;
   private ArrayList<Spring> springs;  // ArrayList can grow, arrays cannot. 

   private Ball(){   // nobody can create a block without state
     this(1.0,0.1,0,0);
   }
   public Ball(double mass, double radius, double position, double speed){
      super(id++);
      this.mass = mass;
        this.radius = radius;
      pos_t = position;
      speed_t = speed;
   }
   public double getRadius() {
      return radius;
   }
   public double getSpeed() {
      return speed_t;
   }
   public double getMass() {
       return mass;
   }
   
   public double getPos() {
       return pos_t;
   }
   
   private double getNetForce() {
       if (springs != null) {
    	 for (Spring sp:springs) {
           if (sp.a_end != null && sp.b_end != null)
               return sp.getForce(this);
        }
       }
       return 0;
   }

   public void computeNextState(double delta_t, MyWorld world) {
     Ball b;  // Assumption: on collision we only change speed.  
     if ((b=world.findCollidingBall(this))!= null) { /* elastic collision */
        speed_tPlusDelta=(speed_t*(mass-b.getMass())+2*b.getMass()*b.getSpeed())/(mass+b.getMass());
        pos_tPlusDelta = pos_t;
        a_t = getNetForce() / mass;
     } 
     else {
    	a_t=getNetForce()/mass;
   		speed_tPlusDelta = speed_t+(a_t)*delta_t;
  	    pos_tPlusDelta = pos_t + (speed_tPlusDelta)*delta_t;
  	    
//      ECUACIONES DE http://profesores.elo.utfsm.cl/~agv/elo329/1s11/Assignments/T1/MassSimulationModel.pdf
// 		a_t = getNetForce()/mass;
// 		speed_tPlusDelta = speed_t + 0.5 * (3*a_t - a_tMinusDelta)*delta_t;
// 		pos_tPlusDelta = pos_t + speed_t*delta_t + (1/6)*(4*a_t - a_tMinusDelta)*delta_t*delta_t;       		 	      		     		
     }
   }
   
   public boolean collide(Ball b) {
	   boolean tocando;
	   boolean acercando;
	   
	   if(this == b) return false; //ELIMINAR CHOQUE CONMIGO MISMO
	   tocando = Math.abs(getPos()-b.getPos()) < (getRadius()+b.getRadius());
	   
	   acercando = (b.getSpeed() < getSpeed());
	   if (b.getPos() < getPos()) acercando = !acercando;
	   
	   return tocando && acercando;
    }
        
   public void updateState() {
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
     a_t     =  a_tMinusDelta ; 
   }
   
   public String getDescription(){
	   id = getId();
	   String id_out = "Ball_" +id;
	   return id_out;
   }
   public String getState() {
	   return (""+pos_t);
   }
    
   public void attachSpring(Spring sa) {
        springs = new ArrayList<Spring>();
        springs.add(sa);
        if (this==sa.a_end) {
            pos_t = sa.getAendPosition();
        }
        if (this==sa.b_end) {
            pos_t = sa.getBendPosition();
         } 
    }
}


