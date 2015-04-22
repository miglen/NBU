package cscb402v2;
/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.*;
import java.beans.PropertyVetoException;
/**
 *
 * @author Administrator
 */
public class squarePanel extends javax.swing.JPanel implements ActionListener{
    private int side = 80;

    /**
     * Creates new form stepPanel
     */
    public squarePanel() {
        initComponents();
        sqUp.addActionListener(this);
        sqDown.addActionListener(this);
        sqFld.addActionListener(this);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sqUp = new javax.swing.JButton();
        sqDown = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        sqFld = new javax.swing.JTextField();

        sqUp.setText("+1");

        sqDown.setText("-1");

        label.setText("Размер на квадрата");

        sqFld.setText("80");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sqUp, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sqDown, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sqFld))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sqFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sqUp)
                    .addComponent(sqDown))
                .addContainerGap())
        );

        sqUp.getAccessibleContext().setAccessibleName("btnUp");
        sqDown.getAccessibleContext().setAccessibleName("btnDown");
    }// </editor-fold>//GEN-END:initComponents

    public int getSide(){
        return this.side;
    }

    public void setSide(int side) throws PropertyVetoException{
        int oldSide = this.side;
        this.side = side;
        fireVetoableChange("side", oldSide, this.side);
        firePropertyChange("side", oldSide, this.side);
        sqFld.setText(Integer.toString(this.side));
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    private javax.swing.JButton sqDown;
    private javax.swing.JTextField sqFld;
    private javax.swing.JButton sqUp;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
        if ((e.getSource()).equals(sqUp)) setSide(getSide()+1);
        if ((e.getSource()).equals(sqDown)) setSide(getSide()-1);
        if ((e.getSource()).equals(sqFld)) setSide(Integer.parseInt(sqFld.getText()));
        } catch (Exception ex){
            System.out.println("Exception caught!");
        }
    }
}
