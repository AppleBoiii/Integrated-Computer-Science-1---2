import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class Display
{
    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        
        initDisplay();
    }

    private void initDisplay()
    {
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setResizable(false);
        // frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // frame.setFocusable(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false); //only the JFrames need to be focused on input

        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        // frame.requestFocus();
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public JFrame getJFrame()
    {
        return frame;
    }

    public void close()
    {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

}