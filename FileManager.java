
package Game;

    // ==============================================
// CLASS 3: FileManager
// ==============================================
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FileManager {

    private static final String FILE_NAME = "game_history.txt";

    // Save Data
    public void saveData(String name, String guess, String result, int score) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("Name: " + name + " | Guess: " + guess + " | Result: " + result + " | Score: " + score);
            pw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "❌ Error saving data!");
        }
    }

    // Show History
    public void showHistory() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            StringBuilder history = new StringBuilder("📜 GAME HISTORY 📜\n-----------------------------\n");

            while ((line = br.readLine()) != null) {
                history.append(line).append("\n");
            }
            br.close();

            JTextArea textArea = new JTextArea(history.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(450, 300));

            JOptionPane.showMessageDialog(null, scrollPane, "Game History", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "❌ No history found!");
        }
    }
}
