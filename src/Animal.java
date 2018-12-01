import javax.swing.ImageIcon;

/**
 *
 * @author nathaniel
 */
public class Animal {
    String name;
    Location coord = new Location();
    ImageIcon theIcon;
    int power;
    boolean canSwim;
    boolean canJump;
    boolean isRedSide;
    
    public void setName(String newName)
    {
        this.name = newName;
    }
    
    public void setLocation(int newX, int newY)
    {
        coord.setX(newX);
        coord.setY(newY);
    }
    
    public void setImage(ImageIcon newIcon)
    {
        this.theIcon = newIcon;
    }
    
    public void setPower(int newPower)
    {
        this.power = newPower;
    }
    
    public void setSide(boolean color)
    {
        this.isRedSide = color;
        
    }
    
    public String getName()
    {
        return name;
    }
    
    public Location getLocation()
    {
        return coord;
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
