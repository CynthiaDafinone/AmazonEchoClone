//import java.awt.Color;
//import java.awt.TextField;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.ImageIcon;
//
///*
// * Buttons for Amazon Echo simulation. 640028138 15/02/2017.
// */
//public class Buttons extends JFrame {
//
//    final PowerButton btnPOW = new PowerButton("POW");
//    final MuteButton btnMUTE = new MuteButton("MUTE");
//    final ListenButton btnLIST = new ListenButton("LIST");
//
//    /*
//     * Power button
//     */
//    private class PowerButton extends JButton {
//        PowerButton(String s) {
//            setIcon(new ImageIcon("power.png"));
//            setBorder(null);
//            addMouseListener(new MouseAdapter() {
//                public void mouseClicked(MouseEvent me) {
//                    System.out.println("Echo turned on");
//                    //Hello jingle plays
//                    //Enters listening mode
//                    //Lights glow CYAN
//                    //Microphone enabled
//                    System.out.println("Echo turned off");
//                    //Turn off jingle plays
//                    //Lights off
//                    //On/off button remains enabled
//                }
//            });
//        }
//    }
//  /*
//    IN ANSWER MODE THE LIGHT SHOULD GLOW BLUE
//    */
//
//
//    /*
//    * Mute Button
//    */
//    private class MuteButton extends JButton {
//        MuteButton(String s) {
//            setIcon(new ImageIcon("mute.png"));
//            setBorder(null);
//            addMouseListener(new MouseAdapter() {
//                public void mouseClicked(MouseEvent me) {
//                    System.out.println("Microphone muted");
//                    //Remains muted until pressed again
//                }
//            });
//        }
//    }
//
//    private class ListenButton extends JButton {
//        ListenButton(String s) {
//            setIcon(new ImageIcon("dot.png"));
//            setBorder(null);
//            addMouseListener(new MouseAdapter() {
//                public void mouseClicked(MouseEvent me) {
//                    System.out.println("Echo is listening");
//                    //Remains muted until pressed again
//                }
//            });
//        }
//    }
//
//
//    public Buttons() {
//        setTitle("Echo");
//        setContentPane(new JLabel(new ImageIcon("echo.jpg")));
//        setLayout(null);
//
//        disp.setBounds(62, 52, 230, 20);
//        add(disp);
//
//        btnMUTE.setBounds(48, 149, 53, 30);
//        add(btnMUTE);
//        btnPOW.setBounds(140, 149, 626, 626);
//        add(btnPOW);
//
//    }
//
//    public static void main(String[] argv) {
//        JFrame frame = new Buttons();
//        frame.setLocationRelativeTo(null);
//        frame.setSize(1500, 1500);
//        frame.setResizable(false);
//        frame.setVisible(true);
//    }
//}
//
