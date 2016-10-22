#include <iostream>
#include <cstdlib>
using namespace std;

bool ok(int q[], int c)
{
    for (int x = 0; x < c; x++)
        if (q[x] == q[c] || (c-x) == (q[c] - q[x]) || (c-x) == (q[x] - q[c]))
            return false;
    return true;
}

void print(int q[]){
	static int count=0;
    
    for (int i = 0; i<8; i++){
    	for(int j=0; j<8; j++){
		
			if(q[j]==q[i]) cout << "1" << " ";
       	 else cout << "0" << " ";
   		}
         cout << endl;
 	}
	count++;
	cout << "Solution #" << count << ": ";
	cout << endl << endl;
}

void backTrack(int &c){
    c--;
}

//compare with your assignment 5 to fill up rest of the code
int main(){
    int q[8];
    int c=0;
    bool skip = true;
    
    while(true){ //go through all the columns (NextCol)
        c++;
        if (c == 8) {
            print(q);
            backTrack(c);
        }
        else
        q[c] = -1;
        
        while(true){//go through all the rows (NextRow)
            q[c]++;
            if(q[c] == 8) {
                backTrack(c);
                if (c == -1) {
                    return 0;
                }
                //some code here!
                //To make it go back to the top of the while loop that go through the rest of the rows
                skip = false;
                //...
            }//end if
            if(ok(q,c) && skip) { //call ok function to test for column c.
                break; //go out of the loop that go through all the rows and continue to the top of the while loop that goes through all the columns(goto NextCol)
            }
            skip = true;
            
        }//end row while
    }//end col while
}
