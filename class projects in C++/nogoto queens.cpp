#include <iostream>
#include <cmath>
#include <cstdlib>

using namespace std;
bool isOk(int q[], int c){
	for(int i=0; i< c; i++)
		if(q[i]==q[c]||(c-i)==abs(q[c]-q[i])) return false;
	
	return true;
}
void print(int q[]){
	static int count=0;
	
	for(int i=0; i<8; i++){
		for(int j=0; j<8; j++){		
			if(q[i]==j)
					cout << "Q" << " ";
					else cout << "0" << " ";
			}
		cout << endl;	
	}
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
		

	
		

