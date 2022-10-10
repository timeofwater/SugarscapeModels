# ��������

����ģ�� Sugarscape models��һ����ѭ�򵥹���Ļ��������ģ�ͣ������˹����ܡ��������彨ģ�Լ��������������Լ���ǡ�����˹̹ Joshua M. Epstein���޲��ء�����˹�ض� Robert
Axtellһ�������鼮[����δ����˹���ᡷGrowing Artificial Societies](https://archive.org/details/growingartificia00epst/page/224)������ϸ���ܡ�

# ��������

### 1.<span id="2-1">����</span>

1. ���سߴ�Ϊ50 x 50�������Ρ�
2. [����](#2-2) �� [����](#2-3) �������������ϡ�
3. ��ͬ���صķ��ǲ����������䡣
4. ���Բ������ǵĳ��أ������ص�ǰδ������ռ�죬��ǰ���ǲ��䡣

### 2.<span id="2-2">����</span>

1. ��Դ�� [����](#2-3) ��Ҫ������ȡ��������������������
2. �����ɳ��ع̶�������
3. ����ֻ������״̬���� **����** �� **��ʰȡ**��

### 3.<span id="2-3">����</span>

1. �����ߣ�ÿ�غϾ���Ҫ��ȡ��Դ��
2. �غϽ���ʱδ��ȡ������������ [����](#2-2) ��������
3. ����ֻ������״̬���� **���** �� **����**��
4. ���˿�����Ѱ������ **����** ��Χ�ڵķ��ǡ�
5. ���˻��Զ��� **����** ��Χ�ڵģ������������� [����](#2-1) �ƶ���
    1. ÿ�����˵�����Ϊ��Χ��������ɡ�
    2. ������ΧΪʮ���Ρ�
    3. ������ֵ��ʾʮ�ֱ۳����������ģ���
    4. �ƶ���ʽΪ�غϽ���ʱ˲���ƶ���

### 4.�غ�

1. �������̿���

    1. ��ʼ�׶�
        1. ���ɿհ׳��ء�
        2. ���ɷ��Ƿֲ���
        3. ���ɳ�ʼ���ˡ�
        4. ���˻�ȡ�����ϵķ��ǡ�
        5. ����˻غϵĳ�ʼ����λ�á���������λ�á�ʣ������λ�á������ڳ����ϵķֲ���
    2. ����׶�
        1. �������д�����ˣ�Ѱ��������Χ���Ƕ��ڵ�ǰ�ĳ��ؽ����ƶ���
        2. ���˳��з������ӵ�ǰ���صĲ�����������
        3. �������д�������Ƿ��������󣬽���������������
        4. ����˻غϵĳ�ʼ����λ�á���������λ�á�ʣ������λ�á������ڳ����ϵķֲ���
        5. ���������е��ǽ���ͳ�ƣ����������Ҫ�����������׶Ρ�
    3. �����׶�
        1. �����˵� **�����˳��з��ǵ�����** ������ֵ���� **�غ���** �ﵽ���ֵ�����������׶Ρ�
        2. ���ͳ��ͼ���������˵�����λ�á��������˵�״̬�� **���** �� **����** ����������˵ķ������ճ�������
2. ���ݽṹ����

    1. ���أ�SugarSocialExp.Block[ ][ ]
    2. ���Ƿֲ���int SugarSocialExp.Block.product
    3. ���ˣ�List< SugarSocialExp.Role >
    4. ����λ�ã�List < SugarSocialExp.V2 >
    5. ����״̬��int SugarSocialExp.Role.status
    6. ��ǰ������е����ˣ�SugarSocialExp.Role
    7. �غ�����int
    8. ʤ�ߵķ������ճ�������List< Integer >
3. �����

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
