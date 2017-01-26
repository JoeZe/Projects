#include <iostream>
using namespace std;
int main(){
        int i=1, A;
        while(i>0){
			i*i ==A ;        
        	if((A%10)==1&&((A/10)%10)==1){
        		cout << "the first perfect square whose last two digits are both odd " << A;
        	    break;          
        	}
			i++;	
		}
		cout << "no perfect square found that last two digits are both odd!" << endl;
        return 0;
}


