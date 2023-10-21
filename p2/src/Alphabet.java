import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Alphabet {
    private DrawShapes shapes;
    private Map <Character,Bitmap> alphabet = new HashMap<>();
    private int pixelSize = 2;
    public Alphabet(DrawShapes shapes){
        this.shapes = shapes;
        initializeAlphabet();
    }
    public void drawChar(int x, int y,  Color color, Character c){
        Bitmap bitmap = alphabet.get(c);

        if (bitmap != null) {
            for (int i = 0; i < 7 ; i++) {//Traverse Columns
                for (int j = 0; j < 7; j++) {//Traverse Rows
                    if (bitmap.getPixel(i, j) == 1) //Compare bitmap
                        shapes.putPixel(x + (j * pixelSize), y + (i * pixelSize), color, pixelSize);
                    else
                        shapes.putPixel(x + (j * pixelSize), y + (i * pixelSize), Color.BLACK, pixelSize);
                }
            }
        }
    }
    public void initializeAlphabet(){
        initializeNumbers();
        initializeLetters();
    }
    private void initializeLetters(){
        alphabet.put('a', new Bitmap( new int[][]  {
                {0, 0, 1, 1, 1, 0, 0}, // 01 row
                {0, 1, 1, 0, 1, 1, 0}, // 02 row
                {1, 1, 0, 0, 0, 1, 1}, // 03 row
                {1, 1, 0, 0, 0, 1, 1}, // 04 row
                {1, 1, 1, 1, 1, 1, 1}, // 05 row
                {1, 1, 0, 0, 0, 1, 1}, // 06 row
                {1, 1, 0, 0, 0, 1, 1}, // 07 row
        }));
        alphabet.put('c', new Bitmap( new int[][]  {
                {0, 0, 1, 1, 1, 1, 0}, // 01 row
                {0, 1, 1, 0, 0, 1, 1}, // 02 row
                {1, 1, 0, 0, 0, 0, 0}, // 03 row
                {1, 1, 0, 0, 0, 0, 0}, // 04 row
                {1, 1, 0, 0, 0, 0, 0}, // 05 row
                {0, 1, 1, 0, 0, 1, 1}, // 06 row
                {0, 0, 1, 1, 1, 1, 0}, // 07 row
        }));
        alphabet.put('e', new Bitmap( new int[][]  {
                {1, 1, 1, 1, 1, 1, 1}, // 01 row
                {1, 1, 0, 0, 0, 0, 0}, // 02 row
                {1, 1, 0, 0, 0, 0, 0}, // 03 row
                {1, 1, 1, 1, 1, 1, 0}, // 04 row
                {1, 1, 0, 0, 0, 0, 0}, // 05 row
                {1, 1, 0, 0, 0, 0, 0}, // 06 row
                {1, 1, 1, 1, 1, 1, 1}, // 07 row
        }));
        alphabet.put('i', new Bitmap( new int[][]  {
                {1, 1, 1, 1, 1, 1, 1}, // 01 row
                {0, 0, 1, 1, 1, 0, 0}, // 02 row
                {0, 0, 1, 1, 1, 0, 0}, // 03 row
                {0, 0, 1, 1, 1, 0, 0}, // 04 row
                {0, 0, 1, 1, 1, 0, 0}, // 05 row
                {0, 0, 1, 1, 1, 0, 0}, // 06 row
                {1, 1, 1, 1, 1, 1, 1}, // 07 row
        }));
        alphabet.put('l', new Bitmap( new int[][]  {
                {1, 1, 0, 0, 0, 0, 0}, // 01 row
                {1, 1, 0, 0, 0, 0, 0}, // 02 row
                {1, 1, 0, 0, 0, 0, 0}, // 03 row
                {1, 1, 0, 0, 0, 0, 0}, // 04 row
                {1, 1, 0, 0, 0, 0, 0}, // 05 row
                {1, 1, 0, 0, 0, 0, 1}, // 06 row
                {1, 1, 1, 1, 1, 1, 1}, // 07 row
        }));
        alphabet.put('n', new Bitmap( new int[][]  {
                {1, 1, 0, 0, 0, 1, 1}, // 01 row
                {1, 1, 1, 0, 0, 1, 1}, // 02 row
                {1, 1, 1, 1, 0, 1, 1}, // 03 row
                {1, 1, 1, 1, 1, 1, 1}, // 04 row
                {1, 1, 0, 1, 1, 1, 1}, // 05 row
                {1, 1, 0, 0, 1, 1, 1}, // 06 row
                {1, 1, 0, 0, 0, 1, 1}, // 07 row
        }));
        alphabet.put('o', new Bitmap( new int[][]  {
                {0, 1, 1, 1, 1, 1, 0}, // 01 row
                {1, 1, 0, 0, 0, 1, 1}, // 02 row
                {1, 1, 0, 0, 0, 1, 1}, // 03 row
                {1, 1, 0, 0, 0, 1, 1}, // 04 row
                {1, 1, 0, 0, 0, 1, 1}, // 05 row
                {1, 1, 0, 0, 0, 1, 1}, // 06 row
                {0, 1, 1, 1, 1, 1, 0}, // 07 row
        }));
        alphabet.put('p', new Bitmap( new int[][]  {
                {1, 1, 1, 1, 1, 1, 0}, // 01 row
                {1, 1, 0, 0, 0, 1, 1}, // 02 row
                {1, 1, 0, 0, 0, 1, 1}, // 03 row
                {1, 1, 0, 0, 0, 1, 1}, // 04 row
                {1, 1, 1, 1, 1, 1, 0}, // 05 row
                {1, 1, 0, 0, 0, 0, 0}, // 06 row
                {1, 1, 0, 0, 0, 0, 0}, // 07 row
        }));
        alphabet.put('r', new Bitmap( new int[][]  {
                {1, 1, 1, 1, 1, 1, 0}, // 01 row
                {1, 1, 0, 0, 0, 1, 1}, // 02 row
                {1, 1, 0, 0, 0, 1, 1}, // 03 row
                {1, 1, 0, 0, 1, 1, 1}, // 04 row
                {1, 1, 1, 1, 1, 1, 0}, // 05 row
                {1, 1, 0, 0, 1, 1, 0}, // 06 row
                {1, 1, 0, 0, 0, 1, 1}, // 07 row
        }));
        alphabet.put('s', new Bitmap( new int[][]  {
                {0, 1, 1, 1, 1, 1, 0}, // 01 row
                {1, 1, 0, 0, 0, 1, 1}, // 02 row
                {1, 1, 0, 0, 0, 0, 0}, // 03 row
                {0, 1, 1, 1, 1, 1, 0}, // 04 row
                {0, 0, 0, 0, 0, 1, 1}, // 05 row
                {1, 1, 0, 0, 0, 1, 1}, // 06 row
                {0, 1, 1, 1, 1, 1, 0}, // 07 row
        }));
        alphabet.put('t', new Bitmap( new int[][]  {
                {1, 1, 1, 1, 1, 1, 1}, // 01 row
                {1, 1, 1, 1, 1, 1, 1}, // 02 row
                {0, 0, 1, 1, 1, 0, 0}, // 03 row
                {0, 0, 1, 1, 1, 0, 0}, // 04 row
                {0, 0, 1, 1, 1, 0, 0}, // 05 row
                {0, 0, 1, 1, 1, 0, 0}, // 06 row
                {0, 0, 1, 1, 1, 0, 0}, // 07 row
        }));
        alphabet.put('v', new Bitmap( new int[][]  {
                {1, 1, 0, 0, 0, 1, 1}, // 01 row
                {1, 1, 0, 0, 0, 1, 1}, // 02 row
                {1, 1, 0, 0, 0, 1, 1}, // 03 row
                {1, 1, 1, 0, 1, 1, 1}, // 04 row
                {0, 1, 1, 1, 1, 1, 0}, // 05 row
                {0, 0, 1, 1, 1, 0, 0}, // 06 row
                {0, 0, 0, 1, 0, 0, 0}, // 07 row
        }));
        alphabet.put('x', new Bitmap( new int[][]  {
                {1, 1, 0, 0, 0, 1, 1}, // 01 row
                {1, 1, 1, 0, 1, 1, 1}, // 02 row
                {0, 1, 1, 1, 1, 1, 0}, // 03 row
                {0, 0, 1, 1, 1, 0, 0}, // 04 row
                {0, 1, 1, 1, 1, 1, 0}, // 05 row
                {1, 1, 1, 0, 1, 1, 1}, // 06 row
                {1, 1, 0, 0, 0, 1, 1}, // 07 row
        }));
        alphabet.put('y', new Bitmap( new int[][]  {
                {1, 1, 0, 0, 0, 1, 1}, // 01 row
                {1, 1, 0, 0, 0, 1, 1}, // 02 row
                {1, 1, 1, 0, 1, 1, 1}, // 03 row
                {0, 1, 1, 1, 1, 1, 0}, // 04 row
                {0, 0, 1, 1, 1, 0, 0}, // 05 row
                {0, 0, 1, 1, 1, 0, 0}, // 06 row
                {0, 0, 1, 1, 1, 0, 0}, // 07 row
        }));
        alphabet.put('-', new Bitmap( new int[][]  {
                {0, 0, 0, 0, 0, 0, 0}, // 01 row
                {0, 0, 0, 0, 0, 0, 0}, // 02 row
                {0, 0, 0, 0, 0, 0, 0}, // 03 row
                {0, 1, 1, 1, 1, 1, 0}, // 04 row
                {0, 1, 1, 1, 1, 1, 0}, // 05 row
                {0, 0, 0, 0, 0, 0, 0}, // 06 row
                {0, 0, 0, 0, 0, 0, 0}, // 07 row
        }));
    }
    private void initializeNumbers(){
        //Initialize Numbers
        alphabet.put('0', new Bitmap( new int[][]  {
                {0, 0, 1, 1, 1, 0, 0}, // 01 row
                {0, 1, 0, 0, 1, 1, 0}, // 02 row
                {1, 1, 0, 0, 0, 1, 1}, // 03 row
                {1, 1, 0, 1, 0, 1, 1}, // 04 row
                {1, 1, 0, 0, 0, 1, 1}, // 05 row
                {0, 1, 1, 0, 0, 1, 0}, // 06 row
                {0, 0, 1, 1, 1, 0, 0}, // 07 row
        }));
        alphabet.put('1', new Bitmap( new int[][]  {
                {0, 0, 1, 1, 1, 0, 0}, // 01 row
                {0, 1, 1, 1, 1, 0, 0}, // 02 row
                {0, 0, 1, 1, 1, 0, 0}, // 03 row
                {0, 0, 1, 1, 1, 0, 0}, // 04 row
                {0, 0, 1, 1, 1, 0, 0}, // 05 row
                {0, 0, 1, 1, 1, 0, 0}, // 06 row
                {1, 1, 1, 1, 1, 1, 1}, // 07 row
        }));
        alphabet.put('2', new Bitmap( new int[][]  {
                {0, 1, 1, 1, 1, 1, 0}, // 01 row
                {1, 1, 0, 0, 0, 1, 1}, // 02 row
                {0, 0, 0, 0, 0, 1, 1}, // 03 row
                {0, 0, 0, 1, 1, 1, 1}, // 04 row
                {0, 1, 1, 1, 1, 0, 0}, // 05 row
                {1, 1, 1, 0, 0, 0, 1}, // 06 row
                {1, 1, 1, 1, 1, 1, 1}, // 07 row
        }));
        alphabet.put('3', new Bitmap( new int[][]  {
                {1, 1, 1, 1, 1, 1, 1}, // 01 row
                {0, 0, 0, 0, 1, 1, 0}, // 02 row
                {0, 0, 0, 1, 1, 0, 0}, // 03 row
                {0, 0, 1, 1, 1, 1, 0}, // 04 row
                {0, 0, 0, 0, 0, 1, 1}, // 05 row
                {1, 1, 0, 0, 0, 1, 1}, // 06 row
                {0, 1, 1, 1, 1, 1, 0}, // 07 row
        }));
        alphabet.put('4', new Bitmap( new int[][]  {
                {0, 0, 0, 1, 1, 1, 0}, // 01 row
                {0, 0, 1, 1, 1, 1, 0}, // 02 row
                {0, 1, 1, 0, 1, 1, 0}, // 03 row
                {1, 1, 0, 0, 1, 1, 0}, // 04 row
                {1, 1, 1, 1, 1, 1, 1}, // 05 row
                {0, 0, 0, 0, 1, 1, 0}, // 06 row
                {0, 0, 0, 0, 1, 1, 0}, // 07 row
        }));;
        alphabet.put('5', new Bitmap( new int[][]  {
                {1, 1, 1, 1, 1, 1, 1}, // 01 row
                {1, 1, 0, 0, 0, 0, 0}, // 02 row
                {1, 1, 1, 1, 1, 1, 0}, // 03 row
                {0, 0, 0, 0, 0, 1, 1}, // 04 row
                {0, 0, 0, 0, 0, 1, 1}, // 05 row
                {1, 1, 0, 0, 0, 1, 1}, // 06 row
                {0, 1, 1, 1, 1, 1, 0}, // 07 row
        }));
        alphabet.put('6', new Bitmap( new int[][]  {
                {0, 0, 1, 1, 1, 1, 1}, // 01 row
                {0, 1, 0, 0, 0, 0, 1}, // 02 row
                {1, 1, 0, 0, 0, 0, 0}, // 03 row
                {1, 1, 1, 1, 1, 1, 0}, // 04 row
                {1, 1, 0, 0, 0, 1, 1}, // 05 row
                {1, 1, 0, 0, 0, 1, 1}, // 06 row
                {0, 1, 1, 1, 1, 1, 0}, // 07 row
        }));
        alphabet.put('7', new Bitmap( new int[][]  {
                {1, 1, 1, 1, 1, 1, 1}, // 01 row
                {1, 1, 0, 0, 0, 1, 1}, // 02 row
                {0, 0, 0, 0, 1, 1, 0}, // 03 row
                {0, 0, 0, 1, 1, 0, 0}, // 04 row
                {0, 0, 1, 1, 0, 0, 0}, // 05 row
                {0, 0, 1, 1, 0, 0, 0}, // 06 row
                {0, 0, 1, 1, 0, 0, 0}, // 07 row
        }));
        alphabet.put('8', new Bitmap( new int[][]  {
                {0, 1, 1, 1, 1, 1, 0}, // 01 row
                {1, 1, 0, 0, 0, 1, 1}, // 02 row
                {1, 1, 0, 0, 0, 1, 1}, // 03 row
                {0, 1, 1, 1, 1, 1, 0}, // 04 row
                {1, 1, 0, 0, 0, 1, 1}, // 05 row
                {1, 1, 0, 0, 0, 1, 1}, // 06 row
                {0, 1, 1, 1, 1, 1, 0}, // 07 row
        }));
        alphabet.put('9', new Bitmap( new int[][]  {
                {0, 1, 1, 1, 1, 1, 0}, // 01 row
                {1, 1, 0, 0, 0, 1, 1}, // 02 row
                {1, 1, 0, 0, 0, 1, 1}, // 03 row
                {0, 1, 1, 1, 1, 1, 1}, // 04 row
                {0, 0, 0, 0, 0, 1, 1}, // 05 row
                {1, 0, 0, 0, 0, 1, 1}, // 06 row
                {0, 1, 1, 1, 1, 1, 0}, // 07 row
        }));
    }
    public void drawWord(int x, int y, String word, Color color){
        int step = 15, i = 0;
        for (char c : word.toCharArray()) {
            drawChar(x + (step * i++), y, color, c);
        }
    }
}

