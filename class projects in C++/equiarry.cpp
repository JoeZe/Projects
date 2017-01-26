#include <iostream>
#include <cstdlib>
using namespace std;
bool equivalent(int c[], int d[], int n){
	for( int i=0; i<n; i++){
		
		if (c[(i+5)%(n-1)]== d[i]) return true;
		
	}
	return false;
}
int main(){
	int a[8] = {1,2,3,4,5,6,7,8};
	int b[8] = {6,7,8,1,2,3,4,5};
	if (equivalent(a, b, 5 )) cout << " they are shift equivalent";
	else cout << " they are not shift equivalent";
	
	
	
	
	
	return 0;
}
