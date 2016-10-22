#include <iostream>
#include <cmath>
#include <cstdlib>

using namespace std;
bool isOk(int q[], int c){
	for(int i=0; i< c; i++)
		if(q[i]==q[c]||(c-i)==abs(q[c]-q[i])) return false;
	
	return true;
}

void backtrack(int &c){
	c--;
			
}

void run( int n){
	int *q = new int[n];
    q[0]=0;
	int count=0,c=0;
	bool jump = true;
	bool breaker= true;
	while(breaker){
		c++;
		if(c==n){
			count++;
			backtrack(c);
		} 
		else q[c]=-1;

		while(breaker){
			q[c]++;
		
			if(q[c]==n){
			
				backtrack(c);
				if(c==-1) {
						
					cout << "There are " << count << " solutions to the " << n << " queens problem." << endl;
					delete[] q;	
					breaker=false;
	           	}
				jump=false;
			}
			if(isOk(q,c)&&jump) break;
			jump=true;
			}	
		
		} 
	}

int main(){
	
	int n;
	cout << "Enter the number of queens ";
	cin >> n;
	for(int i=1; i<=n; i++)
		run(i);
	return 0;
}
