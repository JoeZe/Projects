#include <iostream>
#include <fstream>
#include <cmath>
using namespace std;
int main()
{
    ifstream f;
    int count=0,x,temp,count1=0;
    f.open("read.txt");
    f>>x;
    cout <<x << endl;
    int array[x][21];
    for(int i=0;i<x;i++)
    {
        for(int j=0;j<21;j++){
            f>>array[i][j];
            
            cout<<array[i][j]<<" ";
            
        }
        cout<<endl;
    }
    cout<<endl;
    cout<<"Number of steps: "<<endl;
    for(int i=0;i<x;i++)
    {    
        for(int j=0;j<20;j++){
            for(int k=j+1; k<21; k++)
            {
                if(array[i][k]<array[i][j]){
                    temp=array[i][j];
                    array[i][j]=array[i][k];
                    array[i][k]=temp;
                    count++;
                }
                
            }
            
        }
        cout<<i+1<<" : ";
        cout<<abs(count-count1)<<endl;
        count1=count;
    }
    cout << "Sorted" << endl;
    cout << x << endl;
    for(int i=0;i<x;i++)
    {
        for(int j=0;j<21;j++)
            
            cout<<array[i][j]<<" ";
            
        
        cout<<endl;
    }
    
}
