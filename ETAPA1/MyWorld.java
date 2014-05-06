import java.util.*;
import java.io.*;

public class MyWorld {
   private PrintStream out; //OBJETO OUT DE LA CLASE PRINTSTREAM
   
   //CREA ARREGLO CON OBJETOS RESTRINGIDOS A <PhysicsElement>
   //Array to hold everything in my world
   private ArrayList<PhysicsElement> elements;  
   
   //SI NO OBTIENE PARAMETROS, LLAMA A OUT PRESENTE EN MYWORLD
   public MyWorld(){
      this(System.out);  // delta_t= 0.1[ms] and refreshPeriod=200 [ms]
   }
   public MyWorld(PrintStream output){
      out = output;
      elements = new ArrayList<PhysicsElement>();  //SE CREAN LOS OBJETOS   
   }
   
   //AGREGA ELEMENTO POR PARAMETRO AL FINAL DEL ARREGLO
   public void addElement(PhysicsElement e) {
      elements.add(e); 
   }

   //LLAMA A PRINTDESCRIPTION E IMPRIME ENCABEZADOS - OK!!
   public void printStateDescription(){
     String s = "Time\t\t";
     for (PhysicsElement e:elements)
       s+=e.getDescription() + "\t\t";
     out.println(s);
   }

   //ENTREGA POSICIÓN DE LAS BOLAS EN FUNCIÓN DEL TIEMPO - OK!!
   public void printState(double t){
	 String s = t+"\t\t";
	 for (PhysicsElement e:elements)
		 s+=e.getState() + "\t\t"; 
	 out.println(s);
   }

   //SIMULA PASO DEL TIEMPO
   public void simulate (double delta_t, double endTime, double samplingTime) { 
	   
      double t=0;
      printStateDescription();
	  printState(t); //IMPRIME ESTADO INICIAL
	  
      while (t<endTime) {
		  
         for(double nextStop=t+samplingTime; t<nextStop; t+=delta_t) {
           for (PhysicsElement e:elements)   // compute each element next state based on current global state  
			   e.computeNextState(delta_t,this); // compute each element next state based on current global state
          //actualiza el estado para cada elemento//
           for (PhysicsElement e:elements)  // for each element update its state. 
			   e.updateState();     // update its state
         }		 
          printState(t);
      }
   }  
    
   //RECORRE EL ARREGLO DE BOLAS Y PASA VERIFICA CERCANIA - OK!!
   public Ball findCollidingBall(Ball me) {

	   for(PhysicsElement e:elements){
		   Ball aux_ball = (Ball) e;
		   if (aux_ball.collide(me)) return aux_ball;
	   }
	   return null;
   }   
} 

