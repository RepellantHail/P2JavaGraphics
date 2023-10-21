import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TetrisGame {
    private DrawShapes shapes;//Auxiliary Object to get access to lines and PutPixel
    private TetrisShapes tetris;
    private Alphabet alphabet;
    private int width = 600;
    private int height = 650;
    private Color blueMargin = new Color(51, 204, 255);
    public TetrisGame(){
        shapes = new DrawShapes("P2 Proyecto", width,height);
        tetris = new TetrisShapes(shapes);
        alphabet = new Alphabet(shapes);
    }
    private void drawGame(){
        drawBackground();
        drawTextBox(40 ,40 ,140, 60);// Type Box
        drawTextBox(20 ,140,180,470);// Statistics Box
        drawTextBox(200,20 ,200, 60);// Title Box
        drawTextBox(200,80 ,200,530);// Display Box
        drawTextBox(400,20 ,180,200);// Score Box
        drawTextBox(400,240,180,180);// Next Shape Box
        drawTextBox(400,420 ,180,80);// Level Box

        drawStatistics();//Draw Components
        drawTitles();
        drawDisplay();

        //Fill boxes
        shapes.floodFill(60 , 60 , Color.BLACK); //Fill Game Type
        shapes.floodFill(40 , 160, Color.BLACK); //Fill Statistics
        shapes.floodFill(240, 60 , Color.BLACK); //Fill Title
        shapes.floodFill(430, 80 , Color.BLACK); //Fill Next
        shapes.floodFill(430, 260, Color.BLACK); //Fill Next
        shapes.floodFill(430, 460, Color.BLACK); //Fill Level
        shapes.floodFill(1  ,1   , Color.GRAY) ; //Fill Background
    }
    private void drawTitles(){
        Color color = new Color(204, 102, 255);

        alphabet.drawWord(67 ,65 ,"a-type",     color); //Game type
        alphabet.drawWord(40 ,170,"statistics", color); //Statistics title
        alphabet.drawWord(235,45 ,"lines-153",  color); //Lines title
        alphabet.drawWord(435,65 ,"top",        color); //Top title
        alphabet.drawWord(435,85 ,"010000",     color); //Top Number
        alphabet.drawWord(435,145,"score",      color); //Score title
        alphabet.drawWord(435,165,"010000",     color); //Score Number
        alphabet.drawWord(460,265,"next",       color); //Next title
        alphabet.drawWord(450,440,"level",      color); //Level title
        alphabet.drawWord(470,460,"18",      color); //Level Number
    }
    public void drawStatisticsPoints(){
        int x = 120, y = 220;
        int rows = 7, colums = 3;
        int xStep = 16, yStep = 47;

        AtomicBoolean flag = new AtomicBoolean(false);
        Runnable action = () -> {
            while (true) {
                try {
                    for (int r = 0; r < rows; r++){ //Draw a Row of 3 Columns
                        for (int c = 0; c < colums; c++) {
                            if(flag.get())
                                alphabet.drawChar(x + (c * xStep), y + (r * yStep), Color.RED, randomChar());
                            else
                                alphabet.drawChar(x + (c * xStep), y + (r * yStep), Color.RED, '-');
                        }
                    }
                    flag.set(true);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(action);
        thread.start();

    }
    public void drawShapesStatistics(){
        int x = 65; int y = 220;
        tetris.line          (x - 15 , y         , 15, 0);
        tetris.square        (x         , y + 30 , 15);
        tetris.zShape        (x         , y + 80 , 15);
        tetris.lShape        (x         , y + 130, 15,0);
        tetris.zShapeInverted(x         , y + 180, 15);
        tetris.tShape        (x         , y + 230, 15, 0);
        tetris.lInvertedShape(x         , y + 280, 15, 0);
        drawNextShape();
    }
    private void drawBackground(){
        //Background Borders
        shapes.drawRectangle(0,0,599,649,Color.BLACK);

        //Draw background pattern

    }
    private void drawTextBox(int x, int y, int width, int height){
        width -= 1;     height -= 1;
        //Draw Border
        shapes.drawRectangle(x,y,width,height,Color.BLACK);

        //Draw Gray Margin
        int marginWidth = 8;
        x += 1;         y += 1;
        drawMargin(x ,y, width , height , Color.GRAY, marginWidth);

        //Draw Black Margin
        shapes.drawRectangle(x,y,width,height,Color.BLACK);

        //Draw Blue Margin
        x += (marginWidth + 1);       y += (marginWidth + 1);
        width -= (marginWidth * 2);   height -= (marginWidth * 2);
        width -= 1;                   height -= 1;
        marginWidth = 4;
        drawMargin(x,y, width, height, blueMargin, marginWidth);

        x += marginWidth; y += marginWidth;
        //shapes.floodFill(x + 1,y + 1, Color.BLACK);
    }
    private void drawStatistics(){
        drawShapesStatistics();
        drawStatisticsPoints();
    }
    private void drawMargin(int x, int y, int width, int height, Color color, int marginWidth){
        x += marginWidth;
        y += marginWidth;

        //Set margin to actual size
        width -= marginWidth * 2;
        height -= marginWidth * 2;

        //Set border
        shapes.drawRectangle(x, y , width, height, Color.BLACK);

        //Recalculate origin

        x -= marginWidth;
        y -= marginWidth;
        shapes.floodFill(x, y, color);
    }
    private void drawNextShape(){

        Runnable action = () -> {
            while (true) {
                try {
                    tetris.nextShape(460,320,randomGenerator(7), 30);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(action);
        thread.start();

    }
    private char randomChar(){
        char c = switch (randomGenerator(10)) {
            case 0 -> '0';
            case 1 -> '1';
            case 2 -> '2';
            case 3 -> '3';
            case 4 -> '4';
            case 5 -> '5';
            case 6 -> '6';
            case 7 -> '7';
            case 8 -> '8';
            case 9 -> '9';
            default -> '0';
        };
        return c;
    }
    private void drawDisplay(){
        int x = 216, y = 565;
        int squareSize = 15;
        tetris.square        (x, y, squareSize);
        tetris.line          (x + 45, y - 30        , squareSize, 90);
        tetris.lShape        (x + 45, y - 45        , squareSize, 90);
        tetris.lShape        (x + 45,    y             , squareSize, 0 );
        tetris.tShape        (x + 75, y - 30, squareSize, 90);
        tetris.lInvertedShape(x + 45,    y - 100        , squareSize, 270 );
    }
    private int randomGenerator(int limit){
        return (int ) (Math.random() * limit);
    }

    public static void main(String[] args){
        TetrisGame game = new TetrisGame();
        game.drawGame();
    }
}
