import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static ArrayList<Integer> partyList[];
    static ArrayList<Integer> peopleList[];
    static ArrayList<Integer> isKnow;
    static boolean isVisited[];

    public static void main(String[] args) throws Exception {
        Main app = new Main();

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int people = Integer.parseInt(st.nextToken());
        int party = Integer.parseInt(st.nextToken());

        partyList = new ArrayList[party]; //n번째 파티에 방문한 사람 리스트
        peopleList = new ArrayList[people]; //n번째 사람이 방문한 파티 리스트
        isKnow = new ArrayList<>();
        isVisited = new boolean[party + 1];

        for(int i=0; i<party; i++) {
            partyList[i] = new ArrayList<>();
        }
        for(int i=0; i<people; i++) {
            peopleList[i] = new ArrayList<>();
        }


        st = new StringTokenizer(reader.readLine());
        int num = Integer.parseInt(st.nextToken());
        for (int i = 0; i < num; i++) {
            int temp = Integer.parseInt(st.nextToken()) - 1;
            isKnow.add(temp);
        }

        for (int i = 0; i < party; i++) {
            st = new StringTokenizer(reader.readLine());
            int sum = Integer.parseInt(st.nextToken());

            for (int j = 0; j < sum; j++) {
                int know = Integer.parseInt(st.nextToken()) - 1;
                partyList[i].add(know);
                peopleList[know].add(i);
            }
        }

        writer.write(String.valueOf(app.findMaximum()));
        writer.flush();
        writer.close();
    }

    public int findMaximum() {
        Queue<Integer> availableParty = new LinkedList<>();
        int answer = 0;

        for(int i=0; i<isKnow.size(); i++) {
            //진실을 아는 i번째 사람이 참석한 파티를 queue에 추가
            int size = peopleList[isKnow.get(i)].size();
            for(int j = 0; j<size; j++) {
                int partyIndex = peopleList[isKnow.get(i)].get(j);
                if(!isVisited[partyIndex]) {
                    availableParty.add(partyIndex);
                    answer++;
                }
                isVisited[partyIndex] = true;
            }
        }

        while(!availableParty.isEmpty()) {
            int index = availableParty.poll();

            for(int i=0; i<partyList[index].size(); i++) {
                //queue에 들어간 파티들에 대하여 파티에 참석한 사람들 모두 조회

                int size = peopleList[partyList[index].get(i)].size();
                for(int j = 0; j<size; j++) {
                    int partyIndex = peopleList[partyList[index].get(i)].get(j);
                    if(!isVisited[partyIndex]) {
                        availableParty.add(partyIndex);
                        answer++;
                    }
                    isVisited[partyIndex] = true;
                }
            }
        }

        return partyList.length - answer;

    }

}