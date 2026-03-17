import processing.core.PApplet;
public class BackgroundScreen {
    PApplet window; 
    public BackgroundScreen(PApplet window) {
        this.window = window;
    }
public void drawScreen() {
        window.background(30, 35, 45); 
        
        // --- 1. THE BACK BUTTON ---
        window.fill(231, 76, 60); 
        window.noStroke();
        window.rect(30, 30, 120, 45, 8);
        window.fill(255);
        window.textAlign(window.CENTER, window.CENTER);
        window.textSize(18);
        window.text("BACK", 90, 50);

        //Title
        window.fill(52, 152, 219); 
        window.textAlign(window.CENTER, window.TOP);
        window.textSize(45);
        window.text("The Theory of Cellular Automata", window.width/2, 30);

        //Background
        window.textAlign(window.LEFT, window.TOP);
        window.fill(200, 210, 220); 
        window.textSize(18);
        
        String history = "Invented by British mathematician John Horton Conway in 1970, the Game of Life is the most " +
                         "famous example of a cellular automaton. It is a 'zero-player game', meaning its evolution is " +
                         "determined entirely by its initial state, requiring no further human input.\n\n" +
                         "SIGNIFICANCE: Despite having only four simple rules, the Game of Life is Turing Complete. " +
                         "This means that the grid can be programmed to simulate any real-world computer algorithm. " +
                         "Because of this, the fate of complex patterns is 'undecidable' there is no algorithm that can " +
                         "predict if a random pattern will live forever or die out without actually running the simulation.";
        
        // The text box 
        window.text(history, 60, 100, window.width - 120, 200); 

        // Rules
        window.fill(46, 204, 113); 
        window.textSize(24);
        window.text("THE 4 RULES OF SURVIVAL:", 60, 280);
        
        window.fill(220); 
        window.textSize(18);
        String rules = "Every cell interacts with its eight neighbors (horizontal, vertical, diagonal).\n\n" +
                       "1. Underpopulation: A live cell with fewer than 2 live neighbors dies.\n\n" +
                       "2. Survival: A live cell with 2 or 3 live neighbors lives on.\n\n" +
                       "3. Overpopulation: A live cell with more than 3 live neighbors dies.\n\n" +
                       "4. Reproduction: A dead cell with exactly 3 live neighbors becomes alive.";
        window.text(rules, 60, 320, 420, 300); 

        // Pattern
        window.fill(241, 196, 15); 
        window.textSize(24);
        window.text("COMMON SHAPES & AUTOMATA:", 520, 280);

        window.fill(220);
        window.textSize(17);

        // Box
        window.text("1. Still Lifes (Stable, never change): 'The Block'", 520, 320);
        drawCellBlock(520, 345); 

        // Oscillator
        window.text("2. Oscillators (Flip back and forth): 'The Blinker'", 520, 400);
        drawBlinker(520, 425); 

        // SGLider
        window.text("3. Spaceships (Move across grid): 'The Glider'", 520, 480);
        drawGlider(520, 505); 

        // Methuselahs & Guns
        String complex = "4. Methuselahs: Patterns that take thousands of generations to stabilize (e.g., 'R-pentomino').\n\n" +
                         "5. Guns: Massive engines that infinitely shoot spaceships (e.g., 'Gosper Glider Gun').";
        window.text(complex, 520, 580, 420, 150);
    }

    // --- MINI DRAWING FUNCTIONS ---
    private void drawCellBlock(int x, int y) {
        window.fill(241, 196, 15);
        window.stroke(30, 35, 45); 
        window.strokeWeight(2);
        window.rect(x, y, 15, 15);
        window.rect(x + 15, y, 15, 15);
        window.rect(x, y + 15, 15, 15);
        window.rect(x + 15, y + 15, 15, 15);
    }

    private void drawBlinker(int x, int y) {
        window.fill(46, 204, 113);
        window.stroke(30, 35, 45);
        window.strokeWeight(2);
        window.rect(x, y, 15, 15);
        window.rect(x + 15, y, 15, 15);
        window.rect(x + 30, y, 15, 15);
    }

    private void drawGlider(int x, int y) {
        window.fill(52, 152, 219);
        window.stroke(30, 35, 45);
        window.strokeWeight(2);
        window.rect(x + 15, y, 15, 15); 
        window.rect(x + 30, y + 15, 15, 15); 
        window.rect(x, y + 30, 15, 15); 
        window.rect(x + 15, y + 30, 15, 15); 
        window.rect(x + 30, y + 30, 15, 15); 
    }
}