import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame implements ActionListener {

    public MainPage() {
        setTitle("Game Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        JButton sudokuButton = new JButton("Sudoku");
        sudokuButton.setActionCommand("sudoku");
        sudokuButton.addActionListener(this);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(sudokuButton);
        sudokuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton ticTacToeButton = new JButton("Tic Tac Toe");
        ticTacToeButton.setActionCommand("tictactoe");
        ticTacToeButton.addActionListener(this);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(ticTacToeButton);
        ticTacToeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton eightPuzzleButton = new JButton("8 Puzzle");
        eightPuzzleButton.setActionCommand("eightpuzzle");
        eightPuzzleButton.addActionListener(this);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(eightPuzzleButton);
        eightPuzzleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createVerticalGlue());
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("sudoku")) {
            System.out.println("Launching Sudoku...");
            // SwingUtilities.invokeLater(() -> {
            //     SudokuSolver sudoku = new SudokuSolver();
            //     sudoku.setTitle("Solve Sudoku");
            // });
        } else if (command.equals("tictactoe")) {
            System.out.println("Launching Tic Tac Toe...");
            // SwingUtilities.invokeLater(() -> {
            //     TicTacToe ticTacToeGUI = new TicTacToe();
            //     ticTacToeGUI.setTitle("Play Tic Tac Toe");
            // });
        } else if (command.equals("eightpuzzle")) {
            System.out.println("Launching 8 Puzzle...");
            SwingUtilities.invokeLater(() -> {
                EightPuzzle eightPuzzle = new EightPuzzle();
                eightPuzzle.setTitle("Solve 8 Puzzle");
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPage());
    }
}