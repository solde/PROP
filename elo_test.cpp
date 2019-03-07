#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

struct player{
    int id;
    int w, l;
    double elo;
    double elo_sum;
};

int main(){
    srand( time(NULL) );

    player p1;
    p1.id = 1;
    p1.w = 0;
    p1.l = 0;
    p1.elo = 10000.0;
    p1.elo_sum = 0;

    player p2;
    p2.id = 1;
    p2.w = 0;
    p2.l = 0;
    p2.elo = 10.0;
    p2.elo_sum = 0;

    int n;
    while(cin >> n){
        for(int i = 0; i < n; ++i){
            int r = rand();
            p1.elo_sum += p2.elo;
            p2.elo_sum += p1.elo;
            if (r % 2 == 0) {
                cout << "p1" << endl;
                p2.w++;
                p1.l++;    
            }
            else{
                cout << "p2" << endl;
                p2.l++;
                p1.w++;
            }
            p1.elo = (p1.elo_sum + 400*(p1.w - p1.l))/(p1.w+p1.l);
            p2.elo = (p2.elo_sum + 400*(p2.w - p2.l))/(p2.w+p2.l);
        }  
        cout << "p1: " << p1.elo << endl << "p2: " << p2.elo << endl;
    }
}