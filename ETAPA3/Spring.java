public class Spring extends PhysicsElement {
   private static int id=0;  // Spring identification number
   protected final double restLength;
   private final double stiffness;
   protected SpringAttachable a_end, b_end;

   private Spring(){   // nobody can create a block without state
      this(0,0);
   }
   
   public Spring(double restLength, double stiffness){
      super(id++);
      this.restLength = restLength;
      this.stiffness = stiffness;
      a_end = b_end = null;
   }
   
   public void attachEnd (SpringAttachable sa) {  // note: we attach a spring to a ball, 
     								  // not the other way around.
	  if(a_end == null) a_end = sa;                 
      else if(b_end == null) b_end = sa;
	  else return;    

      sa.attachSpring(this);
   }
   
   public double getAendPosition() {
      if (a_end != null)
         return a_end.getPos();
      if (b_end != null)
         return b_end.getPos()-restLength;
      return 0;
   }
   
   public double getBendPosition() {
	   if (b_end != null)
          return b_end.getPos();
       if (a_end != null)
          return a_end.getPos()+restLength;
       return 0;
   }
   
   public double getForce(Ball ball) {
      
	  double force = 0;
	  
      if ((a_end == null) || (b_end == null))
         return force;
      if ((ball != a_end) && (ball != b_end))
         return force;
	  
	  //LEY DE HOOKE -> F=K*DeltaX
	  force = stiffness * (restLength - Math.abs(getAendPosition() - getBendPosition()));
	  
	  //CAMBIA DIRECION DE LA FUERZA
	  if (ball == b_end) return force;
	  else return (-force);
  }
   
   public void computeNextState(double delta_t, MyWorld w){
   } 
   
   public void updateState(){
   }

   //ESTA FUNCIÓN ENTREGA IDENTIFICADOR RESORTE - OK!!
   public String getDescription() {
      return "Spring_"+ getId()+":a_end\tb_end";
   }
   
   //ESTA FUNCIÓN ENTREGA POSICIÓN EXTREMOS RESORTE - OK!!
   public String getState() {
	   return (""+getAendPosition()+"\t"+getBendPosition());
   }
}