package singlePlayerFiveChess;

 public class Sort{ 
	 public void sort(int shape[][][]){ 
  
		 int temp;  
		 for(int i=1;i<=15;i++)
         for(int j=1;j<=15;j++){
                                 
        	for(int h=1;h<=3;h++){
             	for(int w=3;w>=h;w--){
                 	if(shape[i][j][w-1]<shape[i][j][w]){
                 		temp=shape[i][j][w-1];
                 		shape[i][j][w-1]=shape[i][j][w];
                 		shape[i][j][w]=temp;
                   	} 
             	} 
            }
         }
	 }
}