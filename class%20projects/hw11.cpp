/*
#include <iostream>
using namespace std;
int main( )
{int b[3]={1,2,3};
cout<<sizeof(b)<<endl;
cout<<sizeof(b+0)<<endl;
cout<<sizeof(*(b+0))<<endl;
// the next line prints 0012FF68
cout<<"The address of b is: "<<b<<endl;
cout<<"The address of b+1 is: "<<b+1<<endl;
cout<<"The address of &b is: "<<&b<<endl;
;
cout<<"The address of &b+1 is: "<<&b+1<<endl<<endl;
return 0;
}
*/
#include <iostream>
using namespace std;
int main(){
	int i =0;
	while(i<10) ++i;
	cout << i;
	return 0;
}
