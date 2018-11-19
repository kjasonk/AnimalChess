import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChessBoard{
    
    JButton[][] board = new JButton[7][9];
    Animal blackRat = new Animal();
    Animal blackCat = new Animal();
    Animal blackDog = new Animal();
    Animal blackWolf = new Animal();
    Animal blackLeopard = new Animal();
    Animal blackTiger = new Animal();
    Animal blackLion = new Animal();
    Animal blackElephant = new Animal();
    Animal redRat = new Animal();
    Animal redCat = new Animal();
    Animal redDog = new Animal();
    Animal redWolf = new Animal();
    Animal redLeopard = new Animal();
    Animal redTiger = new Animal();
    Animal redLion = new Animal();
    Animal redElephant = new Animal();
    ImageIcon river = new ImageIcon (getClass().getResource("Assets/river.png"));
    ImageIcon grass = new ImageIcon (getClass().getResource("Assets/grass.png"));
    ImageIcon den = new ImageIcon (getClass().getResource("Assets/den.png"));
    ImageIcon trap = new ImageIcon (getClass().getResource("Assets/trap.png"));
    boolean isRedTurn = true;
    boolean isMove = false;
    boolean jumpAttempt = false;
    JFrame jfrm = new JFrame("Animal Chess");
    JButton selectedButton;
    Animal selectedAnimal;
    
    public ChessBoard() {
        // Specify BorderLayout for the layout manager. 
        jfrm.getContentPane().setLayout(new BorderLayout());

        // Give the frame an initial size. 
        jfrm.setSize(800, 600);

        // Set application to center when open
        jfrm.setLocationRelativeTo(null);

        // Terminate the program when the user closes the application. 
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set icon
        jfrm.setIconImage(new ImageIcon("Assets/den.png").getImage());
        
        // Create board
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(7, 9));
        
        setBlackAnimal();
        setRedAnimal();
        
        // Assign river, trap, den, and grass image to the button
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                JButton btn = new JButton();
                btn.addActionListener(new GamePlay());
                if(isRiverNext(i,j))
                {
                    btn.setIcon(river);
                }
                
                if(isTrapNext(i,j))
                {
                    //btn.setText("Trap");
                    btn.setIcon(trap);
                }
                
                if(i == 3 && j == 0 || i == 3 && j == 8)
                {
                    //btn.setText("Den");
                    btn.setIcon(den);
                }
                
                // Assign animal image to the button icon by calling each animal location
                if(i == blackRat.getLocation().getX() && j == blackRat.getLocation().getY())
                    btn.setIcon(blackRat.getImage());
                else if(i == blackCat.getLocation().getX() && j == blackCat.getLocation().getY())
                    btn.setIcon(blackCat.getImage());
                else if(i == blackDog.getLocation().getX() && j == blackDog.getLocation().getY())
                    btn.setIcon(blackDog.getImage());
                else if(i == blackWolf.getLocation().getX() && j == blackWolf.getLocation().getY())
                    btn.setIcon(blackWolf.getImage());
                else if(i == blackLeopard.getLocation().getX() && j == blackLeopard.getLocation().getY())
                    btn.setIcon(blackLeopard.getImage());
                else if(i == blackTiger.getLocation().getX() && j == blackTiger.getLocation().getY())
                    btn.setIcon(blackTiger.getImage());
                else if(i == blackLion.getLocation().getX() && j == blackLion.getLocation().getY())
                    btn.setIcon(blackLion.getImage());
                else if(i == blackElephant.getLocation().getX() && j == blackElephant.getLocation().getY())
                    btn.setIcon(blackElephant.getImage());
                else if(i == redRat.getLocation().getX() && j == redRat.getLocation().getY())
                    btn.setIcon(redRat.getImage());
                else if(i == redCat.getLocation().getX() && j == redCat.getLocation().getY())
                    btn.setIcon(redCat.getImage());
                else if(i == redDog.getLocation().getX() && j == redDog.getLocation().getY())
                    btn.setIcon(redDog.getImage());
                else if(i == redWolf.getLocation().getX() && j == redWolf.getLocation().getY())
                    btn.setIcon(redWolf.getImage());
                else if(i == redLeopard.getLocation().getX() && j == redLeopard.getLocation().getY())
                    btn.setIcon(redLeopard.getImage());
                else if(i == redTiger.getLocation().getX() && j == redTiger.getLocation().getY())
                    btn.setIcon(redTiger.getImage());
                else if(i == redLion.getLocation().getX() && j == redLion.getLocation().getY())
                    btn.setIcon(redLion.getImage());
                else if(i == redElephant.getLocation().getX() && j == redElephant.getLocation().getY())
                    btn.setIcon(redElephant.getImage());
                
                if(btn.getIcon() == null)
                    btn.setIcon(grass);
                btn.setSize(100,100);
                board[i][j] = btn;
                p1.add(board[i][j]);
            }
        }
        
        jfrm.add(p1, BorderLayout.CENTER);
        jfrm.setVisible(true);
    }
    
    // Where action happen
    class GamePlay implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e){
            JButton selected = (JButton) e.getSource();
            ImageIcon selectedIcon = null;
            
            // Same animal got selected twice -> unselect the animal      
            if(selectedButton == selected)
            {
                selectedButton = null;
                selectedAnimal = null;
                selected.setFocusPainted(false);
            }
            else
            {
                selectedIcon = (ImageIcon) selected.getIcon();
            }
                
            if(selectedAnimal != null && selectedIcon != null)
            {
                // Second button selected
                
                Location position = selectedAnimal.getLocation();
                int rowPos = position.getX();
                int columnPos = position.getY();
                int nextRowPos = 0, nextColumnPos = 0;
                
                for(int i = 0; i < 7; i++)
                {
                    for(int j = 0; j < 9; j++)
                    {
                        if(board[i][j] == selected)
                        {
                            nextRowPos = i;
                            nextColumnPos = j;
                        }
                    }
                }
                //Case: tiger and lion case
                if(selectedAnimal.getPower() == 6 || selectedAnimal.getPower() == 7)
                {
                    // jump horizontally
                    if(rowPos == nextRowPos)
                        if(columnPos - nextColumnPos == 4 || nextColumnPos - columnPos == 4)
                        {
                            // Check if any animal is in the river or if the next 3 tiles are river
                            if(river == board[rowPos][columnPos + 1].getIcon() && river == board[rowPos][columnPos+2].getIcon()
                                    && river == board[rowPos][columnPos+3].getIcon())
                            {
                                // Jump to grass
                                if(grass == board[nextRowPos][nextColumnPos].getIcon())
                                {
                                    swapImage(selectedButton, selected);
                                    selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                    isMove = true;
                                }
                                else
                                // Jump to animal
                                {
                                    Animal opponent = null;
                                    if(isRedTurn)
                                        opponent = getBlackAnimal((ImageIcon) selected.getIcon());
                                    else
                                        opponent = getRedAnimal((ImageIcon) selected.getIcon());

                                    if(opponent != null)
                                    {
                                        if(opponent.getPower() <= selectedAnimal.getPower())
                                        {
                                            swapImage(selectedButton, selected);
                                            selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                            selectedButton.setIcon(grass);
                                            isMove = true;
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(jfrm, selectedAnimal.getName() + " cannot capture" + opponent.getName());
                                        }
                                    }
                                }
                            }
                            else
                            {
                                jumpAttempt = true;
                                JOptionPane.showMessageDialog(jfrm, "Animal is in the way.");
                            }
                        }
                    if(columnPos == nextColumnPos)
                    {
                        // Jump vertically
                        if(rowPos - nextRowPos == 3 || nextRowPos - rowPos == 3)
                        {
                            // Check if any animal is in the river or if the next 2 tiles are river
                            if(river == board[rowPos + 1][columnPos].getIcon() && river == board[rowPos+2][columnPos].getIcon())
                            {
                                // Jump to grass
                               if(grass == board[nextRowPos][nextColumnPos].getIcon())
                               {
                                   swapImage(selectedButton, selected);
                                   selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                   isMove = true;
                               }
                               else
                               // Jump to animal
                               {
                                   Animal opponent = null;
                                   if(isRedTurn)
                                       opponent = getBlackAnimal((ImageIcon) selected.getIcon());
                                   else
                                       opponent = getRedAnimal((ImageIcon) selected.getIcon());

                                   if(opponent != null)
                                   {
                                       if(opponent.getPower() <= selectedAnimal.getPower())
                                       {
                                           swapImage(selectedButton, selected);
                                           selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                           selectedButton.setIcon(grass);
                                           isMove = true;
                                       }
                                       else
                                       {
                                           JOptionPane.showMessageDialog(jfrm, selectedAnimal.getName() + " cannot capture" + opponent.getName());
                                       }
                                   }
                               }
                            }
                            else
                            {
                                jumpAttempt = true;
                                JOptionPane.showMessageDialog(jfrm, "Animal is in the way.");
                            }
                        }
                    }
                }
                if(!isMove)
                {
                    if(rowPos - nextRowPos == 1 || nextRowPos - rowPos == 1)
                    {
                        // Going vertically
                        if(columnPos == nextColumnPos)
                        {
                            //Case: next tile is river
                            if(river == board[nextRowPos][nextColumnPos].getIcon())
                            {
                                if(selectedAnimal.isSwimable())
                                {
                                    swapImage(selectedButton, selected);
                                    selectedButton.setIcon(grass);
                                    selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                    if(isRiverNext(rowPos, columnPos))
                                    {
                                        selectedButton.setIcon(river);
                                    }
                                    isMove = true;
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(jfrm, selectedAnimal.getName() + " cannot swim.");
                                }
                            }
                            //Case: next tile is trap
                            else if(trap == board[nextRowPos][nextColumnPos].getIcon())
                            {
                                swapImage(selectedButton, selected);
                                selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                selectedButton.setIcon(grass);
                                if(!selectedAnimal.isRed() || !isRedTrap(nextRowPos, nextColumnPos))
                                {
                                    selectedAnimal.setPower(0);
                                }
                                isMove = true;
                            }
                            //Case: next tile is den
                            else if(den == board[nextRowPos][nextColumnPos].getIcon())
                            {
                               if(isRedTurn)  
                                {
                                    if(nextRowPos == 3 && nextColumnPos == 8)
                                    {
                                        swapImage(selectedButton, selected);
                                        selectedButton.setIcon(trap);
                                        int i = JOptionPane.showConfirmDialog(jfrm, "Red won.");
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(jfrm, "Animal cannot move into their own den.");
                                    }
                                }
                                else
                                {
                                    if(nextRowPos == 3 && nextColumnPos == 0)
                                    {
                                        swapImage(selectedButton, selected);
                                        selectedButton.setIcon(trap);
                                        int i = JOptionPane.showConfirmDialog(jfrm, "Black won.");
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(jfrm, "Animal cannot move into their own den.");
                                    }
                                }
                            }
                            //Case: next tile is grass
                            else if(grass == board[nextRowPos][nextColumnPos].getIcon())
                            {
                                swapImage(selectedButton, selected);
                                selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                if(isRiverNext(rowPos, columnPos))
                                {
                                    selectedButton.setIcon(river);
                                }
                                if(isTrapNext(rowPos, columnPos))
                                {
                                    selectedButton.setIcon(trap);
                                    if(selectedAnimal.getPower() == 0)
                                    {
                                        regainPower(selectedAnimal);
                                    }
                                }
                                isMove = true;
                            }
                            //Case: next tile is an animal
                            else
                            {
                                Animal opponent = null;
                                if(isRedTurn)
                                    opponent = getBlackAnimal((ImageIcon) selected.getIcon());
                                else
                                    opponent = getRedAnimal((ImageIcon) selected.getIcon());

                                if(opponent != null)
                                {
                                    if((opponent.getPower() == 1 && selectedAnimal.getPower() == 8))
                                    {
                                        JOptionPane.showMessageDialog(jfrm, "Elephant cannot capture rat.");
                                    }
                                    else if(opponent.getPower() <= selectedAnimal.getPower() 
                                            || (opponent.getPower() == 8 && selectedAnimal.getPower() == 1))
                                    {
                                        if(!isRiverNext(nextRowPos, nextColumnPos))
                                        {
                                            swapImage(selectedButton, selected);
                                            selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                        }
                                        else if (isRiverNext(nextRowPos, nextColumnPos))
                                        {
                                            if (selectedAnimal.isSwimable())
                                            {
                                               swapImage(selectedButton, selected);
                                               selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                            }
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(jfrm, selectedAnimal.getName() + "cannot swim.");
                                        }
                                        
                                        if(isRiverNext(rowPos, columnPos))
                                        {
                                            selectedButton.setIcon(river);
                                        }
                                        else if(isTrapNext(rowPos, columnPos))
                                        {
                                            selectedButton.setIcon(trap);
                                        }
                                        else
                                        {
                                            selectedButton.setIcon(grass);
                                        }
                                        isMove = true;
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(jfrm, selectedAnimal.getName() + " cannot capture" + opponent.getName());
                                    }
                                }
                            }
                        }
                        else
                        {
                            if(!jumpAttempt)
                            {
                                JOptionPane.showMessageDialog(jfrm, "Animal can only go 1 step at a time.");
                                jumpAttempt = false;
                            }
                        }
                    }
                    else if(columnPos - nextColumnPos == 1 || nextColumnPos - columnPos == 1)
                    {
                        //Going horizontally
                        if(rowPos == nextRowPos)
                        {
                            //Case: next tile is river
                            if(river == board[nextRowPos][nextColumnPos].getIcon())
                            {
                                if(selectedAnimal.isSwimable())
                                {
                                    swapImage(selectedButton, selected);
                                    selectedButton.setIcon(grass);
                                    selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                    if(isRiverNext(rowPos, columnPos))
                                    {
                                        selectedButton.setIcon(river);
                                    }
                                    isMove = true;
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(jfrm, selectedAnimal.getName() + " cannot swim.");
                                }
                            }
                            //Case: next tile is trap
                            else if(trap == board[nextRowPos][nextColumnPos].getIcon())
                            {
                                swapImage(selectedButton, selected);
                                selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                selectedButton.setIcon(grass);
                                if(!selectedAnimal.isRed() || !isRedTrap(nextRowPos, nextColumnPos))
                                {
                                    selectedAnimal.setPower(0);
                                }
                                isMove = true;
                            }
                            //Case: next tile is den
                            else if(den == board[nextRowPos][nextColumnPos].getIcon())
                            {
                                if(isRedTurn)  
                                {
                                    if(nextRowPos == 3 && nextColumnPos == 8)
                                    {
                                        swapImage(selectedButton, selected);
                                        selectedButton.setIcon(trap);
                                        int i = JOptionPane.showConfirmDialog(jfrm, "Red won.");
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(jfrm, "Animal cannot move into their own den.");
                                    }
                                }
                                else
                                {
                                    if(nextRowPos == 3 && nextColumnPos == 0)
                                    {
                                        swapImage(selectedButton, selected);
                                        selectedButton.setIcon(trap);
                                        int i = JOptionPane.showConfirmDialog(jfrm, "Black won.");
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(jfrm, "Animal cannot move into their own den.");
                                    }
                                }
                            }
                            //Case: next tile is grass
                            else if(grass == board[nextRowPos][nextColumnPos].getIcon())
                            {
                                swapImage(selectedButton, selected);
                                selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                if(isRiverNext(rowPos, columnPos))
                                {
                                    selectedButton.setIcon(river);
                                }
                                if(isTrapNext(rowPos, columnPos))
                                {
                                    selectedButton.setIcon(trap);
                                    if(selectedAnimal.getPower() == 0)
                                    {
                                        regainPower(selectedAnimal);
                                    }
                                }
                                isMove = true;
                            }
                            //Case: next tile is an animal
                            else
                            {
                                Animal opponent = null;
                                if(isRedTurn)
                                    opponent = getBlackAnimal((ImageIcon) selected.getIcon());
                                else
                                    opponent = getRedAnimal((ImageIcon) selected.getIcon());

                                if(opponent != null)
                                {
                                    if((opponent.getPower() == 1 && selectedAnimal.getPower() == 8))
                                    {
                                        JOptionPane.showMessageDialog(jfrm, "Elephant cannot win against rat.");
                                    }
                                    else if(opponent.getPower() <= selectedAnimal.getPower() 
                                            || (opponent.getPower() == 8 && selectedAnimal.getPower() == 1))
                                    {
                                        if(!isRiverNext(nextRowPos, nextColumnPos))
                                        {
                                            swapImage(selectedButton, selected);
                                            selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                        }
                                        else if (isRiverNext(nextRowPos, nextColumnPos))
                                        {
                                            if (selectedAnimal.isSwimable())
                                            {
                                                swapImage(selectedButton, selected);
                                                selectedAnimal.setLocation(nextRowPos, nextColumnPos);
                                            }
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(jfrm, selectedAnimal.getName() + "cannot swim.");
                                        }

                                        if(isRiverNext(rowPos, columnPos))
                                        {
                                            selectedButton.setIcon(river);
                                        }
                                        else if(isTrapNext(rowPos, columnPos))
                                        {
                                            selectedButton.setIcon(trap);
                                        }
                                        else
                                        {
                                            selectedButton.setIcon(grass);
                                        }
                                        isMove = true;
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(jfrm, selectedAnimal.getName() + " cannot capture" + opponent.getName());
                                    }
                                }
                            }
                        }
                        else
                        {
                            if(!jumpAttempt)
                            {
                                jumpAttempt = false;
                                JOptionPane.showMessageDialog(jfrm, "Animal can only go 1 step at a time.");
                            }
                        }
                    }
                    else
                    {
                        if(!jumpAttempt)
                        {
                            jumpAttempt = false;
                            JOptionPane.showMessageDialog(jfrm, "Animal can only go 1 step at a time.");
                        }
                    }
                }
                
                selectedButton = null;
                selectedAnimal = null;

                if(isMove)
                {
                    if(isRedTurn)
                        isRedTurn = false;
                    else
                        isRedTurn = true;
                    isMove = false;
                }
            }
            else if(selectedIcon != null)
            {
                //First selected button (required with animal image)
                if(selectedIcon != river && selectedIcon != grass && selectedIcon != den && selectedIcon != trap)
                {
                    if(selectedButton == null)
                    {
                        if (isRedTurn)
                            selectedAnimal = getRedAnimal(selectedIcon);
                        else
                            selectedAnimal = getBlackAnimal(selectedIcon);
                        if(selectedAnimal != null)
                            selectedButton = selected;
                    }
                }
                else
                {
                    selected.setFocusPainted(false);
                }
            }
        }
    }
    
    // Set Black Animal Variable
    private void setBlackAnimal()
    {
        blackRat.setName("black rat");
        blackRat.setImage(new ImageIcon (getClass().getResource("Assets/black/b1.png")));
        blackRat.setLocation(0, 6);
        blackRat.setPower(1);
        blackRat.Jumpable(false);
        blackRat.Swimable(true);
        blackRat.setSide(false);
        
        blackCat.setName("black cat");
        blackCat.setImage(new ImageIcon (getClass().getResource("Assets/black/b2.png")));
        blackCat.setLocation(5, 7);
        blackCat.setPower(2);
        blackCat.Jumpable(false);
        blackCat.Swimable(false);
        blackCat.setSide(false);
        
        blackDog.setName("black dog");
        blackDog.setImage(new ImageIcon (getClass().getResource("Assets/black/b3.png")));
        blackDog.setLocation(1, 7);
        blackDog.setPower(3);
        blackDog.Jumpable(false);
        blackDog.Swimable(false);
        blackDog.setSide(false);
        
        blackWolf.setName("black wolf");
        blackWolf.setImage(new ImageIcon (getClass().getResource("Assets/black/b4.png")));
        blackWolf.setLocation(4, 6);
        blackWolf.setPower(4);
        blackWolf.Jumpable(false);
        blackWolf.Swimable(false);
        blackWolf.setSide(false);
        
        blackLeopard.setName("black leopard");
        blackLeopard.setImage(new ImageIcon (getClass().getResource("Assets/black/b5.png")));
        blackLeopard.setLocation(2, 6);
        blackLeopard.setPower(5);
        blackLeopard.Jumpable(false);
        blackLeopard.Swimable(false);
        blackLeopard.setSide(false);
        
        blackTiger.setName("black tiger");
        blackTiger.setImage(new ImageIcon (getClass().getResource("Assets/black/b6.png")));
        blackTiger.setLocation(6, 8);
        blackTiger.setPower(6);
        blackTiger.Jumpable(true);
        blackTiger.Swimable(false);
        blackTiger.setSide(false);
        
        blackLion.setName("black lion");
        blackLion.setImage(new ImageIcon (getClass().getResource("Assets/black/b7.png")));
        blackLion.setLocation(0, 8);
        blackLion.setPower(7);
        blackLion.Jumpable(true);
        blackLion.Swimable(false);
        blackLion.setSide(false);
        
        blackElephant.setName("black elephant");
        blackElephant.setImage(new ImageIcon (getClass().getResource("Assets/black/b8.png")));
        blackElephant.setLocation(6, 6);
        blackElephant.setPower(8);
        blackElephant.Jumpable(false);
        blackElephant.Swimable(false);
        blackElephant.setSide(false);
        
    }
    
    // Set Red Animal Variable
    private void setRedAnimal()
    {
        redRat.setName("red rat");
        redRat.setImage(new ImageIcon (getClass().getResource("Assets/red/r1.png")));
        redRat.setLocation(6, 2);
        redRat.setPower(1);
        redRat.Jumpable(false);
        redRat.Swimable(true);
        redRat.setSide(true);
        
        redCat.setName("red cat");
        redCat.setImage(new ImageIcon (getClass().getResource("Assets/red/r2.png")));
        redCat.setLocation(1, 1);
        redCat.setPower(2);
        redCat.Jumpable(false);
        redCat.Swimable(false);
        redCat.setSide(true);
        
        redDog.setName("red dog");
        redDog.setImage(new ImageIcon (getClass().getResource("Assets/red/r3.png")));
        redDog.setLocation(5, 1);
        redDog.setPower(3);
        redDog.Jumpable(false);
        redDog.Swimable(false);
        redDog.setSide(true);
        
        redWolf.setName("red wolf");
        redWolf.setImage(new ImageIcon (getClass().getResource("Assets/red/r4.png")));
        redWolf.setLocation(2, 2);
        redWolf.setPower(4);
        redWolf.Jumpable(false);
        redWolf.Swimable(false);
        redWolf.setSide(true);
        
        redLeopard.setName("red leopard");
        redLeopard.setImage(new ImageIcon (getClass().getResource("Assets/red/r5.png")));
        redLeopard.setLocation(4, 2);
        redLeopard.setPower(5);
        redLeopard.Jumpable(false);
        redLeopard.Swimable(false);
        redLeopard.setSide(true);
        
        redTiger.setName("red tiger");
        redTiger.setImage(new ImageIcon (getClass().getResource("Assets/red/r6.png")));
        redTiger.setLocation(0, 0);
        redTiger.setPower(6);
        redTiger.Jumpable(true);
        redTiger.Swimable(false);
        redTiger.setSide(true);
        
        redLion.setName("red lion");
        redLion.setImage(new ImageIcon (getClass().getResource("Assets/red/r7.png")));
        redLion.setLocation(6, 0);
        redLion.setPower(7);
        redLion.Jumpable(true);
        redLion.Swimable(false);
        redLion.setSide(true);
        
        redElephant.setName("red elephant");
        redElephant.setImage(new ImageIcon (getClass().getResource("Assets/red/r8.png")));
        redElephant.setLocation(0, 2);
        redElephant.setPower(8);
        redElephant.Jumpable(false);
        redElephant.Swimable(false);
        redElephant.setSide(true);
    }
    
    // This function is called when animal step out of the trap
    private void regainPower(Animal injuredAnimal)
    {
        if(injuredAnimal.getName().contains("rat"))
        {
            injuredAnimal.setPower(1);
        }
        else if(injuredAnimal.getName().contains("cat"))
        {
            injuredAnimal.setPower(2);
        }
        else if(injuredAnimal.getName().contains("dog"))
        {
            injuredAnimal.setPower(3);
        }
        else if(injuredAnimal.getName().contains("wolf"))
        {
            injuredAnimal.setPower(4);
        }
        else if(injuredAnimal.getName().contains("leopard"))
        {
            injuredAnimal.setPower(5);
        }
        else if(injuredAnimal.getName().contains("tiger"))
        {
            injuredAnimal.setPower(6);
        }
        else if(injuredAnimal.getName().contains("lion"))
        {
            injuredAnimal.setPower(7);
        }else if(injuredAnimal.getName().contains("elephant"))
        {
            injuredAnimal.setPower(8);
        }
    }
    
    // Check if the next tiles is red trap or black trap.
    private boolean isRedTrap(int x, int y)
    {
        return (x == 2 && y == 0) || (x == 3 && y == 1) || (x == 4 && y == 0);
    }
    
    // Check if the next tiles is trap
    private boolean isTrapNext(int x, int y)
    {
        return (x == 2 && y == 0) || (x == 3 && y == 1) || (x == 4 && y == 0) ||
            (x == 2 && y == 8) || (x == 3 && y == 7) || (x == 4 && y == 8);
    }
    
    // Check if the next tiles is river
    private boolean isRiverNext(int x, int y)
    {
        return x == 1 && y == 3 || x == 1 && y == 4 
                || x == 1 && y == 5 || x == 2 && y == 3
                || x == 2 && y == 4 || x == 2 && y == 5
                || x == 4 && y == 3 || x == 4 && y == 4
                || x == 4 && y == 5 || x == 5 && y == 3
                || x == 5 && y == 4 || x == 5 && y == 5;
    }
    
    // Swap image of two button
    public void swapImage(JButton first, JButton second)
    {
        Icon temp = first.getIcon();
        first.setIcon(second.getIcon());
        second.setIcon(temp);
    }
    
    // This function returns the animal depends on the parameter icon. 
    //If not match, that means the selected animal is red
    public Animal getBlackAnimal(ImageIcon selectedIcon){
        if(selectedIcon == blackRat.getImage())
        {
            return blackRat;
        }
        else if(selectedIcon == blackCat.getImage())
        {
            return blackCat;
        }
        else if(selectedIcon == blackDog.getImage())
        {
            return blackDog;
        }
        else if(selectedIcon == blackWolf.getImage())
        {
            return blackWolf;
        }
        else if(selectedIcon == blackLeopard.getImage())
        {
            return blackLeopard;
        }
        else if(selectedIcon == blackTiger.getImage())
        {
            return blackTiger;
        }
        else if(selectedIcon == blackLion.getImage())
        {
            return blackLion;
        }
        else if(selectedIcon == blackElephant.getImage())
        {
            return blackElephant;
        }
        else
        {
            if(selectedAnimal != null)
                JOptionPane.showMessageDialog(jfrm, "You are fighting your own side.");
            else
                JOptionPane.showMessageDialog(jfrm, "It is black turn.");
            return null;
        }
    }
    
    // This function returns the animal depends on the parameter icon. 
    //If not match, that means the selected animal is black
    public Animal getRedAnimal(ImageIcon selectedIcon){
        if(selectedIcon == redRat.getImage())
        {
            return redRat;
        }
        else if(selectedIcon == redCat.getImage())
        {
            return redCat;
        }
        else if(selectedIcon == redDog.getImage())
        {
            return redDog;
        }
        else if(selectedIcon == redWolf.getImage())
        {
            return redWolf;
        }
        else if(selectedIcon == redLeopard.getImage())
        {
            return redLeopard;
        }
        else if(selectedIcon == redTiger.getImage())
        {
            return redTiger;
        }
        else if(selectedIcon == redLion.getImage())
        {
            return redLion;
        }
        else if(selectedIcon == redElephant.getImage())
        {
            return redElephant;
        }
        else
        {
            if(selectedAnimal != null)
                JOptionPane.showMessageDialog(jfrm, "You are fighting your own side.");
            else
                JOptionPane.showMessageDialog(jfrm, "It is red turn");
            return null;
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(ChessBoard::new);
    }
}
