package SugarSocialExp;

/**
 * @author Shang Zemo on 2022/10/10
 */
public class Block {

    final int product;
    final V2 position;
    boolean isHold;

    Block(int product, V2 position) {
        this.product = product;
        this.position = position;
        isHold = false;
    }

    Block(int product, int x, int y) {
        this.product = product;
        this.position = new V2(x, y);
        isHold = false;
    }

    @Override
    public String toString() {
        return "SugarSocalExp.Block{" +
                "product=" + product +
                ", x=" + position.x +
                ", y=" + position.y +
                ", isHold=" + isHold +
                '}';
    }
}
