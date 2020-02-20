package particle_version09_steering_alignment;

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
      steering.applyForce(steering.followMousePosition(flummi.position));
      steering.applyForce(steering.separation(flummi, 50));
      steering.applyForce(steering.alignment(flummi, 10));
      
      flummi.velocity.add(steering.acceleration);
      flummi.velocity.truncate(MAX_VELOCITY);
      flummi.position.add(flummi.velocity);
      
      steering.resetAcceleration();
   }
}
