import javax.swing.SwingUtilities;

public class NameListApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NameListGUI();
            }
        });
    }
}
