package mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

class Set{
    static final int ITERATIONS = 100;

    static final int SIZE_X = 1600;
    static final int SIZE_Y = 800;

    static final double C_A_MAX = 2.0;
    static final double C_A_MIN = -2.0;
    static final double C_B_MAX = 1.0;
    static final double C_B_MIN = -1.0;

    static final double STEP_SIZE_X = (C_A_MAX-C_A_MIN)/(double)SIZE_X;
    static final double STEP_SIZE_Y = (C_B_MAX-C_B_MIN)/(double)SIZE_Y;

    public static void main(String... args){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        BufferedImage image = new BufferedImage(SIZE_X, SIZE_Y, BufferedImage.TYPE_3BYTE_BGR);

        drawImage(image);

        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);
        panel.add(label, BorderLayout.CENTER);

        JFrame frame = new JFrame("Mandelbrot Set Renderer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        System.out.println(STEP_SIZE_X);
        System.out.println(STEP_SIZE_Y);
        System.out.println("done");
    }

    private static void drawImage(BufferedImage image) {
        for (int i = 0; i < SIZE_X*SIZE_Y; i++){
            int x = i%SIZE_X;
            int y = i/SIZE_X;

            int color = 0xFFFFFF;
            //System.out.format("%f, %f\n", x*STEP_SIZE_X+C_A_MIN, y*STEP_SIZE_Y+C_B_MIN);
            if(getPointValue(x*STEP_SIZE_X+C_A_MIN, C_B_MAX-y*STEP_SIZE_Y) == ITERATIONS){
                color = 0;
            }
            image.setRGB(x,y,color);
        }
    }


    static int getPointValue(double c_a, double c_b){
        double a_n = c_a;
        double b_n = c_b;
        for (int i = 1; i < ITERATIONS; i++){
            double a_n2 = a_n*a_n - b_n*b_n + c_a;
            b_n = 2*a_n*b_n + c_b;
            a_n = a_n2;
            if (a_n > 20) {
                return i;
            }
        }
        return ITERATIONS;
    }
}
