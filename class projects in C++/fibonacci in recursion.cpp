#include <iostream>
#include <vector>
using namespace std;

/*
int main(){
	long long x=0;
	long long y=1;
	long long temp=1;
	for(int i =1; i<70; i++){
		
		y=temp;
		temp=x;
		x=y+temp;
		cout << "fib(" << i << ")=" << x << endl;
		
		
	}
}
*/
int fib(long long n){
	static long long m[100]={0};
	if(m[n]!=0)
		return m[n];
	if(n==1 || n==2) return 1;
	return m[n]=fib(n-1)+fib(n-2);
};
int main(){
	for(long long i=1; i<70; i++)
	cout<<" fib("<<i<<") = "<<fib(i)<<endl;
}

