import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

public class Main
{
  public static final int LENGTH = 1000;
  public static final int HEIGHT = 1000;
  public static final int GRAVITY = 200;
  
  public static void main(String[] paramArrayOfString)
  {
    Random localRandom = new Random();
    ArrayList<Circle> localArrayList = new ArrayList<Circle>();
    for (int i = 0; i < 20; i++)
    {
      int j = localRandom.nextInt(20) + 10;
      int k = j / 10;
      Circle localCircle = new Circle(new Point(localRandom.nextInt(LENGTH - 200) + 50, localRandom.nextInt(HEIGHT - 200) + 50), j, k, 1.0F, new Vector(localRandom.nextInt(300) - 150, localRandom.nextInt(300) - 150));
      localArrayList.add(localCircle);
    }
    GUI2 localGUI2 = new GUI2(localArrayList);
    JFrame localJFrame = new JFrame();
    localJFrame.add(localGUI2);
    localJFrame.setVisible(true);
    localJFrame.setSize(LENGTH, HEIGHT);
    localJFrame.setDefaultCloseOperation(3);
    localJFrame.setTitle("Moving Ball");
  }
}
