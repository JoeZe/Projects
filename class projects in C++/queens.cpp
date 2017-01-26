#include <iostream>
using namespace std;
int main(){
	int b[8][8]={0},r=0,c=0;
	int count =0;
	b[0][0]=1;
	
	NC:
		c++;
		if(c==8) goto Print;
	
	r=-1;
	NR:
		r++;
		if(r==8) goto Backtrack;
		//row test
		for(int i=0; i<c; i++)
			if(b[r][i]==1) goto NR;
		// upd test
		for(int j=1; (r-j)>=0&&(c-j)>=0;j++)
			if(b[r-j][c-j]==1) goto NR;
		// downd test
		for(int k=0; (r+k)<8&&(c-k)>=0; k++)
			if(b[r+k][c-k]==1) goto NR;
			
		b[r][c]=1;
		goto NC;
		
	Backtrack:
		c--;
		if(c==-1){ 
			
			return 0;
		}
		r=0;
		while(b[r][c]!=1)
		
			r++;
		b[r][c]=0;
		goto NR;
	
	Print:
		for(r=0; r<8; r++){
			for(c=0; c<8; c++){
				cout << b[r][c] << " ";
			}
			cout << endl;
		}
		cout << endl;
		count++;
		cout <<  "Solution # " << count << endl;
		goto Backtrack;
		
}
