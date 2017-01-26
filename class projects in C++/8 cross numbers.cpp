#include<iostream>
#include <cmath>
using namespace std;
bool ok(int b[], int c){
	int q[8][5]={{-1},{0,-1}, {0,1,-1},{0,2,-1},{1,2,-1},{1,2,3,4,-1},{2,3,5,-1},{4,5,6,-1}};
	
	int r=0;
		while(q[c][r]!=-1){
			
			if(abs(b[c]-b[q[c][r]])==1||b[q[c][r]]==b[c]) return false;
		 
			r++;
		}

		for(int i=0; i<c; i++)
		 if(b[c]==b[i]) return false;
			return true;


	

}
void print(int b[]){
	int static count=0;
	count++;
	int i=1;
	cout << "solution # " << count << endl;
	cout << "  " << b[1]+1 << " "<< b[4]+1 << "  " << endl;
	cout << b[0]+1 << " " << b[2]+1 << " "<< b[5]+1 << " "<< b[7]+1 << endl;
	cout << "  " <<  b[3]+1 << " "<< b[6]+1 << " " << endl;
	
	cout << endl;
	
	
}
void backtrack(int &c){
	c--;
}
int main(){
	int b[8],c=0;
	b[0]=1;
	bool skip = true;
	while(true){
		c++;
		if(c==8) {
			print(b);	
			backtrack(c);
		}
		else b[c]=-1;
		while(true){
			b[c]++;
			if(b[c]==8) {	
				backtrack(c);
				if(c==-1) return 0;
				skip=false;
		}
		if(ok(b,c)&&skip) break;
		skip=true;
	}
	
	
	
	}
	
	
}
