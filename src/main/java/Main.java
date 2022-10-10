import SugarSocialExp.Model;

/**
 * @author Shang Zemo on 2022/9/26
 */
public class Main {

    public static void main(String[] args) {
        try {
            Model model = new Model(10, 5, 4, 50);
            model.setRiskPoint(1);
            model = model.start(50, 100);
            System.out.println(model.getRound()+" rounds");
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

}

