public class Circle
{
  private Point position;
  private float radius;
  private float mass;
  private float restitution;
  private Vector vel;
  
  public Circle(Point paramPoint, float paramFloat1, float paramFloat2, float paramFloat3, Vector paramVector)
  {
    this.position = paramPoint;
    this.radius = paramFloat1;
    this.mass = paramFloat2;
    this.restitution = paramFloat3;
    this.vel = paramVector;
  }
  
  //Returns position of the Circle as a point
  public Point getPosition()
  {
    return this.position;
  }
  
  //Returns the radius
  public float getRadius()
  {
    return this.radius;
  }
  
  //Returns the velocity as a vector
  public Vector getVector()
  {
    return this.vel;
  }
  
  //Returns the mass of the object
  public float getMass()
  {
    return this.mass;
  }
  
  public float getRestitution()
  {
    return this.restitution;
  }
  
  public void setRadius(int paramInt)
  {
    this.radius = paramInt;
  }
  
  public void setPosition(Point paramPoint)
  {
    this.position = paramPoint;
  }
  
  public void setVector(Vector paramVector)
  {
    this.vel = paramVector;
  }
  
  public void reverseX()
  {
    this.vel.reverseX();
  }
  
  public void reverseY()
  {
    this.vel.reverseY();
  }
  
  public boolean intersectCircle(Circle paramCircle)
  {
    if (Math.pow(this.radius + paramCircle.getRadius(), 2.0D) > Math.pow(this.position.getX() - paramCircle.getPosition().getX(), 2.0D) + Math.pow(this.position.getY() - paramCircle.getPosition().getY(), 2.0D)) {
      return true;
    }
    return false;
  }
  
  public void move(float paramFloat1, float paramFloat2)
  {
    setPosition(new Point(this.position.getX() + paramFloat1 * this.vel.xComp(), this.position.getY() + paramFloat1 * this.vel.yComp()));
    this.vel.shiftY(paramFloat2 * paramFloat1);
  }
  
  public String toString()
  {
    return this.position.toString();
  }
}
