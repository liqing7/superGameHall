package singlePlayerFiveChess;

public class AutoPlay{
     int x,y;
     void autoPlay(int chesspad[][],int a,int b){
          int randomNumber=(int)(Math.random()*8)+1;
          switch(randomNumber){
             case(1):
                  if(a-1>0 && b-1>0&&chesspad[a-1][b-1]==0)
                  {x=a-1;y=b-1;break;}
                  else if(a-2>0 && b-2>0&&chesspad[a-2][b-2]==0)
                  {x=a-2;y=b-2;break;} 
                  else{}
                  
                   
             case(2):
                  if(a-1>0 &&chesspad[a-1][b]==0)
                  {x=a-1;y=b;break;}
                  else if(a-2>0&&chesspad[a-2][b]==0)
                  {x=a-2;y=b;break;}
                  else{}
             case(3):
                  if(a-1>0 && b+1<=15&&chesspad[a-1][b+1]==0)
                  {x=a-1;y=b+1;break;}
                  else if(a-2>0 &&b+2<=15&&chesspad[a-2][b+2]==0)
                  {x=a-2;y=b+2;break;}
                  else{}
             case(4):
                  if(b+1<=15&&chesspad[a][b+1]==0)
                  {x=a;y=b+1;break;}
                  else if( b+2<=15&&chesspad[a][b+2]==0 )
                  {x=a;y=b+2;break;}
                  else{}
             case(5):
                  if(a+1<=15 && b+1<=15&& chesspad[a+1][b+1]==0 )
                  {x=a+1;y=b+1;break;}
                  else if(a+2<=15 && b+2<=15 && chesspad[a+2][b+2]==0)
                  {x=a+2;y=b+2;break;} 
                  else{}
             case(6):
                  if(a+1<=15&&chesspad[a+1][b]==0)
                  {x=a+1;y=b;break;}
                  else if(a+2<=15&&chesspad[a+2][b]==0)
                  {x=a+2;y=b;break;}
                  else{}
             case(7):
                  if( a+1<=15 && b-1>0&&chesspad[a+1][b-1]==0)
                  {x=a+1;y=b-1;break;}
                  else if( a+2<=15 && b-2>0&&chesspad[a+2][b-2]==0)
                  {x=a+2;y=b-2;break;}
                  else{}
             case(8):
                  if(b-1>0&& chesspad[a][b-1]==0)
                  {x=a;y=b-1;break;}
                  else  if(b-2>0&& chesspad[a][b-2]==0)
                  {x=a;y=b-2;break;}
                  else{}
            }
      }
} 