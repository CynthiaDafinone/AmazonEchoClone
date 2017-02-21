import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.File;
import javax.sound.sampled.*;

/*
 * Buttons for ECM2415 Coursework. 640028138 20/02/2017.
 */
public class Buttons extends JFrame {

    boolean isPowered = false;
    boolean isPressed = false;

    final TextField      disp   = new TextField();

    final PowerButton    btnPOW  = new PowerButton   	( "POW"  );
    final MuteButton     btnMUTE = new MuteButton   	( "MUTE" );
    final ListenButton   btnLIST = new ListenButton 	( "LIST" );

    /*
     * Power button
     */
    private class PowerButton extends JButton {

        PowerButton( String s ) {
            setIcon( new ImageIcon( "power.png") );
            setBorder( null );
            addMouseListener( new MouseAdapter() {
                public void mouseClicked( MouseEvent me) {
                    if (isPowered){
                        System.out.println("Power Off");

                        //Shut down noise plays MUST CHANGE NOISE
                        String[] args = {};
                        Sound2.main(args);

                        isPowered = false;
                    }
                    else{
                        System.out.println("Power On");
                        isPressed = false;
                        //Power On tune plays MUST CHANGE NOISE
                        String[] args = {};
                        Sound.main(args);

                        isPowered = true;
                    }
                }
            });
        }}
  /*
    IN ANSWER MODE THE LIGHT SHOULD GLOW BLUE
    */


    /*
    * Mute Button
    */
    private class MuteButton extends JButton {
        MuteButton( String s ) {
            setIcon( new ImageIcon( "mute.png") );
            setBorder( null );
            addMouseListener( new MouseAdapter() {
                public void mouseClicked( MouseEvent me) {
                    if (isPowered){
                        //Holds previous value after being switched off and on

                        if (isPressed){
                            System.out.println("Microphone activated");
                            String[] args = {};
                            Sound4.main(args);

                            isPressed = false;
                        }

                        else{
                            System.out.println("Microphone muted");
                            String[] args = {};
                            Sound3.main(args);

                            //Stop any audio inputs @Will
                            isPressed = true;
                        }
                    }
                }
            });
        }
    }

    /*
    * Force Listen button
    */
    private class ListenButton extends JButton {
        ListenButton( String s ) {
            setIcon( new ImageIcon( "list.png") );
            setBorder( null );
            addMouseListener( new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    if (isPowered){
                        //fileName = cyan;?????
                        System.out.println("Echo is listening");
                        //Forces Echo into listening mode, lights glow CYAN
                    }
                }
            });
        }
    }

    //Coordinates of buttons+background, will change when GUI is implemented
    public Buttons() {
        setTitle( "Echo" );
        setContentPane( new JLabel( new ImageIcon( "blue.png") ) );
        setLayout( null );

        disp.setBounds(62,  52, 230, 20 );      add(disp);

        btnMUTE.setBounds(50, 120, 53, 30 );    add(btnMUTE);
        btnPOW.setBounds(50, 180, 53, 30);     add(btnPOW );
        btnLIST.setBounds(50, 240, 53, 30);    add(btnLIST);

    }

    public static void main( String[] argv ) {
        JFrame frame = new Buttons();
        frame.setLocationRelativeTo( null );
        frame.setSize( 600, 600 );
        frame.setResizable( false );
        frame.setVisible( true );
    }
}
