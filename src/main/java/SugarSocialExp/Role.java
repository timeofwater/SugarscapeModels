package SugarSocialExp;

/**
 * @author Shang Zemo on 2022/10/10
 */
public class Role {

    V2 position;
    int status;
    int sugar;
    final int mouth;
    final int eye;

    Role(V2 position, int mouth, int eye) {
        this.position = position;
        status = 1;
        sugar = 0;
        this.mouth = mouth;
        this.eye = eye;
    }

    Role(int x, int y, int mouth, int eye) {
        this.position = new V2(x, y);
        status = 1;
        sugar = 0;
        this.mouth = mouth;
        this.eye = eye;
    }

    void earn(int product) {
        if (status == 1) {
            this.sugar += product;
        }
    }

    boolean eat() {
        if (status == 1) {
            this.sugar -= this.mouth;
            if (this.sugar < 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    boolean earnAndEat(int product) {
        earn(product);
        return eat();
    }

    @Override
    public String toString() {
        return "SugarSocalExp.Role{" +
                "x=" + position.x +
                ", y=" + position.y +
                ", status=" + status +
                ", sugar=" + sugar +
                ", mouth=" + mouth +
                ", eye=" + eye +
                '}';
    }
}
