import javax.swing.ImageIcon;

public class Rat extends Animal {
    String name;
    Location startLocation = new Location();
    ImageIcon theIcon;
    int power = 1;
    boolean canSwim = true;
    boolean canJump = false;
    boolean isRedSide;

	public Rat(boolean color) {
		this.isRedSide = color;
        if (color) {
        	name = "Red rat";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/red/r1.png"));
        	startLocation.setX(6);
        	startLocation.setY(2);
        }
        else {
        	name = "Black rat";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/black/b1.png"));
        	startLocation.setX(0);
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
