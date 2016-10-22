#include<iostream>
using namespace std;
bool ok(int q[], int c){
	int mp[3][3]={0,2,1,0,2,1,1,2,0};
	int wp[3][3]={2,1,0,0,1,2,2,0,1};
	for(int i=0; i<c; i++)
		if((q[i] == q[c]) || (mp[i][q[c]] < mp[i][q[i]] && wp[q[c]][i] < wp[q[c]][c]) || (mp[c][q[i]] < mp[c][q[c]] && wp[q[i]][c] < wp[q[i]][i]))
		return false;
	
	return true;
	
	
}
void print(int q[]){
	static int count =0;
	count++; 
	cout << "soultion # " << count << endl;
	cout << "Man\t" << "woman" <<endl;
	for(int i=0; i<3; i++)	
		cout << i << "\t" << q[i] << endl;
	
}

void move(int b[], int i){
	if(i==3){
		print(b);
		return;
	}
	for(int j=0; j<3; j++){
		b[i]=j;
		if(ok(b,i))
		move(b,i+1);
	}
}
int main(){
	int b[3];
	int i=0;
	move(b,i);
	system("pause");
}
	
