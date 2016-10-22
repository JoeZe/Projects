#include<iostream>
using namespace std;
const int rows = 5;
const int cols = 6;
int direction[rows][cols]={0};
int cost(int i, int j){ // i is the row, j is the column
 int weight[rows][cols]={
 {3,4,1,2,8,6},
 {6,1,8,2,7,4},
 {5,9,3,9,9,5},
 {8,4,1,3,2,6},
 {3,7,2,8,6,4}};
 
 int m[rows][cols]={0};
 //base case
 if(j==0)
 	return weight[i][0];
 
 
 if(m[i][j]!=0)
 	return m[i][j];
 // recursive call
 int left =weight[i][j]+cost(i,j-1);
 int up =weight[i][j]+ cost((i-1+rows)%rows,j-1);
 int down = weight[i][j]+cost((i+1)%rows, j-1);

 // find the value of the shortest path through cell (i,j)
 int min = left;
 direction[i][j]=0;
 if(up<min){
 
 	min=up;
 	direction[i][j]=-1;
 }
 if(down<min){
 
 	min=down;
 	direction[i][j]=1;
 }

 return m[i][j]=min;
 }

int main(){
 int ex[rows], path[rows];

 // get the shortest path out of each cell on the right
 for( int i=0; i<rows; i++)
 	ex[i]=cost(i,cols-1);

 
 int min= ex[0];
 for(int j=1; j<rows; j++)
 	if(ex[j]<min){
	 
 		min=ex[j];
 		path[0]=j;
 	}
 	
 int t=cols;
 for(int i=1; i< cols; i++)
 	path[i]=path[i-1]+direction[i-1][t--];
for(int i=rows; i>=0;i--){
	if(i==0) cout << path[i]+1 << " ";
	else
		cout << path[i] << " ";
	
}
 cout << endl;
 cout<<"the shortest path is of length "<<min<<endl;
 system("PAUSE");
 return EXIT_SUCCESS;

} 
