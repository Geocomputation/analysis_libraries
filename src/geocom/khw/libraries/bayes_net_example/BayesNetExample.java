package geocom.khw.libraries.bayes_net_example;

import javabayes.Helpers.BayesNetHelper;
import javabayes.InferenceGraphs.InferenceGraph;
import javabayes.InferenceGraphs.InferenceGraphNode;
import weka.classifiers.bayes.BayesNet;

public class BayesNetExample {
    public BayesNetExample(){
        InferenceGraph inferenceGraph = new InferenceGraph();

        // nodes
        InferenceGraphNode age = BayesNetHelper.createNode(inferenceGraph, "under55", "<55", ">55");
        InferenceGraphNode smoker = BayesNetHelper.createNode(inferenceGraph, "smoker", "smokes", "doesnotsmoke");
        InferenceGraphNode duration = BayesNetHelper.createNode(inferenceGraph, "smoker", "<2Y", ">2Y");
        InferenceGraphNode surgical = BayesNetHelper.createNode(inferenceGraph, "surgicalOutcome", "positive", "negative");

        // links
        inferenceGraph.create_arc(age, smoker);
        inferenceGraph.create_arc(smoker, surgical);
        inferenceGraph.create_arc(duration, surgical);

        BayesNetHelper.setProbabilityValues(smoker, "<55", 0.4, 0.6);
        BayesNetHelper.setProbabilityValues(smoker, ">55", 0.8, 0.2);

        BayesNetHelper.setProbabilityValues(surgical, "smokes", "<2Y", 0.1, 0.9);
        BayesNetHelper.setProbabilityValues(surgical, "smokes", ">2Y", 0.01, 0.99);
        BayesNetHelper.setProbabilityValues(surgical, "doesnotsmoke", "<2Y", 0.8, 0.2);
        BayesNetHelper.setProbabilityValues(surgical, "doesnotsmoke", ">2Y", 0.58, 0.42);

        BayesNetHelper.setProbabilityValues(duration, 0.9, 0.1);

        BayesNetHelper.setProbabilityValues(age, 0.8, 0.2);

        double belief = BayesNetHelper.getBelief(inferenceGraph, surgical);
        System.out.println("수술 성공 확률은 :" + belief);

        age.set_observation_value("<55");
        belief = BayesNetHelper.getBelief(inferenceGraph, surgical);
        System.out.println("환자가 55세 이하일 때 수술에 성공할 확률은 : " + belief);

        smoker.set_observation_value("smokes");
        belief = BayesNetHelper.getBelief(inferenceGraph, surgical);
        System.out.println("환자가 흡연자이고 55세 이하일 때 수술에 성공할 확률은 : " + belief);

        duration.set_observation_value(">2Y");
        belief = BayesNetHelper.getBelief(inferenceGraph, surgical);
        System.out.printf("환자가 흡연자이고 55세 이하이며 2년 이상 투병 시 수술에 성공할 확률은 : " + belief);

    }

    public static void main(String[] args){
        BayesNetExample example = new BayesNetExample();
    }
}
