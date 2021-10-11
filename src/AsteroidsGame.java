import java.util.*;

/**
 * The AsteroidsGame class provides all of the functionality for the Asteroid Game. It creates the
 * stars, generates the score board, and also checks for the collision between all of the objects.
 *  
 * 
 * @author Ethan Crawford
 * @version 1.0 4/3/19
 */
public class AsteroidsGame
{

  private int lives;
  private int score;
  private ArrayList<Point> stars;
  private ArrayList<Asteroid> asteroids;
  private ArrayList<Saucer> saucers;
  private ArrayList<Bullet> bullets;
  private Ship ship;

  /**
   * Creates the Asteroid Game with the desired number of asteroids. Each array list is initialized
   * and created.
   * 
   * @param numAsteroids
   *          the number of asteroids being created in the game.
   */
  public AsteroidsGame(int numAsteroids)
  {
    score = 0;
    lives = GameConstants.NUMBER_OF_LIVES;

    // creating asteroids
    asteroids = new ArrayList<Asteroid>(numAsteroids);
    for (int i = 0; i < numAsteroids; i++)
    {
      asteroids.add(new Asteroid());
    }

    // creating saucers
    saucers = new ArrayList<Saucer>(numAsteroids);

    // creating stars
    stars = new ArrayList<Point>(GameConstants.NUMBER_OF_STARS);
    for (int i = 0; i < GameConstants.NUMBER_OF_STARS; i++)
    {
      Point star = new Point(GameConstants.GENERATOR.nextInt(GameConstants.SCREEN_WIDTH),
          GameConstants.GENERATOR.nextInt(GameConstants.SCREEN_HEIGHT));
      stars.add(star);
    }

    // creating the ship
    ship = new Ship();

    // creating bullets
    bullets = new ArrayList<Bullet>();
  }

  /**
   * Creates the stars that are displayed in the background of the game.
   */
  public void createStars()
  {
    for (int i = 0; i < GameConstants.NUMBER_OF_STARS; i++)
    {
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledCircle(this.stars.get(i).getX(), this.stars.get(i).getY(),
          GameConstants.STAR_RADIUS);
    }
  }

  /**
   * Checks to see if the asteroid collides with the ship.
   */
  public void checkAsteroidCollision()
  {
    Iterator<Asteroid> asteroidI = asteroids.iterator();
    while (asteroidI.hasNext())
    {
      if (asteroidI.next().intersects(ship))
      {
        asteroidI.remove();
        lives--;
        if (lives != 0)
        {
          ship = new Ship();
        }
      }
    }
  }

  /**
   * Checks to see if the saucer collides with the ship.
   */
  public void checkSaucerCollision()
  {
    Iterator<Saucer> saucerI = saucers.iterator();
    while (saucerI.hasNext())
    {
      if (saucerI.next().intersects(ship))
      {
        saucerI.remove();
        lives--;
        if (lives != 0)
        {
          ship = new Ship();
        }
      }
    }
  }

  /**
   * Checks to see if the bullet collides with the asteroid.
   */
  public void checkBulletCollisionAsteroid()
  {
    Iterator<Bullet> bulletI = bullets.iterator();
    while (bulletI.hasNext())
    {
      Bullet currBullet = bulletI.next();
      Iterator<Asteroid> asteroidI = asteroids.iterator();
      while (asteroidI.hasNext())
      {
        Asteroid currAsteroid = asteroidI.next();
        if (currBullet.intersects(currAsteroid))
        {
          asteroidI.remove();
          if (bullets.contains(currBullet))
          {
            bulletI.remove();
          }
          this.score += GameConstants.ASTEROID_POINTS;
        }
      }
    }
  }

  /**
   * Checks to see if the bullet collides with the saucer.
   */
  public void checkBulletCollisionSaucer()
  {
    Iterator<Bullet> bulletI = bullets.iterator();
    while (bulletI.hasNext())
    {
      Bullet currBullet = bulletI.next();
      Iterator<Saucer> saucerI = saucers.iterator();
      while (saucerI.hasNext())
      {
        Saucer currSaucer = saucerI.next();
        if (currBullet.intersects(currSaucer))
        {
          saucerI.remove();
          bulletI.remove();
          this.score += GameConstants.SAUCER_POINTS;
        }
      }
    }
  }

  /**
   * Draws up all of the objects that are being used in the Asteroid game.
   */
  public void draw()
  {
    // generates the score and lives.
    StdDraw.text(GameConstants.SCORE_X, GameConstants.SCORE_Y, "Score: " + this.score);
    StdDraw.text(GameConstants.LIVES_X, GameConstants.LIVES_Y, "Lives: " + this.lives);
    createStars();

    // checks to make sure that the lives are not zero so it can draw the ship.
    if (lives != 0)
    {
      ship.draw();
    }

    // generates the asteroids
    if (asteroids.size() == 0)
    {
      for (int i = 0; i < GameConstants.DEFAULT_ASTEROIDS_PER_LEVEL; i++)
      {
        asteroids.add(new Asteroid());
      }
    }
    for (Asteroid asteroid : asteroids)
    {
      asteroid.draw();
    }

    // generates the saucers at 0.002 probability
    if (GameConstants.GENERATOR.nextDouble() <= GameConstants.SAUCER_APPEARANCE_PROB)
    {
      saucers.add(new Saucer());
    }
    for (int i = 0; i < saucers.size(); i++)
    {
      if (lives != 0)
      {
        saucers.get(i).draw();
      }
    }

    // generating the bullets
    for (int i = 0; i < bullets.size(); i++)
    {
      bullets.get(i).draw();
    }

  }

  /**
   * Updates all of the objects that are created in the game.
   */
  public void update()
  {
    if (lives != 0)
    {
      ship.update();
      checkAsteroidCollision();
      checkSaucerCollision();
      checkBulletCollisionAsteroid();
      checkBulletCollisionSaucer();

      // updating asteroids
      for (Asteroid asteroid : asteroids)
      {
        asteroid.update();
      }

      // updating saucers and checks to see if saucer is out of bounds
      for (Saucer saucer : saucers)
      {
        saucer.update();
      }
      Iterator<Saucer> saucerOut = saucers.iterator();
      while (saucerOut.hasNext())
      {
        Saucer currSaucer = saucerOut.next();
        if (currSaucer.outOfBounds())
        {
          saucerOut.remove();
        }
      }

      // creates bullets when space bar is pressed
      if (StdDraw.hasNextKeyTyped() && StdDraw.nextKeyTyped() == ' ')
      {
        Pose shipP = new Pose(ship.getPosition());
        bullets.add(new Bullet(shipP));
      }

      // checks to see if bullet is in range and updates
      Iterator<Bullet> bulletI = bullets.iterator();
      while (bulletI.hasNext())
      {
        Bullet currBullet = bulletI.next();
        for (Bullet bullet : bullets)
        {
          bullet.update();
        }
        if (currBullet.getBulletLife() >= GameConstants.BULLET_LIFETIME)
        {
          bulletI.remove();
        }
      }
    }
  }
}
