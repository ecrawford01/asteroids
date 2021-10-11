/**
 * The Bullet class creates a small filled circle that is used to destroy the asteroids and saucers.
 * A bullet is provided with a bullet life to track the steps of the bullet.
 * 
 * 
 * 
 * @author Ethan Crawford
 * @version 1.0 4/3/19
 *
 */
public class Bullet extends Sprite
{

  private int bulletLife = 0;

  /**
   * Creates the Bullet object at the indicated position and with the indicated vector.
   * 
   * @param position
   *          the position of the bullet.
   * @param vector
   *          the movement of the bullet.
   */
  public Bullet(Pose position, Vector2D vector)
  {
    super(position, vector);
  }

  /**
   * Creates the bullet at the indicated position. Creates a new vector with the current position
   * and the constant speed of a bullet.
   * 
   * @param position
   *          the position of the bullet.
   */
  public Bullet(Pose position)
  {
    this.position = position;
    vector = new Vector2D(this.position.getHeading(), GameConstants.BULLET_SPEED);
  }

  /**
   * Gets the amount of time the bullet has been displayed.
   * 
   * @return the amount of time the bullet has been displayed.
   */
  public int getBulletLife()
  {
    return bulletLife;
  }

  @Override
  public void draw()
  {
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.filledCircle(this.getPosition().getX(), this.getPosition().getY(),
        GameConstants.BULLET_RADIUS);
  }

  @Override
  public void update()
  {
    position.moveAndWrap(vector, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_WIDTH);
    bulletLife++;
  }

}
