package singlePlayerFiveChess;

public class Scan{
	int shape[][][]=new int[16][16][5];
 
   	void scan(int chesspad[][],int colour){//�鿴�˷���������ͬɫ���Ӹ���
     	int i,j;

        for(i=1;i<=15;i++){
        	for(j=1;j<=15;j++){
        		if(chesspad[i][j]==0){
                  	int m=i,n=j;
                	while(n-1>0&&chesspad[m][--n]==colour){//����
                      	shape[i][j][0]++;
                 	}
                   	n=j;
                 	while(n+1<=15&&chesspad[m][++n]==colour){//����
                      	shape[i][j][0]++;
                   	}          
                 	n=j;
                  	while(m-1>0&&chesspad[--m][n]==colour){//����
                      	shape[i][j][1]++;
                  	}
                   	m=i;
                   	while(m+1<=15&&chesspad[++m][n]==colour){//����
                   		shape[i][j][1]++;
                  	}	
                     	m=i;
                   	while(m-1>0&&n+1<=15&&chesspad[--m][++n]==colour){//����
                       	shape[i][j][2]++;
                 	}
          		   	m=i;n=j; 
          		  	while(m+1<=15&&n-1>0&&chesspad[++m][--n]==colour){//����
                       	shape[i][j][2]++;
                   	}
          		          
                  	m=i;n=j; 
                                  
                	while(m-1>=0&&n-1>0&&chesspad[--m][--n]==colour){//����
                      	shape[i][j][3]++;
                 	}
          		   	m=i;n=j; 
          			while(m+1<=15&&n+1<=15&&chesspad[++m][++n]==colour){//����
                      	shape[i][j][3]++;
                  	}
        		} 
        	}
   		}     
	} 
}