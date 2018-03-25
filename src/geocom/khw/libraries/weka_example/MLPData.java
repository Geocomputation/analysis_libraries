package geocom.khw.libraries.weka_example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MLPData {
    private String[] classtype = new String[]{"Bike", "Car", "Bus", "Truck"};

    public MLPData(){
        Random rand = new Random(System.nanoTime());

        try{
            BufferedWriter out = new BufferedWriter(new FileWriter("resources/vehicledata.csv"));
            out.write("wheels,chassis,pax,vtype\n");
            for(int i=0;i<100000;i++){
                StringBuilder sb = new StringBuilder();
                switch (rand.nextInt(3)){
                    case 0:
                        // 오토바이
                        sb.append((rand.nextInt(1)+1)+","); // 바퀴 갯수
                        sb.append((rand.nextInt(1)+1)+","); // 섀시 길이
                        sb.append((rand.nextInt(1)+1)+","); // 승차 인원수
                        sb.append(classtype[0] + "\n");
                        break;
                    case 1:
                        // 승용차
                        sb.append((rand.nextInt(2)+4)+","); // 바퀴 갯수
                        sb.append((rand.nextInt(4)+1)+","); // 섀시 길이
                        sb.append((rand.nextInt(4)+1)+","); // 승차 인원수
                        sb.append(classtype[1] + "\n");
                        break;
                    case 2:
                        // 버스
                        sb.append((rand.nextInt(6)+4)+","); // 바퀴 갯수
                        sb.append((rand.nextInt(12)+12)+","); // 섀시 길이
                        sb.append((rand.nextInt(30)+10)+","); // 승차 인원수
                        sb.append(classtype[2] + "\n");
                        break;
                    case 3:
                        // 트럭
                        sb.append("18,"); // 바퀴 갯수
                        sb.append((rand.nextInt(10)+20)+","); // 섀시 길이
                        sb.append((rand.nextInt(2)+1)+","); // 승차 인원수
                        sb.append(classtype[3] + "\n");
                        break;
                    default:
                        break;
                }
                out.write(sb.toString());
            }
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        MLPData mpl = new MLPData();
    }
}
