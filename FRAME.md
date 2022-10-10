# 背景介绍

糖域模型 Sugarscape models是一个遵循简单规则的基于主体的模型，用于人工智能、基于主体建模以及社会仿真等领域，在约书亚·爱泼斯坦 Joshua M. Epstein和罗伯特·阿克斯特尔 Robert
Axtell一起出版的书籍[《如何创造人工社会》Growing Artificial Societies](https://archive.org/details/growingartificia00epst/page/224)中有详细介绍。

# 基本规则

### 1.<span id="2-1">糖域</span>

1. 场地尺寸为50 x 50的正方形。
2. [方糖](#2-2) 和 [糖人](#2-3) 可以生成于其上。
3. 相同场地的方糖产出数量不变。
4. 可以产出方糖的场地，若场地当前未被糖人占领，则当前方糖不变。

### 2.<span id="2-2">方糖</span>

1. 资源， [糖人](#2-3) 需要持续获取才能满足自身存活条件。
2. 方糖由场地固定产出。
3. 方糖只有两种状态，即 **产出** 和 **被拾取**。

### 3.<span id="2-3">糖人</span>

1. 生存者，每回合均需要获取资源。
2. 回合结算时未获取到所需数量的 [方糖](#2-2) 则死亡。
3. 糖人只有两种状态，即 **存活** 和 **死亡**。
4. 糖人可以搜寻到自身 **视力** 范围内的方糖。
5. 糖人会自动朝 **视力** 范围内的，方糖数量最多的 [场地](#2-1) 移动。
    1. 每个糖人的视力为范围内随机生成。
    2. 视力范围为十字形。
    3. 视力数值表示十字臂长（不含中心）。
    4. 移动方式为回合结算时瞬间移动。

### 4.回合

1. 总体流程控制

    1. 开始阶段
        1. 生成空白场地。
        2. 生成方糖分布。
        3. 生成初始糖人。
        4. 糖人获取场地上的方糖。
        5. 输出此回合的初始糖人位置、死亡糖人位置、剩余糖人位置、方糖在场地上的分布。
    2. 演绎阶段
        1. 遍历所有存活糖人，寻找视力范围内糖多于当前的场地进行移动。
        2. 糖人持有方糖增加当前场地的产出数量个。
        3. 遍历所有存活糖人是否满足需求，将不满足者置死。
        4. 输出此回合的初始糖人位置、死亡糖人位置、剩余糖人位置、方糖在场地上的分布。
        5. 将糖人所有的糖进行统计，若满足结束要求则进入结束阶段。
    3. 结束阶段
        1. 若糖人的 **最大个人持有方糖的数量** 超过阈值，或 **回合数** 达到最大值，则进入结束阶段。
        2. 输出统计图表，包括糖人的最终位置、所有糖人的状态（ **存活** 或 **死亡** ）、存活糖人的方糖最终持有量。
2. 数据结构控制

    1. 场地，SugarSocialExp.Block[ ][ ]
    2. 方糖分布，int SugarSocialExp.Block.product
    3. 糖人，List< SugarSocialExp.Role >
    4. 糖人位置，List < SugarSocialExp.V2 >
    5. 糖人状态，int SugarSocialExp.Role.status
    6. 当前场上最富有的糖人，SugarSocialExp.Role
    7. 回合数，int
    8. 胜者的方糖最终持有量，List< Integer >
3. 类控制

    1. SugarSocialExp.Block
        * **final int** product
        * **final SugarSocialExp.V2** position
        * **boolean** isHold
        * SugarSocialExp.Block(int product, SugarSocialExp.V2 position)
        * SugarSocialExp.Block(int product, int x, int y)
        * **String** toString()
    2. SugarSocialExp.Role
        * **SugarSocialExp.V2** position
        * **int** status
        * **int** sugar
        * **final int** mouth
        * **final int** eye
        * SugarSocialExp.Role(SugarSocialExp.V2 position, int mouth, int eye)
        * SugarSocialExp.Role(int x, int y, int mouth, int eye)
        * **void** earn(int product)
        * **boolean** eat()
        * **boolean** earnAndEat(int product)
        * **String** toString()
    3. SugarSocialExp.V2
        * **int** x
        * **int** y
        * SugarSocialExp.V2(int x, int y)
        * **String** toString()
    4. SugarSocialExp.VisibleGenerator extends JFrame
        * //TODO
    5. SugarSocialExp.Model
        * **SugarSocialExp.Block[ ][ ]** square
        * **int[ ][ ]** productions
        * **List< SugarSocialExp.Role >** humanList
        * **List< SugarSocialExp.V2 >** LocationList
        * **int[ ]** statuses
        * **SugarSocialExp.Role** richest
        * **int** round
        * **List< SugarSocialExp.V2 >** resultList // x = status, y = sugar
        * **final int** maxEyeAbility
        * **final int** maxMouthAbility
        * **final int** maxProductAbility
        * **final int** size
        * **int** riskPoint
        * SugarSocialExp.Model(int maxEye, int maxMouth, int maxProduct, int size)
        * **void** init()
        * **void** initSquare()
        * **void** initProductions()
        * **void** initHumanList()
        * **void** initLocationList()
        * **void** oneTurnToEarn()
        * **void** oneTurnToMove()
        * **void** oneTurnToEat()
        * **List< SugarSocialExp.V2 >** getAllHumanLocation()
        * **List< SugarSocialExp.V2 >** getLiveHumanLocation()
        * **List< SugarSocialExp.V2 >** getDeadHumanLocation()
        * **int[ ][ ]** getProductions()
        * **SugarSocialExp.Role** getTheRichest()
        * **List< SugarSocialExp.V2 >** getResultList()
        * **SugarSocialExp.Model** start(int maxRound)
        * **String** toString()

    * Main
        * **public static void** main(String[] args)
