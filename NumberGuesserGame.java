
package Game;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class NumberGuesserGame extends JFrame {

    // Mga Variables para sa game
    int randomNumber;        // Ang number na huhulaan
    int score = 0;           // Puntos ng player
    int maxTrials = 5;       // Bilang ng trials - 5 beses lang
    int trialsLeft;         // Natitirang trial
    boolean gameRunning;    // Tandaan kung tapos na ang laro
     String rank;

    // Mga Component ng Interface
    JTextField txtGuess, txtName;
    JLabel lblMessage, lblHint, lblScore, lblTrials;
    JButton btnCheck;

    // Para sa File Handling
    FileManager fileManager;

   
    
    // CONSTRUCTOR
    public NumberGuesserGame() {
        fileManager = new FileManager(); //connected sa FileManager Class

        // Window
        setTitle("✨ Cute Number Guesser Game ✨");
        setSize(550, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 228, 240)); 

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // MENU BAR
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255, 182, 193));

        JMenu menuGame = new JMenu("🎮 Game");
        menuGame.setForeground(Color.WHITE);
        JMenuItem itemNew = new JMenuItem("🔄 New Game");
        JMenuItem itemHistory = new JMenuItem("📜 View History");
        JMenuItem itemExit = new JMenuItem("❌ Exit");

        JMenu menuHelp = new JMenu("❓ Help");
        menuHelp.setForeground(Color.WHITE);
        JMenuItem itemHow = new JMenuItem("📖 How to Play");

        menuGame.add(itemNew);
        menuGame.add(itemHistory);
        menuGame.addSeparator();
        menuGame.add(itemExit);
        menuHelp.add(itemHow);
        menuBar.add(menuGame);
        menuBar.add(menuHelp);
        setJMenuBar(menuBar);

        

        // TITLE
        JLabel lblTitle = new JLabel("GUESS THE NUMBER!", JLabel.CENTER);
        lblTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        lblTitle.setForeground(new Color(219, 112, 147));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);

        // YOUR NAME
        JLabel lblName = new JLabel("Your Name:", JLabel.CENTER);
        lblName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblName, gbc);

        txtName = new JTextField();
        txtName.setFont(new Font("Arial", Font.PLAIN, 16));
        txtName.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 1;
        add(txtName, gbc);
        txtName.addActionListener(e -> {
        if (!txtName.getText().trim().isEmpty()) {
        score = 0;
        rank = "None";  
        startNewGame();
    }
});
        gbc.gridx = 0; // reset

        // SCORE — RANK
        lblScore = new JLabel("Score: 0 | Rank: Novice", JLabel.CENTER);
        lblScore.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        lblScore.setForeground(new Color(46, 139, 87)); // Green
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(lblScore, gbc);

        // ENTER YOUR GUESS BELOW
        lblMessage = new JLabel("Enter your guess below!", JLabel.CENTER);
        lblMessage.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        lblMessage.setForeground(new Color(128, 0, 128)); // Purple
        gbc.gridy = 3;
        add(lblMessage, gbc);

        // INPUT BOX
        txtGuess = new JTextField();
        txtGuess.setFont(new Font("Arial", Font.PLAIN, 18));
        txtGuess.setHorizontalAlignment(JTextField.CENTER);
        txtGuess.setBackground(new Color(255, 255, 224)); 
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipadx = 60;
        add(txtGuess, gbc);
        
        

        // CHECK ANSWER BUTTON
        btnCheck = new JButton("CHECK ANSWER");
        btnCheck.setBackground(new Color(152, 251, 152)); // Light green
        btnCheck.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        gbc.gridy = 5;
        add(btnCheck, gbc);

        // HINT
        lblHint = new JLabel("Hint: Guess a number between 1 - 100", JLabel.CENTER);
        lblHint.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
        lblHint.setForeground(new Color(139, 69, 19)); // Brown
        gbc.gridy = 6;
        add(lblHint, gbc);

        // TRIALS LEFT
        lblTrials = new JLabel("Trials Left: 5", JLabel.CENTER);
        lblTrials.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        lblTrials.setForeground(new Color(255, 140, 0)); // Orange
        gbc.gridy = 7;
        add(lblTrials, gbc);

        // ACTION LISTENERS
        btnCheck.addActionListener(e -> checkAnswer());
        itemNew.addActionListener(e -> startNewGame());
        itemExit.addActionListener(e -> System.exit(0));

        itemHow.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "📖 How to Play:\n\n"
                + "1. Enter your Name.\n"
                + "2. You have ONLY 5 trials to guess the number between 1 - 100.\n"
                + "3. Faster guess = MORE POINTS!\n"
                + "4. Reach high score to get better Rank!\n"
                + "5. TOO HIGH ➡️ Guess Lower | TOO LOW ➡️ Guess Higher 🎉", "Instructions", JOptionPane.INFORMATION_MESSAGE));

        itemHistory.addActionListener(e -> fileManager.showHistory());

        
        startNewGame();
    }

    
    // NEW GAME
    public void startNewGame() {
        Random rand = new Random();
    randomNumber = rand.nextInt(100) + 1;

    
    trialsLeft = maxTrials;
    gameRunning = true;

    // Reset display
    lblMessage.setText("Enter your guess below!");
    lblMessage.setForeground(new Color(128, 0, 128));
    updateScoreAndRank();  //
    lblTrials.setText("Trials Left: " + trialsLeft);
    lblHint.setText("Hint: Guess a number between 1 - 100");

    txtGuess.setText("");
    txtGuess.setEnabled(true);
    btnCheck.setEnabled(true);
}
    
    //METHOD: SCORE + RANK

    public void updateScoreAndRank() {
        String rank;
        if (score >= 150) rank = "MASTER GUESSER";
        else if (score >= 100) rank = "EXPERT";
        else if (score >= 60) rank = "ADVANCED";
        else if (score >= 30) rank = "BEGINNER";
        else rank = "None";

        lblScore.setText("Score: " + score + " | Rank: " + rank);
    }


    // CHECK ANSWER 
   
    public void checkAnswer() {
        if (!gameRunning) {
            JOptionPane.showMessageDialog(null, "Game Over! Start a New Game.");
            return;
        }

        try {
            String name = txtName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your NAME first!");
                return;
            }

            if (!InputValidator.isNumber(txtGuess.getText())) {
                JOptionPane.showMessageDialog(null, "Please enter a valid NUMBER only!");
                txtGuess.setText("");
                return;
            }

            int guess = Integer.parseInt(txtGuess.getText());

            // Validate number range
            if (!InputValidator.isInRange(guess)) {
                JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 100!");
                txtGuess.setText("");
                return;
            }

            

    String result;
    if (guess == randomNumber) {
    result = "CORRECT";
    lblMessage.setText("CORRECT! You Win!");
    lblMessage.setForeground(new Color(34, 139, 34));

    if (trialsLeft == 5) { score += 50; lblHint.setText("PERFECT! +50 POINTS"); }
    else if (trialsLeft == 4) { score += 40; lblHint.setText("GREAT! +40 POINTS"); }
    else if (trialsLeft == 3) { score += 30; lblHint.setText("GOOD JOB! +30 POINTS"); }
    else if (trialsLeft == 2) { score += 20; lblHint.setText("NICE! +20 POINTS"); }
    else { score += 10; lblHint.setText("CLOSE CALL! +10 POINTS"); }

    updateScoreAndRank();
    txtGuess.setEnabled(false);
    btnCheck.setEnabled(false);
    gameRunning = false;

} else {
    trialsLeft--;
    lblTrials.setText("Trials Left: " + trialsLeft);

    if (guess > randomNumber) {
        result = "TOO HIGH";
        lblHint.setText("TOO HIGH! Try a number between 1 and " + guess);
        lblMessage.setForeground(new Color(255, 0, 0));
        txtGuess.setText("");
    } else {
        result = "TOO LOW";
        lblHint.setText("TOO LOW! Try a number between " + guess + " and 100");
        lblMessage.setForeground(new Color(30, 144, 255));
        txtGuess.setText("");
    }

    if (trialsLeft == 0) {
        lblMessage.setText("YOU LOSE! Try again.");
        lblMessage.setForeground(Color.RED);
        lblHint.setText("The correct number was: " + randomNumber);
        txtGuess.setEnabled(false);
        btnCheck.setEnabled(false);
        gameRunning = false;
        result = "FAILED";
    }
}

            // Save record
            fileManager.saveData(name, String.valueOf(guess), result, score);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid NUMBER only!");
            txtGuess.setText("");
        }
    }

   
    // MAIN
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            new NumberGuesserGame().setVisible(true);
        });
    }
}
