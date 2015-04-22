/*
 * CSCB402 - Visual Java
 * Variant 2
 * Student: Miglen Evlogiev
 * Faculty number: F43454
 * Protocol: 19063
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.*;

/**
 * The Class KvadratCvqtControl.
 */
public class KvadratCvqtControl extends JPanel implements ActionListener {
    
    private JComboBox dropdown;
    private JLabel label;    
    private Color color = Color.red;
    
    /**
     * Instantiates a new kvadrat cvqt control.
     */
    public KvadratCvqtControl(){
        
        label = new JLabel();
        label.setText("Цвят на квадрата");
        label.setBounds(0, 0, 150, 25);

        dropdown = new JComboBox();
        dropdown.setModel(new DefaultComboBoxModel(new String[] { "Червен", "Зелен", "Син" }));
        dropdown.setSelectedIndex(0);
        dropdown.setBounds(0,30, 100, 25);
        dropdown.addActionListener(this);
        
        this.setBounds(350, 275, 285, 65);
        // this.setBackground(new Color(192, 192, 192));
        this.setLayout(null);
        
        this.add(dropdown);
        this.add(label);
    }
    
    
    /**
     * Sets the color.
     *
     * @param new_color the new color
     */
    public void setColor(Color new_color){
        Color old_color = this.color;
        this.color = new_color;
        
        firePropertyChange("color", old_color, new_color);
        
    }    

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       

        Color oldCol = this.color;
        switch(dropdown.getSelectedIndex()){
            case 0:
                setColor(Color.red);
                break;
            case 1:
                setColor(Color.green);
                break;
            case 2:
                setColor(Color.blue);
                break;
        }
    }
}