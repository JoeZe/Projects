#include <iostream>
#include <string>
using namespace std;
int main(){
	retry:
	string result;
	int numRunner=0;
	int alpha[26]={0};
	int numTeam=0;
	double score[26];
	cout << "**************************************************************" << endl;
	cout << "*********** Welcome to the 'Score the Race' game!*************" << endl;
	cout << "**************************************************************" << endl;
	cout <<  endl << endl;
	cout << "Each team has a name, which is one of the uppercase letters A-Z. " << endl;
	cout << "No two teams have the same name, so there are a maximum number of 26 teams." << endl;
	cout << "Each team need to has the same number of members." << endl;
	cout << "If you want to end the program please enter 'done'." << endl;
	cout << "Pease in enter a string of uppercasee characters indicating the outcomed of a race. ";
	
	cin >> result;
	if (result == "done") return 0;
	for(int i=0; i<result.length();i++){
		switch(result.at(i)){
		
		
		//result.at(i);
		case 'A':
			alpha[0]++;
			break;
		case 'B':
			alpha[1]++;
			break;
		case 'C':
			alpha[2]++;
			break;
		case 'D':
			alpha[3]++;
			break;
		case 'E':
			alpha[4]++;
			break;
		case 'F':
			alpha[5]++;
			break;
		case 'G':
			alpha[6]++;
			break;
		case 'H':
			alpha[7]++;
			break;
		case 'I':
			alpha[8]++;
			break;
		case 'J':
			alpha[9]++;
			break;
			
		case 'K':
			alpha[10]++;
			break;
		case 'L':
			alpha[11]++;
			break;
			
		case 'M':
			alpha[12]++;
			break;
		case 'N':
			alpha[13]++;
			break;
		case 'O':
			alpha[14]++;
			break;
		case 'P':
			alpha[15]++;
			break;
		case 'Q':
			alpha[16]++;
			break;
		case 'R':
			alpha[17]++;
			break;
		case 'S':
			alpha[18]++;
			break;
		case 'T':
			alpha[19]++;
			break;
		case 'U':
			alpha[20]++;
			break;
		case 'V':
			alpha[21]++;
			break;
		case 'W':
			alpha[22]++;
			break;
		case 'X':
			alpha[23]++;
			break;
		case 'Y':
			alpha[24]++;
			break;
		case 'Z':
			alpha[25]++;
			break;
	
		default:
			break;
		}
	}
	for(int i=0; i<26;i++){
		if(alpha[i]!=0){
			numTeam++;	
		
		}
		
	}
	cout << "There are " << numTeam << " teams." << endl;
	for(int i=0; i<26; i++){
		if(alpha[i]!=0){
			numRunner+=alpha[i];
			
		}
		break;
	}
	for(int i=0; i<26;i++){
			if(numRunner!=alpha[i]&&alpha[i]>0){	
				cout << "You need to have same runner for each team" << endl;
				cout << "Please try it again!" << endl;
				
			goto retry;
			}		
	}
	cout << endl;
	cout << "Each team has " << numRunner << " runners." << endl;
	cout << endl << endl;
	cout << "Team\t" << "Score" << endl;
	
	//alpha[26]={0};
	for(int i=0; i<result.length();i++){
		alpha[result.at(i)-65]+=(i);
	}
	for(int i=0; i<26;i++){
		if(alpha[i]!=0){
			score[i]=alpha[i]/(double)numRunner;
		}
		
	}
	
	
	for(int i=0; i<26; i++){
		
			if(alpha[i]!=0){
				cout << (char)(i+65)  << "\t" << score[i] << endl;
			}
	}
	
	cout << "The winning team is team " << result.at(0) << " with a score of " << score[result.at(0)-65] << " ." << endl;
	
}
