import java.util.*;


public class FixedHook extends PhysicsElement implements SpringAttachable {

	   private static int id=0;  // Ball identification number
	   private final double mass; 
	   private double pos_t;     // current position at time t
	   private double pos_tPlusDelta;  // next position in delta time in future
	   private double speed_t;   // speed at time t
	   private double speed_tPlusDelta;   // speed in delta time in future
 
	   Spring spring;

	   public FixedHook(){   // nobody can create a block without state
	     this(0,0);
	   }
	   
	   public FixedHook(double mass, double position){
		  super(id++);
	      this.mass = mass;
		  pos_t = position;
		  
	   }
			
	   public double getPos(){
			   return pos_t;
	   }
		
	   public void attachSpring(Spring sp){
		       this.spring = sp;
	   }
		
	   public String getDescription() {
			   id = getId();
			   String id_out = "Hook" +id;
			   return id_out;
		}
		   
		public String getState(){
			   return (""+pos_t);
		}
		   
		public void computeNextState(double delta_t, MyWorld aThis){
			pos_tPlusDelta=pos_t;
			speed_tPlusDelta=speed_t;		
		}

		public void updateState(){
			pos_t=pos_tPlusDelta;
			speed_t=speed_tPlusDelta;
		}
		
} 
