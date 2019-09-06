import javax.swing.JFrame;

public class GUE extends JFrame {

    public GUE() {
        this.createWindow();
        this.startGame();
    }

    private void createWindow(){
        super.setSize(1000,500);
        super.setTitle("Jump Game");
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    private void startGame(){
        Game game = new Game();
        super.add(game);
    }

}
