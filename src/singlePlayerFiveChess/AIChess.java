package singlePlayerFiveChess;

import java.awt.Color;
import java.util.ArrayList;

/**
 * AI chess
 * @author Qing
 *
 */
public class AIChess {
    int size=16; //���̴�С
    int[][] preBoard1=new int[size][size]; //Ԥ������1
    int[][] preBoard2=new int[size][size]; //Ԥ������2
    int[][] preBoard3=new int[size][size]; //Ԥ������3
    int[][] preBoard4=new int[size][size]; //Ԥ������4
    int[][] preBoard5=new int[size][size]; //Ԥ������5
    int[][] preBoard6=new int[size][size]; //Ԥ������6
    int[][] preBoard7=new int[size][size]; //Ԥ������7
    Color color=Color.white; //������ɫ
    int maxValue1=0, maxValue2=0, maxValue3=0, maxValue4=0, maxValue5=0, maxValue6=0, maxValue7=0; //Ԥ���������ֵ
    int number1=0, number2=0, number3=0, number4=0, number5=0, number6=0, number7=0; //Ԥ���������ֵ����

    //���ֵ��������
    ArrayList maxX1=new ArrayList();
    ArrayList maxX2=new ArrayList();
    ArrayList maxX3=new ArrayList();
    ArrayList maxX4=new ArrayList();
    ArrayList maxX5=new ArrayList();
    ArrayList maxX6=new ArrayList();
    ArrayList maxX7=new ArrayList();

    ArrayList maxY1=new ArrayList();
    ArrayList maxY2=new ArrayList();
    ArrayList maxY3=new ArrayList();
    ArrayList maxY4=new ArrayList();
    ArrayList maxY5=new ArrayList();
    ArrayList maxY6=new ArrayList();
    ArrayList maxY7=new ArrayList();

    public AIChess() {
    }

    /**
     * <p>Description: ��ʼ������</p>
     */
    public void initData(){
      for(int i=0;i<size;i++){
        for(int j=0;j<size;j++){
          preBoard1[i][j]=0;
          preBoard2[i][j]=0;
          preBoard3[i][j]=0;
          preBoard4[i][j]=0;
          preBoard5[i][j]=0;
          preBoard6[i][j]=0;
          preBoard7[i][j]=0;
        }
      }
      maxValue1=0;
      maxValue2=0;
      maxValue3=0;
      maxValue4=0;
      maxValue5=0;
      maxValue6=0;
      maxValue7=0;

      number1=0;
      number2=0;
      number3=0;
      number4=0;
      number5=0;
      number6=0;
      number7=0;

      maxX1.clear();
      maxX2.clear();
      maxX3.clear();
      maxX4.clear();
      maxX5.clear();
      maxX6.clear();
      maxX7.clear();

      maxY1.clear();
      maxY2.clear();
      maxY3.clear();
      maxY4.clear();
      maxY5.clear();
      maxY6.clear();
      maxY7.clear();

      color=color.white;
    }

    /**
     * <p>Description: �������ֵ����</p>
     * @param n Ԥ���������(0-6)
     */
    public void refreshMax(int n){
      switch(n){
        case 1:{ //����Ԥ������1���ֵ��������
          maxValue1=0;
          number1=0;
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              if(preBoard1[i][j]>maxValue1){
                maxValue1=preBoard1[i][j];
                maxX1.clear();
                maxY1.clear();
                maxX1.add(i);
                maxY1.add(j);
                number1=1;
              }
              else if(preBoard1[i][j]==maxValue1){
                maxX1.add(i);
                maxY1.add(j);
                number1++;
              }
            }
          }
          break;
        }
        case 2:{ //����Ԥ������2���ֵ��������
          maxValue2=0;
          number2=0;
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              if(preBoard2[i][j]>maxValue2){
                maxValue2=preBoard2[i][j];
                maxX2.clear();
                maxY2.clear();
                maxX2.add(i);
                maxY2.add(j);
                number2=1;
              }
              else if(preBoard2[i][j]==maxValue2){
                maxX2.add(i);
                maxY2.add(j);
                number2++;
              }
            }
          }
          break;
        }
        case 3:{ //����Ԥ������3���ֵ��������
          maxValue3=0;
          number3=0;
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              if(preBoard3[i][j]>maxValue3){
                maxValue3=preBoard3[i][j];
                maxX3.clear();
                maxY3.clear();
                maxX3.add(i);
                maxY3.add(j);
                number3=1;
              }
              else if(preBoard3[i][j]==maxValue3){
                maxX3.add(i);
                maxY3.add(j);
                number3++;
              }
            }
          }
          break;
        }
        case 4:{ //����Ԥ������4���ֵ��������
          maxValue4=0;
          number4=0;
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              if(preBoard4[i][j]>maxValue4){
                maxValue4=preBoard4[i][j];
                maxX4.clear();
                maxY4.clear();
                maxX4.add(i);
                maxY4.add(j);
                number4=1;
              }
              else if(preBoard4[i][j]==maxValue4){
                maxX4.add(i);
                maxY4.add(j);
                number4++;
              }
            }
          }
          break;
        }
        case 5:{ //����Ԥ������5���ֵ��������
          maxValue5=0;
          number5=0;
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              if(preBoard5[i][j]>maxValue5){
                maxValue5=preBoard5[i][j];
                maxX5.clear();
                maxY5.clear();
                maxX5.add(i);
                maxY5.add(j);
                number5=1;
              }
              else if(preBoard5[i][j]==maxValue5){
                maxX5.add(i);
                maxY5.add(j);
                number5++;
              }
            }
          }
          break;
        }
        case 6:{ //����Ԥ������6���ֵ��������
          maxValue6=0;
          number6=0;
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              if(preBoard6[i][j]>maxValue6){
                maxValue6=preBoard6[i][j];
                maxX6.clear();
                maxY6.clear();
                maxX6.add(i);
                maxY6.add(j);
                number6=1;
              }
              else if(preBoard6[i][j]==maxValue6){
                maxX6.add(i);
                maxY6.add(j);
                number6++;
              }
            }
          }
          break;
        }
        case 7:{ //����Ԥ������7���ֵ��������
          maxValue7=0;
          number7=0;
          for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
              if(preBoard7[i][j]>maxValue7){
                maxValue7=preBoard7[i][j];
                maxX7.clear();
                maxY7.clear();
                maxX7.add(i);
                maxY7.add(j);
                number7=1;
              }
              else if(preBoard7[i][j]==maxValue7){
                maxX7.add(i);
                maxY7.add(j);
                number7++;
              }
            }
          }
          break;
        }
      }
    }
}
