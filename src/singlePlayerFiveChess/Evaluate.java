package singlePlayerFiveChess;

public class Evaluate{
    int max_x,max_y,max;
    public void evaluate(int shape[][][]){
    	int i=1,j=1;
         
    	for(i=1;i<=15;i++)
    		for(j=1;j<=15;j++){
    			switch(shape[i][j][0]) {
                    case 5:
                        shape[i][j][4]=200;
                        break;
                    case 4:
                        switch(shape[i][j][1]){
                         	case 4:
                             	shape[i][j][4]=150+shape[i][j][2]+shape[i][j][3];   
                               	break;    
                         	case 3:
                              	shape[i][j][4]=100+shape[i][j][2]+shape[i][j][3];
                             	break;
                         	default:
                               	shape[i][j][4]=50+shape[i][j][2]+shape[i][j][3];
                              
                         }
                         break;
                    case 3:
                      	switch(shape[i][j][1]){
                          	case 3:
                             	shape[i][j][4]=75+shape[i][j][2]+shape[i][j][3];
                                     break;              
                         	default:
                               	shape[i][j][4]=20+shape[i][j][2]+shape[i][j][3];
                           }
                           break; 
                   case 2:
                         shape[i][j][4]=10+shape[i][j][1]+shape[i][j][2]+shape[i][j][3];
                         break; 
                   case 1:
                         shape[i][j][4]=shape[i][j][0]+shape[i][j][1]+shape[i][j][2]+shape[i][j][3];
                   default : shape[i][j][4]=0;                      
                  }   
             }       
     
    	int x=1,y=1;
    	max=0;
    	for(x=1;x<=15;x++)
    		for(y=1;y<=15;y++)
    			if(max<shape[x][y][4]){   
    				max=shape[x][y][4];  
    				max_x=x;max_y=y;
    			}     
     }
}