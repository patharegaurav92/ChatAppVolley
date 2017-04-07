package edu.student.android.chatappvolley;

/**
 * Created by Gaurav on 02-04-2017.
 */

import android.location.Location;

import java.util.Comparator;
import java.util.PriorityQueue;

import static android.R.attr.x;

public class kClosestLocations {
    Point res[];
    public kClosestLocations() {
        Point[] a=new Point[5];
        a[0]=new Point(33.787362, -118.114346); //Walter Pyramid asdsada
        a[1]=new Point(33.782361, -118.153457); //3302 E Anaheim St asdsa
        a[2]=new Point(33.862242, -118.094805); //239 Los Cerritos Center
        a[3]=new Point(33.803556, -118.073133); //10876 Chestnut St  asdsa
        a[4]=new Point(33.802869, -117.993353);

        res = Solution(a,new Point(33.777226, -118.114590),3); // university library
        for(Point c:res){
            System.out.println(c.x+" "+c.y);
        }
    }


    public static Point[] Solution(Point[] array, final Point curr, int k) {
        Point[] result = new Point[k];
        PriorityQueue<Point> pq = new PriorityQueue<Point>(k+1,new Comparator<Point>(){
            @Override
            public int compare(Point a, Point b) {
                // TODO Auto-generated method stub

                if(getDistance(b,curr)>getDistance(a,curr)){

                    //System.out.println("b: "+b.x+","+b.y+" a: "+a.x+","+a.y + " return 1");
                    return 1;
                }else if(getDistance(b,curr)==getDistance(a,curr)){

                    //System.out.println("b: "+b.x+","+b.y+" a: "+a.x+","+a.y + " return 0");
                    return 0;
                }
                else {
                    //System.out.println("b: "+b.x+","+b.y+" a: "+a.x+","+a.y + " return -1");
                    return -1;
                }
            }
        });
        for(int i=0;i<array.length;i++){

            pq.offer(array[i]);

            if(pq.size()>k)
                pq.poll();

        }
        int ind=0;
        while(!pq.isEmpty())
            result[ind++]=pq.poll();
        return result;
    }

    public static Float getDistance(Point a,Point b){
        // System.out.println("a:"+a.x+","+a.y+"\nb:"+b.x+","+b.y);
        //System.out.println("Distance: "+Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y)));
        double lat1 = a.x;
        double long1 = a.y;
        double lat2 = b.x;
        double long2 = b.y;

        Location l1 = new Location("Point a");
        Location l2 = new Location("Point b");

        l1.setLatitude(lat1);
        l1.setLongitude(long1);
        l2.setLatitude(lat2);
        l2.setLongitude(long2);

        Float distance = l1.distanceTo(l2);
        System.out.println("a.x = "+lat1+" a.y = "+long1+" b.x= "+lat2+" b.y= "+long2+" Distance= "+distance);

        return distance;
    }

}
class Point {
    double x; //latitude
    double y; //longitude
    public Point(double a, double b) {
        x = a;
        y = b;
    }
}