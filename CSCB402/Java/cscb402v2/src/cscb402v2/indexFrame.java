package cscb402v2;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class MyCanvas extends Canvas { 
    
    private int kv_size = 150;
    private int kv_pos = (150 - (kv_size/2));
    private int kr_pos = 150;
    private int kr_x = kr_pos;
    private int kr_y = (300 - kr_pos);
    
    private int kr_size = 10;
    
    private Color kv_color = new Color(255,0,0); 
    
    public int getKrPos(){
        return this.kr_pos;
    }
    
    public int getKvSize(){
        return this.kv_size;
    }
 
    
    public void setKvColor(String color){
        switch(color){
            case "Син":
                kv_color = new Color(0,0,255);
            break;
            case "Зелен":
                kv_color = new Color(0,255,0);
            break;            
            default:
                kv_color = new Color(255,0,0);
            break;
        }
        this.repaint();
        
    }
    
    public boolean setKvSize(int size){
        int pos = (150 - (size/2));
        
        if(pos <= this.kr_pos 
                && (pos + size) >= (this.kr_pos+(this.kr_size/2))
                && (this.kr_y+(this.kr_size/2))  < (pos + size)
                && (this.kr_x+(this.kr_size/2))  < (pos + size)){
            this.kv_size = size;
            this.kv_pos = pos;
            this.repaint();
            return true;
        }
        return false;
    }

    public boolean setKrPos(int pos){
        int x = (300 - pos);
        if(this.kv_pos <= pos 
                && (this.kv_pos + this.kv_size) >= (pos+(this.kr_size/2))
                && (x+(this.kr_size/2))  < (this.kv_pos + this.kv_size)
                && (x+(this.kr_size/2))  < (this.kv_pos + this.kv_size)){
            this.kr_pos = pos;
            this.kr_x = pos;
            this.kr_y = (300 - pos);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public void paint(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        

        g2d.setColor(kv_color);
        g2d.fillRect(kv_pos, kv_pos, kv_size, kv_size);
        
        g2d.setColor(new Color(0,0,0));
        g2d.fillOval(kr_x-(kr_size/2), kr_y-(kr_size/2), kr_size, kr_size);
    }
}

class MyActionListener implements ActionListener{

    private JTextField field;
    
    public MyActionListener(JTextField field){
        this.field = field;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println(field.getText());
    }
    
}

class MyInputMethodListener implements InputMethodListener {

    private JTextField field;
    
    public MyInputMethodListener(JTextField field){
        this.field = field;
    }    
    
    @Override
    public void caretPositionChanged(InputMethodEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inputMethodTextChanged(InputMethodEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println(field.getText());
    }
    
}


class ButtonClickListener implements MouseListener {
    private boolean inc;
    private JTextField field;
    public static String TEXT_PROPERTY = "";
    
    public ButtonClickListener(JTextField field,boolean inc) {
        this.inc = inc;
        this.field = field;
        this.TEXT_PROPERTY = field.getText();
    }
        

    public void mouseClicked(MouseEvent e) {
       
        int i = Integer.valueOf((field.getText()));
        if(inc){
            i++;
        }else i--;
        
        field.setText(Integer.toString(i));
        
    }
    
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}

public class indexFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    indexFrame frame = new indexFrame();
                    frame.setTitle("CSCB402 - Visual Java (Variant 2)");
                    frame.setResizable(false);
                    frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Create the frame.
     */
    public indexFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 534, 391);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        Object cmboitem;
        
        
        MyCanvas canvas = new MyCanvas();
        canvas.setBackground(new Color(245, 245, 220));
        canvas.setBounds(191, 25, 310, 310);

        contentPane.add(canvas);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192));
        panel.setBounds(25, 25, 141, 115);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel label = new JLabel("\u0420\u0430\u0437\u043C\u0435\u0440 \u043D\u0430 \u043A\u0432\u0430\u0434\u0440\u0430\u0442");
        label.setBounds(10, 11, 127, 14);
        panel.add(label);
        
        textField = new JTextField();
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                doSomething();
            }

            private void doSomething() {
                // TODO Auto-generated method stub
                //System.out.println("a");
                int i = Integer.valueOf((textField.getText()));
                if(canvas.setKvSize(i)){
                    textField.setText(Integer.toString(i));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                doSomething();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                doSomething();
            }
        });

        /*
        textField.addInputMethodListener(new InputMethodListener() {
            public void caretPositionChanged(InputMethodEvent arg0) {
            }
            public void inputMethodTextChanged(InputMethodEvent arg0) {
                canvas.setKvSize(Integer.valueOf((textField.getText())));
            }
        });
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setKvSize(Integer.valueOf((textField.getText())));
            }
        });
        */
        textField.setText(Integer.toString(canvas.getKvSize()));
        textField.setBounds(10, 36, 114, 25);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton button = new JButton("+1");
        button.addMouseListener(new ButtonClickListener(textField,true));
        button.setBounds(10, 72, 52, 23);
        panel.add(button);
        
        JButton button_1 = new JButton("-1");
        button_1.addMouseListener(new ButtonClickListener(textField,false));
        button_1.setBounds(72, 72, 52, 23);
        panel.add(button_1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(25, 162, 141, 115);
        panel_1.setBackground(new Color(192, 192, 192));
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel label_1 = new JLabel("\u041F\u043E\u043B\u043E\u0436\u0435\u043D\u0438\u0435 \u043A\u0440\u044A\u0433");
        label_1.setBounds(10, 11, 127, 14);
        panel_1.add(label_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(10, 36, 114, 25);
        textField_1.setText(Integer.toString(canvas.getKrPos()));
        
        /*
        textField_1.addInputMethodListener(new InputMethodListener() {
            public void caretPositionChanged(InputMethodEvent arg0) {
            }
            public void inputMethodTextChanged(InputMethodEvent arg0) {
                canvas.setKrPos(Integer.valueOf((textField_1.getText())));
            }
        });
        textField_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.setKrPos(Integer.valueOf((textField_1.getText())));
            }
        });  
              textField_1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                doSomething();
            }

            private void doSomething() {
                // TODO Auto-generated method stub
                //System.out.println("a");
                int i = Integer.valueOf((textField.getText()));
                if(canvas.setKvSize(i)){
                    textField.setText(Integer.toString(i));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                doSomething();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                doSomething();
            }
        }); 
        */
               
        panel_1.add(textField_1);
        
        JButton button_2 = new JButton("+1");
        button_2.setBounds(10, 72, 52, 23);
        button_2.addMouseListener(new ButtonClickListener(textField_1,true));       
        panel_1.add(button_2);
        
        JButton button_3 = new JButton("-1");
        button_3.setBounds(72, 72, 52, 23);
        button_3.addMouseListener(new ButtonClickListener(textField_1,false));             
        panel_1.add(button_3);
        
        
        JComboBox comboBox = new JComboBox();
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                canvas.setKvColor(comboBox.getSelectedItem().toString()); 
            }
        });
        cmboitem = comboBox.getSelectedItem();
        comboBox.setBounds(25, 318, 141, 20);
        comboBox.addItem("Червен");
        comboBox.addItem("Зелен");
        comboBox.addItem("Син");
        contentPane.add(comboBox);
        
        JLabel label_2 = new JLabel("\u0426\u0432\u044F\u0442 \u043D\u0430 \u043A\u0432\u0430\u0434\u0440\u0430\u0442\u0430");
        label_2.setBounds(25, 293, 112, 14);
        contentPane.add(label_2);
    }
}
