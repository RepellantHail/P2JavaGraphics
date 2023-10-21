import java.awt.*;
public class TetrisShapes {
    private int length = 0;
    DrawShapes canvas;
    public TetrisShapes(DrawShapes canvas){
        this.canvas = canvas;
    }
    public void square(int x, int y, int squareSize){
        length = squareSize * 2;
        canvas.drawRectangle(x, y, length, length, Color.BLACK);//Square borders
        canvas.drawLine(x + squareSize, y, x + squareSize , y + length, Color.BLACK); // vertical Line
        canvas.drawLine(x, y + squareSize, x + length, y + squareSize , Color.BLACK); // Horizontal Line

        //Move points to paint figure
        x += 1;  y += 1;

        // Paint it Yellow
        canvas.floodFill(x, y, Color.YELLOW); //First quarter
        canvas.floodFill(x + squareSize, y, Color.YELLOW); //Second quarter
        canvas.floodFill(x , y + squareSize, Color.YELLOW); //Third quarter
        canvas.floodFill(x + squareSize, y + squareSize, Color.YELLOW); //Fourth quarter
    }
    public void tShape(int x, int y, int squareSize, int angle){
        Color color = new Color(153, 0, 255);
        length = squareSize * 3;
        Point[] p = new Point[8];

        //Initialize the points
        p[0] = new Point(x, y);
        p[1] = new Point(x, y + squareSize);
        p[2] = new Point(x + squareSize, y + squareSize);
        p[3] = new Point(x + squareSize, y + (squareSize * 2));
        p[4] = new Point(x + (squareSize * 2), y + (squareSize * 2));
        p[5] = new Point(x + (squareSize * 2), y + squareSize);
        p[6] = new Point(x + (squareSize * 3), y + (squareSize * 1));
        p[7] = new Point(x + (squareSize * 3), y + (squareSize * 0));

        drawRotatedLine(p, x, y, angle); // Apply rotation to the points
        joinPoints(p);// Draw Shape
        canvas.floodFill( (angle == 0) ? x+1 : x - 1, y + 1, color); //flood Shape

        //Draw intersection lines
        canvas.drawLine( ( angle == 0) ? p[0].x + squareSize: p[0].x,( angle == 0) ? p[0].y : p[0].y + squareSize, p[2].x, p[2].y, Color.BLACK);
        canvas.drawLine( ( angle == 0) ? p[7].x - squareSize: p[7].x,( angle == 0) ? p[7].y : p[7].y - squareSize, p[5].x, p[5].y, Color.BLACK);
        canvas.drawLine( p[2].x, p[2].y, p[5].x, p[5].y, Color.black);
    }
    public void lShape(int x, int y, int squareSize, int angle){
        Color color = new Color(51, 51, 255);
        length = squareSize * 3;
        Point[] p = new Point[6];

        //Initialize the points
        p[0] = new Point(x, y);
        p[1] = new Point(x + squareSize, y);
        p[2] = new Point(x + squareSize, y + squareSize);
        p[3] = new Point( x + length, y + squareSize);
        p[4] = new Point( x + length, y + (squareSize * 2));
        p[5] = new Point( x, y + (squareSize * 2));

        drawRotatedLine(p, x, y, angle);// Apply rotation to the points
        joinPoints(p);// Draw Shape

        //Draw lines
        if(angle == 0){
            canvas.drawLine(p[0].x,p[0].y + squareSize, p[1].x, p[1].y + squareSize, Color.BLACK);
            canvas.drawLine(p[2].x,p[2].y, p[2].x, p[2].y + squareSize, Color.BLACK);
            canvas.drawLine(p[2].x + squareSize,p[2].y, p[2].x + squareSize, p[2].y + squareSize,Color.black);

            //Fill line
            canvas.floodFill(x + 1, y + 1, color);
            for(int i = 0; i < 3; i++)
                canvas.floodFill(x + 1 + (squareSize * i), y + 1 + squareSize, color);
        }
        else {
            canvas.drawLine(p[0].x - squareSize, p[0].y, p[2].x, p[2].y, Color.BLACK);
            canvas.drawLine(p[2].x, p[2].y, p[2].x - squareSize, p[2].y, Color.BLACK);
            canvas.drawLine(p[2].x - squareSize, p[2].y + squareSize, p[2].x, p[2].y + squareSize, Color.BLACK);


            //Fill line
            canvas.floodFill(p[5].x + 1 + squareSize, p[0].y + 1, color);
            for(int i = 0; i < 3; i++)
                canvas.floodFill(p[5].x + 1, y + 1 + (squareSize * i), color);
        }
    }
    public void lInvertedShape(int x, int y, int sS, int angle){
        //sS = squareSize
        Color color = new Color(255, 153, 0);
        length = sS * 3;
        Point[] p = new Point[6];

        x += length;
        //Initialize the points
        p[0] = new Point(x, y);
        p[1] = new Point(x - sS, y);
        p[2] = new Point(x - sS, y + sS);
        p[3] = new Point(x - (sS * 3), y + sS);
        p[4] = new Point(x - (sS * 3), y + (sS * 2));
        p[5] = new Point(x, y + (sS * 2));

        drawRotatedLine(p, x, y, angle);// Apply rotation to the points
        joinPoints(p);// Draw Shape
        canvas.floodFill( (p[2].x + p[5].x) / 2, (p[2].y + p[5].y) / 2, color); //Flood shape

        //Draw lines
        canvas.drawLine( (angle == 0) ? p[0].x : p[0].x + sS, (angle == 0) ? p[0].y + sS : p[0].y, p[2].x, p[2].y, Color.BLACK);
        canvas.drawLine( p[2].x, p[2].y, (angle == 0) ? p[2].x : p[2].x +sS, (angle == 0) ? p[2].y + sS: p[2].y, Color.BLACK);
        canvas.drawLine( (angle == 0) ? p[3].x + sS: p[3].x, (angle == 0) ? p[3].y: p[3].y - sS, (angle == 0) ? p[4].x + sS: p[4].x, (angle == 0) ? p[4].y: p[4].y - sS
                , Color.BLACK);
    }
    public void line(int x, int y, int squareSize, int angle){
        double radians = Math.toRadians(angle); // Convert angle to radians
        double cosTheta = Math.cos(radians);
        double sinTheta = Math.sin(radians);

        length = squareSize * 4;
        Color color = new Color(0, 255, 255);
        Point[] p = new Point[4];

        //Initialize the points
        p[0] = new Point(x, y);
        p[1] = new Point(x + length, y);
        p[2] = new Point(x + length,y + squareSize);
        p[3] = new Point(x,y + squareSize);

        // Apply rotation to the points
        for (int i = 0; i < 4; i++) {
            int newX = (int) (cosTheta * (p[i].x - x) - sinTheta * (p[i].y - y) + x);
            int newY = (int) (sinTheta * (p[i].x - x) + cosTheta * (p[i].y - y) + y);
            p[i].x = newX;
            p[i].y = newY;
        }

        //Line borders
        joinPoints(p);

        //Draw lines
        for (int i = 1, j = 0; i <= 4 && j < 4; i++, j++){
            if(angle == 90) {
                canvas.drawLine(p[0].x, p[0].y + (squareSize * i), p[3].x, p[3].y + (squareSize * i), Color.BLACK);
                canvas.floodFill(p[3].x + 1 , p[3].y + 1 + (squareSize * j), color); // paint square
            }else {
                canvas.drawLine(p[0].x + (squareSize * i), p[0].y, p[3].x + (squareSize * i), p[3].y, Color.BLACK); // vertical Line
                canvas.floodFill(p[0].x + 1 + (squareSize * j), p[0].y + 1, color); // paint square/
            }
        }
    }
    public void zShape(int x, int y, int squareSize){
        Color color = new Color(255, 0, 0);
        canvas.drawRectangle(x, y, squareSize * 2, squareSize, Color.BLACK);//Line borders

        //Draw lines
        for (int i = 1, j = 0; i <= 2 && j < 2; i++, j++){
            canvas.drawLine(x + (squareSize * i), y, x + (squareSize * i), y + squareSize, Color.BLACK); // vertical Line
            canvas.floodFill(x + 1 + (squareSize * j), y +1, color); // paint square
        }

       x += squareSize; y += squareSize;
        canvas.drawRectangle(x, y, squareSize * 2, squareSize, Color.BLACK);//Line borders
        for (int i = 1, j = 0; i <= 2 && j < 2; i++, j++){
            canvas.drawLine(x + (squareSize * i), y, x + (squareSize * i), y + squareSize, Color.BLACK); // vertical Line
            canvas.floodFill(x + 1 + (squareSize * j), y +1, color); // paint square
        }
    }
    public void zShapeInverted(int x, int y, int squareSize){
        Color color = new Color(0, 204, 0);
        x += squareSize;
        canvas.drawRectangle(x, y, squareSize * 2, squareSize, Color.BLACK);//Line borders
        canvas.floodFill(x + 1  , y + 1, color); // paint square

        //Draw lines
        canvas.drawLine(x + (squareSize ), y, x + (squareSize ), y + squareSize, Color.BLACK); // vertical Line

        x -= squareSize; y += squareSize;
        canvas.drawRectangle(x, y, squareSize * 2, squareSize, Color.BLACK);//Line borders
        canvas.floodFill(x + 1  , y + 1, color); // paint square
        canvas.drawLine(x + squareSize , y, x + squareSize , y + squareSize, Color.BLACK); // vertical Line

    }
    public void nextShape(int x,int y, int shapeIndex, int squareSize){
        //Blackout screen
        blackoutShape();

        switch (shapeIndex){
            case 0 -> square                (x, y, 30);
            case 1 -> tShape        (x - 15, y, 30, 0);
            case 2 -> lShape        (x - 15, y, 30,0);
            case 3 -> lInvertedShape(x - 15, y, 30, 0);
            case 4 -> line          (x - 30, y, 30, 0);
            case 5 -> zShape        (x - 15, y, 30);
            case 6 -> zShapeInverted(x - 15, y, 30);
            default -> square               (x, y, 30);
        }
        canvas.floodFill(x,y - 10,Color.BLACK);
    }
    private void blackoutShape(){
        int x = 430, y = 320;
        int height = 70, width = 120;

        //Blackout Shape
        for (int i = 0; i < height; i++)
            canvas.drawLine(x, y + i, x + width, y + i, Color.BLACK);
        canvas.floodFill(x,y,Color.white);

    }
    private void joinPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            int nextIndex = (i + 1) % points.length; // Get the next index, wrapping around for the last point
            canvas.drawLine(points[i].x, points[i].y, points[nextIndex].x, points[nextIndex].y, Color.BLACK);
        }
    }
    public void drawRotatedLine(Point[] p, int x, int y, int angle) {
        double radians = Math.toRadians(angle);
        double cosTheta = Math.cos(radians);
        double sinTheta = Math.sin(radians);

        for (int i = 0; i < p.length; i++) {
            int newX = (int) (cosTheta * (p[i].x - x) - sinTheta * (p[i].y - y) + x);
            int newY = (int) (sinTheta * (p[i].x - x) + cosTheta * (p[i].y - y) + y);
            p[i].x = newX;
            p[i].y = newY;
        }
    }
}
