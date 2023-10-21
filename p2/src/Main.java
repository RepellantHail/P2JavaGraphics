import java.awt.*;

public class Main {
    private void drawLinesTypes(){
        DrawLines[] canvas = new DrawLines[4];
        String[] canvasTitles = new String[4];
        sizePosition[] szPns = new sizePosition[4];

        //Initialize titles
        canvasTitles[0] = "Improved Lines";
        canvasTitles[1] = "DDA Lines";
        canvasTitles[2] = "Bresenham Lines";
        canvasTitles[3] = "Punto Medio Lines";

        //Initialize position
        szPns[0] = new sizePosition(0,0);
        szPns[1] = new sizePosition(600,0);
        szPns[2] = new sizePosition(0,600);
        szPns[3] = new sizePosition(600,600);

        //Initialize 4 canvas with Lines
        for (int i = 0; i < 4; i++){
            canvas[i] = new DrawLines(canvasTitles[i]);
            canvas[i].setBounds(
                    szPns[i].getX(),
                    szPns[i].getY(),
                    szPns[i].getWidth(),
                    szPns[i].getHeight()
                    );
            canvas[i].drawLinesCanvas(i);
        }
    }
    private void figurasBasicas(){
        DrawShapes canvas = new DrawShapes("Figuras Basicas");
        //Draw 4 lines
        canvas.drawLine(10,10,100,100, Color.magenta);
        canvas.drawLine(90,50,190,50, Color.cyan);
        canvas.drawLine(330,10,230,100, Color.yellow);
        canvas.drawLine(399,50,300,50, Color.black);

        //Draw Shapes
        int xc = 50, yc = 200;
        //Draw Circles
        canvas.drawCircle           (xc,yc,5,Color.BLUE);
        canvas.drawCirclePolar      (xc,yc,10,Color.BLACK);
        canvas.drawCircle8Sides     (xc,yc,20,Color.RED);
        canvas.drawCircleMidPoint   (xc,yc,30,Color.ORANGE);
        canvas.drawCircleBresenham  (xc,yc,40,Color.GREEN);

        //Draw Rectangles
        canvas.drawRectangle(110,yc-40,130,80,Color.PINK);
        canvas.drawRectangle(130,yc-20,90,40,Color.DARK_GRAY);

        xc += 270;
        //Draw Elipses
        canvas.drawEllipseBresenham           (xc,yc,70,32,Color.RED);
        canvas.drawEllipseBresenham           (xc,yc,55,24,Color.GREEN);
        canvas.drawEllipseBresenham           (xc,yc,40,16,Color.PINK);
        canvas.drawEllipseBresenham           (xc,yc,25,6,Color.BLUE);

    }
    private void tiposDeLinea(){
        DrawLines canvas = new DrawLines("Tipos de Lineas");
        // Ejemplo 1: Línea diagonal con máscara alternante
        Boolean[] mask1 = {true, false};
        canvas.drawLineMask(mask1, 10, 10, 30, 30, Color.BLUE);

        // Ejemplo 2: Línea vertical con máscara uniforme
        Boolean[] mask2 = {true, true, true, true};
        canvas.drawLineMask(mask2, 50, 20, 50, 80, Color.GREEN);

        // Ejemplo 3: Línea horizontal con máscara personalizada
        Boolean[] mask3 = {true, false, true, false, true};
        canvas.drawLineMask(mask3, 80, 60, 160, 60, Color.RED);
        canvas.drawLineWidth(200, 20, 230, 300, Color.BLACK,5);
        canvas.drawLineWidth(15, 300, 200, 300, Color.RED,6);
        canvas.drawLineWidth(100, 100, 250, 250, Color.ORANGE,7);
    }
    private void tiposDeCirculo(){
        int xc = 200, yc = 200, radio = 38;
        DrawShapes canvas = new DrawShapes("Tipos de circulo");
        canvas.drawCircle           (xc,yc, radio * 1, new Color(179,76 ,36 ));
        canvas.drawCirclePolar      (xc,yc, radio * 2, new Color(157,117,92 ));
        canvas.drawCircle8Sides     (xc,yc, radio * 3, new Color(126,93 ,80 ));
        canvas.drawCircleMidPoint   (xc,yc, radio * 4, new Color(74 ,57 ,49 ));
        canvas.drawCircleBresenham  (xc,yc, radio * 5, new Color(148,128,122));
        //Draw mask and width
        Boolean[] mask = {true, true, true, true, true,false,false,true};
        canvas.drawCircleMask (xc,yc,(int) (radio * 4.5), Color.RED, mask);
        canvas.drawCircleWidth(xc,yc,(int) (radio * 3.5), Color.magenta, 8);
    }
    private void paintFigures() {
        DrawShapes canvas = new DrawShapes("Scan Line");
        canvas.defaultFigure();
        canvas.scanLine(Color.GREEN);

        DrawShapes canvas2 = new DrawShapes("Flood Fill");
        canvas2.defaultFigure();
        canvas2.floodFill(200, 150, Color.RED);
    }
    private void recorteVentana(){
        DrawShapes canvas = new DrawShapes("Recorte Lineas");
        //Draw window
        int[] limits = {40,40,320,320};
        canvas.drawRectangle(limits[0],limits[1],limits[2],limits[3],Color.RED);
        //Relocate Limits
        limits[2] += 40;
        limits[3] += 40;

        //Lines out of window
        Point p0 = new Point(1,60);     Point p1 = new Point(150,1);
        Point p2 = new Point(200, 380); Point p3 = new Point(400, 40);
        Point p4 = new Point(40, 300);  Point p5 = new Point(350, 350);
        canvas.drawClippedLine(limits, p0, p1);
        canvas.drawClippedLine(limits, p2, p3);
        canvas.drawClippedLine(limits, p4, p5);
    }

    public void drawRectangle(){
        DrawShapes canvas = new DrawShapes("Rectangulo");
        canvas.drawRectangle(40,40,200,60,Color.magenta);
    }
    private void recorteCircunferencia(){
        DrawShapes canvas = new DrawShapes("Recorte Circunferencia");
        //Draw window
        int[] limits = {40, 40, 320, 320};
        canvas.drawRectangle(limits[0], limits[1], limits[2], limits[3], Color.RED);

        //Relocate Limits
        limits[2] += 40; limits[3] += 40;
        Point center = new Point(399,399);

        //Circle out of windows
        canvas.drawClippedCircle(limits,center, 190);

        //New circle
        center.x = 1;
        center.y = 1;
        canvas.drawClippedCircle(limits,center, 190);

        //New circle
        center.x = 390;
        center.y = 100;
        canvas.drawClippedCircle(limits,center, 90);
    }

    public static void main(String[] args){
        Main main = new Main();

        //Dibujar 4 tipos de circulo
//        main.drawLinesTypes();
//        main.figurasBasicas();
//        main.tiposDeLinea();
//        main.tiposDeCirculo();
//        main.paintFigures();
//        main.recorteVentana();
      //  main.recorteCircunferencia();
        main.drawRectangle();
    }
}

class sizePosition{
    private final int x ;
    private final int y ;
    private final int width = 400;
    private final int height = 400;

    public sizePosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public int getWidth(){return width;}

    public int getHeight(){return  height;}
}