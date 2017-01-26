#include <iostream>
#include <cstdlib>
#include <string>

using namespace std;

int main(){
	
	char tname[26][26];
	int t;
	char result;
	char order[100];
	string done= "done";
	int runner = -1;
	int team=0;
	double score[26]={0};
	double answer[26];

  	cout <<  "Please input each team's name in uppercase letter A-Z. when you are finished input data then enter '.' " << endl;
  	cout << "The maximum number of teams are 26 and each team should has the same number of members." << endl;
	cout << "Enter # of teams you want!";
	cin >> t;
	while(t>26){
		cout << "you can't have more than 26 team! Please enter again!";
		cin >> t;
	}

	for (int i=0; i<t; i++){
		cout << "Team: " << i+1 << " ";
		
  		for(int j=0; j<26; j++){
  			cout << "member: " << j+1 << " ";
	  		cin >> tname[i][j];
	  		if(i==0) (runner++)-1;
	  		
	  		if(i>1&&j>runner){
	  			cout << "Error! You need to have same number of members!";
	  			for (int k=0; k<runner; k++){
	  				cin >> tname[i][k];
	  			}	
	  		}
	  		
  			
  			if(!isalpha(tname[i][j])) break;	
  			//if(tname[i][j]=='done') break;
    	  }
    	
	}  	
	
	
	
	
	
  	for (int i=0; i<t-1; i++){
  		for(int j=0; j<t-1; j++){
  			if(i>0&&i<j) j++;
			if (tname[i][0]==tname[i+1][0]){
				cout << "You can't have two terms with same name! " << endl;
				
				return 0;
				
  			
  			
			  
  			}
	 	}
	}	
	
	
	
	
	cout << "Please enter the results as a string of characters indicating the order in which rnners crossed the finish line." << endl;
	cin >> result;
	for (int i=0; i<(t*runner); i++){
		  result==order[i];
	}
	for(int i=0; i<(t); i++){
		for(int j=0; j<(t*runner);j++){
		
			if(tname[i][i]==order[i]){
			tname[i][i]==(score[i]+=i);
			}
		}
	}
		
	
	for (int i=0; i<t; i++){
		if(tname[i][i]){
			(score+1)[i]/runner==answer[i];
		}
		
	}
	
	
	
	
	cout << "There are " << t << " teams. " << endl; 
	cout << "Each team has " << runner << " runners." << endl;
	cout << "Team\t" << "Score " << endl;
	for (int i=0; i<t;i++){
		cout << tname[i][i] << "\t" << answer[i] << endl;
	}
}
