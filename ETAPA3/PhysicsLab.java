import java.io.*;
import java.util.Scanner;

public class PhysicsLab {
   public static void main(String[] args) {
	   
	   
	   	   
      if (args.length != 3)  {
    	System.out.println("usage: java PhysicsLab <delta_time[s]> <end_time[s]> <sampling_time[s]>");
    	System.out.println("NO SE INGRESARON 3 PARAMETROS DIRECTAMENTE ");
    	System.out.println("Ingrese 3 Parametros porfavor");
       
      Scanner s= new Scanner (System.in);
  
      MyWorld world = new MyWorld(System.out);
      
		  double mass = 1.0;      // 1 [kg] 
		  double radius = 0.1;    // 10 [cm] 
		  double position = 1.5;  // 0 [m] 
		  double speed = 0;     // 0 [m/s]
		  
		  Ball b1 = new Ball(mass, radius, position, speed);
		  Spring spring = new Spring(1.0, 0.5);
		  FixedHook hook = new FixedHook(1,0);
		  
		  spring.attachEnd(hook);
		  spring.attachEnd(b1);
      
	      world.addElement(spring);
          world.addElement(hook);
          world.addElement(b1);
 
            
      world.simulate(s.nextDouble(), s.nextDouble(), s.nextDouble()); // delta time[s], total simulation time [s].

   }
    
      else{
    	  
	      double deltaTime = Double.parseDouble(args[0]);    // [s]
	      double endTime = Double.parseDouble(args[1]);      // [s]
	      double samplingTime = Double.parseDouble(args[2]); // [s]
    	  
	      System.out.println("Los Parametros fueron ingresados directamente al momento de la ejecucion");
	      
	      MyWorld world = new MyWorld(System.out);
	      
	      double mass = 1.0;      // 1 [kg] 
		  double radius = 0.1;    // 10 [cm] 
		  double position = 1.5;  // 0 [m] 
		  double speed = 0;     // 0 [m/s]
		  
		  Ball b1 = new Ball(mass, radius, position, speed);
		  Spring spring = new Spring(1.0, 0.5);
		  FixedHook hook = new FixedHook(1,0);
		  
		  spring.attachEnd(hook);
		  spring.attachEnd(b1);
      
	      world.addElement(spring);
          world.addElement(hook);
          world.addElement(b1);
 
	       
	      world.simulate(deltaTime, endTime, samplingTime); // delta time[s], total simulation time [s].
    	  
    	  
      }
         
}    
}
