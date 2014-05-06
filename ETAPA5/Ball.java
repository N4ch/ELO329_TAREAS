import java.util.*;

public class Ball extends PhysicsElement {
   private static int id=0;  // Ball identification number
   private final double mass;
   private final double radius;
   private double pos_t;     // current position at time t
   private double pos_tPlusDelta;  // next position in delta time in future
   private double speed_t;   // speed at time t
   private double speed_tPlusDelta;   // speed in delta time in future
   
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
   //SE AGREGA METODO GETMASS - ME
   public double getMass(){
	   return mass;
   }
   //SE AGREGA METODO GETPOS - ME
   public double getPos(){
	   return pos_t;
   }

   
   public void computeNextState(double delta_t, MyWorld world) {
     Ball b;  // Assumption: on collision we only change speed. ***  
     
	 //LAS BOLAS COLISIONAN
	 if ((b=world.findCollidingBall(this)) != null){ /* elastic collision */
        speed_tPlusDelta = (speed_t*(mass-b.getMass()) + 2*b.getMass()*b.getSpeed())/(mass+b.getMass());
   		pos_tPlusDelta = pos_t;
     }
	 //LAS BOLAS NO COLISIONAN
	 else {
        speed_tPlusDelta = speed_t;
        pos_tPlusDelta = pos_t + speed_t*delta_t;
     }
   }
   
   // BUSCA CERCANIA CON LA OTRA BOLA - OK!!
   public boolean collide(Ball b) {
	   boolean tocando;
	   boolean acercando;
	   
	   if(this == b) return false; //ELIMINAR CHOQUE CONMIGO MISMO
	   tocando = Math.abs(getPos()-b.getPos()) < (getRadius()+b.getRadius());
	   
	   acercando = (b.getSpeed() < getSpeed());
	   if (b.getPos() < getPos()) acercando = !acercando;
	   
	   return tocando && acercando;
  }
	
	//ACTUALIZA ESTADO, LUEGO DE UN DELTA
	public void updateState(){
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
   }

   //ESTA FUNCIÓN ENTREGA IDENTIFICADOR BOLA - OK!!
   public String getDescription(){
	   id = getId();
	   String id_out = "Ball_" +id;
	   return id_out;
   }
   
   //ESTA FUNCIÓN ENTREGA POSICIÓN BOLAS - OK!!
   public String getState(){
	   return (""+pos_t);
   }
}