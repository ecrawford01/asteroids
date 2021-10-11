/**
 * A saucer is an open rectangle that is generated randomly with a certain possibility of
 * appearance. It also provides a direction change after each time step. When they are destroyed,
 * they give their desired points.
 * 
 * 
 * @author Ethan Crawford
 * @version 1.0 4/3/19
 *
 */
public class Saucer extends Sprite
{
  /**
   * Creates an Asteroid at the specified position with the specified movement.
   * 
   * @param position
   *          the position of the saucer.
   * @param vector
   *          the movement of the saucer.
   */
  public Saucer(Pose position, Vector2D vector)
  {
    super(position, vector);
  }

  /**
   * Creates a saucer at the specified position and sets the vector to the saucers constant speed.
   * Also creates the collision radius for the saucer.
   */
  public Saucer()
  {
    super();
    vector = new Vector2D(position.getHeading(), GameConstants.SAUCER_SPEED);
    collisionRadius = GameConstants.SAUCER_WIDTH / 2;
  }

  /**
   * Checks to see if the saucer is out of bounds.
   * 
   * @return if the the saucer is out of bounds.
   */
  public boolean outOfBounds()
  {
    if (this.position.getX() > GameConstants.SCREEN_WIDTH || this.position.getX() < 0
        || this.position.getY() > GameConstants.SCREEN_HEIGHT || this.position.getX() < 0)
    {
      return true;
    }
    return false;
  }

  @Override
  public void draw()
  {
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.rectangle(this.position.getX(), this.position.getY(), GameConstants.SAUCER_WIDTH / 2,
        GameConstants.SAUCER_HEIGHT / 2);
  }

  @Override
  public void update()
  {
    position.move(vector);
    if (GameConstants.GENERATOR.nextDouble() <= GameConstants.SAUCER_DIRECTION_PROB)
    {
      vector.setHeading(GameConstants.GENERATOR.nextDouble() * (Math.PI * 2));
    }
  }
}
