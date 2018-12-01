import javax.swing.ImageIcon;

public class Dog extends Animal{
	String name;
    Location startLocation = new Location();
    ImageIcon theIcon;
    int power = 3;
    boolean canSwim = false;
    boolean canJump = false;
    boolean isRedSide;
    
    public Dog(boolean color) {
		this.isRedSide = color;
        if (color) {
        	name = "Red Dog";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/red/r3.png"));
        	startLocation.setX(5);
        	startLocation.setY(1);
        }
        else {
        	name = "Black Dog";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/black/b3.png"));
        	startLocation.setX(1);
        	startLocation.setY(7);
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
