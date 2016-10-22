#include<iostream>
#include<cmath>
using namespace std;
int main(){
	int q[8], c=0;
	q[0]=0;
	int count=0;
	NC:
		c++;
		if(c==8) goto Print;
		q[c]=-1;
	NR: 
		q[c]++;
		if(q[c]==8) goto backtrack;
	for(int i=0; i<c; i++)
		if(q[c]==q[i]||(c-i)==abs(q[c]-q[i])) goto NR;
		
	goto NC;
	backtrack: 
		c--;
		if(c==-1) return 0;
		goto NR;
	Print:
		for(int i=0; i<8; i++){
		
			for(int j=0; j<8; j++){
			
				if(q[i]==j)
					cout << "Q" << " ";
				else cout << "0" << " ";
				
			//	break;
			}
		//	}
			cout << endl;
		}
	

	count++; 
	cout << "Solutioin # " << count << endl;
	cout << endl << endl;	
	goto backtrack;
		
}
