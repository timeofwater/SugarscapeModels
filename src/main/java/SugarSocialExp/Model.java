package SugarSocialExp;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Shang Zemo on 2022/10/10
 */
public class Model {

    Block[][] square;
    int[][] productions;
    List<Role> humanList;
    List<V2> LocationList;
    int[] statuses;
    Role richest;
    int round;
    List<V2> resultList;// x = status, y = sugar
    final int maxEyeAbility;
    final int maxMouthAbility;
    final int maxProductAbility;
    final int size;
    int riskPoint;

    public Model(int maxEye, int maxMouth, int maxProduct, int size) {
        this.maxEyeAbility = maxEye;
        this.maxMouthAbility = maxMouth;
        this.maxProductAbility = maxProduct;
        this.size = size;
        riskPoint = 0;

        init();
    }

    void init() {
        initSquare();
        initProductions();
        initHumanList();
        initLocationList();
        this.statuses = new int[size];
        this.round = 0;
        this.resultList = new LinkedList<>();
    }

    void initSquare() {
        this.square = new Block[size][];
        for (int i = 0; i < size; i++) {
            this.square[i] = new Block[size];
        }

        // 先用随机生成，后改为可视化生成
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.square[i][j] = new Block(random.nextInt(this.maxProductAbility + 1), i + 1, j + 1);
            }
        }
    }

    void initProductions() {
        this.productions = new int[size][];
        for (int i = 0; i < size; i++) {
            this.productions[i] = new int[size];
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.productions[i][j] = this.square[i][j].product;
            }
        }
    }

    void initHumanList() {
        this.humanList = new LinkedList<>();

        // 先用随机生成，后改为可视化生成
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.humanList.add(new Role(i + 1,
                        j + 1,
                        random.nextInt(this.maxMouthAbility) + 1,
                        random.nextInt(this.maxEyeAbility) + 1
                ));
                this.square[i][j].isHold = true;
            }
        }
    }

    void initLocationList() {
        this.LocationList = new LinkedList<>();

        for (Role r :
                this.humanList) {
            this.LocationList.add(r.position);
        }
    }

    void oneTurnToEarn() {
        Iterator<Role> roleIterator = this.humanList.iterator();
        int counter = 0;
        while (roleIterator.hasNext()) {
            roleIterator.next().earn(this.square[counter / size][counter % size].product);
            counter += 1;
        }
    }

    void oneTurnToMove() {
        Iterator<Role> roleIterator = this.humanList.iterator();
        HashMap<V2, Integer> productionInEyes;
        while (roleIterator.hasNext()) {
            Role role = roleIterator.next();
            if (role.status == 1) {
                V2 position = role.position;
                productionInEyes = new HashMap<>();
                productionInEyes.put(position, this.square[position.x - 1][position.y - 1].product);
                for (int i = 1; i <= role.eye; i++) {
                    // i-
                    if (position.x - 1 - i >= 0 && !this.square[position.x - 1 - i][position.y - 1].isHold) {
                        productionInEyes.put(new V2(position.x - i, position.y),
                                this.square[position.x - 1 - i][position.y - 1].product);
                    }
                    // i+
                    if (position.x - 1 + i < size && !this.square[position.x - 1 + i][position.y - 1].isHold) {
                        productionInEyes.put(new V2(position.x + i, position.y),
                                this.square[position.x - 1 + i][position.y - 1].product);
                    }
                    // j-
                    if (position.y - 1 - i >= 0 && !this.square[position.x - 1][position.y - 1 - i].isHold) {
                        productionInEyes.put(new V2(position.x, position.y - i),
                                this.square[position.x - 1][position.y - 1 - i].product);
                    }
                    // j+
                    if (position.y - 1 + i < size && !this.square[position.x - 1][position.y - 1 + i].isHold) {
                        productionInEyes.put(new V2(position.x, position.y + i),
                                this.square[position.x - 1][position.y - 1 + i].product);
                    }
                }
                int max = this.square[position.x - 1][position.y - 1].product;
                for (V2 p :
                        productionInEyes.keySet()) {
                    if (productionInEyes.get(p) + this.riskPoint > max) {
                        max = productionInEyes.get(p);
                        this.square[role.position.x - 1][role.position.y - 1].isHold = false;
                        role.position = p;
                        this.square[role.position.x - 1][role.position.y - 1].isHold = true;
                    }
                }
            }
        }
    }

    void oneTurnToEat() {
        Iterator<Role> roleIterator = this.humanList.iterator();
        while (roleIterator.hasNext()) {
            Role role = roleIterator.next();
            if (role.eat()) {
                role.status = 1;
            } else {
                role.status = 0;
                this.square[role.position.x - 1][role.position.y - 1].isHold = false;
            }
        }
    }

    List<V2> getAllHumanLocation() {
        List<V2> rs = new LinkedList<>();

        for (Role r :
                this.humanList) {
            rs.add(r.position);
        }

        return rs;
    }

    List<V2> getLiveHumanLocation() {
        List<V2> rs = new LinkedList<>();

        for (Role r :
                this.humanList) {
            if (r.status == 1) {
                rs.add(r.position);
            }
        }

        return rs;
    }

    List<V2> getDeadHumanLocation() {
        List<V2> rs = new LinkedList<>();

        for (Role r :
                this.humanList) {
            if (r.status == 0) {
                rs.add(r.position);
            }
        }

        return rs;
    }

    int[][] getProductions() {
        return productions;
    }

    Role getTheRichest() {
        return richest;
    }

    List<V2> getResultList() {
        List<V2> rs = new LinkedList<>();

        for (Role r :
                this.humanList) {
            rs.add(new V2(r.status, r.sugar));
        }

        return rs;
    }

    public Model start(int maxRound, int maxSugarInTheRichest) throws InterruptedException {
        // 开始阶段
        oneTurnToEarn();
        System.out.println("*******  init  *******");
        simplePrintInConsole();

        //演绎阶段
        while (this.round < maxRound) {
            round += 1;
            oneTurnToMove();
            oneTurnToEarn();
            oneTurnToEat();
            System.out.println("*******  " + this.round + "  *******");
            simplePrintInConsole();
            int richestSugar = 0;
            for (Role r :
                    this.humanList) {
                if (r.sugar > richestSugar) {
                    this.richest = r;
                    richestSugar = this.richest.sugar;
                }
            }
            if (this.richest.sugar > maxSugarInTheRichest) {
                break;
            }
        }

        //结束阶段
        System.out.println("*******  " + this.round + "  *******");
        simplePrintInConsole();
        System.out.println(this.richest);

        return this;
    }

    public void simplePrintInConsole() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(this.square[i][j].isHold ? "  *  " : "     ");
            }
            System.out.println();
            for (int j = 0; j < size; j++) {
                System.out.print("  " + this.square[i][j].product + "  ");
            }
            System.out.println();
        }
    }

    public void setRiskPoint(int riskPoint) {
        this.riskPoint = riskPoint;
    }

    public int getRound() {
        return round;
    }

    @Override
    public String toString() {
        return "SugarSocalExp.Model{" +
                "square=" + Arrays.toString(square) +
                ", productions=" + Arrays.toString(productions) +
                ", humanList=" + Arrays.toString(humanList.toArray()) +
                ", LocationList=" + Arrays.toString(LocationList.toArray()) +
                ", statuses=" + Arrays.toString(statuses) +
                ", richest=" + richest +
                ", round=" + round +
                ", resultList=" + Arrays.toString(resultList.toArray()) +
                ", maxEyeAbility=" + maxEyeAbility +
                ", maxMouthAbility=" + maxMouthAbility +
                ", maxProductAbility=" + maxProductAbility +
                ", size=" + size +
                '}';
    }
}
