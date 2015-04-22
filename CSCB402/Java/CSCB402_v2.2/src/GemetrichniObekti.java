/*
 * CSCB402 - Visual Java
 * Variant 2
 * Student: Miglen Evlogiev
 * Faculty number: F43454
 * Protocol: 19063
 */
import java.awt.*;
import java.beans.*;

/**
 * The Class GemetrichniObekti.
 */
public class GemetrichniObekti  extends Canvas implements VetoableChangeListener, PropertyChangeListener {

    private final int ramka = 250;
    private final int krug_radius = 5;
    private int krug_offset = 0;
    private int kvadrat_size = 80;
    private Color kvadrat_color = Color.red;
    private final Color krug_color = Color.black;
    private final Color ramka_color = Color.white;

    
    /**
     * Instantiates a new gemetrichni obekti.
     */
    public GemetrichniObekti(){
        setSize(ramka,ramka);
        this.setBounds(250, 0, ramka, ramka);
        
    }    
    

    /* (non-Javadoc)
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent e){
        if ((e.getPropertyName()).equals("size")){
            // System.out.println("New Side value"+e.getNewValue());
            setMySize((Integer)e.getNewValue());
        }
        
        if ((e.getPropertyName()).equals("krug")){
            // System.out.println("New Offset value"+e.getNewValue());
            setOffset((Integer)e.getNewValue());
        }
        
        if ((e.getPropertyName().equals("color"))){
            // System.out.println("New Color: "+e.getNewValue());
            setColor((Color)e.getNewValue());
        }
        
        this.repaint();
    }

    public boolean canSetSize(int size){
        if(size<=this.ramka)
            return true;
        
        // add another case if outside of the circle
        
        return false;
    }

    
    public void setOffset(int offset){
        this.krug_offset = offset;
    }    
    
    public void setMySize(int size){
        this.kvadrat_size = size;
    }
    
    public void setColor(Color c){
        this.kvadrat_color = c;
    }    
    
    @Override
    public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException{
        if ((e.getPropertyName()).equals("size")){
            int new_size = (Integer) e.getNewValue();
            if((new_size<=(krug_radius+krug_offset)*2)|| (new_size<=-(krug_offset-krug_radius)*2)||(new_size > ramka)) 
            throw new PropertyVetoException ("Value out of bounds @side!", e);
        }
        if ((e.getPropertyName()).equals("krug")){
            int new_offset = (Integer) e.getNewValue();
            if((new_offset >= (kvadrat_size-(krug_radius*2))/2)||(new_offset > kvadrat_size) || (new_offset <= -(kvadrat_size-(krug_radius*2))/2)) 
            throw new PropertyVetoException ("Value out of bounds @offset!", e);
        }
    }
    
    

    /* (non-Javadoc)
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);           
        
        g2d.setColor(ramka_color);
        g2d.fillRect(0,0,ramka,ramka);
        
        g2d.setColor(kvadrat_color);
        g2d.fillRect((ramka/2)-(kvadrat_size/2), (ramka/2)-(kvadrat_size/2), kvadrat_size, kvadrat_size);
        
        g2d.setColor(krug_color);
        g2d.fillOval((ramka/2)-krug_radius+krug_offset, (ramka/2)-krug_radius-krug_offset, (krug_radius*2), (krug_radius*2));
    } 
}