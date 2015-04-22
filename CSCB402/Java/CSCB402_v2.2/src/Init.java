

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * The Class Init.
 */
public class Init extends JFrame {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 857074997887010216L;

    /**
     * Launch the application.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    Init frame = new Init();
                    frame.setTitle("CSCB402 - Visual Java (Variant 2) | F43454 - Miglen Evlogiev");;
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
    public Init() {
   
        /*
         * Create the Frame
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Add the Canvas with the geometric objects
        GemetrichniObekti geobjects = new GemetrichniObekti();
        contentPane.add(geobjects);

        // Add the controls of the Square size
        KvadratCvqtControl kvadratcvqt = new KvadratCvqtControl();
        contentPane.add(kvadratcvqt);
        kvadratcvqt.addPropertyChangeListener(geobjects);   
        kvadratcvqt.setLayout(null);
        
        // Add the controls of the Square color
        KvadratControl kvadratcontrol = new KvadratControl();
        kvadratcontrol.setSize(250, 100);
        kvadratcontrol.setLocation(45, 25);
        kvadratcontrol.addPropertyChangeListener(geobjects);
        kvadratcontrol.addVetoableChangeListener(geobjects);   
        contentPane.add(kvadratcontrol);
        
        // Add the controls of the Oval position
        KrugControl krugcontrol = new KrugControl();
        krugcontrol.setSize(250, 100);
        krugcontrol.setLocation(45, 157);
        krugcontrol.addPropertyChangeListener(geobjects);
        krugcontrol.addVetoableChangeListener(geobjects);   
        contentPane.add(krugcontrol);        
        
    }

}
