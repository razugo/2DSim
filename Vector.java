public class Vector
{
  private float x;
  private float y;
  private float magnitude;
  
  public Vector(float paramFloat1, float paramFloat2)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
    this.magnitude = ((float)Math.sqrt(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2));
  }
  
  public float xComp()
  {
    return this.x;
  }
  
  public float yComp()
  {
    return this.y;
  }
  
  public void reverseX()
  {
    this.x = (-this.x);
  }
  
  public void reverseY()
  {
    this.y = (-this.y);
  }
  
  public void shiftY(float paramFloat)
  {
    this.y += paramFloat;
  }
  
  public Vector scale(float paramFloat)
  {
    this.x *= paramFloat;
    this.y *= paramFloat;
    return new Vector(this.x, this.y);
  }
  
  public static Vector addVector(Vector paramVector1, Vector paramVector2)
  {
    return new Vector(paramVector1.xComp() + paramVector2.xComp(), paramVector1.yComp() + paramVector2.yComp());
  }
  
  public static float dotProduct(Vector paramVector1, Vector paramVector2)
  {
    return paramVector1.xComp() * paramVector2.xComp() + paramVector1.yComp() * paramVector2.yComp();
  }
  
  public float magnitude()
  {
    return this.magnitude;
  }
  
  public String toString()
  {
    return this.x + "\t" + this.y;
  }
}
