#include<iostream>
#include<vector>
using namespace std;

int main(){
	vector<int> T[3];
	int to, from, move=0, candidate;
	int n;
	cout << "Please enter a number of rings to move:";
	cin >> n;
	cout << endl;
	for(int i=n+1; i>=1; i--)
		T[0].push_back(i);
		
	T[1].push_back(n+1);
	T[2].push_back(n+1);
	
	if(n%2==1){
	
		from=0;
		to=1;
		candidate=1;
	}
	else{
		from=0;
		to=2;
		candidate=1;
	}
	
	while(T[1].size()<n+1){
		T[to].push_back(candidate);
		T[from].pop_back();

		cout<< "move number " << ++move << ": Transfer ring " << candidate << " from tower " << char(from+65) << " to tower " << char( to + 65) << endl;
	
		if(T[(to+1)%3].back()<T[(to+2)%3].back()) 
			from=(to+1)%3;
		else
			from=(to+2)%3;
			
		candidate=T[from].back();	
		if(n%2==1){
			if(candidate%2==1)
			
				to=(from+1)%3;
		
			else
				to=(from+2)%3;	
		}
		else{
			if(candidate%2==0)
				to=(from+1)%3;
				
			else
				to==(from+2)%3;
		}
		candidate=T[from].back();
	}
		
	return 0;
	
				
	}
	

