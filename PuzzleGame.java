import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class EightPuzzle extends JFrame {
    private JButton[][] tiles = new JButton[3][3];
    private int emptyRow, emptyCol;
    private final int SIZE = 3;

    public EightPuzzle() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(SIZE, SIZE));
        
        initializeBoard();
        addTilesToFrame();
        shuffleBoard();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeBoard() {
        int number = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == SIZE - 1 && j == SIZE - 1) {
                    tiles[i][j] = new JButton("");
                    emptyRow = i;
                    emptyCol = j;
                } else {
                    tiles[i][j] = new JButton(String.valueOf(number++));
                }
                tiles[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                tiles[i][j].addActionListener(new TileListener());
            }
        }
    }

    private void addTilesToFrame() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                add(tiles[i][j]);
            }
        }
    }

    private void shuffleBoard() {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int direction = rand.nextInt(4);
            switch (direction) {
                case 0: if (emptyRow > 0) moveTile(emptyRow - 1, emptyCol); break; // Up
                case 1: if (emptyRow < SIZE - 1) moveTile(emptyRow + 1, emptyCol); break; // Down
                case 2: if (emptyCol > 0) moveTile(emptyRow, emptyCol - 1); break; // Left
                case 3: if (emptyCol < SIZE - 1) moveTile(emptyRow, emptyCol + 1); break; // Right
            }
        }
    }

    private void moveTile(int row, int col) {
        if (isAdjacent(row, col)) {
            String temp = tiles[row][col].getText();
            tiles[row][col].setText("");
            tiles[emptyRow][emptyCol].setText(temp);
            emptyRow = row;
            emptyCol = col;
            checkWin();
        }
    }

    private boolean isAdjacent(int row, int col) {
        return (Math.abs(row - emptyRow) == 1 && col == emptyCol) ||
               (Math.abs(col - emptyCol) == 1 && row == emptyRow);
    }

    private void checkWin() {
        int expected = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == SIZE - 1 && j == SIZE - 1) {
                    if (tiles[i][j].getText().equals("")) {
                        showWinMessage();
                        return;
                    }
                } else {
                    if (!tiles[i][j].getText().equals(String.valueOf(expected++))) {
                        return;
                    }
                }
            }
        }
    }

    private void showWinMessage() {
        JOptionPane.showMessageDialog(this, "Congratulations! You solved the puzzle!",
                "Victory!", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private class TileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (tiles[i][j] == button) {
                        moveTile(i, j);
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EightPuzzle());
    }
}