package geocom.khw.libraries.decision_tree;

public class InformationGain {
    private double calcLog2(double value){
        if(value <= 0.){
            return 0.;
        }
        return Math.log10(value)/Math.log10(2.); // log_10(value)/log_10(2) = log_2(value)
    }
    public double calcGain(double positive, double negative){
        double sum = positive + negative;
        double gain = positive * calcLog2(positive/sum)/sum + negative * calcLog2(negative/sum)/sum;
        // 정보량 H = -E(ln(f)) = - integral f(x)ln(f(x)) dx = 이를 모델과 실제 확률 분포 사이의 KL-Divergence로 표현 가능
        // 즉, E[logf(x)/p(x)], 여기서 f(x) 실제 분포, p(x) 추정 분포로 내는 것을 Kullback-Leibler Information이라 한다.
        return -gain;
    }
    public static void main(String[] args){
        // data
        //////////////////////////////////////////////////////////////////
        //         신용카드 유무   리뷰 참고  이전 구매 경험  구매 여부 //
        // 사용자 A        N           Y            Y            Y      //
        // 사용자 B        Y           Y            Y            Y      //
        // 사용자 C        N           N            Y            N      //
        // 사용자 D        N           N            N            Y      //
        // 사용자 E        Y           Y            Y            Y      //
        //////////////////////////////////////////////////////////////////

        InformationGain ig = new InformationGain();
        ig.printlnfoGain(new double[]{3,2,2,1,1,1});

        // 이후 다른 두 속성 정보획득량도 계산 가능

        ig.printlnfoGain(new double[]{3,2,3,0,1,1});

        ig.printlnfoGain(new double[]{4,1,2,1,2,0});

    }

    public void printlnfoGain(double[] probability_vars){

        // 신용가드 소지 유무( 소지 : 3, 미소지 : 2)
        double firstGain = calcGain(probability_vars[0],probability_vars[1]);
        System.out.println("Gain : " + firstGain);
        // 여기서 Information Gain은 0.97

        // 리뷰 참고 여부 참고함(신용카드 소지 | 리뷰 참고, [Y,Y,N], 미소지 | 리뷰 참고, [N,Y])
        double split_y = calcGain(probability_vars[2],probability_vars[3]);
        double split_n = calcGain(probability_vars[4],probability_vars[5]);

        double net_gain = (probability_vars[4]+probability_vars[5])/(probability_vars[0]+probability_vars[1])*split_y
                + (probability_vars[2]+probability_vars[3])/(probability_vars[0]+probability_vars[1])*split_n;
        System.out.println("Net Gain : " + net_gain);
        // 여기서 Information Gain은 0.96

        double InformationGain = firstGain - net_gain;
        System.out.println("Information Gain : " + InformationGain);
        System.out.println("");
        // 여기서 InformationGain은 Gain(before the split) - Gain(after the split) = 0.01
    }
}
