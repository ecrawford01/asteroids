/**
 * An Asteroid object is an open circle that is generated at a random location of the screen. When
 * asteroids are destroyed, they add 20 points to the score.
 * 
 * References and Acknowledgments: I received no help making this class.
 * 
 * @author Ethan Crawford
 * @version 1.0 4/3/19
 *
 */
public class Asteroid extends Sprite
{

  /**
   * Creates an Asteroid at the specified position with the specified movement.
   * 
   * @param position
   *          the position of the Asteroid.
   * @param vector
   *          the movement of the asteroid.
   */
  public Asteroid(Pose position, Vector2D vector)
  {
    super(position, vector);
  }

  /**
   * Creates an Asteroid with the specified position and sets the vector to an asteroids constant
   * speed.
   */
  public Asteroid()
  {
    super();
    vector = new Vector2D(position.getHeading(), GameConstants.ASTEROID_SPEED);
    collisionRadius = GameConstants.ASTEROID_RADIUS;
  }

  /**
   * Gets the points of the asteroid.
   * 
   * @return the points of the asteroid.
   */
  public int getPoints()
  {
    return GameConstants.ASTEROID_POINTS;
  }

  @Override
  public void draw()
  {
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.circle(position.getX(), position.getY(), GameConstants.ASTEROID_RADIUS);
  }

  @Override
  public void update()
  {
    position.moveAndWrap(vector, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
  }
}
