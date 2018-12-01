import javax.swing.ImageIcon;

public class Cat extends Animal{
	String name;
    Location startLocation = new Location();
    ImageIcon theIcon;
    int power = 2 ;
    boolean canSwim = false;
    boolean canJump = false;
    boolean isRedSide;
    
    public Cat(boolean color) {
		this.isRedSide = color;
        if (color) {
        	name = "Red Cat";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/red/r2.png"));
        	startLocation.setX(1);
        	startLocation.setY(1);
        }
        else {
        	name = "Black Cat";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/black/b2.png"));
        	startLocation.setX(5);
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
