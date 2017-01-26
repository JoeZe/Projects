#include <iostream>
#include <cmath>
#include <cstdlib>

using namespace std;
int k,n;
bool isOk(int q[], int c, int n){
	int row=q[c]/n;
	int column=q[c]%n;
	int cRow, cColumn;
	for(int i=0; i< c; i++){
	
		cRow=q[i]/n;
		cColumn=q[i]%n;
		
		if(abs(row-cRow)==abs(column-cColumn)) return false;
	}
	return true;
}

void backtrack(int &bishop){
    bishop--;
  
}

void run(int bi, int n ){
	    int* q = new int[bi]; 
        int count = 0, b = 0, t;
        q[b] = 0;
		
        while(true){ 
            while(b<k){ 
                while(q[b]<n*n){ 
                    if(isOk(q,b,n)) break;
                    else q[b]++;
                }

                if(q[b]==n*n){
                    backtrack(b);
                      if(b==-1){
        				cout << n <<"\t" << k<<"\t" << count << endl;
        			
						
   					 }
   					 
						
                    q[b]++;
                    continue;
                }

                else{
                    t = q[b];
                    b++;
                    q[b] = t;
                }
            }

            count++;
			
           backtrack(b);
              if(b==-1){
        		cout << n <<"\t" << k<<"\t" << count << endl;;
        		
				
   			}
            q[b]++;
          
        }
        
	
}



int main(){

    cout << "Enter the size of the boards: ";
    cin >> n;
    cout << "Enter number of bishops: ";
    cin >> k;
    if(n<k||n==-1) return 0;
    cout << "n\t" << "k\t" << "number of  bishops" << endl;
    
	for(int i=1;  i<=k; i++)
		run(i,n);
	
    return 0;
}

