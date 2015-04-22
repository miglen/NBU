package cscb402v2;
/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.beans.*;
/**
 *
 * @author Administrator
 */
public class Picture extends Canvas implements VetoableChangeListener, PropertyChangeListener {
    
    private final int SIZE = 100;
    private final int RADIUS = 5;
    private int side = 80;
    private int off = 0;
    private Color col = Color.GREEN;
    
    public Picture(){
        setSize(SIZE, SIZE);
    }
    @Override
    public void vetoableChange(PropertyChangeEvent pce) throws PropertyVetoException{
        if ((pce.getPropertyName()).equals("side")){
            int newSide = (Integer)pce.getNewValue();
            if((newSide<=(RADIUS+off)*2)|| (newSide<=-(off-RADIUS)*2)||(newSide > SIZE)) 
            throw new PropertyVetoException ("Value out of bounds @side!", pce);
        }
        if ((pce.getPropertyName()).equals("offset")){
            int newOff = (Integer)pce.getNewValue();
            if((newOff >= (side-(RADIUS*2))/2)||(newOff > side) || (newOff <= -(side-(RADIUS*2))/2)) 
            throw new PropertyVetoException ("Value out of bounds @offset!", pce);
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent pce){
        if ((pce.getPropertyName()).equals("side")){
            System.out.println("New Side value"+pce.getNewValue());
            setSide((Integer)pce.getNewValue());
        }
        if ((pce.getPropertyName()).equals("offset")){
            System.out.println("New Offset value"+pce.getNewValue());
            setOffset((Integer)pce.getNewValue());
        }
        if ((pce.getPropertyName().equals("color"))){
            setColor((Color)pce.getNewValue());
        }
        repaint();
    }
    
    public void setSide(int side){
        this.side = side;
    }
    
    public int getSide(){
        return this.side;
    }
    
    public void setOffset(int offset){
        this.off = offset;
    }
    
    public int getOffset(){
        return this.off;
    }
    
    public void setColor(Color col){
        this.col = col;
    }
    
    public Color getColor(){
        return this.col;
    }
    
    @Override
    public void paint(Graphics g){
        Dimension d = getSize();
        g.setColor(Color.white);
        g.fillRect(0,0,SIZE,SIZE);
        g.setColor(col);
        g.fillRect((d.width-side)/2, (d.height-side)/2, side, side);
        g.setColor(Color.BLACK);
        g.fillOval(d.width/2-RADIUS+off, d.height/2-RADIUS-off, RADIUS*2, RADIUS*2);
    }
}
