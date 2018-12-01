import javax.swing.ImageIcon;

public class Leopard extends Animal{

	String name;
    Location startLocation = new Location();
    ImageIcon theIcon;
    int power = 5;
    boolean canSwim = false;
    boolean canJump = false;
    boolean isRedSide;    
    public Leopard(boolean color) {
		this.isRedSide = color;
        if (color) {
        	name = "Red Leopard";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/red/r5.png"));
        	startLocation.setX(4);
        	startLocation.setY(2);
        }
        else {
        	name = "Black Leopard";
        	this.theIcon = new ImageIcon(getClass().getResource("Assets/black/b5.png"));
        	startLocation.setX(2);
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
