
// Rat class i.e. a class for rational numbers
#include <iostream>
using namespace std;
class Rat{
private:
 int n;
 int d;
public:
 // constructors

 // default constructor
 Rat(){
 n=0;
 d=1;
 }

 // 2 parameter constructor
 Rat(int i, int j){
 n=i;
 d=j;
 }
 // conversion constructor
 Rat(int i){
 n=i;
 d=1;
 }

 //accessor functions (usually called get() and set(...) )
 int getN(){ return n;}
 int getD(){ return d;}
 void setN(int i){ n=i;}
 void setD(int i){ d=i;}

 //arithmetic operators

 Rat operator+(Rat r){
 	Rat t;
 	t.n=n*r.d+d*r.n;
 	t.d=d*r.d;
 	return t;
 }

 Rat operator-(Rat r){
 	Rat t;
 	t.n=n*r.d-d*r.n;
 	t.d=d*r.d;
 	return t;
 }
 Rat operator* (Rat r){
 	Rat t;
 	t.n=n*r.n;
 	t.d=d*r.d;
 	return t;
 }
 
  Rat operator/ (Rat r){
 	Rat t;
 	t.n=n*r.d;
 	t.d=d*r.n;
 	return t;
 }
 // 2 overloaded i/o operators
 friend ostream& operator<<(ostream& os, Rat r);
 friend istream& operator>>(istream& is, Rat& r);
}; //end Rat
// operator<<() is NOT a member function but since it was declared a friend of Rat
// it has access to its private parts.
ostream& operator<<(ostream& os, Rat r){
	for(int i=r.n; i>0; i--){
		if(r.n%i==0&& r.d%i==0){
			r.n=r.n/i;
			r.d=r.d/i;
		
		}
	}
	if(r.n>r.d){
		os <<r.n/r.d << "+"<< r.n-((r.n/r.d)*r.d) << "/" << r.d<< endl;
		
	}
	else
 	os<<r.n<<" / "<<r.d<<endl;
 	return os;
	 }
// operator>>() is NOT a member function but since it was declared a friend of Rat
// it has access to its provate parts.
istream& operator>>(istream& is, Rat& r){
 is>>r.n>>r.d;
 return is;
}
double cf1(int a[], int i){
    if (i==4) return a[i];
    return a[i] + 1/cf1(a, i+1);
}
int* cf2(int a[], int i) {
    static int v[2]={0,0};
    
    int temp;
    
    if(i==2){//base case
        v[1]=1;
        v[0]=a[2];
        return v;
    }
    cf2(a, i+1);
    temp=v[0];
    v[0]=a[i]*temp+v[1];
    v[1]=temp;
    return v;
    
}

Rat cf3(int a[], int i) {
    int *b = cf2(a,i);
    Rat r(b[0],b[1]);
   return r;
}
int main(){
    int array[] = {5, 8, 12, 3};
    cout << "Continued fractions function 1: " <<
    cf1(array, 0) << endl;
    
    int *a = cf2(array,0);
    cout << "Continued fractions function 2: " <<
    a[0] << '/' << a[1] << endl;
    
    
    cout << "Continued fractions function 3: " <<
    cf3(array, 0) << endl;;
 system("pause");
 return 0;
}
