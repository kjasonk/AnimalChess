import javax.swing.ImageIcon;

public class Elephant extends Animal{

	String name;
    Location startLocation = new Location();
    ImageIcon theIcon;
    int power = 8;
    boolean canSwim = false;
    boolean canJump = false;
    boolean isRedSide;
    
    public Elephant(boolean color) {
		this.isRedSide = color;
        if (color) {
        	name = "Red Elephant";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/red/r8.png"));
        	startLocation.setX(0);
        	startLocation.setY(2);
        }
        else {
        	name = "Black Elephant";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/black/b8.png"));
        	startLocation.setX(6);
        	startLocation.setY(6);
        }
	}
    
    public void setLocation(int newX, int newY)
    {
        coord.setX(newX);
        coord.setY(newY);
    }
    
    public String getName()
    {
        return name;
    }
    
    public Location getLocation()
    {
        return startLocation;
    }
    
    public ImageIcon getImage()
    {
        return this.theIcon;
    }
    
    public int getPower()
    {
        return power;
    }
    
    public boolean isSwimable()
    {
        return canSwim;
    }
    
    public boolean isJumpable()
    {
        return canJump;
    }
    
    public boolean isRed()
    {
        return isRedSide;
    }
}
