/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays_2d;

/**
 *
 * @author vshanmugham
 */

// https://stackoverflow.com/questions/58470316/minimise-manhattan-distance-between-x-and-y-in-a-matrix

/*
int n = 4;
const int inf = 1234567890;
vector<string>M = {"x000","0y0y","xx00","0y00"};
vector<vector<int>>D(n, vector<int>(n,inf));
queue<array<int,2>>Q;

for(int i=0; i<n; i++)
for(int j=0; j<n; j++)
    if(M[i][j]=='x')
{
    Q.push({i,j});
    D[i][j]=0;
}

int res = inf;

while(!Q.empty())
{
    int row = Q.front()[0];
    int col = Q.front()[1];
    if(M[row][col]=='y')
    {
        res=D[row][col];
        break;
    }
    Q.pop();

    int dr[] = {-1,1,0,0};
    int dc[] = {0,0,-1,1};

    for(int k=0; k<4; k++)
    {
        int r = row + dr[k];
        int c = col + dc[k];
        if(min(r,c) >=0 && max(r,c) < n && D[r][c]==inf)
        {
            D[r][c]=D[row][col]+1;
            Q.push({r,c});
        }
    }
}

cout<<res;

*/


public class MinimumManhattenL1DistanceBetweenXAndY {
  
}
