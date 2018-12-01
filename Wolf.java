import javax.swing.ImageIcon;

public class Wolf extends Animal{
	String name;
    Location startLocation = new Location();
    ImageIcon theIcon;
    int power = 4;
    boolean canSwim = false;
    boolean canJump = false;
    boolean isRedSide;
    
    public Wolf(boolean color) {
		this.isRedSide = color;
        if (color) {
        	name = "Red Wolf";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/red/r4.png"));
        	startLocation.setX(2);
        	startLocation.setY(2);
        }
        else {
        	name = "Black Wolf";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/black/b4.png"));
        	startLocation.setX(4);
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
