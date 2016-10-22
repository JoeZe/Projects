#include <iostream>
#include <cmath>
using namespace std;
bool isOk(int q[], int c){
	for(int i=0; i< c; i++)
		if(q[i]==q[c]||(c-i)==abs(q[c]-q[i])) return false;
	
	return true;
}
void print(int q[]){
	int i,j,k,l;
 	typedef char box[5][7];
 	box bb,wb,bq,wq,*queen,*board[8][8];
	
	//fill in bb=black box and wb=whitebox
	 for(i=0;i<5;i++)
        for(j=0;j<7;j++)
        {
            wb[i][j]=' ';
            bb[i][j]=char(219);
            wq[i][j]=' ';
            bq[i][j]=char(219);
        }
    
    for(i=0;i<5;i++)
        for(j=0;j<7;j++) {
          if(i>0&&i<4&&(j==1||j==3||j==5)||((i==2||i==3)&&(j==2||j==4)))
            {
                wq[i][j]=char(219);
                bq[i][j]=' ';
           }
        }
    
    
    //fill board with pointers to bb and wb in alternate positions
    for(i=0;i<8;i++)
        for(j=0;j<8;j++)
            if((i+j)%2==0)
                board[i][j]=&wb;
            else
                board[i][j]=&bb;
    
    for(i=0;i<8;i++)
    {
        queen = board[i][q[i]];
        if(queen == &wb)
            board[i][q[i]] = &wq;
        else
            board[i][q[i]] = &bq;
    }	 
		 
 // print the board via the pointers in array board
 // first print upper border
 	cout<<" ";		
 	for(i=0;i<7*8;i++)
	 cout<<'_';
	 cout<<endl;
 // now print the board
 	for(i=0;i<8;i++)
	 for(k=0;k<5;k++)
 	{cout<<" "<<char(179); //print left border
	 for(j=0;j<8;j++)
	 for(l=0;l<7;l++)
 	cout<<(*board[i][j])[k][l];
 	cout<<char(179)<<endl; // at end of line print bar and then newline
	 }
 //	efore exiting print lower border
	 cout<<" ";
 	for(i=0;i<7*8;i++)
	 cout<<char(196);
	 cout<<endl;	
	static int count=0;

	count++;
	cout << "Solutioin # " << count << endl;

	cout << endl << endl;	
	
}

void backtrack(int &c){
	c--;
			
}

int main(){
	int q[8], c=0;
	q[0]=0;
	
	bool jump = true;
	while(true){
		c++;
		if(c==8){
			print(q);
			backtrack(c);
		} 
		else q[c]=-1;
	
		while(true){
			q[c]++;
			
			if(q[c]==8){
			
				backtrack(c);
				if(c==-1) return 0;
			jump=false;
		}
			if(isOk(q,c)&&jump) break;
			jump=true;
		}	
		
	} 
	
	
}

