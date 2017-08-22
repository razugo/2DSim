import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D.*;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GUI2 extends JPanel implements ActionListener
{
  private static final long serialVersionUID = 1;
  private ArrayList<Circle> shapes;
  private Timer t = new Timer(5, this);
  
  //Constructor of the GUI that takes in an ArrayList
  public GUI2(ArrayList<Circle> paramArrayList)
  {
    this.shapes = paramArrayList;
  }
  
  //Draws all shapes
  public void paintComponent(Graphics paramGraphics)
  {
    super.paintComponent(paramGraphics);
    Graphics2D localGraphics2D = (Graphics2D)paramGraphics;
    for (int i = 0; i < this.shapes.size(); i++)
    {
      Point localPoint = (this.shapes.get(i)).getPosition();
      drawCenteredCircle(localGraphics2D, (int)localPoint.getX(), (int)localPoint.getY(), (int)(this.shapes.get(i)).getRadius());
    }
    this.t.start();
  }
  
  //Moves all shapes and checks if any are colliding
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    for (int i = 0; i < this.shapes.size(); i++)
    {
      (this.shapes.get(i)).move(0.005F, Main.GRAVITY);
      for (int j = 0; j < this.shapes.size(); j++) {
        if (i != j) {
          if (((this.shapes.get(i)).intersectCircle(this.shapes.get(j)))) {
            collision(this.shapes.get(i), this.shapes.get(j));
          }
        }
      }
      Circle localCircle = this.shapes.get(i);
	  
	  //If shape is hitting boarders than velocity is changed
      if ((localCircle.getPosition().getX() < localCircle.getRadius()) || (localCircle.getPosition().getX() > Main.LENGTH - 2.0F * localCircle.getRadius())) {
        (this.shapes.get(i)).reverseX();
      }
      if ((localCircle.getPosition().getY() < localCircle.getRadius()) || (localCircle.getPosition().getY() > Main.HEIGHT - localCircle.getRadius() - 50.0F)) {
        (this.shapes.get(i)).reverseY();
      }
    }
    repaint();
  }
  
  //Draws a circle when given an x, y coordninate and radius
  public void drawCenteredCircle(Graphics2D paramGraphics2D, int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 -= paramInt3 / 2;
    paramInt2 -= paramInt3 / 2;
    int i = 2 * paramInt3;
	paramGraphics2D.fillOval(paramInt1, paramInt2, i, i);
  }
  
  //Calculates the new velocity directions and magnitudes
  public void collision(Circle paramCircle1, Circle paramCircle2)
  {
    Vector localVector1 = new Vector(paramCircle2.getPosition().getX() - paramCircle1.getPosition().getX(), paramCircle2.getPosition().getY() - paramCircle1.getPosition().getY());
    
    System.out.println("Normal Vector: " + localVector1);
    
    localVector1.scale(1.0F / localVector1.magnitude());
    
    System.out.println("Unit Vector: " + localVector1);
    
    Vector localVector2 = new Vector(paramCircle2.getVector().xComp() - paramCircle1.getVector().xComp(), paramCircle2.getVector().yComp() - paramCircle1.getVector().yComp());
    
    System.out.println("Relative Velocity: " + localVector2);
    
    float f1 = Vector.dotProduct(localVector1, localVector2);
    
    System.out.println("Vel Along Normal: " + f1);
    if (f1 > 0.0F) {
      return;
    }
    float f2 = Math.min(paramCircle1.getRestitution(), paramCircle2.getRestitution());
    
    System.out.println("Restitution: " + f2);
    
    float f3 = -(1.0F + f2) * f1;
    
    System.out.println("j before mass: " + f3);
    
    f3 /= (1.0F / paramCircle1.getMass() + 1.0F / paramCircle2.getMass());
    
    System.out.println("j after mass: " + f3);
    
    Vector localVector3 = localVector1.scale(f3);
    
    System.out.println("impulse: " + localVector3);
    
    Vector localVector4 = localVector3.scale(1.0F / paramCircle1.getMass());
    
    System.out.println("DeltaA vector: " + localVector4);
    
    Vector localVector5 = localVector3.scale(1.0F / paramCircle2.getMass());
    
    System.out.println("DeltaB vector: " + localVector5);
    
    paramCircle1.setVector(new Vector(paramCircle1.getVector().xComp() - localVector4.xComp(), paramCircle1.getVector().yComp() - localVector4.yComp()));
    
    System.out.println("New A Velocity: " + paramCircle1.getVector());
    
    paramCircle2.setVector(new Vector(paramCircle2.getVector().xComp() + localVector5.xComp(), paramCircle2.getVector().yComp() + localVector5.yComp()));
    
    System.out.println("New B Velocity: " + paramCircle2.getVector());
  }
}
