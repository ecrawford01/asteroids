import java.awt.event.*;

/**
 * A Ship is represented by the player to use. It has the ability to turn and thrust based off of
 * the keyboard codes that are pressed. It is displayed at the center of the screen as a triangle.
 * 
 * 
 * References and Acknowledgments: I received help from TA Maddie.
 * 
 * @author Ethan Crawford
 * @version 1.0 4/3/19
 *
 */
public class Ship extends Sprite
{

  /**
   * Creates a ship at the specified position with the specified movement.
   * 
   * @param position
   *          the position of the ship.
   * @param vector
   *          the movement of the ship.
   */
  public Ship(Pose position, Vector2D vector)
  {
    super(position, vector);
  }

  /**
   * Creates a ship at the center of the screen with no movement to begin. Creates the collision
   * radius for the ship as well.
   */
  public Ship()
  {
    position = new Pose(GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT / 2,
        GameConstants.SHIP_START_HEADING);
    vector = new Vector2D(GameConstants.SHIP_START_HEADING, GameConstants.SHIP_START_SPEED);
    collisionRadius = GameConstants.SHIP_HEIGHT / 2;
  }

  @Override
  public void draw()
  {
    GameUtils.drawPoseAsTriangle(this.position, GameConstants.SHIP_WIDTH,
        GameConstants.SHIP_HEIGHT);
  }

  @Override
  public void update()
  {
    double dir = position.getHeading();

    if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
    {
      position.setHeading(dir + GameConstants.SHIP_TURN_SPEED);
    }
    if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
    {
      position.setHeading(dir - GameConstants.SHIP_TURN_SPEED);
    }
    if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
    {
      GameUtils.applyThrust(vector, position.getHeading(), GameConstants.SHIP_ACCELERATION);
    }
    else
    {
      vector.setMagnitude(vector.getMagnitude() * GameConstants.SHIP_FRICTION);
    }
    position.moveAndWrap(vector, GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
  }
}
