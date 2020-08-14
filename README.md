# Algorithm

#### `[ Table of Contents ]`

### 1. RECURSIVE FUNCTION

### 2. DIVIDE AND CONQUER

### 3. GRAPH
* [그래프](#그래프)
* [그래프 모델링](#그래프-모델링)
* [BFS를 통한 그래프의 경로 탐색](#BFS를-통한-그래프의-경로-탐색)
* [DFS를 통한 그래프의 복잡한 경로 탐색](#DFS를-통한-그래프의-경로-탐색)
* [그래프의 연결성](#그래프의-연결성)
* [최소 신장 트리 알고리즘](#최소-신장-트리-알고리즘)
* [최단거리 알고리즘](#최단거리-알고리즘)

### 4. DYNAMIC PROGRAMMING

***
***

### 그래프
  1. 대상들과 대상들 간의 1:1 관계를 도형을 나타낸 것

  2. 대상은 Vertex, 관계는 Edge

  3. Tree : 싸이클이 존재하지 않는 연결 그래프 (Vertex - 1 = Edge)

  4. 그래프의 탐색
      * DFS
      * BFS
        <details><summary>코드 보기</summary>

        ``` Java
        //DFS
        while (!dfsList.isEmpty()) {
            Graph current = dfsList.pop();

            if (isVisited[current.nodeIndex]) continue;
            
            visitedNode.add(current.nodeIndex);
            isVisited[current.nodeIndex] = true;

            for (int i = N; i > 0; i--) {
            if (isVisited[i] == false && (adj[i][current.nodeIndex] || adj[current.nodeIndex][i])) {
                Graph nextNode = new Graph(i, current.nodeDepth+1);
                dfsList.add(nextNode);
            }
            }
        }

        //BFS
        while (!bfsList.isEmpty()) {
            Graph current = bfsList.poll();

            if (isVisited[current.nodeIndex]) continue;
            
            visitedNode.add(current.nodeIndex);
            isVisited[current.nodeIndex] = true;

            for (int i = 1; i < N + 1; i++) {
                if (isVisited[i] == false && (adj[current.nodeIndex][i] || adj[i][current.nodeIndex])) {
                Graph nextNode = new Graph(i, current.nodeDepth + 1);
                bfsList.add(nextNode);
                }
            }
            }
        ```

        </details>


  5. 그래프의 종류
      * 무방향 그래프 vs 방향 그래프

      * 가중치 그래프
      * 연결 그래프 vs 비연결 그래프
      * 사이클 vs 비순환 그래프
      * 완전 그래프
  
  6. 그래프의 구현
      * 인접 리스트
      * 인접 행렬

        ```Markdown
        * 인접 행렬에 가중치(weight)를 추가할 경우 간선이 존재하는 행렬에 1 대신 가중치 값을 설정한다.
        * 그래프 내에 간선의 수가 적은 희소 그래프는 인접 리스트를, 간선이 많이 존재하는 밀집 그래프는 인접 행렬을 사용한다.
        ```

        <details><summary>코드 보기</summary>

        ``` Java
        //Adjacency List
        class Graph { 
            public Node[] nodes; 
        }

        class Node {
            public String name;
            public Node[] children;
        }

        //Adjacency Matrix
        if(간선 (i, j)가 그래프에 존재)
            matrix[i][j] = 1;
        else
            matrix[i][j] = 0;
        ```

        </details>
    

***

### 그래프 모델링
그래프 상에서 정점과 간선의 특징에 따라 원하는 결과를 계산하는 알고리즘이 존재한다.
  1. [BFS를 통한 그래프의 경로 탐색](#BFS를-통한-그래프의-경로-탐색)

  2. [DFS를 통한 그래프의 복잡한 경로 탐색](#DFS를-통한-그래프의-경로-탐색)

  3. [그래프의 연결성](#그래프의-연결성)
  4. [최소 신장 트리 알고리즘](#최소=신장=알고리즘)
  5. [최단거리 알고리즘](#최단거리-알고리즘)
  6. 최대 유량
  7. 강한 연결 요소

***


### BFS를 통한 그래프의 경로 탐색
``` Markdown
출발 노드로부터 가까운 노드들부터 차례로 탐색하는 방식으로 
    * 같은 탐색 깊이를 가진 모든 노드들을 조회
    * 최대한 낮은 깊이의 탐색을 해야 할 때 유리 (최단 경로)
    * Queue 를 사용하여 구현
```
> ///

1. 

2. 

***

### DFS를 통한 그래프의 경로 탐색
``` Markdown
하나의 경로로부터 도달할 수 있는 모든 모드를 방문한 이후 다른 경로를 조회하는 방식으로 
    * 한 노드로부터 발생하는 모든 경로를 탐색할 때까지 해당 노드 정보를 유지
    * 한 번에 하나의 경로만을 조회 (각 경로의 고유 정보를 저장하기 유리)
    * Stack 혹은 재귀함수로 구현
    * 탐색의 우선순위를 제어하기 쉽다
```

``` Markdown
탐색의 우선순위 (DFS Ordering)
    * In-order
    * Pre-order
    * Post-order
```

``` Markdown
DFS Tree
    * Connected Tree 를 DFS로 탐색하는 과정에서 각 정점에 다양한 기준으로 번호를 부여한 후 Tree 모양으로 전개
    * 그래프의 다양한 특징을 쉽게 판별 가능
```
<br>

> Sudoku

1. `DFS기법으로 경우의 수를 탐색`하여 스도쿠 판을 채우는 함수로, 채울 방법이 존재한다면 해당 스도쿠 판을 2차원 배열 형태로 반환한다.

2. 
<br>

> N Queen

1. 

2. 
<br>

> 해밀턴 경로(Hamilton Path)

1. 그래프의 `모든 정점을 한 번씩`만 지나는 경로

2. 사용하는 변수
    ``` Java
    int depth;              //현재 탐색의 깊이
    int currentNode;        //현재 탐색중인 노드 인덱스
    boolean[] isVisited;    //방문 여부 배열
    Graph graph;            //그래프 정보
    ```

3. 알고리즘의 순서
    ``` Markdown
    DFS수행할 재귀함수 설계

    1. 임의의 그래프 내의 currentNode 정점을 정한다.
    2. 시작점 currentNode에 대해 방문하지 않은 간선이 있다면 그 간선을 따라 nextNode로 이동
    3. 이동하여 도착한 정점 nextNode에 대해서 또 다시 2번과 같이 방문하지 않은 간선이 있다면 따라서 이동
        + 이미 방문한 노드라면 해밀턴 경로가 아니므로 종료
        + 총 depth개의 정점을 방문했다면 해밀턴 경로를 찾았으므로 종료
    ```

    <details><summary>코드 보기</summary>

    ``` Java
        public boolean hasHamiltonPath(Graph graph, boolean[] isVisited, int depth, int current) {
        int node = graph.getNode();
        if (isVisited[current]) {
            return false;
        } else if (depth == node) {
            return true;
        }

        boolean hasPath = false;
        
        isVisited[current] = true;
        for (int i = 1; i <= node; i++) {
            if (i != current && graph.hasEdge(current, i) && !isVisited[i]) {
                hasPath = hasHamiltonPath(graph, isVisited, depth + 1, i);
                if (hasPath) {
                    break;
                }
            }
        }
        isVisited[current] = false;
        return hasPath;
    }
    ```

    </details>
<br>


> 해밀턴 회로(Hamilton Cycle)

1. 그래프의 모든 정점을 한 번씩만 지난 후, 첫 정점으로 돌아올 수 있는 순환 경로

2. `해밀턴 경로의 마지막 정점이 첫 출발 정점으로 향하는 간선이 존재`하면 된다.

3. 알고리즘
    ```Java
    if (depth == node && graph.hasEdge(current, origin)) {
        return true;
    }
    ```

    <details><summary>연관 문제</summary>

    [외판원 순회 문제](https://github.com/minjiHan95/MINJI_ALGORITHM/blob/master/7.%20Graph%20(BFS%2C%20DFS)/9C.java "백준2098")

    </details>

> 오일러 경로(Eulerian Path)

1. 그래프의 모든 간선을 오직 한 번씩만 지나는 경로

2. 무방향 그래프라면, `연결 그래프` 이면서 `차수`(Degree : 인접한 간선의 수)`가 홀수인 정점의 수가 0개 혹은 2개` 일 때 오일러 경로가 존재한다.

3. [연결 그래프의 성질](#그래프의-연결성)
    ```Markdown
    그래프 G = (V, E)는 연결 그래프이다.
        * 임의의 두 노드 u, v 에 대해 항상 경로가 존재한다.
        * 아무 노드 x에 대해 모든 노드에 대한 경로가 존재한다.
        * 아무 노드 x에 대해 DFS 혹은 BFS로 연결 노드를 찾으면 모든 정점이 나타나야 한다.
    ```
4. 알고리즘의 순서
    ```Markdown
    1. 해당 그래프가 연결 그래프인 경우
    2. 모든 노드에 대하여 차수가 홀수인 정점의 수 계산
    3. 홀수 차수 == 0 혹은 ==2 라면 오일러 경로 존재
    ```
    <details><summary>코드 보기</summary>

    ```Java
    public boolean hasEulerPath(Graph graph) {
        if (isConnected(graph)) return false;

        int count = 0;
        for (int i = 0; i < graph.node; i++) {
            if (graph.degree[i] % 2 != 0) {
                count++;
            }
        }

        if (count == 2 || count == 0) return true;
        else return false;
    }
    ```
    </details>
<br>


> 오일러 회로(Eulerian Circuit)

1. 그래프에 존재하는 모든 간선을 한 번씩만 지난 후, 처음 정점으로 돌아올 수 있는 경로

2. 무방향 그래프라면, `연결 그래프` 이면서 그래프의 모든 정점에 연결된 `간선의 개수가 짝수`일 때만 오일러 회로가 존재한다.

3. 알고리즘의 순서
    ```Markdown
    1. 해당 그래프가 연결 그래프인 경우
    2. 모든 노드에 대하여 차수가 홀수인 정점의 수 계산
    3. 홀수 차수 == 0 이면 오일러 회로 존재
    ```
   <details><summary>코드 보기</summary>

    ```Java
    public boolean hasEulerCircuit(Graph graph) {
        if (isConnected(graph)) return false;

        for (int i = 0; i < graph.node; i++) {
            if (graph.degree[i] % 2 != 0) {
                return false;
            }
        }

        return true;
    }
    ```
    </details>
<br>

***

### 그래프의 연결성


> 그래프의 연결성

1. 그래프상에 존재하는 두 노드 u, v에 대해 다음과 같은 관계를 정의할 수 있다.

    * 두 정점을 직접 이어주는 간선이 존재한다면, 두 노드는 `인접하다`고 한다.
    * 두 정점 사이에 한 개 이상의 간선을 거쳐 도달할 수 있는 경로가 존재한다면 두 노드는 `연결되어 있다`고 한다.

2. 연결 성분(Connected Component)

    * 그래프는 여러 개의 `고립된 부분 그래프`(Isolated Subgraphs)로 구성될 수 있다.
    * 서로 연결되어 있는 여러 개의 고립된 부분 그래프 각각을 `연결 성분` 이라고 한다.

3. 사용하는 변수
    ``` Java
    int count;                //연결 성분의 수
    int currentNode;          //현재 탐색중인 노드 인덱스 
    boolean[] isVisited;      //방문 여부 배열
    ```

4. 알고리즘의 순서
    ```Markdown
    1. BFS, DFS를 시작하면 시작 정점으로부터 도달 가능한 모든 정점들이 하나의 연결 성분이 된다.
    2. 다음에 방문하지 않은 정점을 선택해서 다시 탐색을 시작하면 그 정점을 포함하는 연결 성분이 구해진다.
    3. 이 과정을 그래프 상의 모든 정점이 방문될 때까지 반복하면 그래프에 존재하는 모든 연결 성분들을 찾을 수 있다.
    ```

   <details><summary>코드 보기</summary>

    ```Java
    {
        ...

        ArrayList<Integer>[] adjList;
        boolean[] isVisited = new boolean[node + 1];
        int count = 0;
        for (int i = 0; i < node; i++) {
            if (!isVisited[i]) { 
                isConnected(adjList, i, isVisited); 
                count++;
            }
        }
        ...
    }
    public void isConnected(ArrayList<Integer>[] adjList, int currentNode, boolean[] isVisited) {
        if (isVisited[currentNode]) {
            return;
        }
        
        isVisited[currentNode] = true;
        for (int i : adjList[currentNode]) {
            if (!isVisited[i]) {
                isConnected(adjList, i, isVisited);
            }
        }
    }


    ```
    </details>



> Disjoint Set

1. 여러 원소로 구성된 `집합들`을 관리하기 위한 자료구조

2. Union(합집합) 연산과 Find(소속 검사) 연산을 제공한다.

> Union & Find

1. 

2. 


***

### 최소 신장 트리 알고리즘

> Spanning Tree

1. 그래프 G(V, E)에서 일부 간선만을 선택해 만드는 Connected Tree

2. 정점들간의 Connectivity를 보장해줄 수 있는 최소한의 간선 집합 

> Minimum Spanning Tree

1. 그래프 G(V, E)에 존재하는 Spanning Tree들 중에서 `가중치의 합이 최소`가 되는 트리 

2. `최소의 비용`으로 그래프의 `Connectivity를 보장`해줄 수 있는 부분 그래프

3. 탐색 기법으로 모든 신장 트리를 탐색하는 것은 상당히 비효율적이다.

> Kruskal Algorithm

1. 

2. 

3. 알고리즘의 순서
    ``` Markdown
    1. 모든 간선을 가중치에 대해 오름차순으로 정렬한다.
        * 빠른 정렬 알고리즘 사용
    2. 간선이 없는 그래프 G를 선언한다.
    3. 모든 간선 e를 오름차순으로 순서대로 조회한다.
        if) 간선 e의 두 정점 u, v에 대해 그래프 G에서 u와 v가 연결성이 없다면, 그래프 G에 간선 e를 추가
        * 연결성 검사와 갱신 : Disjoint Set구조 사용
    4. 위 과정을 통해 G는 간선을 (V-1)개 포함한 최소 신장 트리가 된다.
    ```

> Prim Algorithm

1. 

2. 

3. 알고리즘의 순서
    ``` Markdown
    1. 그래프에서 하나의 꼭짓점을 선택하여 트리를 만든다.
    2. 모든 꼭짓점이 트리에 포함되어 있지 않은 동안,
        * 트리와 연결된 변 가운데 트리 속의 두 꼭짓점을 연결하지 않는 가장 가중치가 작은 변을 트리에 추가한다.
    4. 알고리즘이 종료됐을 때 만들어진 트리는 최소 비용 신장트리가 된다.
    ```

***

### 최단거리 알고리즘

> Shortest Path in Weighted Graph
1. `가중치 그래프`에서 최단 경로 : 정점 u에서 정점 v 로 가는 단순 경로들 중 `가중치의 합이 최소`가 되는 간선 집합

2. 비가중치 그래프와의 차이
    ```Markdown
    1. 일반적인 BFS는 depth(거쳐온 간선의 수)가 작은 순서대로 정점을 방문하기 때문에 모든 가중치가 1인 비가중치 그래프에서는 항상 가까운 정점부터 방문함을 보장할 수 있다.
    2. 하지만 가중치 그래프에서는 depth와 가중치의 합이 비례함을 보장할 수 없다.
    ```

> Dijkstra Algorithm

  1. 그래프의 `출발점에서 목표점까지의 최단 거리`를 구할 때 사용하는 알고리즘

  2. 사용하는 변수
      ``` Java
      int[] distance = new int[n+1];

      boolean[][] isVisited = new boolean[n+1][n+1];
      ```

  3. 알고리즘의 순서

> Bellman Ford Algorithm

1. 

2. 
      

***

<details><summary>출처</summary>

https://edu.goorm.io/learn/lecture/554/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%AC%B8%EC%A0%9C%ED%95%B4%EA%B2%B0%EA%B8%B0%EB%B2%95-%EC%9E%85%EB%AC%B8

https://hsp1116.tistory.com/42

https://gmlwjd9405.github.io/2018/08/16/algorithm-connected-component.html

</details>

