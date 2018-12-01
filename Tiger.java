import javax.swing.ImageIcon;

public class Tiger extends Animal{

	String name;
    Location startLocation = new Location();
    ImageIcon theIcon;
    int power = 6;
    boolean canSwim = false;
    boolean canJump = true;
    boolean isRedSide;
    
    public Tiger(boolean color) {
		this.isRedSide = color;
        if (color) {
        	name = "Red Tiger";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/red/r6.png"));
        	startLocation.setX(0);
        	startLocation.setY(0);
        }
        else {
        	name = "Black Tiger";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/black/b6.png"));
        	startLocation.setX(6);
        	startLocation.setY(8);
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
