package particle_version10_steering_cohesion;

import math.Vektor2D;

public class VerhaltenSchwarm implements Verhalten {
   private Flummi flummi;
   private Steuerungsverhalten steering;
   private final float MAX_VELOCITY = 10;
   
   public VerhaltenSchwarm(Flummi flummi) {
      this.flummi   = flummi;
      this.steering = new Steuerungsverhalten();
   }
   
   @Override
   public void update() {
      Vektor2D mouseForce = steering.followMousePosition(flummi.position);
      Vektor2D separationForce = steering.separation(flummi, 50);
      Vektor2D alignmentForce = steering.alignment(flummi, 50);
      Vektor2D cohesionForce = steering.cohesion(flummi, 50);
      
      mouseForce.mult(1f);
      separationForce.mult(1);
      alignmentForce.mult(0.08f);
      cohesionForce.mult(0.01f);
      
      steering.applyForce(mouseForce);
      steering.applyForce(separationForce);
      steering.applyForce(alignmentForce);
      steering.applyForce(cohesionForce);
      
      flummi.velocity.add(steering.acceleration);
      flummi.velocity.truncate(MAX_VELOCITY);
      flummi.position.add(flummi.velocity);
      
      steering.resetAcceleration();
   }
}
