/*
 * CSCB402 - Visual Java
 * Variant 2
 * Student: Miglen Evlogiev
 * Faculty number: F43454
 * Protocol: 19063
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.*;


/**
 * The Class KvadratControl.
 */
public class KvadratControl extends JPanel implements ActionListener {

    private JLabel label;
    private JTextField field;
    private JButton button_down,button_up;
    private int size = 80;
    private int old_size = size;
    
    /**
     * Instantiates a new kvadrat control.
     */
    public KvadratControl(){
        
        label = new JLabel();
        label.setText("Размер на квадрата");
        label.setBounds(10, 0, 150, 25);

        field = new JTextField();
        field.setColumns(10);
        field.setBounds(10, 36, 114, 25);
        field.setText(Integer.toString(getMySize()));
        field.addActionListener(this);
        
        button_down = new JButton("-1");
        button_down.setBounds(0, 72, 52, 23);
        button_down.addActionListener(this);
        
        button_up = new JButton("+1");
        button_up.setBounds(72, 72, 52, 23);
        button_up.addActionListener(this);
        
        this.setBounds(10, 10, 200, 100);
        this.setLayout(null);
        
        this.add(label);
        this.add(field);
        this.add(button_down);
        this.add(button_up);
    }
    
    /**
     * Sets the size.
     *
     * @param size the new size
     * @throws PropertyVetoException the property veto exception
     */
    private void setSize(int size) throws PropertyVetoException {       
        this.old_size = this.size;
        this.size = size;        
        firePropertyChange("size", this.old_size, size);
        fireVetoableChange("size", this.old_size, size);
        field.setText(Integer.toString(size));
        // System.out.println("New size: " + size);
    }
    
    /**
     * Gets the my size.
     *
     * @return the my size
     */
    private int getMySize(){
        return this.size;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
           // System.out.println(e.getSource());
            
        if ((e.getSource()).equals(button_up)) setSize(getMySize()+1);
        if ((e.getSource()).equals(button_down)) setSize(getMySize()-1);
        if ((e.getSource()).equals(field)) setSize(Integer.parseInt(field.getText()));
        
        } catch (Exception ex){
            // System.out.println("Exception caught!" + ex.toString());
            // System.out.println("Exception caught!");
            this.size = this.old_size;
            field.setText(Integer.toString(this.old_size));              
        }
    }
    
    
}