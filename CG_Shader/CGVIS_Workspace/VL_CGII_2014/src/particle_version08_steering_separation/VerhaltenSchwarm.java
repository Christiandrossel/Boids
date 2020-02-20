package particle_version08_steering_separation;

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
      
      flummi.velocity.add(steering.acceleration);
      flummi.velocity.truncate(MAX_VELOCITY);
      flummi.position.add(flummi.velocity);
      
      steering.resetAcceleration();
   }
}
