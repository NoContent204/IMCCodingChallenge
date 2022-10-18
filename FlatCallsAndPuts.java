package Round1;

import java.util.*;

public class FlatCallsAndPuts {

    public static void main(String[] args) {

        Integer[] a = {213, 313, 666, 896, 617, 430, 686, 135, 165, 148};

        ArrayList<Integer> callvolume = new ArrayList<Integer>(Arrays.asList(a));

        Integer[] b = {549, 170, 748, 1, 341, 270, 148, 672, 882, 427 };

        ArrayList<Integer> callprice = new ArrayList<Integer>(Arrays.asList(b));

        Integer[] c = {388, 282, 539, 769, 6, 860, 733, 964, 825, 422 };

        ArrayList<Integer> putprice1 = new ArrayList<Integer>(Arrays.asList(c));

        
        Integer[] d = {352, 476, 287, 22, 288, 327, 942, 846, 564, 324 };
        ArrayList<Integer> putvolume1 = new ArrayList<Integer>(Arrays.asList(d));


        calculateProfit(callprice, callvolume, putprice1, putvolume1);
        
    }



    public static int[] getSumArray(ArrayList<String[]> subsets){
        int sum;
        int[] sumarray = new int[subsets.size()];
        for (int i=0;i<subsets.size();i++) {
            String[] subset = subsets.get(i);
            sum = 0;
            for (String pair : subset) {
                int commaindex = pair.indexOf(",");
                int value = Integer.parseInt(pair.substring(0, commaindex));
                sum+=value;

            }
            sumarray[i] = sum;
        }
        return sumarray;

    }

    public static  int calculateProfit(List<Integer> callStrikePrices, List<Integer> callVolumes, List<Integer> putStrikePrices, List<Integer> putVolumes) {
        if ((callStrikePrices.size()>10) || (callVolumes.size()>10 || (putStrikePrices.size()>10)) || (putVolumes.size()>10)){
            return 0;
        }else{
            ArrayList<String[]> callVolumeSubsets = getSubsets(callVolumes, callStrikePrices);
            
            ArrayList<String[]> putVolumeSubsets = getSubsets(putVolumes, putStrikePrices);
            

            int[] callVolumeSums = getSumArray(callVolumeSubsets);
            int[] putVolumeSums = getSumArray(putVolumeSubsets);


            ArrayList<ArrayList<String[]>> potenialSolutions = new ArrayList<ArrayList<String[]>>();
            
            
            for (int i=0;i<putVolumeSums.length;i++){
                for (int j=0;j<callVolumeSums.length;j++){
                    if (putVolumeSums[i]==callVolumeSums[j]){
                        ArrayList<String[]> tmp = new ArrayList<String[]>(); 
                        tmp.add(putVolumeSubsets.get(i));
                        tmp.add(callVolumeSubsets.get(j));
                        
                        potenialSolutions.add(tmp);
                       

                    }
                }
            }

            int[] profitarray = new int[potenialSolutions.size()];
            for (int i=0; i<potenialSolutions.size();i++){
                ArrayList<String[]> arrayList = potenialSolutions.get(i);
                int j=0;
                int profit = 0 ;
                for (String[] arrayList2 : arrayList) {
                    
                    for(String str:arrayList2){
                        
                        int commaindex = str.indexOf(",");
                        int volume = Integer.parseInt(str.substring(0, commaindex));
                        int price = Integer.parseInt(str.substring(commaindex+1));
                        int total = price*volume;
                       
                        if (j==0){
                            profit+=total;
                        }else{
                            profit-=total;
                        }

                        

                    }
                    j++;
                    
                    
                }
                profitarray[i]=profit;
            }

            int maxprofit=0;
            for (int i : profitarray) {
                if (i>maxprofit){
                    maxprofit=i;
                }
            }
            
            System.out.println(maxprofit);

            return maxprofit;


        }
        
    }


    public static ArrayList<String[]> getSubsets(List<Integer> volumearray, List<Integer> pricearray){
        ArrayList<String[]> subsets = new ArrayList<String[]>();
        
        for (int i = 0; i < volumearray.size(); i++){
            int subsetCount = subsets.size();
            subsets.add(new String[] { volumearray.get(i).toString()+","+pricearray.get(i).toString() });

            for (int j = 0; j < subsetCount; j++){
                String[] newSubset = Arrays.copyOf(subsets.get(j),subsets.get(j).length + 1);
                newSubset[newSubset.length - 1] = volumearray.get(i).toString()+","+pricearray.get(i).toString();
                subsets.add(newSubset);
            }
        }   
        return subsets;

    }
}
