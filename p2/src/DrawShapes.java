import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class DrawShapes {
    private DrawLines lines;//Auxiliary Object to get access to lines and PutPixel
    private int width = 400;
    private int height = 400;
    public DrawShapes(String title){
        lines = new DrawLines(title);
        lines.setBounds(1100,0,400,400);
    }
    public DrawShapes(String title, int width, int height){
        lines = new DrawLines(title, width,height);
        this.width = width;
        this.height = height;
        lines.setSize(width,height);
        lines.setBounds(100,0,width,height);
    }
    public void drawLine(int x0,int y0,int x1,int y1,Color color){
        lines.bresenham(x0,y0,x1,y1,color);
    }
    public void drawRectangle(int x, int y, int width, int height, Color color){
        width += x;
        height += y;
        lines.bresenham(x,y, width,y,color);//Top of the rectangle
        lines.bresenham(x,height, width,height,color);//Bottom of the rectangle
        lines.bresenham(x,y,x,height,color);//Left of the rectangle
        lines.bresenham(width,y,width,height,color);//Left of the rectangle
    }
    public void drawCircle(int xc, int yc, int radio, Color color) {
        for (int x = xc - radio; x <= xc + radio; x++){
            float y = (float) (yc + Math.sqrt((radio * radio) - (x-xc)*(x-xc)));
            lines.putPixel(x, Math.round(y), color);
            y = (float) (yc - Math.sqrt((radio * radio) - (x-xc)*(x-xc)));
            lines.putPixel(x, Math.round(y), color);
        }
    }
    public void drawCirclePolar(int xc, int yc, int radio, Color color) {
        for (double t = 0; t < 2 * Math.PI; t += 0.01){
            int x = (int) (xc + (radio * Math.sin(t)));
            int y = (int) (yc + (radio * Math.cos(t)));
            lines.putPixel(x, y, color);
        }
    }
    public void drawCircle8Sides(int xc, int yc, int radio, Color color) {
        for (double x = 0; x <= radio / Math.sqrt(2); x += 0.01) {
            double y = Math.sqrt((radio * radio) - (x * x));
            plotCircleSections((int) x, (int) y,color, xc, yc);
        }
    }
    public void drawCircleMidPoint(int xc, int yc, int radio, Color color) {
        int x = 0;
        int y = radio;
        int p = 5/4 - radio;//Inicializar p0
        plotCircleSections(x,y,color, xc, yc);
        while (x < y) {
            x++;
            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
            plotCircleSections( x, y, color, xc, yc);
        }
    }
    public void drawCircleBresenham(int xc, int yc, int radio, Color color) {
        int x = 0;
        int y = radio;
        int d = 3 - 2 * radio;

        plotCircleSections(x, y, color, xc, yc);

        while (x <= y) {
            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            plotCircleSections(x, y, color, xc, yc);
        }
    }
    public void drawCircleMask(int xc, int yc, int radio, Color color, Boolean[] mask) {
        int x = 0;
        int y = radio;
        int d = 3 - 2 * radio;
        int i = 0;

        plotCircleSections(x, y, color, xc, yc);

        while (x <= y) {
            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            if(mask[i])
                plotCircleSections(x, y, color, xc, yc);
            i = (i < mask.length - 1) ? i + 1 : 0;
        }
    }
    public void drawCircleWidth(int xc, int yc, int radio, Color color, int width) {
        int x = 0;
        int y = radio;
        int d = 3 - 2 * radio;

        plotCircleSections(x, y, color, xc, yc);

        while (x <= y) {
            for (int i = 0; i < width; i++) {
                plotCircleSections(x + i, y, color, xc, yc);
                plotCircleSections(x , y + i, color, xc, yc);
            }
            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else
                d = d + 4 * x + 6;
        }
    }
    private void plotCircleSections(int x, int y, Color color, int xc, int yc) {
        lines.putPixel(xc + x, yc + y, color); // Top-right
        lines.putPixel(xc - x, yc + y, color); // Top-left
        lines.putPixel(xc + x, yc - y, color); // Bottom-right
        lines.putPixel(xc - x, yc - y, color); // Bottom-left

        lines.putPixel(xc + y, yc + x, color); // Right-top
        lines.putPixel(xc - y, yc + x, color); // Left-top
        lines.putPixel(xc + y, yc - x, color); // Right-bottom
        lines.putPixel(xc - y, yc - x, color); // Left-bottom
    }
    public void drawEllipseBresenham(int xc, int yc, int xRadius, int yRadius, Color color) {
        int x = 0;
        int y = yRadius;
        int d1 = (int) ((yRadius * yRadius) - (xRadius * xRadius * yRadius) + (0.25 * xRadius * xRadius));
        int dx = 2 * yRadius * yRadius * x;
        int dy = 2 * xRadius * xRadius * y;

        plotEllipseSections(x, y, color, xc, yc);

        while (dx < dy) {
            x++;
            if (d1 < 0) {
                dx += 2 * yRadius * yRadius;
                d1 += dx + (yRadius * yRadius);
            } else {
                y--;
                dx += 2 * yRadius * yRadius;
                dy -= 2 * xRadius * xRadius;
                d1 += dx - dy + (yRadius * yRadius);
            }
            plotEllipseSections(x, y, color, xc, yc);
        }

        int d2 = (int) (((yRadius * yRadius) * ((x + 0.5) * (x + 0.5))) +
                        ((xRadius * xRadius) * ((y - 1) * (y - 1))) -
                        (xRadius * xRadius * yRadius * yRadius));

        while (y > 0) {
            y--;
            if (d2 > 0) {
                dy -= 2 * xRadius * xRadius;
                d2 += xRadius * xRadius - dy;
            } else {
                x++;
                dx += 2 * yRadius * yRadius;
                dy -= 2 * xRadius * xRadius;
                d2 += dx - dy + xRadius * xRadius;
            }
            plotEllipseSections(x, y, color, xc, yc);
        }
    }
    private void plotEllipseSections(int x, int y, Color color, int xc, int yc) {
        lines.putPixel(xc + x, yc + y, color); // Top-right
        lines.putPixel(xc - x, yc + y, color); // Top-left
        lines.putPixel(xc + x, yc - y, color); // Bottom-right
        lines.putPixel(xc - x, yc - y, color); // Bottom-left
    }
    public void scanLine(Color color){
        for (int y = 0; y < 400; y++) {
            int left = -1;
            int right = -1;
            for (int x = 0; x < 400; x++) {
                int pixelColor = lines.getPixel(x, y);
                if (pixelColor == Color.BLACK.getRGB()) {
                    if (left == -1)
                        left = x;
                    else
                        right = x;
                } else if (left != -1 && right != -1) {
                    drawLine(left + 1, y, right - 1, y, color);
                    left = -1;
                    right = -1;
                }
                if (pixelColor != Color.BLACK.getRGB() && right != -1) {
                    break;
                }
            }
        }
    }
    public void floodFill( int startX, int startY,Color color){
        int targetColorRGB = lines.getPixel(startX, startY);
        int replacementColorRGB = color.getRGB();

        if (targetColorRGB == replacementColorRGB)
            return;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;

            if (x < 0 || x >= width || y < 0 || y >= height)
                continue;
            if (lines.getPixel(x, y) == targetColorRGB) {
                lines.putPixel(x, y, color);
                queue.add(new Point(x + 1, y));
                queue.add(new Point(x - 1, y));
                queue.add(new Point(x, y + 1));
                queue.add(new Point(x, y - 1));
            }
        }
    }
    public void defaultFigure(){
        Color color = Color.BLACK;
        Point p0 = new Point(10,240);
        Point p1 = new Point(185,10);
        Point p2 = new Point(385,190);
        Point p3 = new Point(260,220);
        Point p4 = new Point(200,385);
        drawLine(p0.x ,p0.y,p1.x, p1.y, color);
        drawLine(p1.x, p1.y,p2.x, p2.y, color);
        drawLine(p2.x, p2.y, p3.x,p3.y, color);
        drawLine(p3.x,p3.y, p4.x, p4.y, color);
        drawLine(p4.x, p4.y, p0.x ,p0.y, color);
    }
    public void drawClippedLine(int[] limits, Point p0, Point p1) {
        Color color = Color.GREEN;

        int xmin = limits[0]; int ymin = limits[1];
        int xmax = limits[2]; int ymax = limits[3];

        int x0 = p0.x; int y0 = p0.y;
        int x1 = p1.x; int y1 = p1.y;

        x0 = clamp(x0, xmin, xmax); y0 = clamp(y0, ymin, ymax);
        x1 = clamp(x1, xmin, xmax); y1 = clamp(y1, ymin, ymax);

        drawLine(x0, y0, x1, y1, color);
    }
    public void drawClippedCircle(int[] limits, Point center, int radius) {
        Color color = Color.GREEN;

        int xmin = limits[0]; int ymin = limits[1];
        int xmax = limits[2]; int ymax = limits[3];

        int centerX = center.x; int centerY = center.y;

        //Recalculate center of the circle
        center.x = Math.min(Math.max(center.x, xmin + radius), xmax - radius);
        center.y = Math.min(Math.max(center.y, ymin + radius), ymax - radius);

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                if (x * x + y * y <= radius * radius) {
                    int pixelX = centerX + x;
                    int pixelY = centerY + y;

                    if (pixelX >= xmin && pixelX <= xmax && pixelY >= ymin && pixelY <= ymax)
                        lines.putPixel(pixelX, pixelY, color);
                }
            }
        }
    }
    private int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public void putPixel(int x,int y, Color color, int pixelSize){
        lines.putPixel(x,y,color,pixelSize);
    }
}

