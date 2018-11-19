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
        name = newName;
    }
    
    public void setLocation(int newX, int newY)
    {
        coord.setX(newX);
        coord.setY(newY);
    }
    
    public void setImage(ImageIcon newIcon)
    {
        theIcon = newIcon;
    }
    
    public void setPower(int newPower)
    {
        power = newPower;
    }
    
    public void setSide(boolean color)
    {
        isRedSide = color;
    }
    
    public void Jumpable(boolean isJumpable)
    {
        canJump = isJumpable;
    }
    
    public void Swimable(boolean isSwimable)
    {
        canSwim = isSwimable;
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
        return theIcon;
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
