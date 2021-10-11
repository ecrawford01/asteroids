/**
 * A Sprite is a movable object that is given a specified position with a specified movement. It
 * holds a collision radius for each subclass that inherits.
 * 
 * 
 * @author Ethan Crawford
 * @version 1.0 4/3/19
 *
 */
public abstract class Sprite
{
  protected Pose position;
  protected Vector2D vector;
  protected double collisionRadius;
  private boolean destroyed;

  /**
   * A Sprite with a specified position and specified movement.
   * 
   * @param position
   *          the position of the sprite.
   * @param vector
   *          the movement of the sprite.
   */
  public Sprite(Pose position, Vector2D vector)
  {
    this.position = position;
    this.vector = vector;
  }

  /**
   * A Sprite with a random position and includes a vector with random movement.
   */
  public Sprite()
  {
    position = new Pose(GameConstants.GENERATOR.nextInt(GameConstants.SCREEN_WIDTH),
        GameConstants.GENERATOR.nextInt(GameConstants.SCREEN_HEIGHT),
        GameConstants.GENERATOR.nextInt(GameConstants.SCREEN_WIDTH));
    vector = new Vector2D(position.getHeading(),
        GameConstants.GENERATOR.nextDouble() * Math.PI * 2);
  }

  /**
   * Gets the status of whether the sprite is destroyed or not.
   * 
   * @return if the sprite is destroyed or not.
   */
  public boolean isDestroyed()
  {
    return destroyed;
  }

  /**
   * Gets the position of the sprite.
   * 
   * @return the position of the sprite.
   */
  public Pose getPosition()
  {
    return position;
  }

  /**
   * Gets the movement of the sprite.
   * 
   * @return the vector of the sprite.
   */
  public Vector2D getVector()
  {
    return vector;
  }

  /**
   * Gets the collision radius of the sprite.
   * 
   * @return the collision radius of the sprite.
   */
  public double getCollisionRadius()
  {
    return collisionRadius;
  }

  /**
   * Checks to see if the distance between each sprite's position is less than the sum of the two
   * sprite's collision radius.
   * 
   * @param other
   *          the other sprite being compared.
   * @return whether or not the two sprites intersect.
   */
  public boolean intersects(Sprite other)
  {
    if (this.position.distance(other.getPosition()) <= other.collisionRadius + this.collisionRadius)
    {
      destroyed = true;
    }
    else
    {
      destroyed = false;
    }
    return destroyed;
  }

  /**
   * An abstract method that draws the sprite to the screen.
   */
  public abstract void draw();

  /**
   * An abstract method that updates the sprite on the screen.
   */
  public abstract void update();

}
