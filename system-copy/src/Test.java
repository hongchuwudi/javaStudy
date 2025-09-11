public class Test {
    public static void main(String[] args) {
        // copyarray
        int[] urlArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] goalNums = new int[10];
        //  参数格式:
        //  System.arraycopy(urlArr,urlBeginIdx,goalNums,goalBeginIdx,size)

        //  教学:
//        System.arraycopy(urlArr,0,goalNums,0,10);
//        for(int i : goalNums) System.out.print(i + " ");

        //  练习:SyStem.arraycopy
        //  0 0 0 1 2 3 0 0 0 0
        System.arraycopy(urlArr,1,goalNums,3, Math.min(10, (goalNums.length - 3)));
        for(int i : goalNums) System.out.print(i + " ");

        
    }
}
