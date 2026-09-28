import processing.core.PApplet;
public class Main extends PApplet {
    
    Grid gameBoard;
    BackgroundScreen bgScreen;
    
    int cellSize = 20; 
    int appState = 0; 
    boolean isPaused = true; 
    int brushSize = 1; 

    public static void main(String[] args) {
        PApplet.main("Main"); 
    }

    public void settings() {
        size(1000, 700); 
        pixelDensity(1);
    }

    public void setup() {
        surface.setResizable(true); 
        gameBoard = new Grid(150, 150); 
        bgScreen = new BackgroundScreen(this); 
        
        for(int i=0; i<400; i++) {
            gameBoard.setCellState((int)random(5, 35), (int)random(5, 45), true);
        }
    }

    public void draw() {
        if (appState == 0) drawMenu();
        else if (appState == 1) bgScreen.drawScreen(); 
        else if (appState == 2) drawSimulation();
    }
    public void drawMenu() {
        background(245, 247, 250); 
        
        if (frameCount % 120 == 0) {
            int r = (int)random(10, 25);
            int c = (int)random(10, 40);
            gameBoard.setCellState(r, c+1, true);
            gameBoard.setCellState(r, c+2, true);
            gameBoard.setCellState(r+1, c, true);
            gameBoard.setCellState(r+1, c+1, true);
            gameBoard.setCellState(r+2, c+1, true);
        }

        if (frameCount % 10 == 0) gameBoard.updateToNextGeneration();
        
        gameBoard.draw(this, cellSize);
        fill(245, 247, 250, 210); 
        noStroke();
        rect(0, 0, width, height);
        
        fill(200, 200, 200, 150);
        textAlign(CENTER, CENTER);
        textSize(80); 
        text("THE GAME OF LIFE", (width / 2) + 4, (height / 3) + 4);
        
        fill(44, 62, 80); 
        text("THE GAME OF LIFE", width / 2, height / 3);

        fill(46, 204, 113); 
        rect(width/2 - 150, height/2, 300, 55, 8); 
        fill(255); 
        textSize(22);
        text("ENTER SIMULATION", width/2, height/2 + 25);

        fill(52, 152, 219); 
        rect(width/2 - 150, height/2 + 80, 300, 55, 8);
        fill(255);
        text("BACKGROUND & THEORY", width/2, height/2 + 105);
    }
    public void drawSimulation() {
        background(255);
        gameBoard.draw(this, cellSize);

        if (!isPaused && frameCount % 5 == 0) {
            gameBoard.updateToNextGeneration();
        }

        fill(40, 44, 52); 
        noStroke();
        rect(0, 0, 220, height); 
        
        fill(255); 
        textAlign(CENTER, TOP);
        textSize(28);
        text("CONTROLS", 110, 30);
        
        fill(isPaused ? color(46, 204, 113) : color(241, 196, 15)); 
        rect(20, 90, 180, 45, 8);
        fill(isPaused ? 255 : 0); 
        textAlign(CENTER, CENTER);
        textSize(20);
        text(isPaused ? "PLAY" : "PAUSE", 110, 110);

        fill(231, 76, 60); 
        rect(20, 150, 180, 45, 8);
        fill(255);
        text("CLEAR", 110, 170);

        fill(149, 165, 166); 
        rect(20, 210, 180, 45, 8);
        fill(255);
        text("BRUSH: " + brushSize + "x" + brushSize, 110, 230);

        fill(52, 152, 219); 
        rect(20, 270, 180, 45, 8);
        fill(255);
        text("SPAWN GLIDER", 110, 290);

        fill(155, 89, 182); 
        rect(20, 330, 180, 45, 8);
        fill(255);
        text("SPAWN GUN", 110, 350);

        fill(52, 73, 94); 
        rect(20, height - 80, 180, 45, 8);
        fill(255);
        text("MAIN MENU", 110, height - 60);
    }
    public void spawnGliderGun() {
        gameBoard.clearBoard(); 
        int r = 10; 
        int c = 10;
        
        int[][] gunCoords = {
            {4,0}, {5,0}, {4,1}, {5,1}, {4,10}, {5,10}, {6,10}, {3,11}, {7,11}, {2,12}, 
            {8,12}, {2,13}, {8,13}, {5,14}, {3,15}, {7,15}, {4,16}, {5,16}, {6,16}, {5,17}, 
            {2,20}, {3,20}, {4,20}, {2,21}, {3,21}, {4,21}, {1,22}, {5,22}, {0,24}, {1,24}, 
            {5,24}, {6,24}, {2,34}, {3,34}, {2,35}, {3,35}
        };

        for (int[] coord : gunCoords) {
            gameBoard.setCellState(r + coord[0], c + coord[1], true);
        }
    }
    public void mousePressed() {
        if (appState == 0) {
            if (mouseX > width/2 - 150 && mouseX < width/2 + 150 && mouseY > height/2 && mouseY < height/2 + 55) {
                gameBoard.clearBoard(); 
                isPaused = true; 
                appState = 2; 
            }
            else if (mouseX > width/2 - 150 && mouseX < width/2 + 150 && mouseY > height/2 + 80 && mouseY < height/2 + 135) appState = 1; 
        } 
        else if (appState == 1) {
            if (mouseX > 30 && mouseX < 150 && mouseY > 30 && mouseY < 75) appState = 0;
        }
        else if (appState == 2) {
            if (mouseX < 220) {
                if (mouseY > 90 && mouseY < 135) isPaused = !isPaused;
                else if (mouseY > 150 && mouseY < 195) gameBoard.clearBoard();
                else if (mouseY > 210 && mouseY < 255) brushSize = (brushSize == 1) ? 3 : 1;
                else if (mouseY > 270 && mouseY < 315) {
                    int midC = (width / cellSize) / 2; 
                    int midR = (height / cellSize) / 2;
                    gameBoard.setCellState(midR, midC, true);
                    gameBoard.setCellState(midR+1, midC+1, true);
                    gameBoard.setCellState(midR+2, midC-1, true);
                    gameBoard.setCellState(midR+2, midC, true);
                    gameBoard.setCellState(midR+2, midC+1, true);
                }
                else if (mouseY > 330 && mouseY < 375) spawnGliderGun();
                else if (mouseY > height - 80 && mouseY < height - 35) appState = 0;
            } 
            else {
                drawWithBrush();
            }
        }
    }

    public void mouseDragged() {
        if (appState == 2 && mouseX > 220) {
            drawWithBrush();
        }
    }
    
    public void drawWithBrush() {
        int baseC = mouseX / cellSize;
        int baseR = mouseY / cellSize;
        
        for (int i = 0; i < brushSize; i++) {
            for (int j = 0; j < brushSize; j++) {
                gameBoard.setCellState(baseR + i, baseC + j, true);
            }
        }
    }

    public void keyPressed() {
        if (appState == 2) { 
            if (key == ' ') isPaused = !isPaused;
            if (key == 'c' || key == 'C') gameBoard.clearBoard();
        }
    }
}