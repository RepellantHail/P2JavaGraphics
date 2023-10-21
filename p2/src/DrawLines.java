import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;
import static java.lang.Math.round;

public class DrawLines extends JFrame {
    private BufferedImage bufferedImage;
    private Graphics graPixel;
    private straightLine[] lines;
    private int width;
    private int height;
    public DrawLines(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Create a larger BufferedImage
        bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        graPixel = bufferedImage.createGraphics();
        graPixel.setColor(Color.WHITE);
        graPixel.fillRect(0, 0, 400, 400); // Fill the image with white
        graPixel.setColor(Color.BLACK); // Set the drawing color to black

        setVisible(true);

        // Initialize the lines array
        initializeLines();
    }
    public DrawLines(String title, int width, int height) {
        setTitle(title);
        this.width = width;
        this.height = height;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);

        // Create a larger BufferedImage
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graPixel = bufferedImage.createGraphics();
        graPixel.setColor(Color.WHITE);
        graPixel.fillRect(0, 0, width, height); // Fill the image with white
        graPixel.setColor(Color.BLACK); // Set the drawing color to black

        setVisible(true);

        // Initialize the lines array
        initializeLines();
    }
    private void initializeLines(){
        lines = new straightLine[9];
        lines[0] = new straightLine(100, 100, 250, 250, Color.ORANGE);         // Diagonal line (45 degrees)
        lines[1] = new straightLine(150, 150, 150, 300, Color.MAGENTA);       // Horizontal line
        lines[2] = new straightLine(15, 300, 200, 300, Color.RED);           // Vertical line
        lines[3] = new straightLine(200, 20, 230, 300, Color.BLACK);        //Steep line
        lines[4] = new straightLine(100, 35, 250, 350, Color.blue);
        lines[5] = new straightLine(150, 35, 350, 45, Color.green);
        lines[6] = new straightLine(250, 0, 251, 380, Color.cyan);
        lines[7] = new straightLine(50, 20, 20, 250, Color.YELLOW);         // Steep positive slope
        lines[8] = new straightLine(200, 50, 50, 200, Color.DARK_GRAY);      // Diagonal line (135 degrees)
    }
    public void putPixel(int x, int y,Color color) {
        bufferedImage.setRGB(x, y, color.getRGB());
        repaint(); // Request a repaint of the JFrame
    }
    public void putPixel(int x, int y,Color color, int pixelSize) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for (int i = 0; i < pixelSize; i++) {
            for (int j = 0; j < pixelSize; j++) {
                int pixelX = x + i;
                int pixelY = y + j;

                // Ensure the coordinates are within the bounds of the image.
                if (pixelX >= 0 && pixelX < width && pixelY >= 0 && pixelY < height) {
                    bufferedImage.setRGB(pixelX, pixelY, color.getRGB());
                }
            }
        }
        repaint(); // Request a repaint of the JFrame
    }
    public int getPixel(int x, int y){
       return bufferedImage.getRGB(x,y);
    }
    private void drawLine(int x0,int y0, int x1,int y1, Color color) {
        int m, b, y;
        for (int x = x0; x <= x1; x++) {
            m = (y1-y0) / (x1-x0 );
            b = y0 - (m * x0);
            y = (m * x) + b;
            putPixel(x, round(y), color);
        }
    }
    private void drawLinePlus(int x0,int y0, int x1,int y1, Color color) {
        int y;
        int dx = x1-x0;
        int dy = y1-y0;

        if(dx == 0){// Vertical line
            int minY = Math.min(y0, y1);
            int maxY = Math.max(y0, y1);
            for (y = minY; y <= maxY; y++)
                putPixel(x0, y, color);
        }
        else if (dy == 0) {// Horizontal line
            int minX = Math.min(x0, x1);
            int maxX = Math.max(x0, x1);
            for (int x = minX; x <= maxX; x++)
                putPixel(x, y0, color);
        }
        else{
            float m = (float) dy / dx;
            int b = y0 - round(m * x0);
            for (int x = x0; x <= x1; x++) {
                y = round(m * x) + b;
                putPixel(x, round(y), color);
            }
        }
    }
    private void dda(int x0,int y0, int x1, int y1,Color color){
        int dx = x1 - x0;
        int dy = y1 - y0;
        int steps = (abs(dx) > abs(dy)) ? abs(dx):abs(dy);
        float xinc = (float) dx / steps;
        float yinc = (float) dy / steps;
        float x = x0;
        float y = y0;
        putPixel(round(x), round(y),color);
        for(int k = 1; k <= steps; k++) {
            x = x + xinc;
            y = y + yinc;
            putPixel(round(x), round(y),color);
        }
    }
    public void bresenham(int x0, int y0, int x1, int y1, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            putPixel(x0, y0, color);

            if (x0 == x1 && y0 == y1) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }
    private void puntoMedio(int x0, int y0, int x1, int y1, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            putPixel(x0, y0, color);

            if (x0 == x1 && y0 == y1) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }
    public void drawLineMask(Boolean[] mask, int x0, int y0, int x1, int y1, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        int i = 0;

        while (true) {
            if(mask[i])
                putPixel(x0, y0, color);
            if(i < mask.length-1)
                i++;
            else
                i = 0;

            if (x0 == x1 && y0 == y1) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }
    public void drawLineWidth(int x0, int y0, int x1, int y1, Color color, int ancho) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        boolean isHorizontal = dy < dx; //Pendiente

        while (true) {
            for (int i = -ancho / 2; i <= ancho / 2; i++) {
                if (isHorizontal) {
                    putPixel(x0, y0 + i, color);
                } else {
                    putPixel(x0 + i, y0, color);
                }
            }

            if (x0 == x1 && y0 == y1) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bufferedImage, 0, 0, this);
    }
    void drawLinesCanvas(int type){
        for (straightLine line : lines) {
            int x0 = line.getX0();
            int y0 = line.getY0();
            int x1 = line.getX1();
            int y1 = line.getY1();
            Color color = line.getColor();
            switch (type){
                case 0 -> drawLinePlus(x0,y0,x1,y1,color);
                case 1 -> dda(x0,y0,x1,y1,color);
                case 2 -> bresenham(x0,y0,x1,y1,color);
                case 3 -> puntoMedio(x0,y0,x1,y1,color);
            }
        }
    }

    /*public static void main(String[] args) {
        DrawLines canvas = new DrawLines("Basic Lines");//Canvas for basic Lines
        DrawLines canvasPlus = new DrawLines("Improved Lines");//Canvas for improved Lines
        DrawLines windowPlus = new DrawLines("DDA Lines");//Canvas for DDA Lines
        DrawLines windowBresenham = new DrawLines("Bresenham Lines");//Canvas for Bresenham Lines

        //Position windows
        canvasPlus.setBounds        (0,0,400,400);
        windowPlus.setBounds        (600,0,400,400);
        windowBresenham.setBounds   (0,600,400,400);
        canvas.setBounds            (600,600,400,400);

        SwingUtilities.invokeLater(() -> {
            canvasPlus.drawLinesCanvas(0);
            windowPlus.drawLinesCanvas(1);
            windowBresenham.drawLinesCanvas(2);
            canvas.drawLinesCanvas(3);
        });
    }*/
}

class straightLine{
    private int x0;
    private int x1;
    private int y0;
    private int y1;
    Color color;

    public straightLine(int x0,int y0,int x1,  int y1, Color color){
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
        this.color = color;
    }

    public int getX0() {
        return x0;
    }

    public int getX1() {
        return x1;
    }

    public int getY0() {
        return y0;
    }

    public int getY1() {
        return y1;
    }

    public Color getColor() {
        return color;
    }
}